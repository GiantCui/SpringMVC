package com.cui.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cui.UDP.UDPSend;
import com.cui.config.ipConfig;
import com.cui.pojo.IPC;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ipc")
public class ipcController {
    private ipConfig config;
    private List<IPC> IPCS_;
    private boolean update = false;

    @RequestMapping("/ipcCfg")
    public String ipcCfg(Model model) throws IOException {
        List<IPC> IPCS;
        if (!update){
            config = new ipConfig();
            // 读取cfg文件
            String ls = config.getProperties("ipcList");
            // 转换JSON数据
            JSONObject data = JSON.parseObject(ls);
            // 解析JSON字符串
            IPCS = JSON.parseArray(data.getJSONArray("ipc").toJSONString(), IPC.class);
        }
        else {
            IPCS = IPCS_;
            update = false;
        }
        System.out.println("ipcCfg ==> " + IPCS);
        model.addAttribute("ipcs", IPCS);
        model.addAttribute("ipcsJSON", JSON.toJSONString(IPCS));
        return "ipcJSP";
    }

    @RequestMapping("singlePing")
    @ResponseBody
    public String singlePing(String  ipAddress) throws IOException {
        int timeOut = 3000;
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        System.out.println(ipAddress + "连接状态" + status);
        if (status){
            return "成功";
        }
        else {
            return "失败";
        }
    }

    @RequestMapping("pingAll")
    @ResponseBody
    public String pingAll(String  ipcs) throws IOException {
        System.out.println("Pingall" + ipcs);
        List<IPC> list = JSON.parseArray(ipcs, IPC.class);
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int i=0; i<list.size(); i++) {
            String rsl;
            rsl = singlePing(list.get(i).getId());
            if (rsl.equals("失败")) {
                count.add(i);
            }
        }
        return JSON.toJSONString(count);
    }

    @RequestMapping("delIpc")
    @ResponseBody
    public String delIpc(String index) throws IOException {
        System.out.println("delIpc->" + index);
        dropOrAdd(Integer.parseInt(index));
        return "ipcCfg";
    }

    public <T> void dropOrAdd(T templete) throws IOException {
        config = new ipConfig();
        // 读取cfg文件
        String ls = config.getProperties("ipcList");
        // 转换JSON数据
        JSONObject data = JSON.parseObject(ls);
        // 解析JSON字符串
        List<IPC> IPCS = JSON.parseArray(data.getJSONArray("ipc").toJSONString(), IPC.class);
        System.out.println(IPCS);
        if (templete instanceof Integer){
            Integer integer = (Integer) templete;
            IPCS.remove(integer-1);
        }
        else if (templete instanceof IPC){
            int index = checkIPC(IPCS, (IPC) templete);
            if (index != 9999){
                IPCS.remove(index);
            }
            IPCS.add((IPC) templete);
        }
        data.put("ipc", IPCS);
        System.out.println("data ==> " + data);
        config.setProperties("ipcList", data.toJSONString());
        config.saveConfig("update");
        update = true;
        IPCS_ = IPCS;
    }

    @RequestMapping("/addIpx")
    @ResponseBody
    public String addIpc(@RequestBody IPC ipc) throws IOException {
        dropOrAdd(ipc);
        return "redirect:ipcCfg";
    }

    public int checkIPC(@NotNull List<IPC> IPCS, IPC ipc){
        int index=9999;
        for (int i=0; i < IPCS.size(); i++){
            boolean st = IPCS.get(i).getId().equals(ipc.getId());
            if(st){
                index =  i;
            }
        }
        return index;
    }

    @RequestMapping("/initRadar")
    @ResponseBody
    public String initRadar(String  ipcs) throws IOException {
        UDPSend udpSend = new UDPSend("192.168.137.1", 8888);
        udpSend.sendMessage("INIT");
        return "success";
    }
}

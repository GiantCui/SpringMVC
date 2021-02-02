package com.cui.controller;

import com.cui.pojo.Log;
import com.cui.pojo.Radar;
import com.cui.service.LogService;
import com.cui.service.LogServiceImpl;
import com.cui.service.RadarService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    @Qualifier("logServiceImpl")
    private LogServiceImpl logService;
    private int logSize;

    @RequestMapping("/allLog")
    public String allLog(Model model){
        /*
        List<Log> list = logService.queryAllLog();
        System.out.println("allRadar ==> " + list);
        model.addAttribute("list", list);
         */
        return "allLog";
    }

    @RequestMapping("/getPageNum")
    @ResponseBody
    public int getPageNum(int pageSize){
        int pageNum;
        logSize = logService.getSize();
        double resulte = logSize*1.0/pageSize;
        pageNum = (int) Math.ceil(resulte);
        System.out.println("共有"+pageNum+"页");
        return pageNum;
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public List<Log> getPage(int page, int pageSize){

        int start = (page-1) * pageSize;
        System.out.println("获取第"+start+"到第"+ pageSize +"条数据");
        return logService.getPage(start, pageSize);
    }

    @RequestMapping("/downloadLog")
    public void downloadLog(HttpServletResponse response) throws IOException{
        System.out.println("下载日志");
        Workbook wb = new HSSFWorkbook();
        String[] headers = {"日志编号", "雷达编号", "日志信息", "时间"};
        int rowIndex = 0;
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(rowIndex++);
        for (int i=0; i< headers.length; i++){
            row.createCell(i).setCellValue(headers[i]);
        }
        logSize = logService.getSize();
        int cur = 0;
        while (cur < logSize){
            List<Log> logs = logService.getPage(cur, 100000);
            cur+=100000;
            for (Log log : logs){
                row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(log.getLogNum());
                row.createCell(1).setCellValue(log.getRadarID());
                row.createCell(2).setCellValue(log.getLog());
                row.createCell(3).setCellValue(log.getTime());
            }
        }
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String("雷达日志.xls".getBytes("utf-8"), "iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}

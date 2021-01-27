let websocket = null;
const warn_num = [];
const warn_map = new Map();
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/websocket");
}
else {
    alert('当前浏览器 Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
    setMessageInnerHTML("WebSocket连接成功");
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
    let ob = document.getElementById("pangLu")
    let state = ob.dataset.state
    const str = event.data
    try {
        const object = JSON.parse(str);
        if (state === "false"){
            if (object.cmd.toUpperCase() === "STARTWORK") turnToPL();
            else console.log("系统旁路")
            return
        }
        console.log(warn_num.length)
        console.log("发来消息 ==> " + str)
        if (object.cmd.toUpperCase() === "FOREIGNMATTER_T"){
            if (warn_num.length === 0){
                // 显示异物位置
                console.log("异物位置：", object.position)
                setChart(object.serialnum, object.position)
            }
            else {
                if (warn_num.indexOf(object.serialnum) === -1){
                    warn_map.set(object.serialnum, object.position)
                }
            }
            /**
             * 响铃
             */
            ring_ring(object.serialnum)
        }
        else if (object.cmd.toUpperCase() === "FOREIGNMATTER_F"){
            if (object.serialnum === warn_num[0]){
                let option = myChart.getOption();
                let arr = option.series[0].data;
                arr.shift();
                myChart.setOption(option)
                if (warn_map.size > 0){
                    console.log(warn_map, warn_num[0])
                    setChart(warn_num[1], warn_map.get(warn_num[1]))
                    warn_map.delete(warn_num[1])
                }
            }
            else {
                warn_num.remove(object.serialnum)
                warn_map.delete(object.serialnum)
            }
            stop_ring(object.serialnum)
        }
        else if (object.cmd.toUpperCase() === "BYPASS"){
            turnToPL();
        }
        else if (object.cmd.toUpperCase() === "ALARM_STOP"){
            alarmElimination(object.serialnum)
        }
        websocket.send(str)
        console.log("收到")
    } catch (error){
        console.log("接受消息非JSON对象")
    }


    setMessageInnerHTML(event.data)
}

//连接关闭的回调方法
websocket.onclose = function () {
    setMessageInnerHTML("WebSocket连接关闭");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    let time = new Date().Format("yyyy-MM-dd hh:mm:ss")
    document.getElementById('message').innerHTML += time + "\n" + innerHTML + "\n";
}

//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send() {
    var message = document.getElementById('text').value;
    websocket.send(message);
}

function connectWebSocket(){
    websocket.connect();
}

function ring_ring(serialnum){
    if (warn_num.indexOf(serialnum) !== -1) console.log("已经收到警报")
    else warn_num.push(serialnum)
    const ring_img = document.getElementById('ring')
    const door_img = document.getElementById('door')
    const sound = document.getElementById("sound")

    ring_img.src = "/static/ring.gif"
    door_img.src = "/static/door_red.png"
    let radar_img = document.getElementById(serialnum+"ring")
    radar_img.src = "/static/警铃red.svg"
    radar_img = document.getElementById(serialnum+"door")
    radar_img.src = "/static/door_close_red.png"
    sound.play()
}

function stop_ring(serialnum){
    warn_num.remove(serialnum)
    console.log("warn_num" + warn_num)
    const ring_img = document.getElementById('ring')
    const door_img = document.getElementById('door')
    const sound = document.getElementById("sound")
    if (warn_num.length === 0){
        sound.pause()
        ring_img.src = "/static/警铃green.svg"
        door_img.src = "/static/door_green.png"
    }
    let radar_img = document.getElementById(serialnum+"ring")
    radar_img.src = "/static/警铃green.svg"
    radar_img = document.getElementById(serialnum+"door")
    radar_img.src = "/static/door_close_green.png"
}
function setChart(serialnum, pst){
    let doorSize_x, doorSize_y, scatter_x, scatter_y;
    $.ajax({    //按雷达编号获取雷达
        type: 'POST',
        url: '/radar/getRadar',
        data: {"sirialnum":serialnum},
        async: false,
        dataType:"text",
        success: function (data){
            console.log("getRadar"+data)
            let doorSize = data.split(',')
            doorSize_x = parseInt(doorSize[0]) //+ parseInt(doorSize[2])
            doorSize_y = parseInt(doorSize[1]) //+ parseInt(doorSize[2])
        }
    })
    /**
     * 显示异物位置
     */
    let position = pst.split(',')
    scatter_x = parseInt(position[0])
    scatter_y = parseInt(position[1])
    let color;
    if (scatter_x < 0 || scatter_x > doorSize_x || scatter_y < 0 || scatter_y > doorSize_y){
        color = 'yellow'
    }
    else color = 'red'
    myChart.setOption({
        xAxis:{min: 0, max: doorSize_x},
        yAxis:{min: 0, max: doorSize_y},
        series:{color:color,rippleEffect:{color: color}}
    })
    let option = myChart.getOption();
    let arr = option.series[0].data;
    arr.push([scatter_x, scatter_y]);
    myChart.setOption(option)
}
function clearChart(){
    let option = myChart.getOption();
    let arr = option.series[0].data;
    console.log("arr.length:  ", arr.length)
    for (let i=0; i<arr.length; i++){
        arr.shift()
    }
    myChart.setOption(option)
}
Array.prototype.indexOf = function(val) {
    for (let i = 0; i < this.length; i++) {
        if (this[i] === val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    const index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
Array.prototype.popAll = function() {
    for (let i = 0; i < this.length; i++) {
        this.pop()
    }
    return -1;
};
function alarmElimination(){
    warn_num.popAll()
    warn_map.clear();
    clearChart()
    let ob = document.getElementById("sound")
    ob.pause()
    for (let i=0; i < radarList.length; i++){
        let ob = document.getElementById(radarList[i] + "door")
        ob.src = "/static/door_close_green.png"
        ob = document.getElementById(radarList[i] + "ring")
        ob.src = "/static/警铃green.svg"
    }
    ob = document.getElementById("door")
    ob.src = "/static/door_green.png"
    ob = document.getElementById("ring")
    ob.src = "/static/警铃green.svg"
    websocket.send("alarmElimination")
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length===1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
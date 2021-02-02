
let websocket = null;
const warn_num = [];
const warn_map = new Map();
let doorSize = [];
let position_last = "";
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
    let date = new Date()
    console.log(date.Format("yyyy-MM-dd hh:mm:ss.S"),"发来消息 ==> " + str)
    try {
        const object = JSON.parse(str);
        if (state === "false"){
            if (object.cmd.toUpperCase() === "STARTWORK") turnToPL();
            else console.log("系统旁路")
            return
        }
        if (object.cmd.toUpperCase() === "FOREIGNMATTER_T"){
            if (warn_num.length === 0){     //若目前无雷达警报
                console.log("异物位置：", object.position)
                console.log("目前无雷达警报")
                doorSize = setChart(object.serialnum, object.position)
                position_last = object.position;
            }
            else {  //已经存在雷达警报
                if (warn_num.indexOf(object.serialnum) === -1){     //若报警雷达不在雷达警报队列
                    warn_map.set(object.serialnum, object.position)
                    console.log("报警雷达不在雷达警报队列")
                }
                else if (warn_num[0]===object.serialnum){      //若报警雷达目前正在响应
                    console.log("报警雷达目前正在响应")
                    if (position_last !== object.position){     //若雷达位置发生改变
                        updataChart(object.position, doorSize)
                        position_last = object.position
                        console.log("雷达位置发生改变")
                    }

                }
            }
            /**
             * 响铃
             */
            ring_ring(object.serialnum)
        }
        else if (object.cmd.toUpperCase() === "FOREIGNMATTER_F"){
            if (object.serialnum === warn_num[0]){  //若正在响应雷达警报消除
                let option = myChart.getOption();
                let arr = option.series[0].data;
                arr.shift();
                myChart.setOption(option)
                if (warn_map.size > 0){
                    console.log(warn_map, warn_num[0])
                    doorSize = setChart(warn_num[1], warn_map.get(warn_num[1]))
                    position_last = warn_map.get(warn_num[1])
                    warn_map.delete(warn_num[1])
                }
            }
            else {  //若雷达报警队列中的警报消除
                warn_num.remove(object.serialnum)
                warn_map.delete(object.serialnum)
            }
            stop_ring(object.serialnum)
        }
        else if (object.cmd.toUpperCase() === "BYPASS"){
            byPass();
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





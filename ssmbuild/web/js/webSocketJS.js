var websocket = null;
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
    const img = document.getElementById('ring')
    const sound = document.getElementById("sound")
    const str = event.data
    try {
        const object = JSON.parse(str);
        console.log(object)
    } catch (error){
        console.log("接受消息非JSON对象")
    }

    if (str === 'red'){
        console.log("发来消息 ==> " + str)
        img.src = "/static/ring.gif"
        sound.play()
    }
    else {
        console.log("发来消息 ==> " + str)
        sound.pause()
        img.src = "/static/警铃green.svg"
    }
    websocket.send(str)
    console.log("收到")
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
    document.getElementById('message').innerHTML += innerHTML + "\n";
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
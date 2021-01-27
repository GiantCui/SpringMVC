
function showWaiting(){
    var node=document.createElement("div");  //创建一个div元素节点，作为整个背景的容器
    var nodeClass=document.createAttribute("class"); //创建class元素属性
    var nodeStyle = document.createAttribute("style"); //创建style元素属性
    var nodeName = document.createAttribute("name"); //创建name元素属性
    nodeName.value = "divWaiting"; //给元素节点命名
    var nodeStyleVal = "position: fixed; z-index: 998; top: 0; right: 0; bottom: 0; left: 0; opacity: 1; background: rgba(0,0,0,0.2); vertical-align: middle; text-align: center;"
    nodeStyleVal += "height:"+window.innerHeight + "px; width:"+window.innerWidth+"px;";
    nodeStyle.value = nodeStyleVal;
    node.setAttributeNode(nodeClass); //设置元素节点的属性及值
    node.setAttributeNode(nodeStyle);
    node.setAttributeNode(nodeName);
    node.setAttribute("id", "divWaiting")
    var iconNode = document.createElement("div");  //创建一个div元素节点，作为旋转图标的容器
    var iconClass = document.createAttribute("class");
    iconClass.value = "fa fa-circle-o-notch fa-pulse"; //必须引用font-awesome
    var iconStyle = document.createAttribute("style");
    iconStyle.value = "position: relative;top: 45%;margin: 0 auto;font-size: 100px;";
    iconNode.setAttributeNode(iconStyle);
    iconNode.setAttributeNode(iconClass);

    node.appendChild(iconNode);  //将图标节点放到整个背景的元素节点
    document.body.appendChild(node); //将整个背景div插入到body中

    const text = document.createElement("h1");
    text.innerText = "加载中请稍后..."
    text.style.position = "relative";
    text.style.top = "45%"
    document.getElementById("divWaiting").appendChild(text);
    console.log(text.innerHTML)
}

function closeWaiting(){
    var wait = document.getElementsByName("divWaiting"); //获取name为divWaiting的元素节点
    //遍历所有的节点，将body中的所有等待旋转效果去掉
    for(var i=wait.length - 1; i>=0; i--){
        document.body.removeChild(wait[i]);
    }
}
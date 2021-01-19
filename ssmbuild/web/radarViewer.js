
colnum = 10
if ((colnum-1)%2 === 0) {
    colspan_1 = (colnum-1)/2
    colspan_2 = (colnum-1)/2
}
else {
    colspan_1 = (colnum-1)/2
    colspan_2 = (colnum-1)/2 + 1
}
// 显示图例
function showAnnotation(){
    console.log("显示注解");
    var str;
    str = "<tr>\n" +
            "<td rowspan=\"3\">\n" +
                "<img src=\"/static/警铃.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
                "<img src=\"/static/门禁-关.svg\" height=\"50px\" width=\"50px\" alt=\"屏蔽门\"><br>\n" +
                "<label>未使用</label><br>\n" +
                "<img src=\"/static/警铃green.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
                "<img src=\"/static/门禁-关green.svg\" height=\"50px\" width=\"50px\" alt=\"屏蔽门\"><br>\n" +
                "<label>正常状态</label><br>\n" +
                "<img src=\"/static/警铃red.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
                "<img src=\"/static/门禁-关red.svg\" height=\"50px\" width=\"50px\" alt=\"屏蔽门\"><br>\n" +
                "<label>报警异常</label><br>\n" +
                "<img src=\"/static/警铃yellow.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
                "<img src=\"/static/门禁-关yellow.svg\" height=\"50px\" width=\"50px\" alt=\"屏蔽门\"><br>\n" +
                "<label>旁路</label><br>\n" +
                "<img src=\"/static/警铃green.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
                "<img src=\"/static/门禁-开green.svg\" height=\"50px\" width=\"50px\" alt=\"屏蔽门\"><br>\n" +
                "<label>屏蔽门打开</label>\n" +
            "</td>\n" +
            "<td colspan=\""+ colspan_1 +"\" rowspan='3'>" +
                "<img src=\"/static/门禁-关.svg\" height=\"500px\" width=\"500px\" alt=\"屏蔽门\" id=\'door\'><br>\n" +
            "</td>\n" +
            "<td colspan=\""+ colspan_2 +"\" rowspan='3'>" +
                "<img src=\"/static/警铃green.svg\" height=\"500px\" width=\"500px\" alt=\"警铃\" id=\'ring\'><br>\n" +
            "</td>\n" +
        "</tr>"

    document.write(str);
}

// 遍历雷达列表进行监控
function showRadars(radar){
    let href;
// 每行显示雷达数
    let str = "<tr>";
    for (let i=0; i < radar.length; i++){
        href = "/radar/toUpdate?id=" + radar[i].radarID + "&href=" + window.location.pathname;
        str +=
            "<td>" +
            "   <a href=\'" + href + "\'>" +
            "   <img src=\'/static/警铃green.svg\' height=\'30px\' width=\'30px\' alt=\"警铃\"><br>" +
            "   <img src=\'/static/door_close_green.png\' height=\'75\' width=\'150\' alt=\"屏蔽门\"><br>" +
            "   <label >" + radar[i].sirialnum + "</label>" +
            "   </a>" +
            "</td>"
    }
    if (radar.length%colnum !== 0){
        var len = colnum - radar.length%colnum
        href = "/radar/toAddRadar?href=" + window.location.pathname;
        for (let i=0; i < len; i++){
            str +=
                "<td>" +
                "   <a href=\'" + href + "\'>" +
                "   <img src=\'/static/警铃.svg\' height=\'30px\' width=\'30px\' alt=\"警铃\"><br>" +
                "   <img src=\'/static/门禁-关.svg\' height=\'50px\' width=\'50px\' alt=\"屏蔽门\"><br>" +
                "   </a>" +
                "</td>"
        }

    }
    str += "</tr>"

    document.write(str)
}

function loadxmlDoc(file) {
    let xmlDoc;
    try {
        //IE
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
    } catch (e) {
        xmlDoc = document.implementation.createDocument("", "", null);
    }

    try {
        xmlDoc.async = false;
        xmlDoc.load(file);//chrome没有load方法
    } catch (e) {
        //针对Chrome,不过只能通过http访问,通过file协议访问会报错
        var xmlhttp = new window.XMLHttpRequest();
        xmlhttp.open("GET", file, false);
        xmlhttp.send(null);
        xmlDoc = xmlhttp.responseXML.documentElement;
    }
    return xmlDoc;
}

// 显示图例
function showAnnotation(){
    console.log("显示注解");
    var str;
    str = "<tr>\n" +
            "<td rowspan=\"3\">\n" +
                "<img src=\"/static/警铃.svg\" height=\"30px\" width=\"30px\"><br />\n" +
                "<img src=\"/static/门禁-关.svg\" height=\"50px\" width=\"50px\"><br>\n" +
                "<label>未使用</label><br>\n" +
                "<img src=\"/static/警铃green.svg\" height=\"30px\" width=\"30px\"><br />\n" +
                "<img src=\"/static/门禁-关green.svg\" height=\"50px\" width=\"50px\"><br>\n" +
                "<label>正常状态</label><br>\n" +
                "<img src=\"/static/警铃red.svg\" height=\"30px\" width=\"30px\"><br />\n" +
                "<img src=\"/static/门禁-关red.svg\" height=\"50px\" width=\"50px\"><br>\n" +
                "<label>报警异常</label><br>\n" +
                "<img src=\"/static/警铃yellow.svg\" height=\"30px\" width=\"30px\"><br />\n" +
                "<img src=\"/static/门禁-关yellow.svg\" height=\"50px\" width=\"50px\"><br>\n" +
                "<label>旁路</label><br>\n" +
                "<img src=\"/static/警铃green.svg\" height=\"30px\" width=\"30px\"><br />\n" +
                "<img src=\"/static/门禁-开green.svg\" height=\"50px\" width=\"50px\"><br>\n" +
                "<label>屏蔽门打开</label>\n" +
            "</td>\n" +
        "</tr>"
    return str;
}

// 遍历雷达列表进行监控
function showRadars(radar){
    // 每行显示雷达数
    var colnum = 15
    var str = "<tr>"
    for (let i=0; i < radar.length; i++){
        var href = "/radar/toUpdate?id=" + radar[i].radarID;
        str +=
            "<td>" +
            "   <a href=\'" + href + "\'>" +
            "   <img src=\'/static/警铃green.svg\' height=\'30px\' width=\'30px\'><br>" +
            "   <img src=\'/static/门禁-关green.svg\' height=\'50\' width=\'50\'><br>" +
            "   <label>" + radar[i].sirialnum + "</label>" +
            "   </a>" +
            "</td>"
    }
    if (radar.length%colnum !== 0){
        var len = colnum - radar.length%colnum
        for (let i=0; i < len; i++){
            str +=
                "<td>" +
                "   <img src=\'/static/警铃.svg\' height=\'30px\' width=\'30px\'><br>" +
                "   <img src=\'/static/门禁-关.svg\' height=\'50px\' width=\'50px\'><br>" +
                "</td>"
        }

    }
    str += "</tr>"

    document.write(str)
}
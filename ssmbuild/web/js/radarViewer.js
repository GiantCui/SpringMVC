
colnum = 8
if ((colnum-1)%2 === 0) {
    colspan_1 = (colnum)/2
    colspan_2 = (colnum)/2
}
else {
    colspan_1 = (colnum)/2
    colspan_2 = (colnum)/2 + 1
}

let radarList = []
// 显示图例
function showAnnotation(){
    console.log("显示注解");
    var str;
    str = "<tr>\n" +
        "<td rowspan=\"3\" >\n" +
        "<img src=\"/static/警铃green.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
        "<img src=\"/static/door_close_green.png\" height=\"75px\" width=\"100%\" alt=\"屏蔽门\"><br>\n" +
        "<label>正常状态</label><br>\n" +
        "<img src=\"/static/警铃red.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
        "<img src=\"/static/door_close_red.png\" height=\"75px\" width=\"100%\" alt=\"屏蔽门\"><br>\n" +
        "<label>报警异常</label><br>\n" +
        "<img src=\"/static/警铃yellow.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
        "<img src=\"/static/door_close_yellow.png\" height=\"75px\" width=\"100%\" alt=\"屏蔽门\"><br>\n" +
        "<label>旁路</label><br>\n" +
        "<img src=\"/static/警铃green.svg\" height=\"30px\" width=\"30px\" alt=\"警铃\"><br />\n" +
        "<img src=\"/static/door_open_green.png\" height=\"75px\" width=\"100%\" alt=\"屏蔽门\"><br>\n" +
        "<label>屏蔽门打开</label>\n" +
        "</td>\n" +
        "<td colspan=\""+ colspan_1 +"\" rowspan='0' >" +
        "<img src=\"/static/door_close_green.png\" height=\"500px\" width=\"100%\" alt=\"屏蔽门\" id=\'door\'><br>\n" +
        "</td>\n" +
        "<td colspan=\""+ colspan_2 +"\" rowspan='0' >" +
        "<img src=\"/static/警铃green.svg\" height=\"500px\" width=\"500px\" alt=\"警铃\" id=\'ring\'><br>\n" +
        "</td>\n" +
        "</tr>"

    document.write(str);
}

// 遍历雷达列表进行监控
function showRadars(radar){
    let href;
    // 每行显示雷达数
    let rowspan = Math.ceil(radar.length/colnum)
    let str = "<tr>";
    str += addBtn(str, rowspan)
    radarList = [];
    for (let i=0; i < radar.length; i++){
        href = "/radar/toUpdate?id=" + radar[i].radarID + "&href=" + window.location.pathname;
        radarList.push(radar[i].radarID)
        str +=
            "<td>" +
            "   <a data-toggle=\"modal\"  data-target=\"#myModal\" rel=\"noopener noreferrer\" title='配置雷达' data-id=\""+radar[i].radarID+"\">" +
            "   <img src=\'/static/警铃green.svg\' height=\'30px\' width=\'30px\' alt=\"警铃\" id=\""+radar[i].radarID+"ring"+"\"><br>" +
            "   <img src=\'/static/door_close_green.png\' height=\'75px\' width=\'150\' alt=\"屏蔽门\" id=\""+radar[i].radarID+"img"+"\"><br>" +
            "   <label >" + radar[i].sirialnum + "</label>" +
            "   </a>" +
            "</td>"

        if ((i+1)%colnum === 0) {
            str += "</tr><tr>"
        }
    }
    if (radar.length%colnum !== 0 || radar.length === 0){
        console.log("radar.length" + radar.length)
        var len = colnum - radar.length%colnum
        // href = "/radar/toAddRadar?href=" + window.location.pathname;
        for (let i=0; i < len; i++){
            str +=
                "<td>" + "</td>"
        }
    }
    str += "</tr>"

    document.write(str)
}

function turnToPL(){
    let ob = document.getElementById("pangLu")
    let state = ob.dataset.state
    if (state === "true"){
        ob.value = "正常"
        ob.dataset.state = "false"
        ob.setAttribute("class", "btn btn-success navbar-btn")
        for (let i=0; i < radarList.length; i++){
            let ob = document.getElementById(radarList[i] + "img")
            ob.src = "/static/door_close_yellow.png"
            ob = document.getElementById(radarList[i] + "ring")
            ob.src = "/static/警铃yellow.svg"
        }

    }
    else {
        let ob = document.getElementById("pangLu")
        ob.value = "旁路"
        ob.dataset.state = "true"
        ob.setAttribute("class", "btn btn-warning navbar-btn")
        for (let i=0; i < radarList.length; i++){
            let ob = document.getElementById(radarList[i] + "img")
            ob.src = "/static/door_close_green.png"
            ob = document.getElementById(radarList[i] + "ring")
            ob.src = "/static/警铃green.svg"
        }
    }
}


function addBtn(str, span){
    str += "<td rowspan=\""+span+"\">" +
        "<h3 style=\'vertical-align: middle\'>控制按钮</h3><br>" +
        "<input type=\"button\" class=\"btn btn-warning navbar-btn\" id=\'pangLu\' value=\'旁路\' style=\"font-size:20px\" onclick=\'turnToPL()\' data-state=\'true\'><br>" +
        "<input type=\"button\" class=\"btn btn-danger navbar-btn\" data-toggle=\"modal\" data-target=\"#myModal\" id=\'xuJing\' value=\'虚警消除\' style=\"font-size:20px\">" +
        "</td>"
    return str
}

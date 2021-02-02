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
    scatter_x = parseFloat(position[0])/10
    scatter_y = parseFloat(position[1])/10
    let date = new Date()
    console.log(date.Format("yyyy-MM-dd hh:mm:ss.S"), "异物位置:", scatter_x, ",", scatter_y)
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
    return [doorSize_x, doorSize_y]
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

function updataChart(pst, doorSize){
    let position = pst.split(',')
    let scatter_x = parseFloat(position[0])/10
    let scatter_y = parseFloat(position[1])/10
    let color;
    if (scatter_x < 0 || scatter_x > doorSize[0] || scatter_y < 0 || scatter_y > doorSize[1]){
        color = 'yellow'
    }
    else color = 'red'
    myChart.setOption({
        series:{color:color,rippleEffect:{color: color}}
    })
    let option = myChart.getOption();
    let arr = option.series[0].data;
    arr.shift()
    arr.push([scatter_x,scatter_y])
    myChart.setOption(option)
    let date = new Date()
    console.log(date.Format("yyyy-MM-dd hh:mm:ss.S"),"更新位置")
}
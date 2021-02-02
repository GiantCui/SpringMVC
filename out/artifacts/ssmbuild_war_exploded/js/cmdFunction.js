
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

function alarmElimination(){
    let json = {"cmd":"alarmElimination", "warn_num":[]}
    while (warn_num.length !== 0){
        json.warn_num.push(warn_num.pop())
    }
    //warn_num.popAll()
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
    websocket.send(JSON.stringify(json))
}

function byPass(){
    let json = {"cmd":"byPassAll"}
    websocket.send(JSON.stringify(json))
}
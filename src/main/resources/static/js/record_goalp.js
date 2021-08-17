var stTime = 0
var endTime = 0
var timerStart
var hour
var min
var sec
var play_or_stop = 0
var stopping = []

function toggleImg() {
    if (play_or_stop == 0) {
        play_or_stop = 1
        if (endTime != 0) {
            restartTime = Date.now()
        }
        document.getElementById("record_icon").src = "./img/stop_record_icon.png";
        $('.home_record_goalp_icon_img').css({
            "margin-left": "17px"
        });
        if (!stTime) {
            stTime = Date.now()
        }
        timerStart = setInterval(function() {
            var nowTime = Date.now() - stTime
            if (endTime != 0) {
                stopping.push(restartTime - endTime)
            }
            endTime = 0
            for (var i = 0; i < stopping.length; i++) {
                nowTime -= stopping[i]
            }
            hour = addZero(Math.floor(nowTime / 3600000))
            min = addZero(Math.floor((nowTime % 3600000) / 60000))
            sec = addZero(Math.floor(((nowTime % 3600000) % 60000) / 1000))

            document.getElementById('Hour').innerText = hour
            document.getElementById('Min').innerText = min
            document.getElementById('Sec').innerText = sec
        }, 1000)

    } else {
        play_or_stop = 0
        document.getElementById("record_icon").src = "./img/play_record_icon.png";
        $('.home_record_goalp_icon_img').css({
            "margin-left": "20px"
        });
        endTime = 0
        if (timerStart) {
            clearInterval(timerStart)
            endTime = Date.now()
        }
    }
}





function addZero(num) {
    return (num < 10 ? '0' + num : '' + num)
}
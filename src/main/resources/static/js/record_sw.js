var stopwatchStart
var hour
var min
var sec
var play_or_stop = 0
var stopping = []
var time = 0

function toggleImg() {
    if (play_or_stop == 0) {
        play_or_stop = 1
        document.getElementById("record_icon").src = "/img/stop_record_icon.png";
        $('.home_record_goalp_icon_img').css({
            "margin-left": "17px"
        });
        stopwatchStart = setInterval(function() {

            time++;

            min = Math.floor(time / 60);
            hour = Math.floor(min / 60);
            sec = time % 60;
            min = min % 60;

            document.getElementById('Hour').innerText = addZero(hour)
            document.getElementById('Min').innerText = addZero(min)
            document.getElementById('Sec').innerText = addZero(sec)
        }, 1000)

    } else {
        play_or_stop = 0
        document.getElementById("record_icon").src = "/img/play_record_icon.png";
        $('.home_record_goalp_icon_img').css({
            "margin-left": "20px"
        });
        if (stopwatchStart) {
            clearInterval(stopwatchStart)
            var record = {
                "time" : time,
                "goalName" : document.getElementById('hiddendata_gn').innerText,
                "goalTag" : document.getElementById('hiddendata_gt').innerText
            }
            $.ajax({
                type : "POST",
                url : "../../../update_record_sw.do",
                contentType : "application/x-www-form-urlencoded",
                data : record,
                success : function (result){
                    if(result==0){
                        console.log("성공");
                    }else{
                        console.log("실패");
                    }

                },
                error : function (jqXHR, status, error){
                    alert("알 수 없는 에러 [" + error + "]")
                }
            });
        }
    }
}




$(document).ready(function(){
    hour = Number(document.getElementById('Hour').innerText);
    min = Number(document.getElementById('Min').innerText);
    sec = Number(document.getElementById('Sec').innerText);

    time = (hour * 3600) + (min * 60) + sec;
});



function addZero(num) {
    return (num < 10 ? '0' + num : '' + num)
}
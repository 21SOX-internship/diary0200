function modifygoal(goalName, timetype){
    event.stopPropagation();
    window.location.href="../home/edit?goalName="+goalName+"&timeType="+timetype;
}

function record_sw(goalName, goalTag){
    var goaldata = {
        "goalName":goalName,
        "goalTag":goalTag
    };

    $.ajax({
        type : "POST",
        url : "../../record_sw.do",
        contentType : "application/x-www-form-urlencoded",
        data : goaldata,
        success : function (time){
            window.location.href = "/home/record/stopwatch?goalName="+goalName+"&goalTag="+goalTag+"&time="+time;
        },
        error : function (jqXHR, status, error){
            alert("알 수 없는 에러 [" + error + "]")
        }
    });
}

function record_t(goalName, goalTag){
    var goaldata = {
        "goalName":goalName,
        "goalTag":goalTag
    };

    $.ajax({
        type : "POST",
        url : "../../record_t.do",
        contentType : "application/x-www-form-urlencoded",
        data : goaldata,
        success : function (jobj){
            var result = JSON.parse(jobj);
            window.location.href = "/home/record/timer?goalName="+goalName+"&goalTag="+goalTag+"&time="+result.time+"&endtime="+result.endTime;
        },
        error : function (jqXHR, status, error){
            alert("알 수 없는 에러 [" + error + "]")
        }
    });
}
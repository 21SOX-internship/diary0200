function gomainhome(){
    window.location.href="/home/main";
}
var istimer = 0;

function savegoal(){
    if(istimer==0){
        var goaldata = {
            "goalName" : $("#goalName").val(),
            "tag" : $("#goalTag").val()
        }

        $.ajax({
            type : "POST",
            url : "../../savegoalsw.do",
            contentType : "application/x-www-form-urlencoded",
            data : goaldata,
            success : function (result){
                if(result==0){
                    alert("저장이 완료되었습니다.");
                    window.location.href="/home/main";
                }else{
                    alert("저장이 실패하였습니다.");
                }
            },
            error : function (jqXHR, status, error){
                alert("알 수 없는 에러 [" + error + "]")
            }
        });
    }else{      //타이머이기 때문에 조금 다르게 넘어가야함.
        var hhmmss = String($("#hh").val()) + ":" + String($("#mm").val()) + ":" + String($("#ss").val());

        var goaldata = {
            "goalName" : $("#goalName").val(),
            "tag" : $("#goalTag").val(),
            "endTime" : hhmmss
        }

        $.ajax({
            type : "POST",
            url : "../../savegoalt.do",
            contentType : "application/x-www-form-urlencoded",
            data : goaldata,
            success : function (result){
                if(result==0){
                    alert("저장이 완료되었습니다.");
                    window.location.href="/home/main";
                }else{
                    alert("저장이 실패하였습니다.");
                }
            },
            error : function (jqXHR, status, error){
                alert("알 수 없는 에러 [" + error + "]")
            }
        });
    }

}

function updategoal(){

}

function timesetting(){
    if($("input:radio[value='timer']").is(":checked")){
        $(".home_makegoalp_timersetting").css("display","block");
        istimer = 1;
    }else{
        $(".home_makegoalp_timersetting").css("display","none");
        istimer = 0;
    }
}



$( document ).ready(function() {
    for(var i = 0; i < 60; i++){
        var option = $("<option>"+addZero(i)+"</option>");
        $('#ss').append(option);
    }
    for(var i = 0; i < 60; i++){
        var option = $("<option>"+addZero(i)+"</option>");
        $('#mm').append(option);
    }
    for(var i = 0; i < 24; i++){
        var option = $("<option>"+addZero(i)+"</option>");
        $('#hh').append(option);
    }
    if($("input:radio[value='timer']").is(":checked")){
        $(".home_makegoalp_timersetting").css("display","block");
        istimer = 1;
    }
});

function addZero(num) {
    return (num < 10 ? '0' + num : '' + num)
}
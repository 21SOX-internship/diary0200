function doLogin(){
    var userid = $('#userid').val();
    var userpw = $('#userpw').val();

    var logindata = {
        "userid" : userid,
        "userpw" : userpw
    };

    $.ajax({
        type : "POST",
        url : "/SignInController",
        contentType : "application/x-www-form-urlencoded",
        data : logindata,
        success : function (result){
            console.log(result);
            var jsonObj = JSON.parse(result);
            if(jsonObj.result == 1) {
                window.location.href = "/home/nogoal";
            }else if(jsonObj.result == 0){
                alert("잘못된 비밀번호입니다.");
            }else if(jsonObj.result == -1){
                alert("잘못된 혹은 없는 아이디입니다.");
            }else{
                alert("DB오류입니다.");
            }
        },
        error : function (jqXHR, status, error){
            alert("알 수 없는 에러 [" + error + "]")
        }
    });
}

//
// function doLogin(){
//     var userid = $('input[name="userid"]').val();
//     var userpw = $('input[name="userpw"]').val();
//
//     $.post("/SignInController2",
//         {
//             "userid" : userid,
//             "userpw" : userpw
//         },
//         function(response){
//             console.log(response);
//             var jsonObj = JSON.parse(response);
//             if(jsonObj.result == 1) {
//                 window.location.href = "/home/nogoal";
//             }else if(jsonObj.result == 0){
//                 alert("잘못된 비밀번호입니다.");
//             }else if(jsonObj.result == -1){
//                 alert("잘못된 혹은 없는 아이디입니다.");
//             }else{
//                 alert("DB오류입니다.");
//             }
//         });
//
// }



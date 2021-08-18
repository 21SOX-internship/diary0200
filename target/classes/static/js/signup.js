var ischecked = 0;

function validateForm() {
    "use strict";
    const userid = document.getElementById("userid").value.trim();
    const userpw = document.getElementById("userpw").value.trim();
    const checkpw = document.getElementById("checkpw").value.trim();
    const username = document.getElementById("username").value.trim();
    const useremail = document.getElementById("useremail").value.trim();
    const userphone = document.getElementById("userphone").value.trim();
    const userbirth = document.getElementById("userbirth").value.trim();


    if (userid != "" && userpw != "" && checkpw != "" && username != "" && useremail != "" && userphone != "" && userbirth != "" &&
        $(':input:radio[name=gender]:checked').val()) {
        if(ischecked == 1){
            $("#signupp_btn_text").attr("disabled", false);
        }
    } else {
        $("#signupp_btn_text").attr("disabled", true);
    }
}
$(function() {
    $('#signup_form').change(function() {
        validateForm();
    })
})

function checkForm() {
    "use strict";
    const myForm = document.getElementById("signup_form");
    myForm.addEventListener("change", function() {
            validateForm();
    });
}



function checkid(){
    $.post("/SignUpCheck",
        {
            "userId" : $("#userid").val()
        },
        function(response){
            var jsonObj = JSON.parse(response);
            if(jsonObj.duplicated) {
                alert("중복되는 아이디입니다.");
                ischecked = 0;
            }else{
                alert("사용 가능한 아이디입니다.");
                ischecked = 1;
            }
        });
}


function dosignup(){
    var userdata = {
        "userid" : $("#userid").val(),
        "userpw" : $("#userpw").val(),
        "username" : $("#username").val(),
        "useremail" : $("#useremail").val(),
        "userphone" : $("#userphone").val(),
        "userbirth" : $("#userbirth").val(),
        "usergender" : $("input[name='gender']:checked").val()
    };
    $.ajax({
        type : "POST",
        url : "/SignInController2",
        contentType : "application/x-www-form-urlencoded",
        data : userdata,
        success : function (result){
            var jsonObj = JSON.parse(result);
            if(jsonObj.result == 1) {
                window.location.href = "/login";
            }else{
                alert("회원가입 실패.");
            }
        },
        error : function (jqXHR, status, error){
            alert("알 수 없는 에러 [" + error + "]")
        }
    });

}

function docheckpw(){
    checkpw = $("#checkpw").val();
    userpw = $("#userpw").val();

    if(userpw == checkpw){
        document.getElementById('ischeckedpw').innerHTML = "O";
        console.log(1);
    }else{
        document.getElementById('ischeckedpw').innerHTML = "X";
        console.log(2);
    }
}

var ischecked = 0;
var pwchecked = 0;

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
    $.post("checkid.do",
        {
            "userid" : $("#userid").val()
        },
        function(result){
            if(result=="true") {
                alert("중복되는 아이디입니다.");
                ischecked = 0;
            }else{
                alert("사용 가능한 아이디입니다.");
                ischecked = 1;
            }
        });
}


function dosignup(){
    if(pwchecked == 0){
        alert("비밀번호를 확인해주세요.");
    }
    else{
        var userdata = {
            "id" : $("#userid").val(),
            "pw" : $("#userpw").val(),
            "name" : $("#username").val(),
            "email" : $("#useremail").val(),
            "tel" : $("#userphone").val(),
            "age" : $("#userbirth").val(),
            "gender" : $("input[name='gender']:checked").val()
        };

        $.ajax({
            type : "POST",
            url : "signup.do",
            contentType : "application/x-www-form-urlencoded",
            data : userdata,
            success : function (result){
                if(result == 1) {
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


}

function docheckpw(){
    checkpw = $("#checkpw").val();
    userpw = $("#userpw").val();

    if(userpw == checkpw){
        document.getElementById('ischeckedpw').innerHTML = "O";
        pwchecked = 1;
    }else{
        document.getElementById('ischeckedpw').innerHTML = "X";
        pwchecked = 0;
    }
}

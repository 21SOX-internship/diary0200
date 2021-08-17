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
        $("#signupp_btn_text").attr("disabled", false);
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
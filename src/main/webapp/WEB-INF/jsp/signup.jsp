<%--
  Created by IntelliJ IDEA.
  User: hwnim
  Date: 2021-08-13
  Time: 오후 3:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
  <title>집중일기</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="js/signup.js"></script>
  <link rel="stylesheet" href="css/cssreset.css">
  <link rel="stylesheet" href="css/font.css">
  <link rel="stylesheet" href="css/picture.css">
  <link rel="stylesheet" href="css/btn.css">
  <link rel="stylesheet" href="css/background.css">
  <link rel="stylesheet" href="css/input.css">
  <link rel="stylesheet" href="css/menubar.css">
</head>

<body>
<div class="signupp_background">
  <form method="POST" id="signup_form" action="/SignUpController">
    <p class="signupp_ment">회원가입 <img class="pink_circle" src="img/pink_twocircle.svg" /></p>
    <p class="signupp_label">아이디</p>
    <input type="text" id="userid" class="signupp_input_short" placeholder="아이디를 입력하세요">
    <button class="signupp_checkid_btn" id="signupp_checkid_text">중복확인</button>
    <p class="signupp_label">비밀번호</p>
    <input type="password" id="userpw" class="signupp_input" placeholder="비밀번호를 입력하세요.">
    <p class="signupp_label">비밀번호 확인</p>
    <input type="password" id="checkpw" class="signupp_input" placeholder="비밀번호를 다시 입력하세요.">
    <p class="signupp_label">이름</p>
    <input type="text" id="username" class="signupp_input" placeholder="이름을 입력하세요">
    <p class="signupp_label">이메일</p>
    <input type="email" id="useremail" class="signupp_input" placeholder="이메일 주소를 입력하세요">
    <p class="signupp_label">휴대폰 번호</p>
    <input type="tel" id="userphone" class="signupp_input" placeholder="'-'구분 없이 입력하세요">
    <p class="signupp_label">생년월일</p>
    <input type="text" id="userbirth" class="signupp_input" placeholder="8자리로 입력하세요">
    <p class="signupp_label">성별</p>
    <div class="signupp_radio_btn">
      <input type="radio" id="signupp_radio_btn-1" name="gender" value="female"><label for="signupp_radio_btn-1" class="signupp_radio_btn-label">여성</label>&emsp;
      <input type="radio" id="signupp_radio_btn-2" name="gender" value="male"><label for="signupp_radio_btn-2" class="signupp_radio_btn-label">남성</label>
    </div>
    <input type="submit" disabled="true" class="signupp_start_btn" id="signupp_btn_text" value="집중일기 시작하기">
  </form>
</div>
</body>

<script>
</script>

</html>
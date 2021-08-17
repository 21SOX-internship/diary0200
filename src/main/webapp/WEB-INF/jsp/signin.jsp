<%--
  Created by IntelliJ IDEA.
  User: hwnim
  Date: 2021-08-13
  Time: 오후 2:55
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
  <link rel="stylesheet" href="css/cssreset.css">
  <link rel="stylesheet" href="css/font.css">
  <link rel="stylesheet" href="css/picture.css">
  <link rel="stylesheet" href="css/btn.css">
  <link rel="stylesheet" href="css/background.css">
  <link rel="stylesheet" href="css/input.css">
  <link rel="stylesheet" href="css/menubar.css">
</head>

<body>
<div class="loginp_background">
  <form method="post" action="#">
    <p class="loginp_ment">로그인을 해주세요 <img class="pink_circle" src="img/pink_twocircle.svg" /></p>
    <p class="loginp_id_text">아이디</p><br>
    <input type="text" class="loginp_input" placeholder="아이디를 입력하세요."><br><br>
    <p class="loginp_pw_text">비밀번호</p><br>
    <input type="password" class="loginp_input" placeholder="비밀번호를 입력하세요."><br>
    <input type="submit" class="loginp_login_btn" id="loginp_btn_text" value="로그인"></button>
    <a href="signup.jsp">
      <p class="loginp_signup">회원이 아닌 경우, 계정 만들기</p>
    </a>
  </form>
</div>
</body>

</html>
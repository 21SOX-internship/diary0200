<%--
  Created by IntelliJ IDEA.
  User: hwnim
  Date: 2021-08-13
  Time: 오후 3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
    <title>집중일기</title>
    <script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="/js/profilep.js"></script>
    <link rel="stylesheet" href="/css/cssreset.css">
    <link rel="stylesheet" href="/css/logo.css">
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/picture.css">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/background.css">
    <link rel="stylesheet" href="/css/inputtext.css">
    <link rel="stylesheet" href="/css/input.css">
    <link rel="stylesheet" href="/css/menubar.css">
    <link rel="stylesheet" href="/css/divdesign.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

</head>

<body>
<div class="home_record_goalp_background">
    <img class="logoimg" src="/img/logo.svg">
    <p class="title_text">집중일기 <img class="pink_circle" src="/img/pink_twocircle.svg" /></p>
    <div class="icon_boarder_pink_big"><img src="/img/tags/goal_coding.png" class="goal_icon_big"></div>
    <div class="home_record_goalp_goal_border">
        <p class="home_record_goalp_goal_name">코딩하기</p>
    </div>
    <div class="time">
        <span id="Hour">00</span>
        <span>:</span>
        <span id="Min">00</span>
        <span>:</span>
        <span id="Sec">00</span>
    </div>
    <div class="home_record_goalp_icon_border" onclick="toggleImg()"><img src="/img/play_record_icon.png" class="home_record_goalp_icon_img" id="record_icon"></div>

</div>
<!-- 메뉴 -->
<ul class="menubar">
    <a href="#">
        <li class="menubar_inner">
            <div><img class="menu_icon_home" src="/img/home_active.png"></div><span class="menubar_text_active">홈</span>
        </li>
    </a>
    <a href="#">
        <li class="menubar_inner">
            <div><img class="menu_icon_friend" src="/img/friend_default.png"></div><span class="menubar_text_default">친구</span>
        </li>
    </a>
    <a href="#">
        <li class="menubar_inner">
            <div><img class="menu_icon_group" src="/img/group_default.png"></div><span class="menubar_text_default">그룹</span>
        </li>
    </a>
    <a href="#">
        <li class="menubar_inner">
            <div><img class="menu_icon_mypage" src="/img/mypage_default.png"></div><span class="menubar_text_default">마이페이지</span>
        </li>
    </a>
</ul>
<!-- 메뉴 -->
</body>
<script>
    console.log(document.getElementById("record_icon").src);
</script>

</html>
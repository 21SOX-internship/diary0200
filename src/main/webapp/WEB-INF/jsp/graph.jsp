<%--
  Created by IntelliJ IDEA.
  User: hwnim
  Date: 2021-08-24
  Time: 오전 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
    <title>집중일기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/home.js"></script>
    <link rel="stylesheet" href="/css/cssreset.css">
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/picture.css">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/background.css">
    <link rel="stylesheet" href="/css/input.css">
    <link rel="stylesheet" href="/css/menubar.css">
    <link rel="stylesheet" href="/css/divdesign.css">
</head>

<body>
<div class="home_makegoalp_background">
    <img class="logoimg" src="img/logo.svg">
    <p class="title_text">집중일기 <img class="pink_circle" src="img/purple_twocircle.svg" /></p>
    <p class="graphp_big_label">오늘의 집중</p>
    <div class="graphp_todays_border"><p class="graphp_big_time">${todaytime}</p></div>
    <p class="graphp_label">전체 평균</p>
    <div class="graphp_avg_border">
        <div class="graphp_time_line"><p class="graphp_small_label">월간 평균</p><p class="graphp_small_time">${monthtime}</p></div>
        <div class="graphp_time_line"><p class="graphp_small_label">주간 평균</p><p class="graphp_small_time">${weektime}</p></div>
    </div>

</div>

<!-- 메뉴 -->
<ul class="menubar">
    <a href="/home/main">
        <li class="menubar_inner">
            <div><img class="menu_icon_home" src="img/home_default.png"></div><span class="menubar_text_default">홈</span>
        </li>
    </a>
    <a href="/friend/main">
        <li class="menubar_inner">
            <div><img class="menu_icon_friend" src="img/friend_default.png"></div><span class="menubar_text_default">친구</span>
        </li>
    </a>
    <a href="/graph">
        <li class="menubar_inner">
            <div><img class="menu_icon_group" src="img/group_active.png"></div><span class="menubar_text_active">통계</span>
        </li>
    </a>
    <a href="/mypage/main">
        <li class="menubar_inner">
            <div><img class="menu_icon_mypage" src="img/mypage_default.png"></div><span class="menubar_text_default">마이페이지</span>
        </li>
    </a>
</ul>
<!-- 메뉴 -->
</body>

</html>
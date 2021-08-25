<%--
  Created by IntelliJ IDEA.
  User: hwnim
  Date: 2021-08-13
  Time: 오후 3:01
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
  <script src="/js/change_icon_border.js" charset="UTF-8"></script>
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
  <img class="logoimg" src="/img/logo.svg">
  <p class="title_text">집중일기 <img class="pink_circle" src="/img/pink_twocircle.svg" /></p>
      <c:forEach items = "${goallistsw}" var = "goallistsw" varStatus = "status">

        <div class="home_goallistp_goal_list">
          <div class="icon_boarder_pink"><img src="/img/tags/${goallistsw.tag}.png" class="goal_icon"><span class="hidden_data_tag" style="display: none; visibility: hidden;">${goallistsw.tag}</span></div>
          <div class="home_goallistp_horizontal_align">
            <p class="home_goallistp_goal_name">${goallistsw.goalName}</p>
          </div>
        </div>
      </c:forEach>

      <c:forEach items = "${goallistt}" var = "goallistt" varStatus = "status">

        <div class="home_goallistp_goal_list">
          <div class="icon_boarder_pink"><img src="/img/tags/${goallistt.tag}.png" class="goal_icon"><span class="hidden_data_tag" style="display: none; visibility: hidden;">${goallistt.tag}</span></div><%--${goallistt.tag}--%>

          <div class="home_goallistp_horizontal_align">
            <p class="home_goallistp_goal_name">${goallistt.goalName}</p>
          </div>
        </div>
      </c:forEach>
</div>

<!-- 메뉴 -->
<ul class="menubar">
  <a href="/home/main">
    <li class="menubar_inner">
      <div><img class="menu_icon_home" src="/img/home_active.png"></div><span class="menubar_text_active">홈</span>
    </li>
  </a>
  <a href="/friend/main">
    <li class="menubar_inner">
      <div><img class="menu_icon_friend" src="/img/friend_default.png"></div><span class="menubar_text_default">친구</span>
    </li>
  </a>
  <a href="/graph">
    <li class="menubar_inner">
      <div><img class="menu_icon_group" src="/img/group_default.png"></div><span class="menubar_text_default">통계</span>
    </li>
  </a>
  <a href="/mypage/main">
    <li class="menubar_inner">
      <div><img class="menu_icon_mypage" src="/img/mypage_default.png"></div><span class="menubar_text_default">마이페이지</span>
    </li>
  </a>
</ul>
<!-- 메뉴 -->
</body>

</html>
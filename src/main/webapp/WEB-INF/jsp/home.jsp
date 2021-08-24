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
<c:choose>
  <c:when test="${numofgoal==0}">
    <img class="home_nogoalimg" src="/img/nogoalimg.svg" />
    <a href="/home/edit">
      <button class="home_nogoalp_setgoal_btn"><span class="home_nogoalp_setgoal_text">목표 설정하기</span></button>
    </a>
  </c:when>

<c:otherwise>

  <c:forEach items = "${goallistsw}" var = "goallistsw" varStatus = "status">

  <div class="home_goallistp_goal_list" onclick="record_sw('${goallistsw.goalName}', '${goallistsw.tag}');">
    <div class="icon_boarder_pink"><img src="/img/tags/${goallistsw.tag}.png" class="goal_icon"><span class="hidden_data_tag" style="display: none; visibility: hidden;">${goallistsw.tag}</span></div>
    <div class="home_goallistp_horizontal_align">
      <p class="home_goallistp_goal_name">${goallistsw.goalName}</p>
        <div class="home_goallistp_modify_icon_border" onclick="modifygoal('${goallistsw.goalName}','stopwatch')"><img class="homep_goallist_modify_icon" src="/img/modify_icon.png"></div>
    </div>
  </div>
  </c:forEach>

  <c:forEach items = "${goallistt}" var = "goallistt" varStatus = "status">

    <div class="home_goallistp_goal_list" onclick="record_t('${goallistt.goalName}', '${goallistt.tag}');">
      <div class="icon_boarder_pink"><img src="/img/tags/${goallistt.tag}.png" class="goal_icon"><span class="hidden_data_tag" style="display: none; visibility: hidden;">${goallistt.tag}</span></div><%--${goallistt.tag}--%>

      <div class="home_goallistp_horizontal_align">
        <p class="home_goallistp_goal_name">${goallistt.goalName}</p>
        <div class="home_goallistp_modify_icon_border" onclick="modifygoal('${goallistt.goalName}','timer')"><img class="homep_goallist_modify_icon" src="/img/modify_icon.png"></div>
      </div>
    </div>
  </c:forEach>

  <a href="/home/edit"><div class="home_goallistp_add_goal_border"><img src="/img/add_goal_icon.png" class="home_goallistp_add_goal_icon"></div></a>
</c:otherwise>
</c:choose>
</div>

<!-- 메뉴 -->
<ul class="menubar">
  <a href="/home/main">
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

</html>
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
  <link rel="stylesheet" href="css/divdesign.css">
</head>

<body>
<div class="home_makegoalp_background">
  <img class="logoimg" src="img/logo.svg">
  <p class="title_text">집중일기 <img class="pink_circle" src="img/pink_twocircle.svg" /></p>

  <div class="home_goallistp_goal_list">
    <div class="icon_boarder_pink"><img src="img/tags/goal_coding.png" class="goal_icon"></div>
    <div class="home_goallistp_horizontal_align">
      <p class="home_goallistp_goal_name">코딩하기</p>
      <a href="#">
        <div class="home_goallistp_modify_icon_border"><img class="homep_goallist_modify_icon" src="img/modify_icon.png"></div>
      </a>
    </div>
  </div>

  <div class="home_goallistp_goal_list">
    <div class="icon_boarder_pink"><img src="img/tags/goal_coding.png" class="goal_icon"></div>
    <div class="home_goallistp_horizontal_align">
      <p class="home_goallistp_goal_name">코딩하기</p>
      <a href="#">
        <div class="home_goallistp_modify_icon_border"><img class="homep_goallist_modify_icon" src="img/modify_icon.png"></div>
      </a>
    </div>
  </div>

  <a href="setgoal.html">
    <div class="home_goallistp_add_goal_border"><img src="img/add_goal_icon.png" class="home_goallistp_add_goal_icon"></div>
  </a>


</div>

<!-- 메뉴 -->
<ul class="menubar">
  <a href="#">
    <li class="menubar_inner">
      <div><img class="menu_icon_home" src="img/home_active.png"></div><span class="menubar_text_active">홈</span>
    </li>
  </a>
  <a href="#">
    <li class="menubar_inner">
      <div><img class="menu_icon_friend" src="img/friend_default.png"></div><span class="menubar_text_default">친구</span>
    </li>
  </a>
  <a href="#">
    <li class="menubar_inner">
      <div><img class="menu_icon_group" src="img/group_default.png"></div><span class="menubar_text_default">그룹</span>
    </li>
  </a>
  <a href="#">
    <li class="menubar_inner">
      <div><img class="menu_icon_mypage" src="img/mypage_default.png"></div><span class="menubar_text_default">마이페이지</span>
    </li>
  </a>
</ul>
<!-- 메뉴 -->
</body>

</html>
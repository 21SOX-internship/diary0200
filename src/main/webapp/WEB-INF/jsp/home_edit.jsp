<%--
  Created by IntelliJ IDEA.
  User: hwnim
  Date: 2021-08-13
  Time: 오후 3:00
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
<div class="home_makegoalp_background">
    <form method="POST"></form>
    <img class="logoimg" src="/img/logo.svg">
    <p class="title_text">집중일기 <img class="pink_circle" src="/img/pink_twocircle.svg" /></p>
    <div class="home_makegoalp_grid">
        <div class="icon_boarder"><img class="goal_icon" src="/img/goal_default.png"></div>
        <div class="home_makegoalp_vertical_align">
            <p class="home_makegoalp_label">목표</p>
            <input type="text" class="home_makegoalp_goal_input" value="오늘의 집중 목표">
            <p class="home_makegoalp_label">태그</p>
            <input type="search" list="tag_list" class="home_makegoalp_tag_input" placeholder="카테고리 선택하기">
            <p class="home_makegoalp_label">시간 유형</p>
            <div class="time_type_radio_btn">
                <input type="radio" id="time_type_radio_btn-1" name="gender" value="timer"><label for="time_type_radio_btn-1" class="time_type_radio_btn-label">Timer</label>&emsp;
                <input type="radio" id="time_type_radio_btn-2" name="gender" value="stopwatch"><label for="time_type_radio_btn-2" class="time_type_radio_btn-label">Stopwatch</label>
            </div>
        </div>
    </div>
    <div class="home_makegoalp_horizontal_align">
        <button class="home_makegoalp_cancle_btn">취소</button>
        <input type="submit" class="home_makegoalp_ok_btn" value="완료">

    </div>
    </form>
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

<datalist id="tag_list">
    <option value="내신 공부">
    <option value="수능 공부">
    <option value="전공 공부">
    <option value="취업 공부">
    <option value="코딩">
    <option value="게임">
    <option value="영상 편집">
    <option value="그래픽">
    <option value="컴퓨터 활용">
    <option value="요리">
    <option value="베이킹">
    <option value="미술">
    <option value="뷰티">
    <option value="노래">
    <option value="악기">
    <option value="작곡">
    <option value="헬스">
    <option value="축구">
    <option value="농구">
    <option value="야구">
    <option value="탁구">
    <option value="배구">
    <option value="볼링">
    <option value="복싱">
    <option value="수영">
    <option value="스케이팅">
    <option value="골프">
    <option value="다이어트">
    <option value="자격증">
</datalist>


</body>

</html>
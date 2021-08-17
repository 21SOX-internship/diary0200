<%--
  Created by IntelliJ IDEA.
  User: hwnim
  Date: 2021-08-17
  Time: 오전 11:37
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
    <script src="js/profilep.js"></script>
    <link rel="stylesheet" href="css/cssreset.css">
    <link rel="stylesheet" href="css/logo.css">
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/picture.css">
    <link rel="stylesheet" href="css/btn.css">
    <link rel="stylesheet" href="css/background.css">
    <link rel="stylesheet" href="css/inputtext.css">
    <link rel="stylesheet" href="css/input.css">
    <link rel="stylesheet" href="css/menubar.css">
    <link rel="stylesheet" href="css/divdesign.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">


</head>

<body>
<div class="friend_list_header_background">
    <img class="profilep_logo" src="img/logo.svg">
    <img class="profilep_circles_image" src="img/profilep_blue_twocircle.svg">
    <p class="friend_list_title">친구관리</p>
</div>
<div class="friend_manage_main_background">
    <div class="friend_manage_search_background">
        <p class="friend_manage_search_text">친구 검색</p>
        <img class="friend_list_search_image" src="img/friend_list_search.png">
        <input type="text" class="friend_manage_search_input" placeholder="아이디를 입력하세요.">
    </div>
    <div class="friend_manage_friend_request">
        <p class="friend_manage_text">친구 요청</p>
        <div class="friend_manage_request_list">
            <img class="friend_list_profile_image" src="img/profilep_profile.png">
            <p class="friend_list_profile_name">이름</p>
            <div class="friend_list_friend_decision">
                <p class="friend_list_friend_accept">확인</p>
                <p class="friend_list_friend_refuse">취소</p>
            </div>
        </div>
    </div>
    <div class="friend_manage_friend_delete">
        <p class="friend_manage_text">친구 삭제</p>
        <div class="friend_manage_friend_list">
            <img class="friend_list_profile_image" src="img/profilep_profile.png">
            <p class="friend_list_profile_name">이름</p>
            <img class="friend_list_delete_img" src="img/friend_list_delete.png">
        </div>
        <div class="friend_manage_friend_list">
            <img class="friend_list_profile_image" src="img/profilep_profile.png">
            <p class="friend_list_profile_name">이름</p>
            <img class="friend_list_delete_img" src="img/friend_list_delete.png">
        </div>
        <div class="friend_manage_friend_list">
            <img class="friend_list_profile_image" src="img/profilep_profile.png">
            <p class="friend_list_profile_name">이름</p>
            <img class="friend_list_delete_img" src="img/friend_list_delete.png">
        </div>
        <div class="friend_manage_friend_list">
            <img class="friend_list_profile_image" src="img/profilep_profile.png">
            <p class="friend_list_profile_name">이름</p>
            <img class="friend_list_delete_img" src="img/friend_list_delete.png">
        </div>
        <div class="friend_manage_friend_list">
            <img class="friend_list_profile_image" src="img/profilep_profile.png">
            <p class="friend_list_profile_name">이름</p>
            <img class="friend_list_delete_img" src="img/friend_list_delete.png">
        </div>
    </div>
</div>






<div class="profilep_menubar">
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
</div>

</body>

</html>
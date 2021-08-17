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
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/picture.css">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/background.css">
    <link rel="stylesheet" href="/css/input.css">
    <link rel="stylesheet" href="/css/menubar.css">
    <link rel="stylesheet" href="/css/divdesign.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

</head>

<body>
<div class="profilep_whole_section">
    <div class="profilep_header_background1">

        <img class="profilep_profile_edit_button" src="/img/profilep_profile_edit.png" onclick="location.href='myPageController'">
        <img class="profilep_profile_setting_button" src="/img/setting.png">

        <img class="profilep_logo" src="/img/logo.svg">
        <img class="profilep_circles_image" src="/img/profilep_blue_twocircle.svg">
        <p class="profilep_ment">마이페이지</p>
    </div>
    <div class="profilep_header_background2">
        <div class="profilep_profile_circle"></div>
        <img class="profilep_profile_image" src="/img/profilep_profile.png">
        <p class="profilep_ment2">이름</p>
        <p class="profilep_ment3">상태메시지상태메시지</p>
    </div>
    <div class="profilep_folder_background">
        <div class="profilep_each_folder_background">
            <img class="profilep_folder_image" src="/img/profilep_folder.png">
            <p class="profilep_folder_name">폴더1</p>
        </div>
        <div class="profilep_each_folder_background">
            <img class="profilep_folder_image" src="/img/profilep_folder.png">
            <p class="profilep_folder_name">폴더2</p>
        </div>
        <div class="profilep_each_folder_background">
            <img class="profilep_folder_image" src="/img/profilep_folder.png">
            <p class="profilep_folder_name">폴더3</p>
        </div>
        <div class="profilep_each_folder_background">
            <img class="profilep_folder_image" src="/img/profilep_folder.png">
            <p class="profilep_folder_name">폴더4</p>
        </div>
        <div class="profilep_each_folder_background">
            <img class="profilep_folder_image" src="/img/profilep_folder.png">
            <p class="profilep_folder_name">폴더5</p>
        </div>
        <div class="profilep_each_folder_background">
            <img class="profilep_folder_image" src="/img/profilep_folder.png">
            <p class="profilep_folder_name">폴더6</p>
        </div>
    </div>
</div>
<div class="profilep_goallist_background">
    <div class="container">
        <div class="row">
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
        </div>
        <div class="row">
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
        </div>
        <div class="row">
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
        </div>
        <div class="row">
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
        </div>
        <div class="row">
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
        </div>
        <div class="row">
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>
        </div>
    </div>
</div>
<div class="profilep_menubar">
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
</div>

</body>

</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi"/>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">


</head>

<body>
<div class="friend_list_header_background">
    <img class="friend_list_setting_image" src="/img/setting.png">
    <img class="profilep_logo" src="/img/logo.svg">
    <img class="profilep_circles_image" src="/img/profilep_blue_twocircle.svg">
    <p class="friend_list_title">친구목록</p>
</div>
<div class="friend_list_my_info">
    <div class="friend_list_my_profile_info">
        <img class="friend_list_profile_image" src="../upload/${myInfo.get(0).getInt("seq")}.png">
        <p class="friend_list_profile_name">${myInfo.get(0).getString("name")}</p>
        <p class="friend_list_profile_time">${myInfo.get(0).getString("time")}</p>
    </div>
</div>
<div class="friend_list_friend_list">
    <div class="friend_list_sorting_criteria_background">
        <div class="friend_list_sorting_button">
            <select id="select" onchange="sort();" class="friend_list_sorting_select">
                <option value="name">이름순</option>
                <option value="time">시간순</option>
            </select>
        </div>
    </div>

    <c:forEach var="friendInfo" items="${friendInfo}">
        <div class="friend_list_each_friend_info">
            <img class="friend_list_profile_image" src="../upload/${friendInfo.getInt("seq")}.png">
            <p class="friend_list_profile_name">${friendInfo.getString("name")}</p>
            <p class="friend_list_profile_time">${friendInfo.getString("time")}</p>
        </div>
    </c:forEach>

</div>


<div class="profilep_menubar">
    <ul class="menubar">
        <a href="/home/main">
            <li class="menubar_inner">
                <div><img class="menu_icon_home" src="/img/home_active.png"></div>
                <span class="menubar_text_active">홈</span>
            </li>
        </a>
        <a href="/friend/main">
            <li class="menubar_inner">
                <div><img class="menu_icon_friend" src="/img/friend_default.png"></div>
                <span class="menubar_text_default">친구</span>
            </li>
        </a>
        <a href="/graph">
            <li class="menubar_inner">
                <div><img class="menu_icon_group" src="/img/group_default.png"></div>
                <span class="menubar_text_default">그룹</span>
            </li>
        </a>
        <a href="/mypage/main">
            <li class="menubar_inner">
                <div><img class="menu_icon_mypage" src="/img/mypage_default.png"></div>
                <span class="menubar_text_default">마이페이지</span>
            </li>
        </a>
    </ul>
</div>



<script>
    function sort(){
        $.ajax({
            type : "POST",
            url : "../../friend/main/sort",
            contentType : "application/x-www-form-urlencoded",
            data : {
                "sortBy": $("#select option:selected").val()
            }// },
            // success : function (time){
            //     window.location.href = "/friend/main";
            // },
            // error : function (jqXHR, status, error){
            //     alert("알 수 없는 에러 [" + error + "]")
            // }
        });
    }
</script>


</body>

</html>
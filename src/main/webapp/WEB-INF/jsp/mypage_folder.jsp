<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="/js/folder.js"></script>
    <link rel="stylesheet" href="/css/cssreset.css">
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/picture.css">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/background.css">
    <link rel="stylesheet" href="/css/input.css">
    <link rel="stylesheet" href="/css/menubar.css">
    <link rel="stylesheet" href="/css/divdesign.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

</head>

<body>
<div class="profilep_whole_section">
    <div class="profilep_header_background1">

        <%--        <img class="profilep_profile_edit_button" src="/img/profilep_profile_edit.png"--%>
        <%--             onclick="location.href='/mypage/edit'">--%>
        <%--        저장버튼 onclick이랑 클래스 css넣어야함.--%>


        <img class="profilep_logo" src="/img/logo.svg">
        <img class="profilep_circles_image" src="/img/profilep_blue_twocircle.svg">
        <p class="profilep_ment">마이페이지</p>
    </div>

            <div class="profilep_header_background3">
                <div class="folder_border2"><img class="profilep_folder_image2" src="/img/profilep_folder.png"></div>
                <p class="folderp_foldername">${folderName}</p>
            </div>
</div>

<div class="profilep_goallist_background">

    <c:choose>
        <c:when test="${goalList!=null}">
            <c:set var="i" value="0"/>
            <c:set var="j" value="3"/>
            <div class="container">
                <c:forEach items="${goalList}" var="goalList">
                    <c:if test="${i%j == 0}">
                        <div class="row">
                    </c:if>
                    <label class="col-4 profilep_col_list" <%--onclick="event.stopPropagation();"--%>>
                        <img src="/img/tags/${goalList.getString("tag")}.png" class="profilep_goallist_img"
                             onclick="location.href='/mypage/pastgoal?date=${goalList.getString("date")}'">
                        <p class="profilep_goal_date">${goalList.getString("date")}</p>
                        <input type="checkbox" style="display: none; visibility: hidden;" name="checkfolder" value="${goalList.getString("date")}"/>
                    </label>
                    <c:if test="${i%j == j-1}">
                        </div>
                    </c:if>
                    <c:set var="i" value="${i+1}"/>
                </c:forEach>
            </div>
        </c:when>

        <c:otherwise>
            목표가 없습니다.

        </c:otherwise>
    </c:choose>


    <%--        <div class="row">--%>
    <%--            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>--%>
    <%--            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>--%>
    <%--            <div class="col profilep_col_list"><p class="profilep_goal_date">2021.08.09</p></div>--%>
    <%--        </div>--%>


</div>

<div class="profilep_menubar">
    <ul class="menubar">
        <a href="/home/main">
            <li class="menubar_inner">
                <div><img class="menu_icon_home" src="/img/home_default.png"></div>
                <span class="menubar_text_default">홈</span>
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
                <div><img class="menu_icon_mypage" src="/img/mypage_active.png"></div>
                <span class="menubar_text_active">마이페이지</span>
            </li>
        </a>
    </ul>
</div>

</body>

</html>

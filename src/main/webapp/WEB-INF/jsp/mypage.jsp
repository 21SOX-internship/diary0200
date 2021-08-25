<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
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

                        <img class="profilep_profile_edit_button" src="/img/profilep_profile_edit.png" onclick="location.href='/mypage/edit'">
                        <%--        <img class="profilep_profile_setting_button" src="/img/setting.png">--%>

                            <img class="profilep_logo" src="/img/logo.svg">
                            <img class="profilep_circles_image" src="/img/profilep_blue_twocircle.svg">
                            <p class="profilep_ment">마이페이지</p>
                    </div>
                    <%--    <div class="profilep_header_background2">--%>
                        <%--        <div class="profilep_profile_circle"></div>--%>
                            <%--        &lt;%&ndash;        <img class="profilep_profile_image" src="/img/profilep_profile.png">&ndash;%&gt;--%>
                                <%--        <img class="profilep_profile_image" src="../upload/${mypageInfo.getInt("seq")}.png">--%>
                                    <%--        <p class="profilep_ment2">이름</p>--%>
                                        <%--        <p class="profilep_ment3"></p>--%>
                                            <%--    </div>--%>

                                                <c:choose>
                                                    <c:when test="${mypageInfo!=null}">
                                                        <div class="profilep_header_background2">
                                                            <div class="profilep_profile_circle"></div>
                                                            <%--        <img class="profilep_profile_image" src="/img/profilep_profile.png">--%>
                                                                <img class="profilep_profile_image" src="/upload/${mypageInfo.getInt("seq")}.png" onerror="this.src='../img/default.png'">
                                                                <p class="profilep_ment2">${mypageName}</p>
                                                                <p class="profilep_ment3">${mypageInfo.getString("message")}</p>
                                                        </div>
                                                    </c:when>

                                                    <c:otherwise>
                                                        Error
                                                    </c:otherwise>
                                                </c:choose>

                                                <div class="profilep_folder_background">
                                                    <div class="profilep_each_folder_background">
                                                        <div class="folder_border"><img class="profilep_folder_image" src="/img/profilep_folder.png"></div>
                                                        <p class="profilep_folder_name">폴더1</p>
                                                    </div>
                                                    <div class="profilep_each_folder_background">
                                                        <div class="newfolder_border"><img onclick="location.href='/mypage/createfolder'" class="mypage_folder_image" src="/img/add_folder_icon.png"></div>
                                                    </div>
                                                </div>
                </div>

                <div class="profilep_goallist_background">

                    <c:choose>
                        <c:when test="${goalList!=null}">
                            <c:set var="i" value="0" />
                            <c:set var="j" value="3" />
                            <div class="container">
                                <c:forEach items="${goalList}" var="goalList">
                                    <c:if test="${i%j == 0}">
                                        <div class="row">
                                    </c:if>
                                    <div class="col-4 profilep_col_list" onclick="location.href='/mypage/pastgoal?date=${goalList.getString("date")}'">
                                        <img src="/img/tags/${goalList.getString("tag")}.png" class="profilep_goallist_img">
                                        <p class="profilep_goal_date">${goalList.getString("date")}</p>
                                    </div>
                                    <c:if test="${i%j == j-1}">
                                        </div>
                                    </c:if>
                                    <c:set var="i" value="${i+1}" />
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
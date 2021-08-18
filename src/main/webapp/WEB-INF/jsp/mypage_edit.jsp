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

<body class="profilep_edit_body">
<div class="profilep_edit_header_background">
    <img class="profilep_logo" src="/img/logo.svg">
    <img class="profilep_circles_image" src="/img/profilep_blue_twocircle.svg">
    <p class="profilep_ment">마이페이지</p>
</div>
<div class="profilep_edit_main_background">
    <form action="myPageEditRequest" method="post" enctype="multipart/form-data">
        <div class="profilep_edit_image_background">
            <div class="profilep_edit_profile_circle"></div>

            <label for="file-input">
                <img class="profilep_edit_profile_edit" src="/img/profilep_profile_edit.png">
            </label>
            <input id="file-input" type="file" accept="image/*" onchange="readURL(this)" name="photo" style="display: none;">

            <img class="profilep_edit_profile_image" id="temp" src="/img/profilep_profile.png">
            <%--        <img class="profilep_edit_profile_edit" src="/img/profilep_profile_edit.png">--%>
        </div>
        <div class="profilep_edit_message_background">
            <div class="profilep_edit_message_box">
                <p class="profilep_edit_message">상태메시지</p>
                <input type="text" class="profilep_edit_message_input" name="message" placeholder="상태메시지를 입력하세요.">
            </div>
        </div>
        <div class="profilep_edit_save_background">
            <input type="submit" class="profilep_edit_save" value="저장">
            <%--        <p class="profilep_edit_save" type="submit">저장</p>--%>
            <%--            <button class="profile_edit_save" type="submit">저장</button>--%>

        </div>
    </form>
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

<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('temp').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('temp').src = "";
        }
    }
</script>

</body>

</html>

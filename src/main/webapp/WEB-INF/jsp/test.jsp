<%--
  Created by IntelliJ IDEA.
  User: win10
  Date: 2021-08-17
  Time: 오후 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>파일 보내기 예제</title>
</head>
<body>
<form name="form" method="post" action="http://localhost:8080/board" enctype="multipart/form-data">
  <input name="user" value="Pyo"/>
  <input name="content" value="Content"/>
  <input type="file" name="files" multiple="multiple"/>
  <input type="submit" id="submit" value="전송"/>
</form>



<img class="profilep_edit_profile_edit" src="/img/profilep_profile_edit.png">

<div class="image-upload">
  <label for="file-input">
    <img src="/img/profilep_profile_edit.png">
  </label>
  <input id="file-input" type="file" accept="image/*" onchange="readURL(this)" style="display: none;">
</div>

<img id="preview">

<script>
  function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function(e) {
        document.getElementById('preview').src = e.target.result;
      };
      reader.readAsDataURL(input.files[0]);
    } else {
      document.getElementById('preview').src = "";
    }
  }
</script>


</body>
</html>

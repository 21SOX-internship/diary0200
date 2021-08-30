<%--
  Created by IntelliJ IDEA.
  User: win10
  Date: 2021-08-24
  Time: 오후 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    var message = "${msg}";
    var url = "${url}";
    alert(message);
    document.location.href = url;
</script>
</body>
</html>



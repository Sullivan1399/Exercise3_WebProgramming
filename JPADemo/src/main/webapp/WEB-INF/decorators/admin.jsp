<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<c:url value="/" var="URL"></c:url>
<html>
<head>
    <title>Admin decorator</title>
</head>
<body>
    <sitemesh:write property='body'/> 
    <script src="${URL}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script>
        function chooseFile(fileInput) {
            if(fileInput.files && fileInput.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#imagess').attr('src', e.target.result);
                }
                reader.readAsDataURL(fileInput.files[0]);
            }
        }
    </script>
</body>
</html>
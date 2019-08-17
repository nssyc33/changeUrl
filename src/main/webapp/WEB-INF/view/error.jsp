<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>error</title>
</head>
<script type="text/javascript">
</script>
<body>
[에러화면] 관리자에게 문의해주시기 바랍니다. 
<br>
[관리자 설영철 : 000-0000-0000]
<p>에러내용 : ${exception.getMessage()}</p>
</body>
</html>
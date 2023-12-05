<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${producer.name}</title>
</head>
<body>
<h1>${producer.name}</h1>
<c:forEach var="tour" items="${producer.tours}">
    <p>
        ${tour}
    </p>
</c:forEach>
</body>
</html>

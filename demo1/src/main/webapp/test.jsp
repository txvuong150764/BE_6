<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/standard-1.2" prefix="fmt" %>

<html>
<head>
    <title>JSTL Example</title>
</head>
<body>
<c:set var="greeting" value="Hello, World!" />
<h1>${greeting}</h1>

<fmt:formatDate value="${pageContext.request.time}" pattern="yyyy-MM-dd HH:mm:ss" />
</body>
</html>
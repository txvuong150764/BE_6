<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Servlet - Hello World</title>
</head>
<body>
    <h1>Hello World!</h1>
    <h1>Welcome <c:out value="${username}"/> </h1>
    <c:if test="${sessionScope.id == null}">
        <a href="login">Login</a>
    </c:if>
    <a href="products">Product</a>
    <a href="cart">Cart(${cartSize})</a>
    <c:if test="${sessionScope.id != null}">
        <a href="login?logout=true">Logout</a>
    </c:if>
</body>
</html>
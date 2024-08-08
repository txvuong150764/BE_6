<%--
  Created by IntelliJ IDEA.
  User: Tran Xuan Vuong
  Date: 7/08/2024
  Time: 1:26 am
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<table>
    <tr>
        <th>Product Name</th>
        <th>Price</th>
        <th>Detail</th>
    </tr>
    <tr>
        <td><c:out value="${product.name}"/></td>
        <td><c:out value="${product.price}"/></td>
        <td><c:out value="${product.description}"/></td>
    </tr>
</table>

<form action="${pageContext.request.contextPath}/cart" method="post">
    <input type="hidden" name="productId" value="${product.id}" />
    <label>
        <input type="number" name="quantity">
    </label>
    <input type="submit" value="Add to Cart">
</form>

<form action="" method="post">
    <label>
        <textarea name="comment" placeholder="Enter your comment here">
        </textarea>
    </label>
    <c:set var="productId" value="${product.id}" />
    <br/>
    <input type="submit" value="submit">
</form>
<h3>Comment:</h3>
<c:if test="${sessionScope.id != null}">
    <c:forEach items="${comments}" var="comment">
        <c:out value="${comment.createTime}"/>
        <br/>
        <c:out value="${comment.detail}"/>
        <br/>
    </c:forEach>
</c:if>
<c:if test="${sessionScope.id == null}">
    <p>Please log in to view comments. Go to <a href="${pageContext.request.contextPath}/login">Login</a></p>
</c:if>
<hr>
<br/>
<a href="../products">Back</a>
</body>
</html>

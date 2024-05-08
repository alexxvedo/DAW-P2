<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Carrito de la compra</title>
</head>
<body bgcolor="#FDF5E6">
    <center>
        <h1>Total a Pagar</h1>
        <c:set var="total" value="0" scope="page" />
        <c:forEach items="${sessionScope.carrito.productos}" var="producto">
            <fmt:formatNumber value="${total + (producto.precio * producto.cantidad)}" pattern="#.##"/>
            <c:set var="total" value="${total + (producto.precio * producto.cantidad)}"/>
        </c:forEach>
        <h2>$<c:out value="${total}" /></h2>
        <form method="post" action="finalizarCompra.jsp">
            <input type="submit" value="Confirmar Compra" />
        </form>
        <a href="verCarrito.jsp">Regresar al carrito</a>
    </center>
</body>
</html>

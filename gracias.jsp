<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Compra Completa</title>
</head>
<body bgcolor="#FDF5E6">
    <center>
        <h2>Gracias por su compra!</h2>

        <h3>Datos del pedido</h3>
        <table border="1">
            <tr>
                <th>Id del pedido</th>
                <th>Precio total</th>
            </tr>
            <c:set var="pedido" value="${sessionScope.pedido}" scope="session" />
            <tr>
                <td><c:out value="${pedido.id}" /></td>
                <td><c:out value="${pedido.total}" /></td>
            </tr>
        </table>
        <a href="index.html">Volver a la página principal</a>

    </center>
</body>
</html>

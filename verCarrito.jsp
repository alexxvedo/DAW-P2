<%@ page import="minitienda2.Carrito" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Carrito de Compras</title>
</head>
<body bgcolor="#FDF5E6">
    <center>
        <h1>Carrito de Compras</h1>
        <form action="/minitienda2/actualizarMusica" method="post">
            <table border="1">
                <tr>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Precio Unitario</th>
                    <th>Total</th>
                    <th>Acción</th>
                </tr>
                <c:forEach var="producto" items="${sessionScope.carrito.productos}">
                    <tr>
                        <td><c:out value="${producto.nombre}" /></td>
                        <td><c:out value="${producto.cantidad}" /></td>
                        <td><c:out value="${producto.precio}" /></td>
                        <td><c:out value="${producto.precio * producto.cantidad}" /></td>
                        <td><input type="checkbox" name="productoSeleccionado" value="${producto.nombre}"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" align="right">
                        <input type="submit" name="action" value="Eliminar Seleccionados"/>
                        <input type="submit" name="action" value="Vaciar Carrito"/>
                        <input type="button" name="action" value="Comprar">Comprar</button>
                    </td>
                </tr>
            </table>
        </form>
        <a href="index.html">Continuar comprando</a>
    </center>
</body>
</html>

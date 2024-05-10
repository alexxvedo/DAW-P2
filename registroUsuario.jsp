<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>Registro de Usuario</title></head>
<body bgcolor="#FDF5E6">
    <center>
        <h1>Registrar y Pagar</h1>
        <form method="POST" action="/minitienda3/FrontControllerServlet">
            Correo Electrónico: <input type="email" name="email" required /><br />
            Contraseña: <input type="password" name="password" required /><br />
            Tipo de Tarjeta:
            <select name="tipo_tarjeta" required>
                <option value="">Seleccione una opción</option>
                <option value="Visa">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="American Express">American Express</option>
            </select><br />
            Número de Tarjeta: <input type="text" name="numero_tarjeta" /><br />
            <input type="submit" name="action" value="registrar" />
        </form>
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
    </center>
</body>
</html>

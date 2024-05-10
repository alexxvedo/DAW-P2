<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>Iniciar Sesión</title></head>
<body bgcolor="#FDF5E6">
<center>
        <h1>Iniciar Sesión</h1>
        <form method="POST" action="/minitienda3/FrontControllerServlet">
            Email: <input type="text" name="email" /><br/>
            Contraseña: <input type="password" name="password" /><br/>
            <input type="submit" name="action" value="login" />
        </form>
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
        <p>O</p>
        <a href="registroUsuario.jsp">Registrarse</a>
    </center>
</body>
</html>

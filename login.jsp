<html>
<head><title>Iniciar Sesión</title></head>
<body bgcolor="#FDF5E6">
<center>
        <h1>Iniciar Sesión</h1>
        <form method="POST" action="/minitienda3/ControladorBD">
            Email: <input type="text" name="email" /><br/>
            Contraseña: <input type="password" name="password" /><br/>
            <input type="submit" name="action" value="login" />
        </form>
        <p>O</p>
        <a href="registroUsuario.jsp">Registrarse</a>
    </center>
</body>
</html>

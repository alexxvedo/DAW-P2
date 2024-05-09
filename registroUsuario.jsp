<html>
<head><title>Registro de Usuario</title></head>
<body bgcolor="#FDF5E6">
    <center>
        <h1>Registrar y Pagar</h1>
        <form method="POST" action="/minitienda3/FrontControllerServlet">
            Correo Electrónico: <input type="text" name="email" /><br />
            Contraseña: <input type="password" name="password" /><br />
            Tipo de Tarjeta: <input type="text" name="tipo_tarjeta" /><br />
            Número de Tarjeta: <input type="text" name="numero_tarjeta" /><br />
            <input type="submit" name="action" value="registrar" />
        </form>
    </center>
</body>
</html>

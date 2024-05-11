package minitienda3.modelos;

import java.sql.*;


public class ModeloBD {


    private static final String URL = "jdbc:mysql://localhost:3306/minitienda";
    private static final String USERNAME = "av";
    private static final String PASSWORD = "abcABC123@";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static Usuario registrarUsuario(String email, String password, String tarjetaTipo, String tarjetaNumero) throws SQLException {
        Connection con = getConnection();
        String query = "INSERT INTO usuarios(correo_electronico, contrasena, tipo_tarjeta, numero_tarjeta) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, email);
        pst.setString(2, password);
        pst.setString(3, tarjetaTipo);
        pst.setString(4, tarjetaNumero);
        int result = pst.executeUpdate();

        if (result > 0) {
            pst = con.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setCorreo(email);
                usuario.setContrasena(password);
                usuario.setTarjetaTipo(tarjetaTipo);
                usuario.setTarjetaNumero(tarjetaNumero);
                return usuario;
            }
        }
        return null;
    }

    public static Usuario getUsuarioByEmailAndPassword(String email, String password) throws SQLException {
        Connection con = getConnection();
        String query = "SELECT * FROM usuarios WHERE correo_electronico = ? AND contrasena = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt(1));
            usuario.setCorreo(rs.getString(2));
            usuario.setContrasena(rs.getString(3));
            usuario.setTarjetaTipo(rs.getString(4));
            usuario.setTarjetaNumero(rs.getString(5));
            return usuario;
        }
        return null;
    }

    public static Pedido crearPedido(Integer id_usuario, double importe_final) {
        Connection con = null;
        PreparedStatement pst = null;
        Pedido newPedido = null;
        try {
            con = getConnection();
            String query = "INSERT INTO pedidos(id_usuario, importe_final) VALUES (?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, id_usuario);
            pst.setDouble(2, importe_final);
            int result = pst.executeUpdate();
            if (result > 0) {
                pst = con.prepareStatement("SELECT id, id_usuario, importe_final FROM pedidos WHERE id = LAST_INSERT_ID()");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    newPedido = new Pedido();
                    newPedido.setId(String.valueOf(rs.getInt(1)));
                    newPedido.setId_usuario(String.valueOf(rs.getInt(2)));
                    newPedido.setTotal(rs.getFloat(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) try { pst.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return newPedido;
    }
    
}

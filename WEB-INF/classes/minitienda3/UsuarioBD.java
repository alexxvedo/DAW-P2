package minitienda3;

import java.sql.*;

public class UsuarioBD {

    public static Usuario login(String correo, String contrasena) {
        Connection con = null;
        PreparedStatement pst = null;
        Usuario newUser = null;

        try {
            con = ConexionBD.getConnection();
            String query = "SELECT * FROM usuarios WHERE correo_electronico = ? AND contrasena = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, correo);
            pst.setString(2, contrasena);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                int userId = rs.getInt(1);
                newUser = new Usuario();
                newUser.setId(userId);
                newUser.setCorreo(correo);
                newUser.setContrasena(contrasena);
                newUser.setTarjetaTipo(rs.getString(4));
                newUser.setTarjetaNumero(rs.getString(5));
                System.out.println("Usuario encontrado");
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) try { pst.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return newUser;
    }


    public static Usuario registrar(String correo, String contrasena, String tipo_tarjeta, String numero_tarjeta) {
        Connection con = null;
        PreparedStatement pst = null;
        Usuario newUser = null;

        try {
            con = ConexionBD.getConnection();
            String query = "INSERT INTO usuarios(correo_electronico, contrasena, tipo_tarjeta, numero_tarjeta) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, correo);
            pst.setString(2, contrasena);
            pst.setString(3, tipo_tarjeta);
            pst.setString(4, numero_tarjeta);
            int result = pst.executeUpdate();

            if (result > 0) {
                pst = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    int userId = rs.getInt(1);
                    newUser = new Usuario();
                    newUser.setId(userId);
                    newUser.setCorreo(correo);
                    newUser.setContrasena(contrasena);
                    newUser.setTarjetaTipo(tipo_tarjeta);
                    newUser.setTarjetaNumero(numero_tarjeta);
                    System.out.println("Usuario encontrado");
                } else {
                    System.out.println("Usuario no encontrado");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) try { pst.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return newUser;
    }
}

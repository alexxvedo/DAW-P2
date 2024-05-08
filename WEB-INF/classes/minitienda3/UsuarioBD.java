package minitienda3;

import java.sql.*;

public class UsuarioBD {

    public static boolean login(String correo, String contrasena) {
        Connection con = null;
        PreparedStatement pst = null;
        boolean usuarioEncontrado = false; 

        try {
            con = ConexionBD.getConnection();
            String query = "SELECT * FROM usuarios WHERE correo_electronico = ? AND contrasena = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, correo);
            pst.setString(2, contrasena);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                usuarioEncontrado = true;
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

        return usuarioEncontrado;
    }
}

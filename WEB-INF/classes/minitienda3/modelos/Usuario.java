package minitienda3.modelos;

public class Usuario {
    private Integer id;
    private String correo;
    private String contrasena;
    private String tarjetaTipo;
    private String tarjetaNumero;

    public Usuario() {}

    public Integer getId() {
        return id;
    }
    public String getCorreo() {
        return correo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public String getTarjetaTipo() {
        return tarjetaTipo;
    }
    public String getTarjetaNumero() {
        return tarjetaNumero;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void setTarjetaTipo(String tarjetaTipo) {
        this.tarjetaTipo = tarjetaTipo;
    }
    public void setTarjetaNumero(String tarjetaNumero) {
        this.tarjetaNumero = tarjetaNumero;
    }
}

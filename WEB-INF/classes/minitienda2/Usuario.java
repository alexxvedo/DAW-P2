package minitienda2;

public class Usuario {
    private int id;                // Identificador único para cada usuario
    private String correo;         // Correo electrónico del usuario
    private String contrasena;     // Contraseña del usuario
    private String tarjetaTipo;    // Tipo de tarjeta de crédito (e.g., Visa, MasterCard)
    private String tarjetaNumero;  // Número de la tarjeta de crédito

    // Constructor por defecto
    public Usuario() {
    }


    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTarjetaTipo() {
        return tarjetaTipo;
    }

    public void setTarjetaTipo(String tarjetaTipo) {
        this.tarjetaTipo = tarjetaTipo;
    }

    public String getTarjetaNumero() {
        return tarjetaNumero;
    }

    public void setTarjetaNumero(String tarjetaNumero) {
        this.tarjetaNumero = tarjetaNumero;
    }
}

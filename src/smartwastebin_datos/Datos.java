package smartwastebin_datos;

import java.util.Date;

/**
 * Clase para manejar los datos del formulario
 * @author gomel
 */
public class Datos {
    // Variables del formulario
    private String IdForm;
    private String nombre;
    private String cedula;
    private int edad;
    private String contraseña;
    private Date fechaRegistro;

    // Constructor
    public Datos() {
        this.fechaRegistro = new Date();
    }

    // Getters y Setters
    public String getId() {
        return IdForm;
    }

    public void setId(String id) {
        this.IdForm = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    // Método para generar factura
    public String generarFactura() {
        return String.format(
            "=== FACTURA DE REGISTRO ===\n" +
            "Fecha: %s\n" +
            "UID: %s\n" +
            "Nombre: %s\n" +
            "Cédula: %s\n" +
            "Edad: %d\n" +
            "Contraseña: %s\n" +
            "==========================",
            this.fechaRegistro,
            this.IdForm,
            this.nombre,
            this.cedula,
            this.edad,
            "*******" // No almacenar contraseña real
        );
    }

    // Método para validar datos
    public boolean validarDatos() {
        if (this.nombre == null || this.nombre.trim().isEmpty()) {
            return false;
        }
        if (this.cedula == null || !this.cedula.matches("\\d{6,}")) {
            return false;
        }
        if (this.edad < 1 || this.edad > 120) {
            return false;
        }
        if (this.contraseña == null || this.contraseña.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
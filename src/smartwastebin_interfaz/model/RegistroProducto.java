/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartwastebin_interfaz.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jundy071809
 */
public class RegistroProducto {

    // Atributos
    private String id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private boolean reciclable;
    private String fechaRegistro; // Nuevo atributo para la fecha de registro

    // Lista estática para almacenar todos los productos
    private static ArrayList<RegistroProducto> productos = new ArrayList<>();

    // Constructor vacío
    public RegistroProducto() {
    }

    // Constructor con parámetros modificado para generar ID automáticamente e incluir fecha
    public RegistroProducto(String idUsuario, String nombre, String tipo, String descripcion, boolean reciclable) {
        this.id = generarIdProducto(idUsuario, tipo);
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.reciclable = reciclable;
        this.fechaRegistro = generarFechaActual(); // Asignar la fecha actual
    }

    // Método para generar ID de producto basado en ID de usuario y tipo de residuo
    private String generarIdProducto(String idUsuario, String tipo) {
        // Obtener un timestamp para hacer el ID único
        long timestamp = System.currentTimeMillis();
        // Crear un ID combinando el ID de usuario, tipo de residuo y timestamp
        return idUsuario + "-" + tipo.substring(0, Math.min(3, tipo.length())).toUpperCase() + "-" + timestamp % 10000;
    }
    
    // Método para generar la fecha actual en formato legible
    private String generarFechaActual() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatoFecha.format(new Date());
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isReciclable() {
        return reciclable;
    }

    public void setReciclable(boolean reciclable) {
        this.reciclable = reciclable;
    }

    // Getter y Setter para la fecha de registro
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Métodos para gestionar la lista de productos

    // Agregar un producto a la lista
    public boolean registrarProducto() {
        // Verificar si ya existe un producto con el mismo ID
        for (RegistroProducto p : productos) {
            if (p.getId().equals(this.id)) {
                return false; // Producto ya registrado
            }
        }
        productos.add(this);
        guardarProductosEnArchivo();
        return true;
    }

    // Obtener todos los productos
    public static ArrayList<RegistroProducto> getProductos() {
        cargarProductosDesdeArchivo();
        return productos;
    }

    // Buscar un producto por ID
    public static RegistroProducto buscarProductoPorId(String id) {
        for (RegistroProducto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // Eliminar un producto por ID
    public static boolean eliminarProducto(String id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productos.remove(i);
                guardarProductosEnArchivo();
                return true;
            }
        }
        return false;
    }

    // Actualizar un producto existente
    public static boolean actualizarProducto(String id, String nombre, String tipo, String descripcion,
            boolean reciclable) {
        for (RegistroProducto p : productos) {
            if (p.getId().equals(id)) {
                p.setNombre(nombre);
                p.setTipo(tipo);
                p.setDescripcion(descripcion);
                p.setReciclable(reciclable);
                guardarProductosEnArchivo();
                return true;
            }
        }
        return false;
    }

    // Guardar productos en archivo CSV
    private static void guardarProductosEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("productos.csv"))) {
            writer.println("id,nombre,tipo,descripcion,reciclable,fechaRegistro");
            for (RegistroProducto producto : productos) {
                writer.printf("%s,%s,%s,%s,%b,%s%n",
                        producto.getId(),
                        producto.getNombre(),
                        producto.getTipo(),
                        producto.getDescripcion(),
                        producto.isReciclable(),
                        producto.getFechaRegistro());
            }
            System.out.println("Productos guardados como CSV.");
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
    }

    // Cargar productos desde archivo CSV
    private static void cargarProductosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("productos.csv"))) {
            String line = reader.readLine(); // Leer cabecera
            productos = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) { // Ahora esperamos 6 campos con la fecha
                    RegistroProducto producto = new RegistroProducto(
                            fields[0], // Usamos el ID como está en el archivo
                            fields[1],
                            fields[2],
                            fields[3],
                            Boolean.parseBoolean(fields[4]));
                    producto.setFechaRegistro(fields[5]); // Establecer la fecha desde el archivo
                    productos.add(producto);
                } else if (fields.length >= 5) { // Compatibilidad con registros antiguos sin fecha
                    RegistroProducto producto = new RegistroProducto(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            Boolean.parseBoolean(fields[4]));
                    productos.add(producto);
                }
            }
            System.out.println("Productos cargados desde CSV.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de productos no encontrado, iniciando con lista vacía.");
        } catch (IOException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

    // Método toString para representación en texto
    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", reciclable=" + reciclable +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }
}

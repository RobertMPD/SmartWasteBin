/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartwastebin_interfaz.model;

import java.io.*;
import java.util.ArrayList;

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

    // Lista estática para almacenar todos los productos
    private static ArrayList<RegistroProducto> productos = new ArrayList<>();

    // Constructor vacío
    public RegistroProducto() {
    }

    // Constructor con parámetros modificado para generar ID automáticamente
    public RegistroProducto(String idUsuario, String nombre, String tipo, String descripcion, boolean reciclable) {
        this.id = generarIdProducto(idUsuario, tipo);
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.reciclable = reciclable;
    }

    // Método para generar ID de producto basado en ID de usuario y tipo de residuo
    private String generarIdProducto(String idUsuario, String tipo) {
        // Obtener un timestamp para hacer el ID único
        long timestamp = System.currentTimeMillis();
        // Crear un ID combinando el ID de usuario, tipo de residuo y timestamp
        return idUsuario + "-" + tipo.substring(0, Math.min(3, tipo.length())).toUpperCase() + "-" + timestamp % 10000;
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
            writer.println("id,nombre,tipo,descripcion,reciclable");
            for (RegistroProducto producto : productos) {
                writer.printf("%s,%s,%s,%s,%b%n",
                        producto.getId(),
                        producto.getNombre(),
                        producto.getTipo(),
                        producto.getDescripcion(),
                        producto.isReciclable());
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
                if (fields.length >= 5) {
                    productos.add(new RegistroProducto(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            Boolean.parseBoolean(fields[4])));
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
                '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartwastebin_interfaz.model;

import java.io.*;
/**
 *
 * @author jundy071809
 */
public class GestorProductos {
    public boolean deleteProducto(String idProducto) {
    File archivoOriginal = new File("products.csv");
    File archivoTemporal = new File("products_temp.csv");

    boolean eliminado = false;

    try (
        BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal));
        PrintWriter escritor = new PrintWriter(new FileWriter(archivoTemporal))
    ) {
        String linea;
        while ((linea = lector.readLine()) != null) {
            if (!linea.startsWith(idProducto + ",")) {
                escritor.println(linea);
            } else {
                eliminado = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Reemplazar el archivo original
    if (archivoOriginal.delete()) {
        archivoTemporal.renameTo(archivoOriginal);
    }

    return eliminado;
}

    
}

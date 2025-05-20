/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartwastebin_interfaz.model;

import java.io.*;
import smartwastebin_interfaz.model.User;
import smartwastebin_interfaz.model.RecyclingRecord;
/**
 *
 * @author JAVIER Y KLEVER
 */
public class procesoResiduos {//Inicio class <--

    public boolean registerRecycling(User user, int plasticBags, int paperBags, int organicBags, int gadgetBags) {
        if (user == null) {
            System.out.println("Usuario no encontrado.");
            return false;
        }

//        user.registerRecycling(plasticBags, paperBags, organicBags, gadgetBags); // Registrar el reciclaje
        return true;
    }
    
    //Metodo para calcular el total de puntos
    public int calcTotalPoints(int plasticBags, int paperBags, int organicBags, int gadgetBags) {
        int totalPoints = 0;
        totalPoints += plasticBags * 30; // Plásticos -> 30 punto por bolsa
        totalPoints += paperBags * 10;   // Papeles -> 10 puntos por bolsa
        totalPoints += organicBags * 15; // Orgánicos -> 15 puntos por bolsa
        totalPoints += gadgetBags * 45;  // Gadgets -> 45 puntos por bolsa
        return totalPoints;

    }

    //Metodo para calcular el total de puntos;
    public static double calcTotalMoney(int CantBolsa) {//Inicio metodo 
    // Calcula el dinero basado en CantBolsa
    return CantBolsa * 0.01;
    }//Fin metodo
    
    
    //Nueva Estructura
    public boolean userExists(int userId) {//inicio metodo<--
    // Verificar en la base de datos o sistema si el usuario existe
    // Por ahora, simulamos que siempre existe
    return true; // Cambiar lógica según sea necesario
}//Fin metodo <--
    //fin nueva estructura
    
     /*public boolean registerRecycling(User user, int plasticBags, int paperBags, int organicBags, int gadgetBags) {//Inicio metodo
        if (user == null) {
            System.out.println("Usuario no encontrado.");
            return false;
        }

        user.registerRecycling(plasticBags, paperBags, organicBags, gadgetBags); // Registrar el reciclaje
        return true;
    }//Fin metodo*/
     
     // Método para registrar el reciclaje
    public void registerRecycling(int plasticBags, int paperBags, int organicBags, int gadgetBags) {
        int totalPoints = calcTotalPoints(plasticBags, paperBags, organicBags, gadgetBags);
        double totalMoney = calcTotalMoney(plasticBags + paperBags + organicBags + gadgetBags);

        RecyclingRecord record = new RecyclingRecord(totalPoints, totalMoney);
        //addRecyclingRecord(record); // Añadir al historial

        // Mostrar el resultado del reciclaje
        System.out.println("Reciclaje registrado:");
        System.out.println(record);
    }
     
    //Nueva estructura
    public boolean registerRecyclingSOUT(int userId, int totalPoints, double totalMoney) {//Inicio metodo <--
    try {
        // Guardar los datos en la base de datos o sistema persistente
        System.out.println("User ID: " + userId);
        System.out.println("Points: " + totalPoints);
        System.out.println("Money: " + totalMoney);
        return true; // Retornar verdadero si el registro es exitoso
    } catch (Exception e) {
        e.printStackTrace();
        return false; // Retornar falso si ocurre un error
    }
}//fin metodo <---
//Fin estructura    
    
    //Nueva estructura
    public boolean registerRecycling(String userId, int totalPoints, double totalMoney) {
    try (PrintWriter writer = new PrintWriter(new FileWriter("recycling.csv", true))) {
        // Escribir los datos en el archivo CSV
        writer.println("IdUser,Points,Money");
        writer.printf("%s,%d,%.2f%n", userId, totalPoints, totalMoney);
        System.out.println("Registro de reciclaje guardado con éxito.");
        return true;
    } catch (IOException e) {
        System.err.println("Error al guardar el registro de reciclaje: " + e.getMessage());
        return false;
    }
}//Fin Estructura

    
}//Fin class <--

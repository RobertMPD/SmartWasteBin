/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author JAVIER Y KLEVER
 */
package smartwastebin_interfaz.model;



public class RecyclingRecord {
    
    private int totalPoints;
    private double totalMoney;

    // Constructor
    public RecyclingRecord(int totalPoints, double totalMoney) {
        
        this.totalPoints = totalPoints;
        this.totalMoney = totalMoney;
    }

    // Getters

    public int getTotalPoints() {
        return totalPoints;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    @Override
    public String toString() {
        return "Fecha: " + ", Puntos: " + totalPoints + ", Dinero: $" + totalMoney;
    }
}

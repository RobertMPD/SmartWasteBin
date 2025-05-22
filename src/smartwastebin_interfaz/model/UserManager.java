/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author JAVIER Y KLEVER
 */
package smartwastebin_interfaz.model;

/**
 *
 * @author JAVIER Y KLEVER
 */
//nueva linea v;
import java.io.*;//Habilita la accion ObjectOutputStream
import java.awt.List;
import java.util.*;
import javax.swing.*;

public class UserManager {//Inicio class

    public static ArrayList<User> users = new ArrayList<>();
    
    public UserManager() {
        //nueva linea v
        loadUsersFromCsv(); // Carga los usuarios al inicializar
    }

    //Register
    public boolean register(User user) {//Inicio metodo Register;
        for (User u : users) {
            if (u.getIdUser().equals(user.getIdUser())) {
                return false; // Usuario ya registrado
            }
        }
        UserManager.users.add(user);
        //nueva linea v
        saveUsersAsCsv(); // Guardar cambios
        return true;
    }//fin Metodo Register;

    //Metodo login
    public User login(String idUser, String pass) {//Inicio login

        System.out.println("Id: "+ idUser + " " + pass);
        System.out.println(UserManager.users);
           for (User u : this.getUsers()) { // Supongamos que tienes un método getUsers() en UserManager
                System.out.println("ID: " + u.getIdUser() + ", Nombre: " + u.getName() + ", Apellido: " + u.getLastName());
            }
        
        for (User u : users) {
            if (u.getIdUser().equalsIgnoreCase(idUser) && u.getPass().equalsIgnoreCase(pass)) {
                JOptionPane.showMessageDialog(null, "Successful login - Welcome " + u.getName() + "!");
                return u;
            }
        }
        JOptionPane.showMessageDialog(null, "The user and password is incorrect, try again");

        return null; // Login fallido
    }//Fin Metodo Login

    public ArrayList<User> getUsers() {//Inicio ArrayList
        return users;
    }//fin ArrayList
    
    // Método para guardar usuarios en archivo
    public void saveUsersAsCsv() {
    try (PrintWriter writer = new PrintWriter(new FileWriter("users.csv"))) {
        writer.println("idUser,name,lastName,password");
        for (User user : users) {
            writer.printf("%s,%s,%s,%s%n", 
                user.getIdUser(), 
                user.getName(), 
                user.getLastName(), 
                user.getPass());
        }
        System.out.println("Usuarios guardados como CSV.");
    } catch (IOException e) {
        System.err.println("Error al guardar usuarios: " + e.getMessage());
    }
}//Fin SaveUser;

    //Nueva estructura;
    // Método para cargar usuarios desde archivo
    public void loadUsersFromCsv() {
    try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
        String line = reader.readLine(); // Leer cabecera
        users = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            users.add(new User(fields[0], fields[1], fields[2], fields[3]));
        }
        System.out.println("Usuarios cargados desde CSV.");
    } catch (FileNotFoundException e) {
        System.out.println("Archivo de usuarios no encontrado, iniciando con lista vacía.");
    } catch (IOException e) {
        System.err.println("Error al cargar usuarios: " + e.getMessage());
    }
}
    
    //Metodo para ver usuarios;
    public void listUsers() {//inicio metodo listUsers;
    if (users.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No registered users.");
        return;
    }
    StringBuilder userList = new StringBuilder("Registered users:\n");
    for (User u : users) {
        userList.append("ID: ").append(u.getIdUser())
                .append(", Name: ").append(u.getName())
                .append(", Last Name: ").append(u.getLastName()).append("\n");
    }
    JOptionPane.showMessageDialog(null, userList.toString());
}//Fin metodo listUsers;
    
    //Metodo edita/Actualizar;
    public boolean updateUser(String idUser, String newName, String newLastName, String newPass){//Inicio metodo UpdateUser;
        for(User user: users){
            if(user.getIdUser().equals(idUser)){
            user.setName(newName);
            user.setLastName(newLastName);
            user.setPass(newPass);
            saveUsersAsCsv();//aqui se guardan los cambios en el archivo;
            return true;//El Usuario se encontro y se actualizo;
            }
        }
        return false;//Usuario no encontrado;    
    }//Fin UpdateUser;
    
    //Metodo para eliminar;
    public boolean deleteUser(String idUser) { // Inicio método delete
    for (int i = 0; i < users.size(); i++) {
        User u = users.get(i);
        if (u.getIdUser().equalsIgnoreCase(idUser)) {
            users.remove(i); // Eliminar al usuario encontrado
            saveUsersAsCsv(); // Guardar cambios en el archivo
            JOptionPane.showMessageDialog(null, "User successfully deleted.");
            return true;
        }
    }
    JOptionPane.showMessageDialog(null, "User not found.");
    return false;
} // Fin delete




/**
 * Busca un usuario por su ID
 * @param idUser ID del usuario a buscar
 * @return El usuario encontrado o null si no existe
 */
public User getUserById(String idUser) {
    for (User user : users) {
        if (user.getIdUser().equals(idUser)) {
            return user;
        }
    }
    return null;
}//Fin class
}
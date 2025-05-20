package smartwastebin_interfaz.model;


import java.awt.List;
import java.util.*;
import javax.swing.*;
import java.io.Serializable;//Nueva linea;
/**
 *
 * @author JAVIER Y KLEVER
 */
public class User implements Serializable{//Inicio class
    public int id;
    public String idUser;
    public String name;
    public String LastName;
    public String pass;
//    private List<RecyclingRecord> recyclingRecords;//Lista registros de reciclaje
    
    public User(int id) {
        this.id = id;
    }

    public User(String idUser) {
        this.idUser = idUser;
    }
    

    public User(String idUser, String name, String lastName, String pass) {
        this.idUser = idUser;
        this.name = name;
        this.LastName = lastName;
        this.pass = pass;
       //  this.recyclingRecords = new ArrayList<>(); // Inicializamos la lista de registros
    }

    public int getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    /*public List<RecyclingRecord> getRecyclingRecords() {
        return recyclingRecords;
    }*/


    // Sobrescritura del método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", idUser=").append(idUser);
        sb.append(", name=").append(name);
        sb.append(", lastName=").append(LastName);
        sb.append(", pass=").append(pass);
        sb.append('}');
        return sb.toString();
    }
    
    public void addRecyclingRecord(RecyclingRecord record) {
       // this.recyclingRecords.add(record); // Agregar un nuevo registro al historial
    }
    
    public void validateUser(String idUser, String name, String LastName, String pass){//Inicio metodo Validate
        if (!idUser.isEmpty() && !name.isEmpty() && !LastName.isEmpty() && !pass.isEmpty()) {
            User user = new User(idUser);
            user.setName(name);
            user.setLastName(LastName);
            user.setPass(pass);
            user.setIdUser(idUser);
            System.out.println("user = " + user);
        }else{
            JOptionPane.showMessageDialog(null, "no se puede enviar vacio");
        }
        
    }//Fin Metodo Validate
    
    
    
    //crear usuario y ponerle los parametros, en el boton validar con un if 
    /*public void validateLogin(String name, String pass){//Inicio metodo ValidLogin
    if(!name.isEmpty() && !pass.isEmpty()){
        User user = new User(idUser);
        user.getName();
        user.getPass();
    }
    
  }//Fin metodo validLogin
    */
}//Fin class
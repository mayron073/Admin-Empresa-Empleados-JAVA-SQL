package Pincipal;

import Vistas.*;
import Model.*;

public class Main {

  
    public static void main(String[] args) {
        // Objeto de la clase "Conexion", llamar funcion para validar la conexion con la bd.
        Conexion conexion = new Conexion();
        conexion.getConnection();  
        
        // objeto de la clase "Login", iniciar visualizacion de la vantana de log in.
        Login login = new Login();
        login.setVisible(true);
        
    }
    
}

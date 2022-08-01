
package Model;

import Model.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BoxSucursal {
    Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    ResultSet rs;
    
    public ArrayList getSucursalesList(){
        ArrayList sucursalesList = new ArrayList();
        Sucursal sucursal = null;
        String query = "SELECT idSucursal, nombreSucursal FROM sucursal;";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setSucursalName(rs.getString("nombreSucursal"));
                sucursalesList.add(sucursal);
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        return sucursalesList;
    }
}

package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataDb {
    // Objeto de la clase "Conexion".
    Conexion conexion = new Conexion();
    // Objeto de la clase "Connection", jdbc.
    Connection connection;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    DataJobPosSucursal dataJobPosSucursal;
    
    public ArrayList<DataJobPosSucursal> getJobPos(int idSucursal){
        // Crear una instancia de la clase "ArrayList".
        ArrayList jobPosList = new ArrayList();
        // SQL query Select.
        String queryS = "SELECT idPuesto, nombrePuesto, salario, FK_idSucursal FROM puestoTrabajo INNER JOIN sucursal WHERE puestoTrabajo.FK_idSucursal = sucursal.idSucursal AND sucursal.idSucursal = "+idSucursal+";";
        try {
            // Validar conexion con la db y ejecutar la consulta SQL.
            connection = conexion.getConnection();
            pst = connection.prepareStatement(queryS);
            //pst.setInt(1, idSucursal);
            rs = pst.executeQuery();
            while(rs.next()){
                // Inicializar instancia de la clase "DataJobPosSucursal";
                dataJobPosSucursal = new DataJobPosSucursal();
                // Guardar datos de las columnas resultantes de la consulta SQL.
                int idPuesto = rs.getInt("idPuesto");
                String nombrePuesto = rs.getString("nombrePuesto");
                float salary = rs.getFloat("salario");
                // Asignar valores a los parametros de los metodos set de la instancia "dataJobPosSucursal".
                dataJobPosSucursal.setIdPuestoT(idPuesto);
                dataJobPosSucursal.setNombrePuestoT(nombrePuesto);
                dataJobPosSucursal.setSalary(salary);
                // Asignarle los valores al ArrayList.
                jobPosList.add(dataJobPosSucursal);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println("puestos: " + jobPosList);
        return jobPosList;
    }
    
    public ArrayList<DataJobPosSucursal> getSucursal(){
        // Crear una instancia de la clase "ArrayList".
        ArrayList sucursalList = new ArrayList();
        // SQL query Select.
        String queryS = "SELECT idSucursal, nombreSucursal FROM sucursal;";
        try {
            // Validar conexion con la db y ejecutar la consulta SQL.
            connection = conexion.getConnection();
            pst = connection.prepareStatement(queryS);
            rs = pst.executeQuery();
            while(rs.next()){
                // Inicializar instancia de la clase "DataJobPosSucursal";
                dataJobPosSucursal = new DataJobPosSucursal();
                // Guardar datos de las columnas resultantes de la consulta SQL.
                int idSucursal = rs.getInt("idSucursal");
                String nombreSucursal = rs.getString("nombreSucursal");
                dataJobPosSucursal.setIdSucursal(idSucursal);
                dataJobPosSucursal.setNombreSucursal(nombreSucursal);
                // Asignarle los valores al ArrayList.
                sucursalList.add(dataJobPosSucursal);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println("sucursales: " + sucursalList);
        return sucursalList;
    }
}

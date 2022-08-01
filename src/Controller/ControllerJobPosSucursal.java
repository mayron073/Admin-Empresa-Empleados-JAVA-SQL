
package Controller;

import Model.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import Vistas.AddUser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class ControllerJobPosSucursal implements ActionListener{
    private final AddUser view;
    // Objeto de la clase "Conexion".
    Conexion conexion = new Conexion();
    // Objeto de la clase "Connection", jdbc.
    Connection connection;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    ArrayList<DataJobPosSucursal> listJS;
    DataDb model = new DataDb();

    public ControllerJobPosSucursal(AddUser view) {
        // Inicializar instancia de la vista AddUser.
        this.view = view;
        // LLamar metodo.
        this.getSucursalList();
        // Asignar a la instancia sucursal el item seleccionado del boxSucursales.
        Sucursal sucursal = (Sucursal) view.boxSucursales.getSelectedItem();
        // LLamar metodo con el parametro id del del item seleccionado.
        this.getJobPosList(sucursal.getIdSucursal());
        this.event();      
        
    }
    // Evento de item seleccionado en el bosSucursales.
    public final void event(){
       view.boxSucursales.addActionListener(this);
    }
    
    public final void getSucursalList(){
        // Asiganr a la lista el valor de retorno de la funcion getSucursal().
        listJS = model.getSucursal();
        // Validar si la lista tiene datos.
        if (listJS.size() > 0){
            // recorrer el la lista.
            for (int i = 0; i < listJS.size(); i++){
                // Asignar el idSucursal y el nombreSucurasl a variables locales.
                int idSucursal = listJS.get(i).getIdSucursal();
                String nombreSucursal = listJS.get(i).getNombreSucursal();
                view.boxSucursales.addItem(new Sucursal(idSucursal, nombreSucursal));
            }
        } else {
            JOptionPane.showMessageDialog(null, "no se han creado sucursales");
        }
    }
    
    public final void getJobPosList(int idSucursal){
        // Asiganr a la lista el valor de retorno de la funcion getJobPos() con el parametro id del item seleccionado.
        listJS = model.getJobPos(idSucursal);
        // Validar si la lista tiene datos.
        if (listJS.size() > 0){
            // recorrer el la lista.
            for (int i = 0; i < listJS.size(); i++){
                // Crear defaultComboBoxModel asignandole el model del boxJObPos de la vista AddUser.
                DefaultComboBoxModel dcbModel = (DefaultComboBoxModel)view.boxJobPos.getModel();
                // Crear objeto.
                Object [] jobPos = new Object[2];
                // Asifnar valores a las posiciones del objeto.
                jobPos[0] = listJS.get(i).getIdPuestoT();
                jobPos[1] = listJS.get(i).getNombrePuestoT();
                dcbModel.addElement(jobPos[1]);
                view.boxJobPos.setModel(dcbModel);
                
            }
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener el codigo del evento.
        Object event = e.getSource();
        // Validarr si el evento fue en boxSucursales de la vista AddUser.
        if (event.equals(view.boxSucursales)){
            //eliminar los items del boxJobPos.
            view.boxJobPos.removeAllItems();
            // Asignar a la instancia sucursal el item seleccionado del boxSucursales.
            Sucursal sucursal = (Sucursal)view.boxSucursales.getSelectedItem();
            // LLamar metodo con el parametro id del del item seleccionado.
            this.getJobPosList(sucursal.getIdSucursal());
        }
    }
}

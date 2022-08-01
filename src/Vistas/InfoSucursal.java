
package Vistas;

import Controller.*;
import Model.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class InfoSucursal extends javax.swing.JDialog {
    // Objeto de la clase "Conexion".
    Conexion conexion = new Conexion();
    // Objeto de la clase "Connection", jdbc.
    Connection connection;
    Statement st;
    ResultSet rs;
    ComboBoxModel enumDepartament, enumZone, enumTypeStreet;

    
    public InfoSucursal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        enumDepartament = new DefaultComboBoxModel(EnumDepartament.values());
        enumZone = new DefaultComboBoxModel(EnumZone.values());
        enumTypeStreet = new DefaultComboBoxModel(EnumTypeStreet.values());
        initComponents();
        this.setLocationRelativeTo(this);
    }
    
    public void reciveSucursalData(int idSucursal){
        String query = "SELECT nombreSucursal, nombreDepartamento, zona, tipoCalle, numero1, numero2,numero3 FROM `sucursal` INNER JOIN `direccion` WHERE FK_idDireccion = idDireccion AND idSucursal = "+idSucursal+";";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                txtSucursal.setText(rs.getString("nombreSucursal"));
                txtNum1.setText(rs.getString("numero1"));
                txtNum2.setText(rs.getString("numero2"));
                txtNum3.setText(rs.getString("numero3"));
                enumDepartament.setSelectedItem(rs.getString("nombreDepartamento"));
                enumZone.setSelectedItem(rs.getString("zona"));
                enumTypeStreet.setSelectedItem(rs.getString("tipoCalle"));
            }
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void updateSucursal(){
        String sucursalName = txtSucursal.getText();
        String queryIdSucursal = "SELECT idSucursal, idDireccion FROM sucursal INNER JOIN direccion WHERE FK_idDireccion = idDireccion AND nombreSucursal = '"+sucursalName+"'";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryIdSucursal);
            while(rs.next()){
                int idSucursal = rs.getInt("idSucursal");
                int idDireccion = rs.getInt("idDireccion");
                String sucursal = txtNewSucursal.getText();
                if (sucursal.isEmpty()){
                    sucursal = txtSucursal.getText();
                } else {
                    //sucursal = sucursal;
                    String queryUpdateS = "UPDATE `sucursal` SET `nombreSucursal`= '"+sucursal+"' WHERE idSucursal = "+idSucursal+";";
                    try {
                        st.executeUpdate(queryUpdateS);
                    }catch(SQLException e){
                        System.out.println(e);
                    }
                }
                String numer1 = txtNum1.getText();
                String numer2 = txtNum2.getText();
                String numer3 = txtNum3.getText();
                String dep = boxDep.getSelectedItem().toString();
                String zone = boxZone.getSelectedItem().toString();
                String street = boxStreet.getSelectedItem().toString();
                String queryUpdate = "UPDATE `direccion` SET `zona`= '"+zone+"',`tipoCalle`='"+street+"',`numero1`='"+numer1+"',`numero2`='"+numer2+"',`numero3`='"+numer3+"',`nombreDepartamento`='"+dep+"' WHERE idDireccion = "+idDireccion+";";
                // Validar datos ingresados.
                if (dep != "SeleccionaOpcion" && street != "SeleccionaOpcion" && zone != "SeleccionaOpcion" && !numer1.isEmpty() && !numer2.isEmpty() && !numer3.isEmpty()){
                        try {
                            st.executeUpdate(queryUpdate);
                            this.dispose();
                        }catch(SQLException e){
                            System.out.println(e);
                        }                
                } else {
                    JOptionPane.showMessageDialog(this, "Faltan campos por completar","", JOptionPane.WARNING_MESSAGE);
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void deleteSucursal(){
        String sucursalName = txtSucursal.getText();
        String queryIdS = "SELECT idSucursal, idDireccion FROM sucursal INNER JOIN direccion WHERE FK_idDireccion = idDireccion AND nombreSucursal = '"+sucursalName+"'";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryIdS);
            while(rs.next()){
                int idSucursal = rs.getInt("idSucursal");
                int idDireccion = rs.getInt("idDireccion");
                String queryDeleteS = "DELETE FROM sucursal WHERE idSucursal = "+idSucursal+";";
                try {
                    st.executeUpdate(queryDeleteS);
                    String queryDeleteD = "DELETE FROM direccion WHERE idDIreccion = "+idDireccion+";";
                    try {
                        st.executeUpdate(queryDeleteD);
                    }catch(SQLException e){
                        System.out.println(e);
                    }
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "sucursal eliminada con exito");
                }catch(SQLException e){
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, "no se ha podido eliminar la informacion","",JOptionPane.WARNING_MESSAGE);
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSucursal = new javax.swing.JTextField();
        boxDep = new javax.swing.JComboBox<>();
        boxZone = new javax.swing.JComboBox<>();
        boxStreet = new javax.swing.JComboBox<>();
        txtNum1 = new javax.swing.JTextField();
        txtNum2 = new javax.swing.JTextField();
        txtNum3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnEditS = new javax.swing.JButton();
        btnDeleteS = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtNewSucursal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel1.setText("Sucursal");

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel2.setText("Departamento");

        jLabel3.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel3.setText("Zona");

        jLabel4.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel4.setText("calle");

        jLabel5.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel5.setText("Numeros");

        txtSucursal.setEditable(false);

        boxDep.setModel(enumDepartament);

        boxZone.setModel(enumZone);

        boxStreet.setModel(enumTypeStreet);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Actualizar informacion");

        btnEditS.setText("Editar");
        btnEditS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSActionPerformed(evt);
            }
        });

        btnDeleteS.setText("Eliminar");
        btnDeleteS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSActionPerformed(evt);
            }
        });

        btnCancel.setText("Volver");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel7.setText("Nueva sucursal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSucursal)
                            .addComponent(boxDep, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(boxZone, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNewSucursal)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnEditS)
                        .addGap(28, 28, 28)
                        .addComponent(btnDeleteS)
                        .addGap(26, 26, 26)
                        .addComponent(btnCancel)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(138, 138, 138))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNewSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(boxDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(boxZone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(boxStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSActionPerformed
        this.updateSucursal();
    }//GEN-LAST:event_btnEditSActionPerformed

    private void btnDeleteSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSActionPerformed
        this.deleteSucursal();
    }//GEN-LAST:event_btnDeleteSActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InfoSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoSucursal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InfoSucursal dialog = new InfoSucursal(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxDep;
    private javax.swing.JComboBox<String> boxStreet;
    private javax.swing.JComboBox<String> boxZone;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDeleteS;
    private javax.swing.JButton btnEditS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNewSucursal;
    private javax.swing.JTextField txtNum1;
    private javax.swing.JTextField txtNum2;
    private javax.swing.JTextField txtNum3;
    private javax.swing.JTextField txtSucursal;
    // End of variables declaration//GEN-END:variables
}

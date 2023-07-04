package com.proyecto.vista;

//import com.mycompany.ilib.DAOUsersImpl;
//import com.mycompany.interfaces.DAOUsers;
import com.proyecto.controladores.ControladorDetalleBancos;
import com.proyecto.controladores.ControladorRegistroFactura;
import com.proyecto.controladores.ControladorRegistroProveedor;
import com.proyecto.controladores.ControladorRegistroUsuario;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Usuario;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class VentanaDetalleBancos extends javax.swing.JPanel {

    private ControladorDetalleBancos controladorDetalleBancos;
    private Usuario currentUser;


    public VentanaDetalleBancos(Usuario currentUser) throws Exception {
        this.controladorDetalleBancos = new ControladorDetalleBancos();
        this.currentUser = currentUser;
        initComponents();
        InitStyles();
    }


    private void InitStyles() throws Exception {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);

        //agrega todos los proveedores al combo box de la ventana
        controladorDetalleBancos.llenarComboBoxCuentasBancarias(bancoCBox);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        regresarVentanaBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        bancoCBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        saldoActualText = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        saldoPrevioText = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        title.setText("DETALLE DE BANCOS");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(200, 10));

        regresarVentanaBtn.setBackground(new java.awt.Color(255, 0, 51));
        regresarVentanaBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        regresarVentanaBtn.setForeground(new java.awt.Color(255, 255, 255));
        regresarVentanaBtn.setText("REGRESAR");
        regresarVentanaBtn.setBorderPainted(false);
        regresarVentanaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        regresarVentanaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarVentanaBtnActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Banco:");

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setPreferredSize(new java.awt.Dimension(200, 10));

        bancoCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bancoCBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Saldo Actual:");

        saldoActualText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saldoActualText.setText("(campo saldo)");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Saldo Previo a ultima operación:");

        saldoPrevioText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saldoPrevioText.setText("(campo previo saldo)");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(bancoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(saldoActualText)
                    .addComponent(jLabel20)
                    .addComponent(saldoPrevioText)
                    .addComponent(regresarVentanaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(362, 362, 362))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(373, 373, 373)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                .addGap(81, 81, 81))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(bancoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saldoActualText)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saldoPrevioText)
                                .addGap(76, 76, 76)
                                .addComponent(regresarVentanaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void regresarVentanaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarVentanaBtnActionPerformed
        VentanaDashboard.ShowJPanelWindows(new VentanaGestorEstadoCuentas(currentUser));
    }//GEN-LAST:event_regresarVentanaBtnActionPerformed

    private void bancoCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bancoCBoxActionPerformed
        // TODO add your handling code here:
       String cuentaBancariaSelecionada = (String) bancoCBox.getSelectedItem();
       controladorDetalleBancos.mostrarDatosBanco(cuentaBancariaSelecionada, saldoActualText, saldoPrevioText);
    }//GEN-LAST:event_bancoCBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bancoCBox;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton regresarVentanaBtn;
    private javax.swing.JLabel saldoActualText;
    private javax.swing.JLabel saldoPrevioText;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.proyecto.vista;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import com.proyecto.controladores.ControladorLogin;
import com.proyecto.controladores.ControladorRecuperarContraseña;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author skiva
 */
public class VentanaRecuperarContraseña extends javax.swing.JFrame {
    private ControladorRecuperarContraseña controladorRecuperarContraseña;
    private int intentosSesion;
    /**
     * Creates new form VentanaLogin
     */
    public VentanaRecuperarContraseña() {
        this.controladorRecuperarContraseña = new ControladorRecuperarContraseña();
        initComponents();
        InitStyles();
        this.setLocationRelativeTo(this);
        this.intentosSesion = 0;
    }

    private void InitStyles() {
        titleTxt.putClientProperty("FlatLaf.style", "font: bold $h1.regular.font");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleTxt = new javax.swing.JLabel();
        dniField = new javax.swing.JTextField();
        motrarDatosBtn = new javax.swing.JButton();
        textLogin1 = new javax.swing.JLabel();
        usernameTxt = new javax.swing.JLabel();
        passwrdTxt = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        regresarVentanaBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));

        titleTxt.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        titleTxt.setForeground(new java.awt.Color(255, 255, 255));
        titleTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTxt.setText("Recuperar \nContraseña");
        titleTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        dniField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dniField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniFieldActionPerformed(evt);
            }
        });

        motrarDatosBtn.setBackground(new java.awt.Color(255, 51, 51));
        motrarDatosBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        motrarDatosBtn.setForeground(new java.awt.Color(255, 255, 255));
        motrarDatosBtn.setText("MOSTRAR DATOS");
        motrarDatosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motrarDatosBtnActionPerformed(evt);
            }
        });

        textLogin1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        textLogin1.setForeground(new java.awt.Color(255, 255, 255));
        textLogin1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textLogin1.setText("Ingrese su DNI");
        textLogin1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usernameTxt.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        usernameTxt.setForeground(new java.awt.Color(255, 255, 255));
        usernameTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usernameTxt.setText("Username:");
        usernameTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        passwrdTxt.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        passwrdTxt.setForeground(new java.awt.Color(255, 255, 255));
        passwrdTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        passwrdTxt.setText("Password:");
        passwrdTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usernameLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usernameLabel.setText("usrlb");
        usernameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        passwordLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        passwordLabel.setText("passlb");
        passwordLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        regresarVentanaBtn.setText("Regresar Ventana");
        regresarVentanaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarVentanaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textLogin1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTxt)
                            .addComponent(passwrdTxt))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel)
                            .addComponent(usernameLabel)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(regresarVentanaBtn)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dniField)
                            .addComponent(motrarDatosBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(titleTxt)
                .addGap(40, 40, 40)
                .addComponent(textLogin1)
                .addGap(18, 18, 18)
                .addComponent(dniField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTxt)
                    .addComponent(usernameLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwrdTxt)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(regresarVentanaBtn)
                .addGap(18, 18, 18)
                .addComponent(motrarDatosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void motrarDatosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motrarDatosBtnActionPerformed
        // TODO add your handling code here:
        String dni = dniField.getText();
        
        
        // Validaciones para los campos
        if (dni.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            dniField.requestFocus();
            //return;
        } else {
            try {
                controladorRecuperarContraseña.recuperarContraseña(dni, usernameLabel, passwordLabel);
            } catch (Exception ex) {
                intentosSesion++;
                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con las credenciales proporcionadas");
                if(intentosSesion == 3) {
                    JOptionPane.showMessageDialog(null, "Se ha superado el número de intentos posibles" 
                            + "\n" + "\nVuelve a intentar más tarde");
                    System.exit(0);
                }
                //Logger.getLogger(VentanaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_motrarDatosBtnActionPerformed

    private void dniFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniFieldActionPerformed

    private void regresarVentanaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarVentanaBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        controladorRecuperarContraseña.regresarVentanaLogin();
    }//GEN-LAST:event_regresarVentanaBtnActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaRecuperarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRecuperarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRecuperarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRecuperarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        FlatMaterialLighterIJTheme.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRecuperarContraseña().setVisible(true);
            }
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dniField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton motrarDatosBtn;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwrdTxt;
    private javax.swing.JButton regresarVentanaBtn;
    private javax.swing.JLabel textLogin1;
    private javax.swing.JLabel titleTxt;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameTxt;
    // End of variables declaration//GEN-END:variables
}

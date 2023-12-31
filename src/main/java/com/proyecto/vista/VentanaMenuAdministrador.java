/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.proyecto.vista;

import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.reiniciarPrograma;

/**
 *
 * @author skiva
 */
public class VentanaMenuAdministrador extends javax.swing.JPanel {
    
    private VentanaDashboard ventanaDashboard;
    private Usuario currentUser;

    /**
     * Creates new form VentanaMenuAdministrador
     */
    public VentanaMenuAdministrador(Usuario currentUser) {
        this.currentUser = currentUser;
        initComponents();
    }

    public void cerrarVentanaDashboard() {
        if (ventanaDashboard != null) {
            ventanaDashboard.cerrarVentana();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principalBtn = new javax.swing.JButton();
        cerrarSesionBtn = new javax.swing.JButton();
        gentionarUsuariosBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 0, 51));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        principalBtn.setBackground(new java.awt.Color(255, 0, 51));
        principalBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        principalBtn.setForeground(new java.awt.Color(255, 255, 255));
        principalBtn.setText("Principal");
        principalBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        principalBtn.setBorderPainted(false);
        principalBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        principalBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        principalBtn.setIconTextGap(13);
        principalBtn.setInheritsPopupMenu(true);
        principalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                principalBtnActionPerformed(evt);
            }
        });
        add(principalBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 60));

        cerrarSesionBtn.setBackground(new java.awt.Color(255, 0, 51));
        cerrarSesionBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cerrarSesionBtn.setForeground(new java.awt.Color(255, 255, 255));
        cerrarSesionBtn.setText("Cerrar Sesion");
        cerrarSesionBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        cerrarSesionBtn.setBorderPainted(false);
        cerrarSesionBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cerrarSesionBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cerrarSesionBtn.setIconTextGap(13);
        cerrarSesionBtn.setInheritsPopupMenu(true);
        cerrarSesionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionBtnActionPerformed(evt);
            }
        });
        add(cerrarSesionBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 270, 60));

        gentionarUsuariosBtn.setBackground(new java.awt.Color(255, 0, 51));
        gentionarUsuariosBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gentionarUsuariosBtn.setForeground(new java.awt.Color(255, 255, 255));
        gentionarUsuariosBtn.setText("Gestionar Usuarios");
        gentionarUsuariosBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        gentionarUsuariosBtn.setBorderPainted(false);
        gentionarUsuariosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gentionarUsuariosBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gentionarUsuariosBtn.setIconTextGap(13);
        gentionarUsuariosBtn.setInheritsPopupMenu(true);
        gentionarUsuariosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gentionarUsuariosBtnActionPerformed(evt);
            }
        });
        add(gentionarUsuariosBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 270, 60));
    }// </editor-fold>//GEN-END:initComponents

    private void principalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_principalBtnActionPerformed
        VentanaDashboard.ShowJPanelWindows(new VentanaPrincipal(currentUser));
    }//GEN-LAST:event_principalBtnActionPerformed

    private void cerrarSesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionBtnActionPerformed
        // TODO add your handling code here:
        //System.exit(0);
        
        reiniciarPrograma();
    }//GEN-LAST:event_cerrarSesionBtnActionPerformed

    private void gentionarUsuariosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gentionarUsuariosBtnActionPerformed
        // TODO add your handling code here:
        VentanaDashboard.ShowJPanelWindows(new VentanaGestorUsuarios(currentUser));
    }//GEN-LAST:event_gentionarUsuariosBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cerrarSesionBtn;
    private javax.swing.JButton gentionarUsuariosBtn;
    private javax.swing.JButton principalBtn;
    // End of variables declaration//GEN-END:variables
}

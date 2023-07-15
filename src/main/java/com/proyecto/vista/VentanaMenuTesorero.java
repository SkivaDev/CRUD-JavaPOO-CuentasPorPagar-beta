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
public class VentanaMenuTesorero extends javax.swing.JPanel {
    
    private VentanaDashboard ventanaDashboard;
    private Usuario currentUser;

    /**
     * Creates new form VentanaMenuAdministrador
     */
    public VentanaMenuTesorero(Usuario currentUser) {
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
        EstadoCuentasBtn = new javax.swing.JButton();
        pagarFacturasBtn = new javax.swing.JButton();
        expedienteProveedoresBtn = new javax.swing.JButton();
        movimientosBancariosBtn = new javax.swing.JButton();
        historialPagosFacturasBtn = new javax.swing.JButton();

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
        add(cerrarSesionBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 270, 60));

        EstadoCuentasBtn.setBackground(new java.awt.Color(255, 0, 51));
        EstadoCuentasBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EstadoCuentasBtn.setForeground(new java.awt.Color(255, 255, 255));
        EstadoCuentasBtn.setText("Estado de cuentas");
        EstadoCuentasBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        EstadoCuentasBtn.setBorderPainted(false);
        EstadoCuentasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        EstadoCuentasBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EstadoCuentasBtn.setIconTextGap(13);
        EstadoCuentasBtn.setInheritsPopupMenu(true);
        EstadoCuentasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoCuentasBtnActionPerformed(evt);
            }
        });
        add(EstadoCuentasBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 270, 60));

        pagarFacturasBtn.setBackground(new java.awt.Color(255, 0, 51));
        pagarFacturasBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pagarFacturasBtn.setForeground(new java.awt.Color(255, 255, 255));
        pagarFacturasBtn.setText("Pagar Facturas");
        pagarFacturasBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        pagarFacturasBtn.setBorderPainted(false);
        pagarFacturasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pagarFacturasBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pagarFacturasBtn.setIconTextGap(13);
        pagarFacturasBtn.setInheritsPopupMenu(true);
        pagarFacturasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarFacturasBtnActionPerformed(evt);
            }
        });
        add(pagarFacturasBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 270, 60));

        expedienteProveedoresBtn.setBackground(new java.awt.Color(255, 0, 51));
        expedienteProveedoresBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        expedienteProveedoresBtn.setForeground(new java.awt.Color(255, 255, 255));
        expedienteProveedoresBtn.setText("Expediente Proveedores");
        expedienteProveedoresBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        expedienteProveedoresBtn.setBorderPainted(false);
        expedienteProveedoresBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        expedienteProveedoresBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        expedienteProveedoresBtn.setIconTextGap(13);
        expedienteProveedoresBtn.setInheritsPopupMenu(true);
        expedienteProveedoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expedienteProveedoresBtnActionPerformed(evt);
            }
        });
        add(expedienteProveedoresBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 270, 60));

        movimientosBancariosBtn.setBackground(new java.awt.Color(255, 0, 51));
        movimientosBancariosBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        movimientosBancariosBtn.setForeground(new java.awt.Color(255, 255, 255));
        movimientosBancariosBtn.setText("Historial Movimientos Bancarios");
        movimientosBancariosBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        movimientosBancariosBtn.setBorderPainted(false);
        movimientosBancariosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        movimientosBancariosBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        movimientosBancariosBtn.setIconTextGap(13);
        movimientosBancariosBtn.setInheritsPopupMenu(true);
        movimientosBancariosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movimientosBancariosBtnActionPerformed(evt);
            }
        });
        add(movimientosBancariosBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 270, 60));

        historialPagosFacturasBtn.setBackground(new java.awt.Color(255, 0, 51));
        historialPagosFacturasBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        historialPagosFacturasBtn.setForeground(new java.awt.Color(255, 255, 255));
        historialPagosFacturasBtn.setText("Historial Pagos de Facturas");
        historialPagosFacturasBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
        historialPagosFacturasBtn.setBorderPainted(false);
        historialPagosFacturasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        historialPagosFacturasBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        historialPagosFacturasBtn.setIconTextGap(13);
        historialPagosFacturasBtn.setInheritsPopupMenu(true);
        historialPagosFacturasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialPagosFacturasBtnActionPerformed(evt);
            }
        });
        add(historialPagosFacturasBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 270, 60));
    }// </editor-fold>//GEN-END:initComponents

    private void principalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_principalBtnActionPerformed
        VentanaDashboard.ShowJPanelWindows(new VentanaPrincipal(currentUser));
    }//GEN-LAST:event_principalBtnActionPerformed

    private void cerrarSesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionBtnActionPerformed
        // TODO add your handling code here:
        //System.exit(0);
        
        reiniciarPrograma();
    }//GEN-LAST:event_cerrarSesionBtnActionPerformed

    private void EstadoCuentasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoCuentasBtnActionPerformed
        // TODO add your handling code here:
        VentanaDashboard.ShowJPanelWindows(new VentanaGestorEstadoCuentas(currentUser));
    }//GEN-LAST:event_EstadoCuentasBtnActionPerformed

    private void pagarFacturasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarFacturasBtnActionPerformed
        // TODO add your handling code here:
        VentanaDashboard.ShowJPanelWindows(new VentanaGestorPagarFacturas(currentUser));
    }//GEN-LAST:event_pagarFacturasBtnActionPerformed

    private void expedienteProveedoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expedienteProveedoresBtnActionPerformed
        // TODO add your handling code here:
        VentanaDashboard.ShowJPanelWindows(new VentanaGestorExpedienteProveedores(currentUser));
    }//GEN-LAST:event_expedienteProveedoresBtnActionPerformed

    private void movimientosBancariosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movimientosBancariosBtnActionPerformed
        // TODO add your handling code here:
        VentanaDashboard.ShowJPanelWindows(new VentanaGestorMovimientosBancarios(currentUser));
    }//GEN-LAST:event_movimientosBancariosBtnActionPerformed

    private void historialPagosFacturasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialPagosFacturasBtnActionPerformed
        // TODO add your handling code here:
        VentanaDashboard.ShowJPanelWindows(new VentanaGestorPagosFacturas(currentUser));
    }//GEN-LAST:event_historialPagosFacturasBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EstadoCuentasBtn;
    private javax.swing.JButton cerrarSesionBtn;
    private javax.swing.JButton expedienteProveedoresBtn;
    private javax.swing.JButton historialPagosFacturasBtn;
    private javax.swing.JButton movimientosBancariosBtn;
    private javax.swing.JButton pagarFacturasBtn;
    private javax.swing.JButton principalBtn;
    // End of variables declaration//GEN-END:variables
}

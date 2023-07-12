package com.proyecto.vista;

//import com.mycompany.ilib.DAOUsersImpl;
//import com.proyecto.vista.VentanaDashboard;
//import com.mycompany.interfaces.DAOUsers;
import com.proyecto.controladores.ControladorGestorProveedores;
import com.proyecto.controladores.ControladorGestorSolicitudesPago;
import com.proyecto.controladores.ControladorGestorUsuarios;
import com.proyecto.entidades.Usuario;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class VentanaGestorSolicitudesPago extends javax.swing.JPanel {

    private Usuario currentUser;
    private ControladorGestorSolicitudesPago ControladorGestorSolicitudesPago;
    private DefaultTableModel modeloTabla;

    public VentanaGestorSolicitudesPago(Usuario currentUser) {
        this.ControladorGestorSolicitudesPago = new ControladorGestorSolicitudesPago();
        this.currentUser = currentUser;
        this.modeloTabla = new DefaultTableModel();

        initComponents();
        InitStyles();
        LoadRequests();
    }

    private void InitStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        userSearchField.putClientProperty("JTextField.placeholderText", "Ingrese el nombre de usuario a buscar.");
    }

    private void LoadRequests() {
        
        // Limpiar el modelo de la tabla
        modeloTabla.setRowCount(0);
        jTable1.setModel(modeloTabla);

        modeloTabla = ControladorGestorSolicitudesPago.listarSolicitudes(jTable1);
        jTable1.setModel(modeloTabla);
        

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
        userSearchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        aprobarSolicitudBtn = new javax.swing.JButton();
        desaprobarSolicitudBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        title.setText("SOLICITUDES DE PAGO");

        searchButton.setBackground(new java.awt.Color(255, 0, 51));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Buscar");
        searchButton.setBorderPainted(false);
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido P.", "Apellido M.", "Domicilio", "Teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        aprobarSolicitudBtn.setBackground(new java.awt.Color(255, 0, 51));
        aprobarSolicitudBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        aprobarSolicitudBtn.setForeground(new java.awt.Color(255, 255, 255));
        aprobarSolicitudBtn.setText("Aprobar");
        aprobarSolicitudBtn.setBorderPainted(false);
        aprobarSolicitudBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        aprobarSolicitudBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprobarSolicitudBtnActionPerformed(evt);
            }
        });

        desaprobarSolicitudBtn.setBackground(new java.awt.Color(255, 0, 51));
        desaprobarSolicitudBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        desaprobarSolicitudBtn.setForeground(new java.awt.Color(255, 255, 255));
        desaprobarSolicitudBtn.setText("Desaprobar");
        desaprobarSolicitudBtn.setBorderPainted(false);
        desaprobarSolicitudBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        desaprobarSolicitudBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desaprobarSolicitudBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addGap(699, 699, 699))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(aprobarSolicitudBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(desaprobarSolicitudBtn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(userSearchField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)))
                        .addGap(50, 50, 50))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aprobarSolicitudBtn)
                    .addComponent(desaprobarSolicitudBtn))
                .addGap(25, 25, 25))
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

    }//GEN-LAST:event_jTable1MousePressed

    private void aprobarSolicitudBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprobarSolicitudBtnActionPerformed
        
       // controladorGestorProveedores.editarProveedores(jTable1);
        

    }//GEN-LAST:event_aprobarSolicitudBtnActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        
       // modeloTabla = controladorGestorProveedores.buscarProveedores(jTable1, userSearchField.getText());
       // jTable1.setModel(modeloTabla);


    }//GEN-LAST:event_searchButtonActionPerformed

    private void desaprobarSolicitudBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desaprobarSolicitudBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_desaprobarSolicitudBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aprobarSolicitudBtn;
    private javax.swing.JPanel bg;
    private javax.swing.JButton desaprobarSolicitudBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel title;
    private javax.swing.JTextField userSearchField;
    // End of variables declaration//GEN-END:variables
}
package com.proyecto.vista;

//import com.mycompany.ilib.DAOUsersImpl;
//import com.mycompany.interfaces.DAOUsers;
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

public class VentanaRegistroExpedienteProveedor extends javax.swing.JPanel {

    private ControladorRegistroFactura controladorRegistroFactura;
    boolean isEdition = false;
    boolean isProducEdition = false;
    private Factura invoiceEdition;
    private List<Producto> productosTemporales;
    private DefaultTableModel modeloTabla;

    private int currentProductId;
    private int currentFacturaId;

    public VentanaRegistroExpedienteProveedor() throws Exception {
        this.controladorRegistroFactura = new ControladorRegistroFactura();
        this.productosTemporales = new ArrayList<>();
        this.modeloTabla = new DefaultTableModel();

        initComponents();
        InitStyles();

        currentFacturaId = 0; // es el id que se pone en 0 porque significa que es una nueva factura para agregar
        currentProductId = 0; // es el id que se utiliza al momento de agregar productos o editarlos
        LoadProducts();
    }

    public VentanaRegistroExpedienteProveedor(Factura invoice) throws Exception {
        this.controladorRegistroFactura = new ControladorRegistroFactura();
        this.productosTemporales = new ArrayList<>();
        this.modeloTabla = new DefaultTableModel();

        initComponents();
        isEdition = true;
        invoiceEdition = invoice;
        InitStyles();

        currentFacturaId = invoice.getIdFactura(); // es el id que se pone su respectiva idFactura registrada al ventanaGestorFactura envia.
        currentProductId = 0;
        LoadProducts();
    }

    private void InitStyles() throws Exception {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);

        //agrega todos los proveedores al combo box de la ventana
        controladorRegistroFactura.llenarComboBoxProveedores(proveedorCBox);
        //

        //El monto total, monto pagado, y monto pendiente se ponen en 0 porque pordefecto es para registrar nuevas facturas
        montoTotalLabel.setText("0");
        montoPagadoLabel.setText("0");
        montoPendienteLabel.setText("0");

        //
        //nameField.putClientProperty("JTextField.placeholderText", "Ingrese el nombre del proveedor.");
        //addressField.putClientProperty("JTextField.placeholderText", "Ingrese la dirección del proveedor.");
        //phoneField.putClientProperty("JTextField.placeholderText", "Ingrese teléfono del proveedor.");
        // creditLineField.putClientProperty("JTextField.placeholderText", "Ingrese la linea de credito del proveedor.");
        if (isEdition) {
            title.setText("Editar Factura");
            registrarFacturaBtn.setText("Guardar");
            controladorRegistroFactura.agregarProductosArrayProductos(productosTemporales, invoiceEdition.getIdFactura());

            if (invoiceEdition != null) {
                String proveedorDeFactura = controladorRegistroFactura.buscarNombreProveedorPorFactura(invoiceEdition.getIdFactura());
                proveedorCBox.setSelectedItem(proveedorDeFactura);

                // Crea el formato deseado para la fecha
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                // Convierte la fecha a String
                String fechaRegistroString = formato.format(invoiceEdition.getFechaRegistro());
                fechaRegistroField.setText(fechaRegistroString);

                String fechaVencimientoString = formato.format(invoiceEdition.getFechaRegistro());
                fechaVencimientoField.setText(fechaVencimientoString);

                descripcionField.setText(invoiceEdition.getDescripcion());

                montoTotalLabel.setText(Double.toString(invoiceEdition.getMontoTotal()));
                montoPagadoLabel.setText(Double.toString(invoiceEdition.getMontoPagado()));
                montoPendienteLabel.setText(Double.toString(invoiceEdition.getMontoPendiente()));

            }
        }
    }

    private void LoadProducts() {
        
        // Limpiar el modelo de la tabla
        modeloTabla.setRowCount(0);

        // Limpia la tabla antes de asignar el nuevo modelo
        productosTable.setModel(new DefaultTableModel());

        // Agrega el nuevo modelo al controlador y obtén el modelo actualizado
        modeloTabla = controladorRegistroFactura.agregarProductosTabla(productosTable, productosTemporales);

        // Asigna el nuevo modelo a la tabla
        productosTable.setModel(modeloTabla);
        
    }

    public void limpiarCamposProducto() {
        nombreProdField.setText("");
        descripcionProdField.setText("");
        cantidadProdField.setText("");
        precioUniProdField.setText("");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        productosTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nombreProdField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        descripcionProdField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cantidadProdField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        precioUniProdField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        registrarFacturaBtn1 = new javax.swing.JButton();
        registrarFacturaBtn2 = new javax.swing.JButton();
        registrarFacturaBtn3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        title.setText("DETALLES DE PROVEEDOR");

        productosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(productosTable);

        jLabel10.setText("PROVEEDOR");

        jLabel11.setText("Nombre:");

        jLabel12.setText("Dirección:");

        jLabel13.setText("Telefono:");

        jLabel14.setText("Linea de credito:");

        registrarFacturaBtn1.setBackground(new java.awt.Color(255, 0, 51));
        registrarFacturaBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registrarFacturaBtn1.setForeground(new java.awt.Color(255, 255, 255));
        registrarFacturaBtn1.setText("PRODUCTOS");
        registrarFacturaBtn1.setBorderPainted(false);
        registrarFacturaBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registrarFacturaBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarFacturaBtn1ActionPerformed(evt);
            }
        });

        registrarFacturaBtn2.setBackground(new java.awt.Color(255, 0, 51));
        registrarFacturaBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registrarFacturaBtn2.setForeground(new java.awt.Color(255, 255, 255));
        registrarFacturaBtn2.setText("FACTURAS PENDIENTES");
        registrarFacturaBtn2.setBorderPainted(false);
        registrarFacturaBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registrarFacturaBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarFacturaBtn2ActionPerformed(evt);
            }
        });

        registrarFacturaBtn3.setBackground(new java.awt.Color(255, 0, 51));
        registrarFacturaBtn3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registrarFacturaBtn3.setForeground(new java.awt.Color(255, 255, 255));
        registrarFacturaBtn3.setText("HISTORIAL TRANSACCIONES");
        registrarFacturaBtn3.setBorderPainted(false);
        registrarFacturaBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registrarFacturaBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarFacturaBtn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descripcionProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(precioUniProdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(registrarFacturaBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(registrarFacturaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(registrarFacturaBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 41, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nombreProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(descripcionProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cantidadProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(precioUniProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrarFacturaBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrarFacturaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrarFacturaBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void registrarFacturaBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarFacturaBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrarFacturaBtn1ActionPerformed

    private void registrarFacturaBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarFacturaBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrarFacturaBtn2ActionPerformed

    private void registrarFacturaBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarFacturaBtn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrarFacturaBtn3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JTextField cantidadProdField;
    private javax.swing.JTextField descripcionProdField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nombreProdField;
    private javax.swing.JTextField precioUniProdField;
    private javax.swing.JTable productosTable;
    private javax.swing.JButton registrarFacturaBtn1;
    private javax.swing.JButton registrarFacturaBtn2;
    private javax.swing.JButton registrarFacturaBtn3;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}

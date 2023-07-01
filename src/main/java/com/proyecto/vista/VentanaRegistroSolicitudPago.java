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

public class VentanaRegistroSolicitudPago extends javax.swing.JPanel {

    private ControladorRegistroFactura controladorRegistroFactura;
    boolean isEdition = false;
    boolean isProducEdition = false;
    private Factura invoiceEdition;
    private List<Producto> productosTemporales;
    private DefaultTableModel modeloTabla;

    private int currentProductId;
    private int currentFacturaId;

    public VentanaRegistroSolicitudPago() throws Exception {
        this.controladorRegistroFactura = new ControladorRegistroFactura();
        this.productosTemporales = new ArrayList<>();
        this.modeloTabla = new DefaultTableModel();

        initComponents();
        InitStyles();

        currentFacturaId = 0; // es el id que se pone en 0 porque significa que es una nueva factura para agregar
        currentProductId = 0; // es el id que se utiliza al momento de agregar productos o editarlos
        LoadProducts();
    }

    public VentanaRegistroSolicitudPago(Factura invoice) throws Exception {
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
        jSeparator1 = new javax.swing.JSeparator();
        registrarFacturaBtn = new javax.swing.JButton();
        cantidadProdField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        precioUniProdField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        registrarFacturaBtn1 = new javax.swing.JButton();
        registrarFacturaBtn2 = new javax.swing.JButton();
        title1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        title.setText("REGISTRAR SOLICITUD PAGOS");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(200, 10));

        registrarFacturaBtn.setBackground(new java.awt.Color(255, 0, 51));
        registrarFacturaBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        registrarFacturaBtn.setForeground(new java.awt.Color(255, 255, 255));
        registrarFacturaBtn.setText("Registrar");
        registrarFacturaBtn.setBorderPainted(false);
        registrarFacturaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registrarFacturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarFacturaBtnActionPerformed(evt);
            }
        });

        jLabel14.setText("Número de cheque:");

        jLabel15.setText("ID Factura:");

        jLabel16.setText("Nombre Proveedor:");

        jLabel17.setText("Metodo de Pago");

        registrarFacturaBtn1.setBackground(new java.awt.Color(255, 0, 51));
        registrarFacturaBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registrarFacturaBtn1.setForeground(new java.awt.Color(255, 255, 255));
        registrarFacturaBtn1.setText("POR CHEQUE");
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
        registrarFacturaBtn2.setText("POR CANJE");
        registrarFacturaBtn2.setBorderPainted(false);
        registrarFacturaBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registrarFacturaBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarFacturaBtn2ActionPerformed(evt);
            }
        });

        title1.setText("PAGO POR CANJE");

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setPreferredSize(new java.awt.Dimension(200, 10));

        jLabel18.setText("Monto:");

        title2.setText("PAGO POR CHEQUE");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(registrarFacturaBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registrarFacturaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(registrarFacturaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(cantidadProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(precioUniProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantidadProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precioUniProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(107, 107, 107)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                        .addComponent(registrarFacturaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(81, 81, 81))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabel17))
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel16)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(registrarFacturaBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(registrarFacturaBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void registrarFacturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarFacturaBtnActionPerformed

        String proveedor = (String) proveedorCBox.getSelectedItem();
        String fechaRegistro = fechaRegistroField.getText();
        String fechaVencimiento = fechaVencimientoField.getText();
        String descripcion = descripcionField.getText();
        String montoTotal = montoTotalLabel.getText();
        String montoPagado = montoPagadoLabel.getText();
        String montoPendiente = montoPendienteLabel.getText();

        String successMsg = isEdition ? "modificado" : "registrado";
        String errorMsg = isEdition ? "modificar" : "registrar";

        // Validaciones para los campos
        if (fechaRegistro.isEmpty() || fechaVencimiento.isEmpty() || descripcion.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            fechaRegistroField.requestFocus();

        } else if (!isEdition) { // codigo donde se agrega

            try {
                //CONDICIONAL fechaRegistro < fechaVencimiento"yyyy-MM-dd"

                // Convierte el String a LocalDate
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaRegistroDate = formato.parse(fechaRegistro);
                Date fechaVencimientoDate = formato.parse(fechaVencimiento);

                // valida que la fecha registro sea menor a la fecha vencimiento
                if (!controladorRegistroFactura.validarFechas(fechaRegistroDate, fechaVencimientoDate)) {
                    fechaRegistroField.requestFocus();
                    return;
                }

                boolean confirmarDatosProveedores = controladorRegistroFactura.confirmarDatosProveedores(productosTemporales, proveedor, fechaRegistro, fechaVencimiento, montoTotal, montoPagado, montoPendiente);
                if (confirmarDatosProveedores) {
                    int idProveedorSelecionado = controladorRegistroFactura.buscarIdProveedorPorNombre(proveedor);
                    controladorRegistroFactura.registrarFacturaConProductos(productosTemporales, idProveedorSelecionado, fechaRegistroDate, fechaVencimientoDate,
                            descripcion, Double.valueOf(montoTotal), Double.valueOf(montoPagado), Double.valueOf(montoPendiente));
                } else {
                    return;
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Proveedor " + successMsg + " exitosamente.\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + errorMsg + " el proveedor. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } else { // codigo donde se edita

            try {
                //CONDICIONAL fechaRegistro < fechaVencimiento"yyyy-MM-dd"

                // Convierte el String a LocalDate
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaRegistroDate = formato.parse(fechaRegistro);
                Date fechaVencimientoDate = formato.parse(fechaVencimiento);

                // valida que la fecha registro sea menor a la fecha vencimiento
                if (!controladorRegistroFactura.validarFechas(fechaRegistroDate, fechaVencimientoDate)) {
                    fechaRegistroField.requestFocus();
                    return;
                }

                boolean confirmarDatosProveedores = controladorRegistroFactura.confirmarDatosProveedores(productosTemporales, proveedor, fechaRegistro, fechaVencimiento, montoTotal, montoPagado, montoPendiente);

                if (confirmarDatosProveedores) {
                    int idFactura = invoiceEdition.getIdFactura();
                    int idProveedorSelecionado = controladorRegistroFactura.buscarIdProveedorPorNombre(proveedor);
                    controladorRegistroFactura.editarFacturaConProductos(productosTemporales, idFactura, idProveedorSelecionado, fechaRegistroDate, fechaVencimientoDate,
                            descripcion, Double.valueOf(montoTotal), Double.valueOf(montoPagado), Double.valueOf(montoPendiente));
                } else {
                    return;
                }

                javax.swing.JOptionPane.showMessageDialog(this, "Proveedor " + successMsg + " exitosamente.\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + errorMsg + " el proveedor. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_registrarFacturaBtnActionPerformed

    private void registrarFacturaBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarFacturaBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrarFacturaBtn1ActionPerformed

    private void registrarFacturaBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarFacturaBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrarFacturaBtn2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JTextField cantidadProdField;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField precioUniProdField;
    private javax.swing.JButton registrarFacturaBtn;
    private javax.swing.JButton registrarFacturaBtn1;
    private javax.swing.JButton registrarFacturaBtn2;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    // End of variables declaration//GEN-END:variables
}

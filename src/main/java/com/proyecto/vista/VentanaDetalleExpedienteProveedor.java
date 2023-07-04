package com.proyecto.vista;

//import com.mycompany.ilib.DAOUsersImpl;
//import com.mycompany.interfaces.DAOUsers;
import com.proyecto.controladores.ControladorRegistroFactura;
import com.proyecto.controladores.ControladorRegistroProveedor;
import com.proyecto.controladores.ControladorRegistroUsuario;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.obtenerFechaActual;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaDetalleExpedienteProveedor extends javax.swing.JPanel {

    private ControladorReagistroFactura controladorRegistroFactura;
    boolean isEdition = false;
    boolean isProducEdition = false;
    private Factura invoiceEdition;
    private List<Producto> productosTemporales;
    private DefaultTableModel modeloTabla;

    private Producto productoEdicion; //ESTO USARIAMOS EN VEZ DE USAR EL currentProductId ya que no se sabe con exactitud por el id = 0

    private int currentProductId;
    private int currentFacturaId;

    public VentanaDetalleExpedienteProveedor() throws Exception {
        this.controladorRegistroFactura = new ControladorRegistroFactura();
        this.productosTemporales = new ArrayList<>();
        this.modeloTabla = new DefaultTableModel();

        initComponents();
        InitStyles();

        currentFacturaId = 0; // es el id que se pone en 0 porque significa que es una nueva factura para agregar
        currentProductId = 0; // es el id que se utiliza al momento de agregar productos o editarlos*************
        LoadProducts();
    }

    public VentanaDetalleExpedienteProveedor(Factura invoice) throws Exception {
        this.controladorRegistroFactura = new ControladorRegistroFactura();
        this.productosTemporales = new ArrayList<>();
        this.modeloTabla = new DefaultTableModel();

        initComponents();
        isEdition = true;
        invoiceEdition = invoice;
        InitStyles();

        currentFacturaId = invoice.getIdFactura(); // es el id que se pone su respectiva idFactura registrada al ventanaGestorFactura envia.
        currentProductId = 0;//************
        LoadProducts();
    }

    private void InitStyles() throws Exception {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);

        fechaRegistroField.setEditable(false);
        fechaRegistroField.setText(obtenerFechaActual()); // Define la fecha actual en la que se esta registrando la factura
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
            hitorialPagosBtn.setText("Guardar");
            fechaRegistroField.setEditable(true);

            if (invoiceEdition != null) {
                controladorRegistroFactura.agregarProductosArrayProductos(productosTemporales, invoiceEdition.getIdFactura());

                String proveedorDeFactura = controladorRegistroFactura.buscarNombreProveedorPorFactura(invoiceEdition.getIdFactura());
                proveedorCBox.setSelectedItem(proveedorDeFactura);

                // Crea el formato deseado para la fecha
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                // Convierte la fecha a String
                String fechaRegistroString = formato.format(invoiceEdition.getFechaRegistro());
                fechaRegistroField.setText(fechaRegistroString);

                String fechaVencimientoString = formato.format(invoiceEdition.getFechaVencimiento());
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
        nameLabel = new javax.swing.JLabel();
        nombreProveedorLabel = new javax.swing.JLabel();
        nameLabel1 = new javax.swing.JLabel();
        direccionLabel = new javax.swing.JLabel();
        nameLabel2 = new javax.swing.JLabel();
        lineaCreditoLabel = new javax.swing.JLabel();
        nameLabel3 = new javax.swing.JLabel();
        telefonoLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        hitorialPagosBtn = new javax.swing.JButton();
        productosProveeBtn = new javax.swing.JButton();
        facturasPendientesBtn = new javax.swing.JButton();
        volvetBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        title.setText("EXPEDIENTE PROVEEDOR");

        nameLabel.setText("Proveedor:");

        nombreProveedorLabel.setText("(nombre proveedor)");

        nameLabel1.setText("Dirección:");

        direccionLabel.setText("(direccion)");

        nameLabel2.setText("Telefono:");

        lineaCreditoLabel.setText("(lineaCredito)");

        nameLabel3.setText("Linea de Credito:");

        telefonoLabel1.setText("(telefono)");

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

        hitorialPagosBtn.setBackground(new java.awt.Color(255, 0, 51));
        hitorialPagosBtn.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        hitorialPagosBtn.setForeground(new java.awt.Color(255, 255, 255));
        hitorialPagosBtn.setText("Historial Pagos");
        hitorialPagosBtn.setBorderPainted(false);
        hitorialPagosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hitorialPagosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitorialPagosBtnActionPerformed(evt);
            }
        });

        productosProveeBtn.setBackground(new java.awt.Color(255, 0, 51));
        productosProveeBtn.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        productosProveeBtn.setForeground(new java.awt.Color(255, 255, 255));
        productosProveeBtn.setText("Productos que Provee");
        productosProveeBtn.setBorderPainted(false);
        productosProveeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        productosProveeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosProveeBtnActionPerformed(evt);
            }
        });

        facturasPendientesBtn.setBackground(new java.awt.Color(255, 0, 51));
        facturasPendientesBtn.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        facturasPendientesBtn.setForeground(new java.awt.Color(255, 255, 255));
        facturasPendientesBtn.setText("Facturas Pendientes");
        facturasPendientesBtn.setBorderPainted(false);
        facturasPendientesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        facturasPendientesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturasPendientesBtnActionPerformed(evt);
            }
        });

        volvetBtn.setBackground(new java.awt.Color(255, 0, 51));
        volvetBtn.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        volvetBtn.setForeground(new java.awt.Color(255, 255, 255));
        volvetBtn.setText("VOLVER");
        volvetBtn.setBorderPainted(false);
        volvetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        volvetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volvetBtnActionPerformed(evt);
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
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nombreProveedorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(direccionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(106, 106, 106)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(nameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lineaCreditoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(nameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(telefonoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(323, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(volvetBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(productosProveeBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hitorialPagosBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(facturasPendientesBtn))
                            .addComponent(jScrollPane1))
                        .addGap(31, 31, 31))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreProveedorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lineaCreditoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hitorialPagosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productosProveeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facturasPendientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volvetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
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

    private void hitorialPagosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitorialPagosBtnActionPerformed

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
                    javax.swing.JOptionPane.showMessageDialog(this, "La fecha de vencimiento debe de ser mayor a la fecha de registro. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    fechaVencimientoField.requestFocus();
                    return;
                }

                // valida que el monto total de la factura no haga que supere la linea de credito del proveedor.
                double montoTotalCalculado = controladorRegistroFactura.calcularMontoTotal(productosTemporales);
                int idProveedorSelecionado = controladorRegistroFactura.buscarIdProveedorPorNombre(proveedor);

                if (!controladorRegistroFactura.validarMontoLineaCredito(montoTotalCalculado, idProveedorSelecionado, 0)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El monto total de la factura supera a la linea de credito del proveedor selecionado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    fechaVencimientoField.requestFocus();
                    return;
                }

                boolean confirmarDatosFactura = controladorRegistroFactura.confirmarDatosFactura(productosTemporales, proveedor, fechaRegistro, fechaVencimiento, montoTotal, montoPagado, montoPendiente);
                if (confirmarDatosFactura) {
                    JOptionPane.showMessageDialog(null, "ES EL ID PROVEEDOR: " + idProveedorSelecionado);

                    controladorRegistroFactura.registrarFacturaConProductos(productosTemporales, idProveedorSelecionado, fechaRegistroDate, fechaVencimientoDate,
                        descripcion, Double.valueOf(montoTotal), Double.valueOf(montoPagado), Double.valueOf(montoPendiente));
                } else {
                    return;
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Factura " + successMsg + " exitosamente.\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + errorMsg + " el factura. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
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

                // valida que el monto total de la factura no haga que supere la linea de credito del proveedor.
                double montoTotalCalculado = controladorRegistroFactura.calcularMontoTotal(productosTemporales);
                int idProveedorSelecionado = controladorRegistroFactura.buscarIdProveedorPorNombre(proveedor);

                if (!controladorRegistroFactura.validarMontoLineaCredito(montoTotalCalculado, idProveedorSelecionado, invoiceEdition.getIdFactura())) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El monto total de la factura supera a la linea de credito del proveedor selecionado. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    fechaVencimientoField.requestFocus();
                    return;
                }

                boolean confirmarDatosProveedores = controladorRegistroFactura.confirmarDatosFactura(productosTemporales, proveedor, fechaRegistro, fechaVencimiento, montoTotal, montoPagado, montoPendiente);

                if (confirmarDatosProveedores) {
                    int idFactura = invoiceEdition.getIdFactura();
                    controladorRegistroFactura.editarFacturaConProductos(productosTemporales, idFactura, idProveedorSelecionado, fechaRegistroDate, fechaVencimientoDate,
                        descripcion, Double.valueOf(montoTotal), Double.valueOf(montoPagado), Double.valueOf(montoPendiente));
                } else {
                    return;
                }

                javax.swing.JOptionPane.showMessageDialog(this, "Factura " + successMsg + " exitosamente.\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + errorMsg + " la factura. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_hitorialPagosBtnActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

    }//GEN-LAST:event_jTable1MousePressed

    private void productosProveeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosProveeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productosProveeBtnActionPerformed

    private void facturasPendientesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturasPendientesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facturasPendientesBtnActionPerformed

    private void volvetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volvetBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volvetBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel direccionLabel;
    private javax.swing.JButton facturasPendientesBtn;
    private javax.swing.JButton hitorialPagosBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lineaCreditoLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nombreProveedorLabel;
    private javax.swing.JButton productosProveeBtn;
    private javax.swing.JLabel telefonoLabel1;
    private javax.swing.JLabel title;
    private javax.swing.JButton volvetBtn;
    // End of variables declaration//GEN-END:variables
}

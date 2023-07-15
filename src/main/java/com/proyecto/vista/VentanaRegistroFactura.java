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

public class VentanaRegistroFactura extends javax.swing.JPanel {

    private ControladorRegistroFactura controladorRegistroFactura;
    boolean isEdition = false;
    boolean isProducEdition = false;
    private Factura invoiceEdition;
    private List<Producto> productosTemporales;
    private DefaultTableModel modeloTabla;

    private Producto productoEdicion; //ESTO USARIAMOS EN VEZ DE USAR EL currentProductId ya que no se sabe con exactitud por el id = 0

    private int currentProductId;
    private int currentFacturaId;

    public VentanaRegistroFactura() throws Exception {
        this.controladorRegistroFactura = new ControladorRegistroFactura();
        this.productosTemporales = new ArrayList<>();
        this.modeloTabla = new DefaultTableModel();

        initComponents();
        InitStyles();

        currentFacturaId = 0; // es el id que se pone en 0 porque significa que es una nueva factura para agregar
        currentProductId = 0; // es el id que se utiliza al momento de agregar productos o editarlos*************
        LoadProducts();
    }

    public VentanaRegistroFactura(Factura invoice) throws Exception {
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
            registrarFacturaBtn.setText("Guardar");
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
        jSeparator1 = new javax.swing.JSeparator();
        phoneLabel = new javax.swing.JLabel();
        registrarFacturaBtn = new javax.swing.JButton();
        proveedorCBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        fechaRegistroField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fechaVencimientoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        descripcionField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        montoTotalLabel = new javax.swing.JLabel();
        montoPagadoLabel = new javax.swing.JLabel();
        montoPendienteLabel = new javax.swing.JLabel();
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
        agregarProdBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        editarProdBtn = new javax.swing.JButton();
        borrarProdBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        title.setText("Registrar nueva Factura");

        nameLabel.setText("Proveedor");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(200, 10));

        phoneLabel.setText("Detalles de la factura");

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

        jLabel1.setText("Fecha de Registro:");

        jLabel2.setText("Fecha de Vencimiento:");

        jLabel3.setText("Descripción:");

        jLabel4.setText("Monto Total:");

        jLabel5.setText("Monto Pagado:");

        jLabel6.setText("Monto Pendiente:");

        montoTotalLabel.setText("jLabel7");

        montoPagadoLabel.setText("jLabel7");

        montoPendienteLabel.setText("jLabel7");

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
        productosTable.setShowGrid(true);
        jScrollPane1.setViewportView(productosTable);

        jLabel10.setText("PRODUCTO");

        jLabel11.setText("Nombre:");

        jLabel12.setText("Descripción:");

        jLabel13.setText("Cantidad:");

        jLabel14.setText("Precio unitario:");

        agregarProdBtn.setText("AGREGAR PRODUCTO");
        agregarProdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProdBtnActionPerformed(evt);
            }
        });

        editarProdBtn.setText("EDITAR");
        editarProdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarProdBtnActionPerformed(evt);
            }
        });

        borrarProdBtn.setText("BORRAR");
        borrarProdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarProdBtnActionPerformed(evt);
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
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaRegistroField, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(fechaVencimientoField)
                            .addComponent(descripcionField))
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(montoTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(montoPagadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(montoPendienteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(proveedorCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(51, 51, 51)
                                .addComponent(nombreProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(33, 33, 33)
                                .addComponent(descripcionProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(agregarProdBtn)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)
                                    .addComponent(precioUniProdField))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(47, 47, 47)
                                    .addComponent(cantidadProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registrarFacturaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(editarProdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(borrarProdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(proveedorCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(montoTotalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(fechaRegistroField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaVencimientoField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(montoPagadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descripcionField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(montoPendienteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(nombreProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(descripcionProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cantidadProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(precioUniProdField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(agregarProdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editarProdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrarProdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(registrarFacturaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    }//GEN-LAST:event_registrarFacturaBtnActionPerformed

    private void agregarProdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProdBtnActionPerformed

        // TODO add your handling code here:
        String nombreProd = nombreProdField.getText();
        String descripcionProd = descripcionProdField.getText();
        String cantidadProd = cantidadProdField.getText();
        String precioUnitarioProd = precioUniProdField.getText();

        String successMsg = isProducEdition ? "editado" : "agregado";
        String errorMsg = isProducEdition ? "editar" : "agregar";

        // Validaciones para los campos
        if (nombreProd.isEmpty() || descripcionProd.isEmpty() || cantidadProd.isEmpty() || precioUnitarioProd.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos del producto. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            nombreProdField.requestFocus();

        } else if (!isProducEdition) { // codigo donde se agrega
            try {
                //VALIDACION: Para que cuando se selecione un producto de la tabla, este que se guia por el nombre del producto. No entre en conflicto con otro duplicado.
                boolean productoRepetido = controladorRegistroFactura.existeProductoConNombre(productosTemporales, nombreProd);
                if (productoRepetido) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El producto(nombre) ya existe en la factura, debe ser nuevo. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    nombreProdField.requestFocus();
                    return;
                }

                boolean confirmarDatosProducto = controladorRegistroFactura.confirmarDatosProducto(nombreProd, descripcionProd, cantidadProd, precioUnitarioProd);
                if (confirmarDatosProducto) {
                    controladorRegistroFactura.agregarNuevoProductoArrayProductos(productosTemporales, 0, currentFacturaId,
                            nombreProd, descripcionProd, cantidadProd, precioUnitarioProd);

                    //
                    double montoTotalCalculado = controladorRegistroFactura.calcularMontoTotal(productosTemporales); // se calcula el monto total cada vez que se agrega un producto
                    montoTotalLabel.setText(Double.toString(montoTotalCalculado));

                    /*double montoPagadoCalculado*/ //el monto pagado no se puede editar porque afectaria a los registros
                    double montoPendienteCalculado = montoTotalCalculado - (Double.parseDouble(montoPagadoLabel.getText()));
                    montoPendienteLabel.setText(Double.toString(montoPendienteCalculado));

                    //
                    limpiarCamposProducto(); // limpia los campos cada vez que se registra un producto
                    LoadProducts(); // actualiza la tabla cada vez que se registra un producto
                } else {
                    return;
                }

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + errorMsg + " el producto. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } else { // codigo donde se edita

            try {

                boolean confirmarDatosProducto = controladorRegistroFactura.confirmarDatosProducto(nombreProd, descripcionProd, cantidadProd, precioUnitarioProd);
                if (confirmarDatosProducto) {

                    controladorRegistroFactura.editarProductoArrayProductos(productosTemporales, productoEdicion, nombreProd, descripcionProd, cantidadProd, precioUnitarioProd);
                    //
                    double montoTotalCalculado = controladorRegistroFactura.calcularMontoTotal(productosTemporales); // se calcula el monto total cada vez que se agrega un producto
                    montoTotalLabel.setText(Double.toString(montoTotalCalculado));

                    /*double montoPagadoCalculado*/ //el monto pagado no se puede editar porque afectaria a los registros
                    double montoPendienteCalculado = montoTotalCalculado - (Double.parseDouble(montoPagadoLabel.getText()));
                    montoPendienteLabel.setText(Double.toString(montoPendienteCalculado));

                    //
                    limpiarCamposProducto(); // limpia los campos cada vez que se registra un producto
                    LoadProducts(); // actualiza la tabla cada vez que se registra un producto

                    //Volviento las cosas a por defecto (de Editar producto -> Agregar producto)
                    isProducEdition = false;
                    agregarProdBtn.setText("AGREGAR PRODUCTO");
                } else {
                    return;
                }

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + errorMsg + " el producto. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_agregarProdBtnActionPerformed

    private void editarProdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarProdBtnActionPerformed
        // TODO add your handling code here:
        isProducEdition = true;
        agregarProdBtn.setText("EDITAR PRODUCTO");


        if (productosTable.getSelectedRow() > -1) {
            try {
                String nombreProducto = (String) productosTable.getValueAt(productosTable.getSelectedRow(), 2); // Asumiendo que el nombre del producto está en la columna 2
                productoEdicion = controladorRegistroFactura.buscarProductoPorNombre(productosTemporales, nombreProducto);
                //currentProductId = productoEdicion.getIdProducto();

                nombreProdField.setText(productoEdicion.getNombre());
                descripcionProdField.setText(productoEdicion.getDescripcion());
                cantidadProdField.setText(Integer.toString(productoEdicion.getCantidadTotal()));
                precioUniProdField.setText(Double.toString(productoEdicion.getPrecioUnitario()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar el producto a editar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_editarProdBtnActionPerformed

    private void borrarProdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarProdBtnActionPerformed
        // TODO add your handling code here:


        if (productosTable.getSelectedRows().length < 1) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar uno o más productos a eliminar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i : productosTable.getSelectedRows()) {
                try {
                    String nombreProducto = (String) productosTable.getValueAt(i, 2); // Asumiendo que el nombre del producto está en la columna 2
                    controladorRegistroFactura.eliminarProductoPorNombre(productosTemporales, nombreProducto);

                    //
                    double montoTotalCalculado = controladorRegistroFactura.calcularMontoTotal(productosTemporales); // se calcula el monto total cada vez que se agrega, edita o elimina un producto
                    montoTotalLabel.setText(Double.toString(montoTotalCalculado));

                    /*double montoPagadoCalculado*/ //el monto pagado no se puede editar porque afectaria a los registros
                    double montoPendienteCalculado = montoTotalCalculado - (Double.parseDouble(montoPagadoLabel.getText()));
                    montoPendienteLabel.setText(Double.toString(montoPendienteCalculado));

                    //
                    limpiarCamposProducto(); // limpia los campos cada vez que se registra un producto
                    LoadProducts(); // actualiza la tabla cada vez que se elimina un producto

                    //Volviento las cosas a por defecto (de Editar producto -> Agregar producto)
                    isProducEdition = false;
                    agregarProdBtn.setText("AGREGAR PRODUCTO");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }//GEN-LAST:event_borrarProdBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarProdBtn;
    private javax.swing.JPanel bg;
    private javax.swing.JButton borrarProdBtn;
    private javax.swing.JTextField cantidadProdField;
    private javax.swing.JTextField descripcionField;
    private javax.swing.JTextField descripcionProdField;
    private javax.swing.JButton editarProdBtn;
    private javax.swing.JTextField fechaRegistroField;
    private javax.swing.JTextField fechaVencimientoField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel montoPagadoLabel;
    private javax.swing.JLabel montoPendienteLabel;
    private javax.swing.JLabel montoTotalLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nombreProdField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField precioUniProdField;
    private javax.swing.JTable productosTable;
    private javax.swing.JComboBox<String> proveedorCBox;
    private javax.swing.JButton registrarFacturaBtn;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}

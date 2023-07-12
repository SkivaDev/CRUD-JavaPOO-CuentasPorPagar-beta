package com.proyecto.vista;

//import com.mycompany.ilib.DAOUsersImpl;
//import com.mycompany.interfaces.DAOUsers;
import com.proyecto.controladores.ControladorRegistroFactura;
import com.proyecto.controladores.ControladorRegistroPagoFactura;
import com.proyecto.controladores.ControladorRegistroProveedor;
import com.proyecto.controladores.ControladorRegistroSolicitudPago;
import com.proyecto.controladores.ControladorRegistroUsuario;
import com.proyecto.entidades.Canje;
import com.proyecto.entidades.CategoriaProducto;
import com.proyecto.entidades.Cheque;
import com.proyecto.entidades.CuentaBancaria;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.SolicitudPago;
import com.proyecto.entidades.Usuario;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class VentanaRegistroPagoFactura extends javax.swing.JPanel {

    private ControladorRegistroPagoFactura controladorRegistroPagoFactura;
    private boolean isCheck;
    private Factura invoicePayment;
    private SolicitudPago paymentRequest;
    private Cheque checkPayment;
    private Canje exchangePayment;

    public VentanaRegistroPagoFactura(Factura invoice, SolicitudPago paymentRequest, Cheque check) throws Exception {
        this.controladorRegistroPagoFactura = new ControladorRegistroPagoFactura();
        this.invoicePayment = invoice;
        this.paymentRequest = paymentRequest;
        this.checkPayment = check;
        this.exchangePayment = null;

        initComponents();
        this.isCheck = true;
        InitStyles();

        ShowInvoiceData();
        ShowCheckData();
    }

    public VentanaRegistroPagoFactura(Factura invoice, SolicitudPago paymentRequest, Canje exchange) throws Exception {
        this.controladorRegistroPagoFactura = new ControladorRegistroPagoFactura();
        this.invoicePayment = invoice;
        this.paymentRequest = paymentRequest;
        this.checkPayment = null;
        this.exchangePayment = exchange;

        initComponents();
        this.isCheck = false;
        InitStyles();

        ShowInvoiceData();
        ShowExchangeData();
    }

    private void InitStyles() throws Exception {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);

        idFacturaField.setEditable(false);
        nombreProveedorField.setEditable(false);
        fechaRegistroField.setEditable(false);
        fechaVencimientoField.setEditable(false);
        descripcionField.setEditable(false);
        montoPendienteField.setEditable(false);
        montoPagadoField.setEditable(false);
        montoTotalField.setEditable(false);

        bancosCbox.setEnabled(false);
        saldoActualBancoField.setEditable(false);
        montoChequeField.setEditable(false);

        detalleCanjeField.setEditable(false);
        categoriaProductoCBox.setEnabled(false);
        productoCBox.setEnabled(false);
        cantidadProductosField.setEditable(false);

        if(isCheck){
            porCanjeBtn.setBackground(Color.GRAY);
        } else {
            porChequeBtn.setBackground(Color.GRAY);
        }
        //
        //controladorRegistroPagoFactura.llenarComboBoxCuentasBancarias(bancosCbox);
        //controladorRegistroPagoFactura.llenarComboBoxCategoriasProducto(categoriaProductoCBox);

    }

    private void ShowInvoiceData() {
        controladorRegistroPagoFactura.mostrarDatosFactura(invoicePayment.getIdFactura(), idFacturaField,
                nombreProveedorField, fechaRegistroField, fechaVencimientoField,
                descripcionField, montoPendienteField, montoPagadoField, montoTotalField);
    }

    private void ShowCheckData() {
        controladorRegistroPagoFactura.mostrarDatosCheque(checkPayment, bancosCbox, saldoActualBancoField, montoChequeField);
    }

    private void ShowExchangeData() {
        controladorRegistroPagoFactura.mostrarDatosCanje(exchangePayment, detalleCanjeField, categoriaProductoCBox, productoCBox, cantidadProductosField);
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
        pagarFacturaBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        porChequeBtn = new javax.swing.JButton();
        porCanjeBtn = new javax.swing.JButton();
        idFacturaField = new javax.swing.JTextField();
        descripcionField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        productoCBox = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        nombreProveedorField = new javax.swing.JTextField();
        fechaRegistroField = new javax.swing.JTextField();
        fechaVencimientoField = new javax.swing.JTextField();
        montoTotalField = new javax.swing.JTextField();
        montoPendienteField = new javax.swing.JTextField();
        montoPagadoField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        montoChequeField = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        bancosCbox = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        saldoActualBancoField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        detalleCanjeField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        categoriaProductoCBox = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cantidadProductosField = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        title.setText("PAGAR FACTURA DE SOLICITUD DE PAGO");

        pagarFacturaBtn.setBackground(new java.awt.Color(255, 0, 51));
        pagarFacturaBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pagarFacturaBtn.setForeground(new java.awt.Color(255, 255, 255));
        pagarFacturaBtn.setText("Pagar Factura");
        pagarFacturaBtn.setBorderPainted(false);
        pagarFacturaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pagarFacturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarFacturaBtnActionPerformed(evt);
            }
        });

        jLabel15.setText("FACTURA");

        jLabel17.setText("METODO DE PAGO SELECCIONADO");

        porChequeBtn.setBackground(new java.awt.Color(255, 0, 51));
        porChequeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        porChequeBtn.setForeground(new java.awt.Color(255, 255, 255));
        porChequeBtn.setText("POR CHEQUE");
        porChequeBtn.setBorderPainted(false);
        porChequeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        porChequeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porChequeBtnActionPerformed(evt);
            }
        });

        porCanjeBtn.setBackground(new java.awt.Color(255, 0, 51));
        porCanjeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        porCanjeBtn.setForeground(new java.awt.Color(255, 255, 255));
        porCanjeBtn.setText("POR CANJE");
        porCanjeBtn.setBorderPainted(false);
        porCanjeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        porCanjeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porCanjeBtnActionPerformed(evt);
            }
        });

        jLabel20.setText("Seleccionar Categoría:");

        jLabel23.setText("ID:");

        jLabel24.setText("Nombre Proveedor:");

        jLabel25.setText("Fecha de Vencimiento:");

        jLabel26.setText("Fecha de Registro:");

        jLabel27.setText("Descripcion:");

        jLabel28.setText("Monto Total:");

        jLabel29.setText("Monto Pagado:");

        jLabel30.setText("Monto Pendiente:");

        jLabel18.setText("Monto:");

        jLabel31.setText("Saldo Actual:");

        bancosCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bancosCboxActionPerformed(evt);
            }
        });

        jLabel32.setText("Seleccionar Banco:");

        jLabel19.setText("Detalle de canje:");

        jLabel21.setText("Seleccionar Producto:");

        categoriaProductoCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaProductoCBoxActionPerformed(evt);
            }
        });

        jLabel22.setText("Cantidad Productos:");

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setPreferredSize(new java.awt.Dimension(200, 10));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descripcionField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                        .addComponent(montoChequeField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(221, 221, 221))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(saldoActualBancoField, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(37, 37, 37))
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(productoCBox, 0, 200, Short.MAX_VALUE)
                                    .addComponent(detalleCanjeField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(porCanjeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(categoriaProductoCBox, 0, 200, Short.MAX_VALUE)
                                    .addComponent(cantidadProductosField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator4)
                            .addComponent(pagarFacturaBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoPendienteField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoPagadoField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montoTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idFacturaField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nombreProveedorField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(porChequeBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bancosCbox, javax.swing.GroupLayout.Alignment.LEADING, 0, 134, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaRegistroField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaVencimientoField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(842, 842, 842))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idFacturaField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(nombreProveedorField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaRegistroField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(fechaVencimientoField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(descripcionField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29)
                    .addComponent(montoPendienteField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoPagadoField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(montoTotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(porChequeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porCanjeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel19)
                            .addComponent(jLabel31)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bancosCbox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saldoActualBancoField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(detalleCanjeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriaProductoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(productoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cantidadProductosField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(montoChequeField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pagarFacturaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void porCanjeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porCanjeBtnActionPerformed
        // TODO add your handling code here:

        /*
        isCheck = false;

        detalleCanjeField.setEditable(true);
        categoriaProductoCBox.setEnabled(true);
        productoCBox.setEnabled(true);
        cantidadProductosField.setEditable(true);

        bancosCbox.setEnabled(false);
        montoChequeField.setEditable(false);
         */
    }//GEN-LAST:event_porCanjeBtnActionPerformed

    private void porChequeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porChequeBtnActionPerformed
        // TODO add your handling code here:

        /*
        isCheck = true;

        bancosCbox.setEnabled(true);
        montoChequeField.setEditable(true);

        detalleCanjeField.setEditable(false);
        categoriaProductoCBox.setEnabled(false);
        productoCBox.setEnabled(false);
        cantidadProductosField.setEditable(false);
         */
    }//GEN-LAST:event_porChequeBtnActionPerformed

    private void pagarFacturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarFacturaBtnActionPerformed
        
        try {
            controladorRegistroPagoFactura.registrarPagoFacturaSolicitud(invoicePayment, paymentRequest, checkPayment, exchangePayment);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Pago de factura efectuada correctamente.\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al pagar la factura. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());

        }
        
        
        
        /*
        String idFactura = idFacturaField.getText();
        String nombreProveedor = nombreProveedorField.getText();
        String fechaRegistro = fechaRegistroField.getText();
        String fechaVencimiento = fechaVencimientoField.getText();
        String montoPendiente = montoPendienteField.getText();
        String montoPagado = montoPagadoField.getText();
        String montoTotal = montoTotalField.getText();

        String cuentaBancariaSelecionada = (String) bancosCbox.getSelectedItem();
        String saldoCuentaBancaria = saldoActualBancoField.getText();
        String montoPagoPorCheque = montoChequeField.getText();

        String detalleCanje = detalleCanjeField.getText();
        String categoriaProductoSeleccionado = (String) categoriaProductoCBox.getSelectedItem();
        Producto productoSeleccionado = (Producto) productoCBox.getSelectedItem();
        String cantidadProductosSeleccionado = cantidadProductosField.getText();

        String successMsg = isCheck ? "modificado" : "registrado";
        String errorMsg = isCheck ? "modificar" : "registrar";
        
        // Validaciones para los campos
        if (isCheck) { // codigo donde se paga por cheque
            try {
                
                //VALIDACION: todos los campos completos
                if (montoPagoPorCheque.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    montoChequeField.requestFocus();
                    return;
                }

                //VALIDACION: el monto a pagar tiene que ser mayor a 0
                double doubleMontoPagoPorCheque = Double.parseDouble(montoPagoPorCheque);
                if (doubleMontoPagoPorCheque < 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El monto registrado debe ser mayor a 0. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    javax.swing.JOptionPane.showMessageDialog(this, doubleMontoPagoPorCheque, "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    montoChequeField.requestFocus();
                    return;
                }

                //VALIDACION: el monto a pagar tiene que ser menor al saldo de la cuenta bancaria
                if (doubleMontoPagoPorCheque > Double.parseDouble(saldoCuentaBancaria)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El monto registrado supera al saldo de la cuenta actual. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    montoChequeField.requestFocus();
                    return;
                }

                boolean confirmarDatosSolicitudPago = controladorRegistroPagoFactura.confirmarDatosSolicitudPagoPorCheque(idFactura, nombreProveedor,
                        fechaRegistro, fechaVencimiento, montoTotal, montoPagado, montoPendiente, cuentaBancariaSelecionada, saldoCuentaBancaria, montoPagoPorCheque);
                if (confirmarDatosSolicitudPago) {

                    Cheque chequeRecienRegistrado = controladorRegistroPagoFactura.registrarRegistroCheque(invoicePayment, doubleMontoPagoPorCheque, cuentaBancariaSelecionada);

                    controladorRegistroPagoFactura.registrarSolicitud(invoicePayment, chequeRecienRegistrado, null);
                } else {
                    return;
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Solicitud " + "registrada" + " exitosamente.\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + "registrar" + " la solicitud por cheque. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else { // codigo donde se paga por canje || NOS QUEDAMOS TERMINANDO DE PROGRAMAR EL PAGO POR CANJE

            try {
                //VALIDACION: todos los campos completos
                if (detalleCanje.isEmpty() || cantidadProductosSeleccionado.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    montoChequeField.requestFocus();
                    return;
                }

                //VALIDACION: la cantidad de productos selecionado tiene que ser mayor a 0
                int intCantidadProductosSeleccionado = Integer.parseInt(cantidadProductosSeleccionado);
                if (intCantidadProductosSeleccionado <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, "La cantidad registrada debe ser mayor a 0. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    montoChequeField.requestFocus();
                    return;
                }

                //VALIDACION: la cantidad de productos selecionado tiene que ser menor a la cantidad disponible en inventario
                if (productoSeleccionado == null) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Debe selecionar un producto valido. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    montoChequeField.requestFocus();
                    return;
                }

                //VALIDACION: la cantidad de productos selecionado tiene que ser menor a la cantidad disponible en inventario
                int cantidadDispinibleProducto = controladorRegistroPagoFactura.cantidadTotalDispinibleProducto(productoSeleccionado.getNombre(), categoriaProductoSeleccionado);
                if (cantidadDispinibleProducto <= intCantidadProductosSeleccionado) {
                    javax.swing.JOptionPane.showMessageDialog(this, "La cantidad registrada supera al cantidad en inventario. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    montoChequeField.requestFocus();
                    return;
                }

                boolean confirmarDatosSolicitudPago = controladorRegistroPagoFactura.confirmarDatosSolicitudPagoPorCanje(idFactura, nombreProveedor,
                        fechaRegistro, fechaVencimiento, montoTotal, montoPagado, montoPendiente,
                        detalleCanje, categoriaProductoSeleccionado, productoSeleccionado.getNombre(), cantidadProductosSeleccionado);
                if (confirmarDatosSolicitudPago) {

                    Canje canjeRecienRegistrado = controladorRegistroPagoFactura.registrarRegistroCanje(invoicePayment, productoSeleccionado,
                            detalleCanje, categoriaProductoSeleccionado, categoriaProductoSeleccionado, intCantidadProductosSeleccionado);

                    controladorRegistroPagoFactura.registrarSolicitud(invoicePayment, null, canjeRecienRegistrado);
                } else {
                    return;
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Solicitud " + "registrada" + " exitosamente.\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al " + "registrar" + " la solicitud por canje. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }*/

    }//GEN-LAST:event_pagarFacturaBtnActionPerformed

    private void bancosCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bancosCboxActionPerformed
        // TODO add your handling code here:
        //String cuentaBancariaSelecionada = (String) bancosCbox.getSelectedItem();
        //controladorRegistroPagoFactura.mostrarSaldoBanco(cuentaBancariaSelecionada, saldoActualBancoField);
    }//GEN-LAST:event_bancosCboxActionPerformed

    private void categoriaProductoCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaProductoCBoxActionPerformed
        // TODO add your handling code here:
        //String categoriaProductoSelecionado = (String) categoriaProductoCBox.getSelectedItem();
        //controladorRegistroPagoFactura.agregarProductosCboxFildrados(categoriaProductoSelecionado, productoCBox);
    }//GEN-LAST:event_categoriaProductoCBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bancosCbox;
    private javax.swing.JPanel bg;
    private javax.swing.JTextField cantidadProductosField;
    private javax.swing.JComboBox<String> categoriaProductoCBox;
    private javax.swing.JTextField descripcionField;
    private javax.swing.JTextField detalleCanjeField;
    private javax.swing.JTextField fechaRegistroField;
    private javax.swing.JTextField fechaVencimientoField;
    private javax.swing.JTextField idFacturaField;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField montoChequeField;
    private javax.swing.JTextField montoPagadoField;
    private javax.swing.JTextField montoPendienteField;
    private javax.swing.JTextField montoTotalField;
    private javax.swing.JTextField nombreProveedorField;
    private javax.swing.JButton pagarFacturaBtn;
    private javax.swing.JButton porCanjeBtn;
    private javax.swing.JButton porChequeBtn;
    private javax.swing.JComboBox<String> productoCBox;
    private javax.swing.JTextField saldoActualBancoField;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}

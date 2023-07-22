/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAdministradorImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.entidades.Administrador;
import com.proyecto.entidades.Almacenero;
import com.proyecto.entidades.EncargadoCompras;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.JefeFinanzas;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.Tesorero;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.generarNumeroRandom;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorRegistroFactura {

    private DAOEncargadoComprasImpl dao;
    private Factura invoice;

    public ControladorRegistroFactura() {
        this.dao = new DAOEncargadoComprasImpl();
    }

    public void registrarFacturaConProductos(List<Producto> productosTemporales, int idProveedor, Date fechaRegistro, Date fechaVencimiento, String descripcion,
            Double montoTotal, Double montoPagado, Double montoPendiente) throws Exception {

        try {
            // Registrar la factura
            invoice = new Factura(0, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, montoPendiente);

            System.out.println("Contrl RegisFacConProd Paso1.\nidProveedor :" + invoice.getIdProveedor() + "\nMontoTotal :" + invoice.getMontoTotal());

            dao.registrarFactura(invoice);
            JOptionPane.showMessageDialog(null, "1) Se registro la factura: " + invoice);

            // Obtener la ID de la factura registrada
            int idFactura = dao.obtenerUltimaFacturaRegistrada();
            JOptionPane.showMessageDialog(null, "2) IDFactura: " + idFactura);

            System.out.println("Contrl RegisFacConProd Paso2.\nUltima id Factura Recien registrada :" + idFactura);

            // Registrar los productos asociados a la factura
            for (Producto producto : productosTemporales) {
                producto.setIdFactura(idFactura);
                String message = "3) Lista, producto: ";
                message += "\nIdFactura " + producto.getIdFactura();
                message += "\nIdProducto " + producto.getIdProducto();
                message += "\nNombre " + producto.getNombre();
                message += "\nDes " + producto.getDescripcion();
                message += "\n";
                message += "\nIdCATEGORIA : " + (producto.getCategoriaProducto() != null ? producto.getCategoriaProducto().getIdCategoriaProducto() : "null carajo");
                message += "\nCant Total " + producto.getCantidadTotal();
                message += "\nCant Ingresada " + producto.getCantidadIngresada();
                message += "\nCant Pendiente " + producto.getCantidadPendiente();
                message += "\nPrecUnit " + producto.getPrecioUnitario();
                message += "\nSub " + producto.getSubtotal();

                JOptionPane.showMessageDialog(null, message);

                dao.registrarProducto(producto);
                JOptionPane.showMessageDialog(null, "3) Se registro el producto id: " + producto.getNombre());
            }

            System.out.println("Factura y productos registrados exitosamente.");
        } catch (Exception e) {
            throw new Exception("Error al registrar la factura con sus respectuvos productos", e);
        }

    }

    public void editarFacturaConProductos(List<Producto> productosTemporales, int idFactura, int idProveedor, Date fechaRegistro, Date fechaVencimiento, String descripcion,
            Double montoTotal, Double montoPagado, Double montoPendiente) throws Exception {

        try {

            // Obtener la lista de productos asociados a la factura antes de modificarla
            List<Producto> productosAnteriores = dao.obtenerListaProductosporFacturaId(idFactura);

            // Registrar la factura
            invoice = new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, montoPendiente);

            System.out.println("DATOS para ver: . \nIdFactura: " + invoice.getIdFactura() + "\nMontoTotal: " + invoice.getMontoTotal() + "\nMontoPendiente: " + invoice.getMontoPendiente());

            dao.modificarFactura(invoice);

            // Registrar o editar los productos asociados a la factura
            for (Producto producto : productosTemporales) {
                producto.setIdFactura(idFactura);
                if (producto.getIdProducto() != 0) {
                    dao.modificarProducto(producto);
                } else {
                    dao.registrarProducto(producto);
                }
            }

            // Eliminar los productos que ya no están en la lista productosTemporales
            for (Producto productoAnterior : productosAnteriores) {
                boolean encontrado = false;
                for (Producto productoTemp : productosTemporales) {
                    if (productoAnterior.getIdProducto() == productoTemp.getIdProducto()) {
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    dao.eliminarProductoPorId(productoAnterior.getIdProducto());
                }
            }

            System.out.println("Factura y productos registrados exitosamente.");
        } catch (Exception e) {
            throw new Exception("Error al registrar la factura con sus respectuvos productos", e);
        }

    }

    public void llenarComboBoxProveedores(JComboBox<String> comboBox) {

        JComboBox<String> comboBoxProveedores = new JComboBox<>();

        try {
            List<Proveedor> proveedores = dao.obtenerListaProveedores("");

            for (Proveedor proveedor : proveedores) {
                comboBox.addItem(proveedor.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //comboBox = comboBoxProveedores;
    }

    public String buscarNombreProveedorPorFactura(int idFactura) throws Exception {
        try {
            String nombreProveedorPorFactura = dao.buscarNombreProveedorPorFactura(idFactura);
            return nombreProveedorPorFactura;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int buscarIdProveedorPorNombre(String nombreProveedor) throws Exception {
        try {
            int buscarIdProveedorPorNombre = dao.buscarIdProveedorPorNombre(nombreProveedor);
            return buscarIdProveedorPorNombre;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // aqui implementar lista de products temporales
    public DefaultTableModel agregarProductosTabla(JTable table, List<Producto> productosTemporales) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IdProducto");
        model.addColumn("IdFactura");
        model.addColumn("Nombre");
        model.addColumn("Descripcion");
        model.addColumn("Cantidad");
        model.addColumn("Precio Unitario");
        model.addColumn("Subtotal");

        productosTemporales.forEach((u) -> model.addRow(new Object[]{u.getIdProducto(), u.getIdFactura(), u.getNombre(), u.getDescripcion(), u.getCantidadTotal(), u.getPrecioUnitario(), u.getSubtotal()}));
        return model;
    }

    public void agregarProductosArrayProductos(List<Producto> productosTemporales, int facturaId) throws Exception {
        List<Producto> listaProductos = dao.obtenerListaProductosporFacturaId(facturaId);
        for (Producto producto : listaProductos) {
            productosTemporales.add(producto);
        }
    }

    public void agregarNuevoProductoArrayProductos(List<Producto> productosTemporales, int productoId, int facturaId,
            String nombreProd, String descripcionProd, String cantidadProd, String precioUnitarioProd) throws Exception {

        int idProducto = productoId;
        int idFactura = facturaId;
        String nombre = nombreProd;
        String descripcion = descripcionProd;
        int cantidadTotal = Integer.parseInt(cantidadProd);
        double precioUnitario = Double.parseDouble(precioUnitarioProd);
        double subtotal = (cantidadTotal * precioUnitario);

        Producto producto = new Producto(idProducto, idFactura, nombre, descripcion, null, cantidadTotal, 0, cantidadTotal, precioUnitario, subtotal);

        String message = "newww, producto: ";
        message += "\nIdFactura " + producto.getIdFactura();
        message += "\nIdProducto " + producto.getIdProducto();
        message += "\nNombre " + producto.getNombre();
        message += "\nDes " + producto.getDescripcion();
        message += "\nIdCatego " + producto.getCategoriaProducto() == null ? producto.getCategoriaProducto().getIdCategoriaProducto() : "null carajo";
        message += "\nCant Total " + producto.getCantidadTotal();
        message += "\nCant Ingresada " + producto.getCantidadIngresada();
        message += "\nCant Pendiente " + producto.getCantidadPendiente();
        message += "\nPrecUnit " + producto.getPrecioUnitario();
        message += "\nSub " + producto.getSubtotal();

        JOptionPane.showMessageDialog(null, message);

        productosTemporales.add(producto);

    }

    public Producto buscarProductoPorId(List<Producto> productosTemporales, int idProducto) {
        for (Producto producto : productosTemporales) {
            if (producto.getIdProducto() == idProducto) {
                return producto;
            }
        }
        return null; // Si no se encuentra el producto con el ID especificado
    }

    //nuevo: aplicando solucion al id 0
    public Producto buscarProductoPorNombre(List<Producto> productosTemporales, String nombreProducto) {
        for (Producto producto : productosTemporales) {
            if (producto.getNombre().equals(nombreProducto)) {
                return producto;
            }
        }
        return null;
    }

    public void eliminarProductoPorId(List<Producto> productosTemporales, int idProducto) {
        Iterator<Producto> iterator = productosTemporales.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getIdProducto() == idProducto) {
                iterator.remove();
                return;
            }
        }
    }

    //nuevo: aplicando solucion al id 0
    public void eliminarProductoPorNombre(List<Producto> productosTemporales, String nombreProducto) {
        Producto productoAEliminar = null;
        for (Producto producto : productosTemporales) {
            if (producto.getNombre().equals(nombreProducto)) {
                productoAEliminar = producto;
                break;
            }
        }
        if (productoAEliminar != null) {
            productosTemporales.remove(productoAEliminar);
        }
    }

    public void editarProductoArrayProductos(List<Producto> productosTemporales, Producto productoEdicion,
            String nombreProd, String descripcionProd, String cantidadProd, String precioUnitarioProd) {

        String nombre = nombreProd;
        String descripcion = descripcionProd;
        int cantidadTotal = Integer.parseInt(cantidadProd);
        double precioUnitario = Double.parseDouble(precioUnitarioProd);
        double subtotal = (cantidadTotal * precioUnitario);

        for (Producto producto : productosTemporales) {
            if (producto.getNombre().equals(productoEdicion.getNombre())) {

                // Actualizar los datos del producto con los del productoEdicion
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setCantidadTotal(cantidadTotal);
                producto.setPrecioUnitario(precioUnitario);
                producto.setSubtotal(subtotal);

                // Detener el ciclo
                break;

            }
        }
    }

    /*VALIDACIONES--------------------------------------------------*/
    public boolean existeProductoConNombre(List<Producto> productosTemporales, String nombreProducto) {
        for (Producto producto : productosTemporales) {
            if (producto.getNombre().equals(nombreProducto)) {
                return true;
            }
        }
        return false;
    }

    public double calcularMontoTotal(List<Producto> productosTemporales) {
        double montoTotal = 0.0;
        for (Producto producto : productosTemporales) {
            montoTotal += producto.getSubtotal();
        }
        return montoTotal;
    }

    public boolean validarFechas(Date fechaRegistro, Date fechaVencimiento) {  // valida que la fecha registro sea menor a la fecha vencimiento
        return fechaRegistro.before(fechaVencimiento);
    }

    public boolean validarMontoLineaCredito(double nuevoMontoTotalARegistrar, int idProveedor, int idFacturaInEdition) throws Exception {
        Proveedor proveedor = dao.obtenerProveedorPorId(idProveedor);
        double lineaCredito = proveedor.getLineaCredito();

        double sumaTotalMontoFacturasRegistradas = 0;
        double sumaMontoFacturasMasNuevoMonto;

        List<Factura> listaFacturasdeProveedor = dao.obtenerListaFacturasPorIdProveedor(idProveedor);

        if (idFacturaInEdition != 0) { //SI EXISTE UNA FACTURA SIENDO EDITADA
            for (Factura factura : listaFacturasdeProveedor) {
                if (factura.getIdFactura() != idFacturaInEdition) {
                    sumaTotalMontoFacturasRegistradas += factura.getMontoTotal();
                }
            }
        } else { //SI NO EXISTE UNA FACTURA SIENDO EDITADA
            for (Factura factura : listaFacturasdeProveedor) {
                sumaTotalMontoFacturasRegistradas += factura.getMontoTotal();
            }
        }

        sumaMontoFacturasMasNuevoMonto = sumaTotalMontoFacturasRegistradas + nuevoMontoTotalARegistrar;

        if (sumaMontoFacturasMasNuevoMonto <= lineaCredito) {
            return true;
        } else {
            return false;
        }
    }

    public boolean confirmarDatosProducto(String nombre, String descripcion, String cantidadTotal, String precioUnitario) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos del Proveedor *****";
        message += "\nNombre: " + nombre;
        message += "\nDescripcion: " + descripcion;
        message += "\ncantidad: " + cantidadTotal;
        message += "\nprecio Unitario: " + precioUnitario;
        message += "\n";
        message += "\nLos datos son correctos?";

        UIManager.put("Button.yesButtonText", "Aceptar");
        UIManager.put("Button.noButtonText", "Cancelar");

        int result = JOptionPane.showConfirmDialog(null, message, title, optionType);
        switch (result) {
            case JOptionPane.YES_OPTION:
                return true;
            case JOptionPane.NO_OPTION:
                return false;

            case JOptionPane.CLOSED_OPTION:
                return false;
            default:
                break;
        }
        return false;
    }

    public boolean confirmarDatosFactura(List<Producto> productosTemporales, String proveedor, String fechaRegistro, String fechaVencimiento, String montoTotal, String montoPagado, String montoPendiente) {
        String message;
        int cantidadProductos = productosTemporales.size();
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos del Proveedor *****";
        message += "\nProveedor: " + proveedor;
        message += "\nFecha Registro: " + fechaRegistro;
        message += "\nFecha Vencimiento: " + fechaVencimiento;
        message += "\nMonto Total: " + montoTotal;
        message += "\nMonto Pagado: " + montoPagado;
        message += "\nMonto Pendiente: " + montoPendiente;
        message += "\nCantidad total de productos: " + cantidadProductos;
        message += "\n";
        message += "\nLos datos son correctos?";

        UIManager.put("Button.yesButtonText", "Aceptar");
        UIManager.put("Button.noButtonText", "Cancelar");

        int result = JOptionPane.showConfirmDialog(null, message, title, optionType);
        switch (result) {
            case JOptionPane.YES_OPTION:
                return true;
            case JOptionPane.NO_OPTION:
                return false;

            case JOptionPane.CLOSED_OPTION:
                return false;
            default:
                break;
        }
        return false;
    }
}

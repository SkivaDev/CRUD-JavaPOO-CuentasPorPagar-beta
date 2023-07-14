/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controladores;

import com.proyecto.baseDatos.consultas.DAOAlmaceneroImpl;
import com.proyecto.baseDatos.consultas.DAOEncargadoComprasImpl;
import com.proyecto.entidades.CategoriaProducto;
import com.proyecto.entidades.Inventario;
import com.proyecto.entidades.MovimientoInventario;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Usuario;
import static com.proyecto.utils.Utils.obtenerFechaActualDate;
import com.proyecto.vista.VentanaDashboard;
import com.proyecto.vista.VentanaBETAAAExpedienteProveedor;
import com.proyecto.vista.VentanaDetalleExpedienteProveedor;
import com.proyecto.vista.VentanaRegistroProveedor;
import com.proyecto.vista.VentanaRegistroUsuario;
import java.awt.Component;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author skiva
 */
public class ControladorRegistroIngresoInventario {

    private DAOAlmaceneroImpl dao;

    //private Usuario user;
    public ControladorRegistroIngresoInventario() {
        this.dao = new DAOAlmaceneroImpl();
    }

    public DefaultTableModel listarProductos(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Limpiar el modelo de la tabla
        model.setRowCount(0);
        table.setModel(model);

        model.addColumn("IDProducto");
        model.addColumn("Nombre");
        model.addColumn("Descripción");
        model.addColumn("Categoría");
        model.addColumn("Cant. Total");
        model.addColumn("Cant. Ingresada");
        model.addColumn("Cant. Pendiente");

        try {

            dao.obtenerListaProductos("").forEach((u) -> {
                Object nombreCategoria = (u.getCategoriaProducto().getNombreCategoria() != null) ? u.getCategoriaProducto().getNombreCategoria() : "ninguna";

                model.addRow(new Object[]{u.getIdProducto(), u.getNombre(), u.getDescripcion(),
                    nombreCategoria, u.getCantidadTotal(), u.getCantidadIngresada(), u.getCantidadPendiente()});
            });

            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void registrarIngresoInventario(Producto productoSeleccionado, CategoriaProducto categoriaProductoSeleccionada, int cantidadProductosIngresoInventario) throws Exception {
        Producto productoIdentificado = null;
        
        
        //Buscar si existe en el inventario un producto ya registrado con el idProducto
        List<Producto> listaProductosEnInventario = dao.obtenerListaProductosEnInventario();

        boolean productoEstaRegistradoInventario = false;
        for (Producto producto : listaProductosEnInventario) {
            if(producto.getIdProducto() == productoSeleccionado.getIdProducto()){
                productoIdentificado = producto;
                productoEstaRegistradoInventario = true;
                break;
            }
            javax.swing.JOptionPane.showMessageDialog(null, "Ahorita es: . \n" + productoEstaRegistradoInventario, "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        javax.swing.JOptionPane.showMessageDialog(null, "AL FINAL TERMINO CON. \n" + productoEstaRegistradoInventario, "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        

        if (productoEstaRegistradoInventario) { //Si existe aumentar la cantidad en inventario de ese producto
            javax.swing.JOptionPane.showMessageDialog(null, "YA ESTA REGISTRADO PEPEPE. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);

            
            Inventario inventarioDelProducto = dao.obtenerInventarioPorIdProducto(productoSeleccionado.getIdProducto());

                //AGREGAR CANTIDAD DE PRODUCTOS AL INVENTARIO
                agregarCantidadAInventarioPorIngreso(inventarioDelProducto, cantidadProductosIngresoInventario);

                //REGISTRAR MOVIMIENTO INVENTARIO
                registrarMovimientoInventarioPorIngreso(productoSeleccionado, inventarioDelProducto, cantidadProductosIngresoInventario);

                //ACTUALIZAR LA CANTIDAD CANTIDAD ENTREGADA, CANTIDAD PENDIENTE del producto
                actualizarCantidadesProductoPorIngreso(productoSeleccionado, cantidadProductosIngresoInventario);

        } else { //Si NO existe registrar un nuevo idInventario con el id del producto, su nombre, y la cantidad que se esta ingresando.

            javax.swing.JOptionPane.showMessageDialog(null, "SUpuestamente se ingresa uno nuevo: . \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);


                //REGISTRAR NUEVO INVENTARIO CON SU PRODUCTO.
                int idInventarioRegistrado = registrarNuevoInventarioPorIngreso(productoSeleccionado, cantidadProductosIngresoInventario);
                
                //REGISTRAR MOVIMIENTO INVENTARIO
                Inventario inventarioRegistrado = dao.obtenerInventarioPorIdProducto(productoSeleccionado.getIdProducto());
                registrarMovimientoInventarioPorIngreso(productoSeleccionado, inventarioRegistrado, cantidadProductosIngresoInventario);

                //ACTUALIZAR LA CANTIDAD CANTIDAD ENTREGADA, CANTIDAD PENDIENTE del producto
                actualizarCantidadesProductoPorIngreso(productoSeleccionado, cantidadProductosIngresoInventario);

        }

        javax.swing.JOptionPane.showMessageDialog(null, "Ninguno de los dos pincjo: . \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);

        //CATEGORIZACION:
        //MODIFICAR LA CATEGORIA DEL PRODUCTO
        dao.modificarIdCategoriaEstablecidaProductoPorId(productoSeleccionado.getIdProducto(), categoriaProductoSeleccionada.getIdCategoriaProducto());
    }

    public void agregarCantidadAInventarioPorIngreso(Inventario inventory, int cantidadProductosIngresar) throws Exception {

        Inventario inventarioDelProducto = inventory;
        int cantidadEnInventarioAntes = inventarioDelProducto.getCantidadProducto();
        int cantidadEnInventarioDespues = (cantidadEnInventarioAntes + cantidadProductosIngresar);

        dao.modificarCantidadProductoInventarioPorId(inventarioDelProducto.getIdInventario(), cantidadEnInventarioDespues);

    }

    public void registrarMovimientoInventarioPorIngreso(Producto product, Inventario inventory, int cantidadProductosIngresar) throws Exception {
        MovimientoInventario movimientoInventario = null;

        Producto productoSeleccionado = product;

        Inventario inventario = inventory;

        String tipoMovimiento = "Ingreso";

        Date fechaRegistroDate = obtenerFechaActualDate();
        int cantidadProductos = cantidadProductosIngresar;

        movimientoInventario = new MovimientoInventario(0, inventario, productoSeleccionado,
                cantidadProductos, tipoMovimiento, fechaRegistroDate);

        dao.registrarMovimientoInventario(movimientoInventario);
    }

    public void actualizarCantidadesProductoPorIngreso(Producto product, int cantidadProductosIngresar) throws Exception {

        Producto productoSeleccionado = product;

        int cantidadTotal = productoSeleccionado.getCantidadTotal();
        int cantidadIngresadaAntes = productoSeleccionado.getCantidadIngresada();

        int cantidadIngresadaDespues = (cantidadIngresadaAntes + cantidadProductosIngresar);
        int cantidadPendienteDespues = (cantidadTotal - cantidadIngresadaDespues);

        dao.modificarCantidadesProductoPorId(productoSeleccionado.getIdProducto(), cantidadIngresadaDespues, cantidadPendienteDespues);

    }

    public int registrarNuevoInventarioPorIngreso(Producto product, int cantidadProductosIngresar) throws Exception {

        Producto productoSeleccionado = product;

        Inventario nuevoInventario = new Inventario(0, productoSeleccionado.getIdProducto(),
                productoSeleccionado.getNombre(), cantidadProductosIngresar);
        int idInventarioRegistrado = dao.registrarInventario(nuevoInventario);
        return idInventarioRegistrado;
    }

    public Producto seleccionarProducto(JTable table) {
        if (table.getSelectedRow() > -1) {
            try {
                int productId = (int) table.getValueAt(table.getSelectedRow(), 0);
                return dao.obtenerProductoPorId(productId);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Debes seleccionar el producto a ingresar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public DefaultTableModel buscarProductos(JTable table, String name) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            dao.obtenerListaProductos(name).forEach((u) -> {
                Object nombreCategoria = (u.getCategoriaProducto().getNombreCategoria() != null) ? u.getCategoriaProducto().getNombreCategoria() : "ninguna";

                model.addRow(new Object[]{u.getIdProducto(), u.getNombre(), u.getDescripcion(),
                    nombreCategoria, u.getCantidadTotal(), u.getCantidadIngresada(), u.getCantidadPendiente()});
            });
            return model;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void llenarComboBoxCategoriasProducto(JComboBox<CategoriaProducto> categoryComboBox) {

        try {
            List<CategoriaProducto> categoriasProducto = dao.obtenerListaCategoriasProducto("");
            categoryComboBox.removeAllItems();

            // Crear un DefaultComboBoxModel con la lista de categorias
            DefaultComboBoxModel<CategoriaProducto> model = new DefaultComboBoxModel<>(categoriasProducto.toArray(new CategoriaProducto[0]));
            categoryComboBox.setModel(model);

            // Configurar el ListCellRenderer para mostrar solo el nombre de la categoria
            categoryComboBox.setRenderer(new ListCellRenderer<CategoriaProducto>() {
                public Component getListCellRendererComponent(JList<? extends CategoriaProducto> list, CategoriaProducto value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    JLabel label = new JLabel();
                    if (value != null) {
                        label.setText(value.getNombreCategoria()); // Mostrar el nombre del producto si no es nulo
                    } else {
                        label.setText("Sin categorias"); // Mostrar un texto predeterminado si es nulo
                    }
                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }
                    label.setOpaque(true);
                    return label;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean confirmarDatosIngresoInventario(Producto productoElegido, CategoriaProducto nuevaCategoria, int cantidadProductosIngresoInventario) {
        String message;
        String title = "Confirmación";
        int optionType = JOptionPane.YES_NO_OPTION;

        message = "***** Datos de ingreso a inventario *****";
        message += "\nNombre de Producto: " + productoElegido.getNombre();
        message += "\nDescripcion: " + productoElegido.getDescripcion();
        message += "\nCategoría: " + nuevaCategoria.getNombreCategoria();
        message += "\nPrecio unitario: " + productoElegido.getPrecioUnitario();
        message += "\nCantidad Total: " + productoElegido.getCantidadTotal();
        message += "\nCantidad Entregada: " + productoElegido.getCantidadIngresada();
        message += "\nCantidad Pendiente: " + productoElegido.getCantidadPendiente();
        message += "\n";
        message += "\nCantidad que se ingresará: " + cantidadProductosIngresoInventario;
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

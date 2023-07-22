/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.baseDatos.consultas;

import com.proyecto.baseDatos.GestorBaseDatos;
import com.proyecto.entidades.Canje;
import com.proyecto.entidades.CategoriaProducto;
import com.proyecto.entidades.Cheque;
import com.proyecto.entidades.CuentaBancaria;
import com.proyecto.entidades.DetalleFactura;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Inventario;
import com.proyecto.entidades.MovimientoBancario;
import com.proyecto.entidades.MovimientoInventario;
import com.proyecto.entidades.PagoFactura;
import com.proyecto.entidades.PagoProgramado;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.entidades.SolicitudPago;
import com.proyecto.interfaces.DAOEncargadoComprasInterfaz;
import com.proyecto.interfaces.DAOTesoreroInterfaz;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skiva
 */
public class DAOTesoreroImpl extends GestorBaseDatos implements DAOTesoreroInterfaz {

    /*
    @Override
    public void registrarProveedor(Proveedor supplier) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO proveedores (nombre, direccion, telefono, linea_credito) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getNombre());
            statement.setString(2, supplier.getDireccion());
            statement.setString(3, supplier.getTelefono());
            statement.setDouble(4, supplier.getLineaCredito());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idProveedor = generatedKeys.getInt(1);
                supplier.setIdProveedor(idProveedor);
            } else {
                throw new SQLException("Error al obtener el ID generado para el Proveedor");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el Proveedor en la base de datos", e);
        }
    }
    
    @Override
    public void modificarProveedor(Proveedor supplier) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE proveedores SET nombre = ?, direccion = ?, telefono = ?, linea_credito = ? WHERE id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, supplier.getNombre());
            statement.setString(2, supplier.getDireccion());
            statement.setString(3, supplier.getTelefono());
            statement.setDouble(4, supplier.getLineaCredito());
            statement.setInt(5, supplier.getIdProveedor());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar el Proveedor en la base de datos", e);
        }
    }

    @Override
    public void eliminarProveedor(int supplierId) throws Exception {
        try {
            this.Conectar();
            String consulta = "DELETE FROM proveedores WHERE id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, supplierId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el Proveedor de la base de datos", e);
        }
    }

    @Override
    public List<Proveedor> obtenerListaProveedores(String name) throws Exception {
        try {
            this.Conectar();
            String consulta = name.isEmpty() ? "SELECT * FROM proveedores" : "SELECT * FROM proveedores WHERE nombre LIKE '%" + name + "%';";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<Proveedor> proveedores = new ArrayList<>(); // nombre, direccion, telefono, linea_credito
            while (resultSet.next()) {
                int idProveedor = resultSet.getInt("id_proveedor");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                Double lineaCredito = resultSet.getDouble("linea_credito");
                Proveedor proveedor;

                proveedor = new Proveedor(idProveedor, nombre, direccion, telefono, lineaCredito);
                proveedores.add(proveedor);
            }

            return proveedores;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener Lista Proveedores la base de datos", e);
        }
    }

    @Override
    public Proveedor obtenerProveedorPorId(int supplierId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM proveedores WHERE id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProveedor = resultSet.getInt("id_proveedor");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                Double lineaCredito = resultSet.getDouble("linea_credito");

                return new Proveedor(idProveedor, nombre, direccion, telefono, lineaCredito);
            }

            // Si no se encuentra el usuario, puedes lanzar una excepción o retornar null, según tu necesidad
            throw new SQLException("No se encontró ningún proveedor con el ID proporcionado");
        } catch (SQLException e) {
            throw new SQLException("Error al consultar obtenerProveedorPorId la base de datos", e);
        }
    }

    @Override
    public int buscarIdProveedorPorNombre(String nombreProveedor) throws Exception {
        int idProveedor = 0;
        try {
            this.Conectar();
            String consulta = "SELECT id_proveedor FROM proveedores WHERE nombre = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setString(1, nombreProveedor);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idProveedor = resultSet.getInt("id_proveedor");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException("Error al buscar el ID del proveedor por nombre en la base de datos", e);
        }

        return idProveedor;
    }

    @Override
    public boolean supplierNameEnUso(String supplierName) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT COUNT(*) FROM proveedores WHERE id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, supplierName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            // Cerrar la conexión y liberar recursos
            resultSet.close();
            statement.close();
            this.Cerrar();
        } catch (Exception e) {
            throw new SQLException("Error al consultar si existe un proveedor con el supplierName la base de datos", e);
        }

        // Si ocurre algún error, se asume que el nombre de usuario no está tomado
        return false;
    }
     */
 /*METODOS FACTURA---------------------------------------------------------------------------------------------------------------*/
    //al momento de registrar la factura este se debe registrar el monto pagado en 0, monto pendiente en 0, 
    //y el monto total se calcula automaticamente de los productos registrados montouni x cantidad
    /*
    @Override
    public void registrarFactura(Factura invoice) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO facturas (id_proveedor, fecha_registro, fecha_vencimiento, descripcion, monto_total, monto_pagado, monto_pendiente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, invoice.getIdProveedor());
            statement.setDate(2, new java.sql.Date(invoice.getFechaRegistro().getTime()));
            statement.setDate(3, new java.sql.Date(invoice.getFechaVencimiento().getTime()));
            statement.setString(4, invoice.getDescripcion());
            statement.setDouble(5, invoice.getMontoTotal());
            statement.setDouble(6, invoice.getMontoPagado());
            statement.setDouble(7, invoice.getMontoPendiente());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idFactura = generatedKeys.getInt(1);
                invoice.setIdFactura(idFactura);
            } else {
                throw new SQLException("Error al obtener el ID generado para la factura");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar la factura en la base de datos", e);
        }
    }

    //al momento en que se edite la factura, este editara al proveedor que pertenece y eso solo afectara a la linea de credito del proveedor
    //al momento de 
    @Override
    public void modificarFactura(Factura invoice) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE facturas SET id_proveedor = ?, fecha_registro = ?, fecha_vencimiento = ?, descripcion = ?, monto_total = ? , monto_pendiente = ? WHERE id_factura = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, invoice.getIdProveedor());
            statement.setDate(2, new java.sql.Date(invoice.getFechaRegistro().getTime()));
            statement.setDate(3, new java.sql.Date(invoice.getFechaVencimiento().getTime()));
            statement.setString(4, invoice.getDescripcion());
            statement.setDouble(5, invoice.getMontoTotal());
            statement.setDouble(6, invoice.getMontoPendiente());
            statement.setInt(7, invoice.getIdFactura());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar la factura en la base de datos", e);
        }
    }

    @Override
    public void eliminarFactura(int invoiceId) throws Exception {
        try {
            this.Conectar();
            String consulta = "DELETE FROM facturas WHERE id_factura = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, invoiceId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar la factura de la base de datos", e);
        }
    }
     */
    @Override
    public List<Factura> obtenerListaFacturas(String name) throws Exception {
        try {
            this.Conectar();
            String consulta = name.isEmpty() ? "SELECT * FROM facturas" : "SELECT * FROM facturas JOIN proveedores ON facturas.id_proveedor = proveedores.id_proveedor WHERE proveedores.nombre LIKE '%" + name + "%';";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<Factura> facturas = new ArrayList<>();
            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idProveedor = resultSet.getInt("id_proveedor");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                Date fechaVencimiento = resultSet.getDate("fecha_vencimiento");
                String descripcion = resultSet.getString("descripcion");
                Double montoTotal = resultSet.getDouble("monto_total");
                Double montoPagado = resultSet.getDouble("monto_pagado");
                Double montoPendiente = resultSet.getDouble("monto_pendiente");

                Factura factura = new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, montoPendiente);
                facturas.add(factura);
            }

            return facturas;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de facturas de la base de datos", e);
        }
    }

    /*
    @Override
    public List<Factura> obtenerListaFacturasPorIdProveedor(int supplierId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM facturas WHERE id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();

            List<Factura> facturas = new ArrayList<>(); // idFactura, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, montoPendiente
            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idProveedor = resultSet.getInt("id_proveedor");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                Date fechaVencimiento = resultSet.getDate("fecha_vencimiento");
                String descripcion = resultSet.getString("descripcion");
                Double montoTotal = resultSet.getDouble("monto_total");
                Double montoPagado = resultSet.getDouble("monto_pagado");
                Double montoPendiente = resultSet.getDouble("monto_pendiente");

                Factura factura = new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, montoPendiente);
                facturas.add(factura);
            }

            return facturas;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de facturas por ID de proveedor de la base de datos", e);
        }
    }
     */
    @Override
    public Factura obtenerFacturaPorId(int invoiceId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM facturas WHERE id_factura = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, invoiceId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idProveedor = resultSet.getInt("id_proveedor");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                Date fechaVencimiento = resultSet.getDate("fecha_vencimiento");
                String descripcion = resultSet.getString("descripcion");
                Double montoTotal = resultSet.getDouble("monto_total");
                Double montoPagado = resultSet.getDouble("monto_pagado");
                Double montoPendiente = resultSet.getDouble("monto_pendiente");

                return new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, montoPendiente);
            }

            // Si no se encuentra el usuario, puedes lanzar una excepción o retornar null, según tu necesidad
            throw new SQLException("No se encontró ninguna factura con el ID proporcionado");
        } catch (SQLException e) {
            throw new SQLException("Error al consultar obtenerFacturaPorId la base de datos", e);
        }
    }

    /*PRODUCTOS--------------------------------------------------*/
 /*
    @Override
    public void registrarProducto(Producto product) throws Exception { // cuando el encargado de compras registra la factura se crea el producto por ende ya es existente
        try {
            this.Conectar();
            String consulta = "INSERT INTO productos (id_factura, nombre, descripcion, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, product.getIdFactura());
            statement.setString(2, product.getNombre());
            statement.setString(3, product.getDescripcion());
            statement.setInt(4, product.getCantidad());
            statement.setDouble(5, product.getPrecioUnitario());
            statement.setDouble(6, product.getSubtotal());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idProducto = generatedKeys.getInt(1);
                product.setIdProducto(idProducto);
            } else {
                throw new SQLException("Error al obtener el ID generado para el producto");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el producto en la base de datos", e);
        }
    }

    @Override
    public void modificarProducto(Producto product) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE productos SET id_factura = ?, nombre = ?, descripcion = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id_producto = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, product.getIdFactura());
            statement.setString(2, product.getNombre());
            statement.setString(3, product.getDescripcion());
            statement.setInt(4, product.getCantidad());
            statement.setDouble(5, product.getPrecioUnitario());
            statement.setDouble(6, product.getSubtotal());
            statement.setInt(7, product.getIdProducto());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar la factura en la base de datos", e);
        }
    }

    @Override
    public void eliminarProductosPorIdFactura(int idFactura) throws Exception { // este metodo se utiliza al momento que el encargado compras elimina una factura, automaticamente se debe eliminar los productos registrados en ella.
        try {
            this.Conectar();
            String consulta = "DELETE FROM productos WHERE id_factura = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, idFactura);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar los productos de la factura en la base de datos", e);
        }
    }

    @Override
    public List<Producto> obtenerListaProductosporFacturaId(int facturaId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM productos WHERE id_factura = " + facturaId;
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<Producto> productos = new ArrayList<>(); // idProducto, idFactura, nombre, descripcion, cantidad, precioUnitario, subtotal
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idFactura = resultSet.getInt("id_factura");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int cantidad = resultSet.getInt("cantidad");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double subtotal = resultSet.getDouble("subtotal");

                Producto producto = new Producto(idProducto, idFactura, nombre, descripcion, cantidad, precioUnitario, subtotal);
                productos.add(producto);
            }

            return productos;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de productos por factura en la base de datos", e);
        }
    }

    @Override
    public int obtenerUltimaFacturaRegistrada() throws Exception {
        int idFactura = 0;
        try {
            this.Conectar();
            String consulta = "SELECT MAX(id_factura) AS max_id FROM facturas";
            Statement statement = this.conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            if (resultSet.next()) {
                idFactura = resultSet.getInt("max_id");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el ID de la última factura registrada", e);
        }

        return idFactura;
    }

     */

 /*EXTRAS-------------------------------------------------------------*/
    @Override
    public String buscarNombreProveedorPorFactura(int invoiceId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT proveedores.nombre AS nombre_proveedor FROM proveedores "
                    + "INNER JOIN facturas ON proveedores.id_proveedor = facturas.id_proveedor "
                    + "WHERE facturas.id_factura = " + invoiceId;
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            if (resultSet.next()) {
                String nombreProveedor = resultSet.getString("nombre_proveedor");
                return nombreProveedor;
            }

            return null;
        } catch (SQLException e) {
            throw new SQLException("Error al buscar el nombre del proveedor por factura en la base de datos", e);
        }
    }

    /*VERIFICACIONES-------------------------------------------------------------*/
 /*
    @Override
    public boolean existeRegistroPago(int idFactura) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT COUNT(*) AS total FROM movimientos_facturas WHERE id_factura = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, idFactura);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int totalRegistros = resultSet.getInt("total");
                return totalRegistros > 0;
            }

            return false;
        } catch (SQLException e) {
            throw new SQLException("Error al verificar los registros de pago en la base de datos", e);
        }
    }


     */
    @Override
    public List<CuentaBancaria> obtenerListaCuentasBancarias() throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM cuentas_bancarias";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<CuentaBancaria> cuentasBancarias = new ArrayList<>();
            while (resultSet.next()) {
                int idCuenta = resultSet.getInt("id_cuenta");
                String nombreBanco = resultSet.getString("nombre_banco");
                String tipoCuenta = resultSet.getString("tipo_cuenta_bancaria");
                Double saldoActual = resultSet.getDouble("saldo_actual");
                Double saldoPrevio = resultSet.getDouble("saldo_previo");

                CuentaBancaria cuentaBancaria = new CuentaBancaria(idCuenta, nombreBanco, tipoCuenta, saldoActual, saldoPrevio);
                cuentasBancarias.add(cuentaBancaria);
            }

            return cuentasBancarias;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de cuentas bancarias de la base de datos", e);
        }
    }

    @Override
    public CuentaBancaria obtenerCuentaBancariaPorNombre(String name) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM cuentas_bancarias WHERE nombre_banco = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idCuenta = resultSet.getInt("id_cuenta");
                String nombreBanco = resultSet.getString("nombre_banco");
                String tipoCuenta = resultSet.getString("tipo_cuenta_bancaria");
                double saldoActual = resultSet.getDouble("saldo_actual");
                double saldoPrevio = resultSet.getDouble("saldo_previo");

                CuentaBancaria cuentaBancaria = new CuentaBancaria(idCuenta, nombreBanco, tipoCuenta, saldoActual, saldoPrevio);
                return cuentaBancaria;
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener la cuenta bancaria de la base de datos", e);
        } finally {
            this.Cerrar();
        }

        return null;
    }

    @Override
    public List<Producto> obtenerListaProductosPorProveedorId(int supplierId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM productos JOIN facturas ON productos.id_factura = facturas.id_factura WHERE facturas.id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();

            List<Producto> productos = new ArrayList<>();
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idFactura = resultSet.getInt("id_factura");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int idCategoriaProducto = resultSet.getInt("id_categoria_producto");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadIngresada = resultSet.getInt("cantidad_ingresada");
                int cantidadPendiente = resultSet.getInt("cantidad_pendiente");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double subtotal = resultSet.getDouble("subtotal");

                CategoriaProducto categoriaProducto = obtenerCategoriaProductoPorId(idCategoriaProducto);

                Producto producto = new Producto(idProducto, idFactura, nombre, descripcion, categoriaProducto, cantidadTotal, cantidadIngresada, cantidadPendiente, precioUnitario, subtotal);
                productos.add(producto);
            }

            return productos;

        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de productos (obtenerListaProductosPorProveedorId) de la base de datos", e);
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Factura> obtenerListaFacturasPorProveedorId(int supplierId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM facturas WHERE id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();

            List<Factura> facturas = new ArrayList<>();
            while (resultSet.next()) {
                int idFactura = resultSet.getInt("id_factura");
                int idProveedor = resultSet.getInt("id_proveedor");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                Date fechaVencimiento = resultSet.getDate("fecha_vencimiento");
                String descripcion = resultSet.getString("descripcion");
                double montoTotal = resultSet.getDouble("monto_total");
                double montoPagado = resultSet.getDouble("monto_pagado");
                double montoPendiente = resultSet.getDouble("monto_pendiente");

                Factura factura = new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, montoPendiente);
                facturas.add(factura);
            }

            return facturas;

        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de facturas(obtenerListaFacturasPorProveedorId) de la base de datos", e);
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public CategoriaProducto obtenerCategoriaProductoPorId(int productCategoryId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM categorias_producto WHERE id_categoria_producto = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, productCategoryId);
            ResultSet resultSet = statement.executeQuery();

            CategoriaProducto categoriaProducto = null;
            if (resultSet.next()) {
                int idCategoriaProducto = resultSet.getInt("id_categoria_producto");
                String nombreCategoria = resultSet.getString("nombre_categoria");
                String descripcionCategoria = resultSet.getString("descripcion_categoria");

                categoriaProducto = new CategoriaProducto(idCategoriaProducto, nombreCategoria, descripcionCategoria);
            }

            return categoriaProducto;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la categoría de producto por ID de la base de datos", e);
        }
    }

    @Override
    public List<CategoriaProducto> obtenerListaCategoriasProducto() throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM categorias_producto";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<CategoriaProducto> categoriasProducto = new ArrayList<>();
            while (resultSet.next()) {
                int idCategoriaProducto = resultSet.getInt("id_categoria_producto");
                String nombreCategoria = resultSet.getString("nombre_categoria");
                String descripcionCategoria = resultSet.getString("descripcion_categoria");

                CategoriaProducto categoriaProducto = new CategoriaProducto(idCategoriaProducto, nombreCategoria, descripcionCategoria);
                categoriasProducto.add(categoriaProducto);
            }

            return categoriasProducto;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de cuentas bancarias de la base de datos", e);
        }
    }

    @Override
    public List<Producto> obtenerListaProductosPorNombreCategoria(String categoryName) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT p.* FROM productos p INNER JOIN categorias_producto c ON p.id_categoria_producto = c.id_categoria_producto WHERE c.nombre_categoria = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setString(1, categoryName);
            ResultSet resultSet = statement.executeQuery();

            List<Producto> productos = new ArrayList<>();
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idFactura = resultSet.getInt("id_factura");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int idCategoriaProducto = resultSet.getInt("id_categoria_producto");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadIngresada = resultSet.getInt("cantidad_ingresada");
                int cantidadPendiente = resultSet.getInt("cantidad_pendiente");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double subtotal = resultSet.getDouble("subtotal");

                CategoriaProducto categoriaProducto = obtenerCategoriaProductoPorId(idCategoriaProducto);

                Producto producto = new Producto(idProducto, idFactura, nombre, descripcion, categoriaProducto, cantidadTotal, cantidadIngresada, cantidadPendiente, precioUnitario, subtotal);
                productos.add(producto);
            }

            return productos;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de productos por nombre de categoría de la base de datos", e);
        }
    }

    @Override
    public int registrarCheque(Cheque check) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO cheques (id_factura, monto_cheque, id_cuenta_bancaria, fecha_emicion, estado_cheque) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, check.getFactura().getIdFactura());
            statement.setDouble(2, check.getMontoCheque());
            statement.setInt(3, check.getCuentaBancaria().getIdCuentaBancaria());
            statement.setDate(4, new java.sql.Date(check.getFechaEmicion().getTime()));
            statement.setString(5, check.getEstadoCheque());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCheque = generatedKeys.getInt(1);
                return idCheque;
            } else {
                throw new SQLException("Error al obtener el ID generado para el cheque");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el cheque en la base de datos", e);
        }
    }

    @Override
    public Cheque obtenerChequePorId(int checkId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM cheques WHERE id_cheque = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, checkId);
            ResultSet resultSet = statement.executeQuery();

            Cheque cheque = null;
            if (resultSet.next()) {
                int idCheque = resultSet.getInt("id_cheque");
                int idFactura = resultSet.getInt("id_factura");
                double montoCheque = resultSet.getDouble("monto_cheque");
                int idCuentaBancaria = resultSet.getInt("id_cuenta_bancaria");
                Date fechaEmicion = resultSet.getDate("fecha_emicion");
                String estadoCheque = resultSet.getString("estado_cheque");

                // Aquí debes obtener la factura y la cuenta bancaria correspondientes utilizando los métodos adecuados
                Factura factura = obtenerFacturaPorId(idFactura); // Utiliza el método adecuado para obtener la factura por su ID
                CuentaBancaria cuentaBancaria = obtenerCuentaBancariaPorId(idCuentaBancaria); // Utiliza el método adecuado para obtener la cuenta bancaria por su ID

                cheque = new Cheque(idCheque, factura, montoCheque, cuentaBancaria, fechaEmicion, estadoCheque);
            }

            return cheque;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el cheque por ID de la base de datos", e);
        }
    }

    @Override
    public CuentaBancaria obtenerCuentaBancariaPorId(int bankAccountId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM cuentas_bancarias WHERE id_cuenta = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, bankAccountId);
            ResultSet resultSet = statement.executeQuery();

            CuentaBancaria cuentaBancaria = null;
            if (resultSet.next()) {
                int idCuentaBancaria = resultSet.getInt("id_cuenta");
                String nombreBanco = resultSet.getString("nombre_banco");
                String tipoCuenta = resultSet.getString("nombre_banco");
                double saldoActual = resultSet.getDouble("saldo_actual");
                double saldoPrevio = resultSet.getDouble("saldo_previo");

                cuentaBancaria = new CuentaBancaria(idCuentaBancaria, nombreBanco, tipoCuenta, saldoActual, saldoPrevio);
            }

            return cuentaBancaria;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la cuenta bancaria por ID de la base de datos", e);
        }
    }

    @Override
    public int registrarSolicitudPago(SolicitudPago paymentRequest) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO solicitudes_pago (id_factura, metodo_pago, id_cheque, id_canje, estado_solicitud, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, paymentRequest.getFactura().getIdFactura());
            statement.setString(2, paymentRequest.getMetodoPago());

            Cheque cheque = paymentRequest.getCheque();
            if (cheque != null) {
                statement.setInt(3, cheque.getIdCheque());
            } else {
                statement.setNull(3, Types.INTEGER);
            }

            Canje canje = paymentRequest.getCanje();
            if (canje != null) {
                statement.setInt(4, canje.getIdCanje());
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            statement.setString(5, paymentRequest.getEstadoSolicitud());
            statement.setDate(6, new java.sql.Date(paymentRequest.getFechaRegistro().getTime()));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idSolicitud = generatedKeys.getInt(1);
                return idSolicitud;
            } else {
                throw new SQLException("Error al obtener el ID generado para la solicitud de pago");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar la solicitud de pago en la base de datos", e);
        }
    }

    @Override
    public List<SolicitudPago> obtenerListaSolicitudesPago() throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM solicitudes_pago";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<SolicitudPago> solicitudesPago = new ArrayList<>();
            while (resultSet.next()) {
                int idSolicitudPago = resultSet.getInt("id_solicitud");
                int idFactura = resultSet.getInt("id_factura");
                String metodoPago = resultSet.getString("metodo_pago");
                int idCheque = resultSet.getInt("id_cheque");
                int idCanje = resultSet.getInt("id_canje");
                String estadoSolicitud = resultSet.getString("estado_solicitud");
                Date fechaRegistro = resultSet.getDate("fecha_registro");

                // Aquí debes obtener la factura, el cheque y el canje correspondientes utilizando los métodos adecuados
                Factura factura = obtenerFacturaPorId(idFactura); // Utiliza el método adecuado para obtener la factura por su ID
                Cheque cheque = obtenerChequePorId(idCheque); // Utiliza el método adecuado para obtener el cheque por su ID
                Canje canje = obtenerCanjePorId(idCanje); // Utiliza el método adecuado para obtener el canje por su ID

                SolicitudPago solicitudPago = new SolicitudPago(idSolicitudPago, factura, metodoPago, cheque, canje, estadoSolicitud, fechaRegistro);
                solicitudesPago.add(solicitudPago);
            }

            return solicitudesPago;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de solicitudes de pago de la base de datos", e);
        }
    }

    @Override
    public Canje obtenerCanjePorId(int exchangeId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM canjes WHERE id_canje = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, exchangeId);
            ResultSet resultSet = statement.executeQuery();

            Canje canje = null;
            if (resultSet.next()) {
                int idCanje = resultSet.getInt("id_canje");
                int idFactura = resultSet.getInt("id_factura");
                String detalleCanje = resultSet.getString("detalle_canje");
                int idProductoCanje = resultSet.getInt("id_producto_canje");
                int cantidadProducto = resultSet.getInt("cantidad_producto");
                double equivalenteDinero = resultSet.getDouble("equivalente_dinero");
                Date fechaEmision = resultSet.getDate("fecha_emicion");
                String estadoCheque = resultSet.getString("estado_canje");

                // Aquí debes obtener la factura y el productoCanje correspondientes utilizando los métodos adecuados
                Factura factura = obtenerFacturaPorId(idFactura); // Utiliza el método adecuado para obtener la factura por su ID
                Producto productoCanje = obtenerProductoPorId(idProductoCanje); // Utiliza el método adecuado para obtener el productoCanje por su ID

                canje = new Canje(idCanje, factura, detalleCanje, productoCanje, cantidadProducto, equivalenteDinero, fechaEmision, estadoCheque);
            }

            return canje;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el canje por ID de la base de datos", e);
        }
    }

    @Override
    public Producto obtenerProductoPorId(int productId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM productos WHERE id_producto = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            Producto producto = null;
            if (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idFactura = resultSet.getInt("id_factura");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int idCategoriaProducto = resultSet.getInt("id_categoria_producto");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadIngresada = resultSet.getInt("cantidad_ingresada");
                int cantidadPendiente = resultSet.getInt("cantidad_pendiente");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double subtotal = resultSet.getDouble("subtotal");

                // Aquí debes obtener la categoríaProducto correspondiente utilizando el método adecuado
                CategoriaProducto categoriaProducto = obtenerCategoriaProductoPorId(idCategoriaProducto); // Utiliza el método adecuado para obtener la categoríaProducto por su ID

                producto = new Producto(idProducto, idFactura, nombre, descripcion, categoriaProducto, cantidadTotal, cantidadIngresada, cantidadPendiente, precioUnitario, subtotal);
            }

            return producto;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el producto por ID de la base de datos", e);
        }
    }

    @Override
    public List<Producto> obtenerListaProductosDisponiblesInventarioPorNombreCategoria(String categoryName) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT p.* FROM productos p "
                    + "INNER JOIN inventario inv ON p.id_producto = inv.id_producto_inventariado "
                    + "INNER JOIN categorias_producto c ON p.id_categoria_producto = c.id_categoria_producto "
                    + "WHERE c.nombre_categoria = ? "
                    + "AND inv.cantidad_producto > 0";

            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setString(1, categoryName);
            ResultSet resultSet = statement.executeQuery();

            List<Producto> productos = new ArrayList<>();
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                int idFactura = resultSet.getInt("id_factura");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                int idCategoriaProducto = resultSet.getInt("id_categoria_producto");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadIngresada = resultSet.getInt("cantidad_ingresada");
                int cantidadPendiente = resultSet.getInt("cantidad_pendiente");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double subtotal = resultSet.getDouble("subtotal");

                CategoriaProducto categoriaProducto = obtenerCategoriaProductoPorId(idCategoriaProducto);

                Producto producto = new Producto(idProducto, idFactura, nombre, descripcion, categoriaProducto, cantidadTotal, cantidadIngresada, cantidadPendiente, precioUnitario, subtotal);
                productos.add(producto);
            }

            return productos;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de productos por nombre de categoría de la base de datos", e);
        }
    }

    @Override
    public Inventario obtenerInventarioPorIdProducto(int productId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM inventario WHERE id_producto_inventariado  = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            Inventario inventario = null;
            if (resultSet.next()) {
                int idInventario = resultSet.getInt("id_inventario");
                int idProductoInventariado = resultSet.getInt("id_producto_inventariado");
                String nombreProducto = resultSet.getString("nombre_producto");
                int cantidadProducto = resultSet.getInt("cantidad_producto");

                inventario = new Inventario(idInventario, idProductoInventariado, nombreProducto, cantidadProducto);
            }
            return inventario;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el inventario por ID de la base de datos", e);
        }
    }

    @Override
    public int registrarCanje(Canje exchange) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO canjes (id_factura, detalle_canje, id_producto_canje, cantidad_producto, equivalente_dinero, fecha_emicion, estado_canje) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, exchange.getFactura().getIdFactura());
            statement.setString(2, exchange.getDetalleCanje());
            statement.setInt(3, exchange.getProductoCanje().getIdFactura());
            statement.setInt(4, exchange.getCantidadProducto());
            statement.setDouble(5, exchange.getEquivalenteDinero());
            statement.setDate(6, new java.sql.Date(exchange.getFechaEmicion().getTime()));
            statement.setString(7, exchange.getEstadoCanje());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCanje = generatedKeys.getInt(1);
                return idCanje;
            } else {
                throw new SQLException("Error al obtener el ID generado para el canje");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el canje en la base de datos", e);
        }
    }

    @Override
    public SolicitudPago obtenerSolicitudPagoPorId(int paymentRequestId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM solicitudes_pago WHERE id_solicitud  = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, paymentRequestId);
            ResultSet resultSet = statement.executeQuery();

            SolicitudPago solicitudPago = null;
            if (resultSet.next()) {
                int idSolicitudPago = resultSet.getInt("id_solicitud");
                int idFactura = resultSet.getInt("id_factura");
                String metodoPago = resultSet.getString("metodo_pago");
                int idCheque = resultSet.getInt("id_cheque");
                int idCanje = resultSet.getInt("id_canje");
                String estadoSolicitud = resultSet.getString("estado_solicitud");
                Date fechaRegistro = resultSet.getDate("fecha_registro");

                Factura factura = obtenerFacturaPorId(idFactura);
                Cheque cheque = obtenerChequePorId(idCheque);
                Canje canje = obtenerCanjePorId(idCanje);

                solicitudPago = new SolicitudPago(idSolicitudPago, factura, metodoPago, cheque, canje, estadoSolicitud, fechaRegistro);
            }
            return solicitudPago;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la solicitud por ID de la base de datos", e);
        }
    }

    @Override
    public int registrarPagoFactura(PagoFactura invoicePayment) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO pagos_facturas (id_pago_factura, id_factura, tipo_pago, id_solicitud, id_pago_programado, monto_pago, fecha_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, invoicePayment.getIdPagoFactura());
            statement.setInt(2, invoicePayment.getFactura().getIdFactura());
            statement.setString(3, invoicePayment.getTipoPagoFactura());

            SolicitudPago solicitudPago = invoicePayment.getSolicitudPago();
            if (solicitudPago != null) {
                statement.setInt(4, solicitudPago.getIdSolicitudPago());
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            PagoProgramado pagoProgramado = invoicePayment.getPagoProgramado();
            if (pagoProgramado != null) {
                statement.setInt(5, pagoProgramado.getIdPagoProgramado());
            } else {
                statement.setNull(5, Types.INTEGER);
            }

            statement.setDouble(6, invoicePayment.getMontoPago());
            statement.setDate(7, new java.sql.Date(invoicePayment.getFechaPago().getTime()));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCanje = generatedKeys.getInt(1);
                return idCanje;
            } else {
                throw new SQLException("Error al obtener el ID generado para el pago de factura");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el pago de factura en la base de datos", e);
        }
    }

    @Override
    public PagoFactura obtenerPagoFacturaPorId(int invoicePaymentId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM pagos_facturas WHERE id_pago_factura  = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, invoicePaymentId);
            ResultSet resultSet = statement.executeQuery();

            PagoFactura pagoFactura = null;
            if (resultSet.next()) {
                int idPagoFactura = resultSet.getInt("id_pago_factura");
                int idFactura = resultSet.getInt("id_factura");
                String tipoPago = resultSet.getString("tipo_pago");
                int idSolicitudPago = resultSet.getInt("id_solicitud");
                int idPagoProgramado = resultSet.getInt("id_pago_programado");
                double montoPago = resultSet.getDouble("monto_pago");
                Date fechaPago = resultSet.getDate("fecha_pago");

                Factura factura = obtenerFacturaPorId(idFactura);
                SolicitudPago solicitudPago = obtenerSolicitudPagoPorId(idSolicitudPago);
                PagoProgramado pagoProgramado = obtenerPagoProgramadoPorId(idPagoProgramado);

                pagoFactura = new PagoFactura(idPagoFactura, factura, tipoPago, solicitudPago, pagoProgramado, montoPago, fechaPago);

            }
            return pagoFactura;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el PagoFactura por ID de la base de datos", e);
        }
    }

    @Override
    public PagoProgramado obtenerPagoProgramadoPorId(int programmedPayment) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM pagos_programados WHERE id_pago_programado  = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, programmedPayment);
            ResultSet resultSet = statement.executeQuery();

            PagoProgramado pagoProgramado = null;
            if (resultSet.next()) {
                int idPagoProgramado = resultSet.getInt("id_pago_programado");
                int idFactura = resultSet.getInt("id_factura");
                String tipoPagoProgramado = resultSet.getString("tipo_pago_programado");
                String metodoPago = resultSet.getString("metodo_pago");
                int idCheque = resultSet.getInt("id_cheque");
                Date fechaProgramada = resultSet.getDate("fecha_programada");

                Factura factura = obtenerFacturaPorId(idFactura);
                Cheque cheque = obtenerChequePorId(idCheque);

                pagoProgramado = new PagoProgramado(idPagoProgramado, factura, tipoPagoProgramado, metodoPago, cheque, fechaProgramada);
            }
            return pagoProgramado;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el pagoProgramado por ID de la base de datos", e);
        }
    }

    @Override
    public int registrarMovimientoBancario(MovimientoBancario bankingMovement) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO movimientos_bancarios (id_movimiento, id_cuenta, tipo_movimiento, monto, fecha_movimiento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bankingMovement.getIdMovimientoBancario());
            statement.setInt(2, bankingMovement.getCuentaBancaria().getIdCuentaBancaria());
            statement.setString(3, bankingMovement.getTipoMovimiento());
            statement.setDouble(4, bankingMovement.getMontoMovimiento());
            statement.setDate(5, new java.sql.Date(bankingMovement.getFechaMovimiento().getTime()));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idMovimientoBancario = generatedKeys.getInt(1);
                return idMovimientoBancario;
            } else {
                throw new SQLException("Error al obtener el ID generado para el movimiento bancario");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el movimiento bancario en la base de datos", e);
        }
    }

    @Override
    public void modificarSaldosCuentaBancariaPorId(int bankAccountId, double saldoActualDespues, double saldoPrevioDespues) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE cuentas_bancarias SET saldo_actual = ?, saldo_previo = ? WHERE id_cuenta = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setDouble(1, saldoActualDespues);
            statement.setDouble(2, saldoPrevioDespues);
            statement.setInt(3, bankAccountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar los sados de la cuenta bancaria en la base de datos", e);
        }
    }

    @Override
    public void modificarEstadoChequePorId(int checkId, String nuevoEstadoCheque) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE cheques SET estado_cheque = ? WHERE id_cheque = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nuevoEstadoCheque);
            statement.setInt(2, checkId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar el estado del cheque en la base de datos", e);
        }
    }

    @Override
    public void modificarCantidadProductosInventarioPorIdProducto(int productId, int cantidadProductosDespues) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE inventario SET cantidad_producto = ? WHERE id_producto_inventariado = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, cantidadProductosDespues);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar los sados de la cuenta bancaria en la base de datos", e);
        }
    }

    @Override
    public int registrarMovimientoInventario(MovimientoInventario inventoryMovement) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO movimientos_inventario (id_movimiento_inventario, id_inventario, id_producto, cantidad_producto, tipo_movimiento, fecha_movimiento) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inventoryMovement.getIdMovimientoInventario());
            statement.setInt(2, inventoryMovement.getInventario().getIdInventario());
            statement.setInt(3, inventoryMovement.getProducto().getIdProducto());
            statement.setInt(4, inventoryMovement.getCantidadProducto());
            statement.setString(5, inventoryMovement.getTipoMovimiento());
            statement.setDate(6, new java.sql.Date(inventoryMovement.getFechaMovimiento().getTime()));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idMovimientoInventario = generatedKeys.getInt(1);
                return idMovimientoInventario;
            } else {
                throw new SQLException("Error al obtener el ID generado para el movimiento inventario");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar el movimiento inventario en la base de datos", e);
        }
    }

    @Override
    public void modificarEstadoCanjePorId(int exchangeId, String nuevoEstadoCanje) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE canjes SET estado_canje = ? WHERE id_canje = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nuevoEstadoCanje);
            statement.setInt(2, exchangeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar el estado del canje en la base de datos", e);
        }
    }

    @Override
    public void modificarMontosPagadosFacturaPorId(int invoiceId, double montoPagadoDespues, double montoPendienteDespues) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE facturas SET monto_pagado = ?, monto_pendiente = ? WHERE id_factura = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setDouble(1, montoPagadoDespues);
            statement.setDouble(2, montoPendienteDespues);
            statement.setInt(3, invoiceId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar los montos de la factura en la base de datos", e);
        }
    }

    @Override
    public Cheque obtenerChequeRespaldoPagoProgramadoPorIdFactura(int invoiceId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT c.* FROM cheques c "
                    + "INNER JOIN pagos_programados pp ON c.id_cheque = pp.id_cheque "
                    + "WHERE pp.id_factura = ? "
                    + "AND pp.tipo_pago_programado = 'Respaldo'";

            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, invoiceId);
            ResultSet resultSet = statement.executeQuery();

            Cheque cheque = null;
            if (resultSet.next()) {
                int idCheque = resultSet.getInt("id_cheque");
                int idFactura = resultSet.getInt("id_factura");
                double montoCheque = resultSet.getDouble("monto_cheque");
                int idCuentaBancaria = resultSet.getInt("id_cuenta_bancaria");
                Date fechaEmicion = resultSet.getDate("fecha_emicion");
                String estadoCheque = resultSet.getString("estado_cheque");

                // Aquí debes obtener la factura y la cuenta bancaria correspondientes utilizando los métodos adecuados
                Factura factura = obtenerFacturaPorId(idFactura); // Utiliza el método adecuado para obtener la factura por su ID
                CuentaBancaria cuentaBancaria = obtenerCuentaBancariaPorId(idCuentaBancaria); // Utiliza el método adecuado para obtener la cuenta bancaria por su ID

                cheque = new Cheque(idCheque, factura, montoCheque, cuentaBancaria, fechaEmicion, estadoCheque);
            }

            return cheque;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el cheque por ID de la base de datos", e);
        }
    }

    @Override
    public void modificarMontoChequeRespaldoProgramadoPorIdCheque(int checkId, double nuevoMontoCheque) throws Exception {
        try {
            this.Conectar();
            String consulta = "UPDATE cheques SET monto_cheque = ? WHERE id_cheque = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setDouble(1, nuevoMontoCheque);
            statement.setInt(2, checkId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar los montos de la factura en la base de datos", e);
        }
    }

    @Override
    public List<MovimientoBancario> obtenerListaMovimientosBancarios(String bankName) throws Exception {
        try {
            this.Conectar();
            String consulta = bankName.isEmpty() ? "SELECT * FROM movimientos_bancarios" : "SELECT * FROM movimientos_bancarios WHERE id_cuenta IN (SELECT id_cuenta FROM cuentas_bancarias WHERE nombre_banco LIKE '%" + bankName + "%' )";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            List<MovimientoBancario> movimientosBancarios = new ArrayList<>();
            while (resultSet.next()) {
                int idMovimientoBancario = resultSet.getInt("id_movimiento");
                int idCuentaBancaria = resultSet.getInt("id_cuenta");
                String tipoMovimiento = resultSet.getString("tipo_movimiento");
                double montoBancario = resultSet.getDouble("monto");
                Date fechaMovimiento = resultSet.getDate("fecha_movimiento");

                CuentaBancaria cuentaBancaria = obtenerCuentaBancariaPorId(idCuentaBancaria);

                MovimientoBancario movimientoBancario = new MovimientoBancario(idMovimientoBancario, cuentaBancaria, tipoMovimiento, montoBancario, fechaMovimiento);
                movimientosBancarios.add(movimientoBancario);
            }

            return movimientosBancarios;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de movimientos de inventario de la base de datos", e);
        }
    }

    @Override
    public List<PagoFactura> obtenerListaPagosFacturas(String supplierName) throws Exception {
        try {
            this.Conectar();
            String consulta = supplierName.isEmpty() ? "SELECT * FROM pagos_facturas" : "SELECT * FROM pagos_facturas WHERE id_factura IN ( SELECT id_factura FROM facturas WHERE id_proveedor IN ( SELECT id_proveedor FROM proveedores WHERE nombre LIKE '%" + supplierName + "%' ))";
            //"SELECT * FROM pagos_facturas WHERE id_factura IN ( SELECT id_factura FROM facturas WHERE id_proveedor IN ( SELECT id_proveedor FROM proveedores WHERE nombre LIKE '%a%' ));"
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            List<PagoFactura> pagosFacturas = new ArrayList<>();
            while (resultSet.next()) {
                int idPagoFactura = resultSet.getInt("id_pago_factura");
                int idFactura = resultSet.getInt("id_factura");
                String tipoPago = resultSet.getString("tipo_pago");
                int idSolicitud = resultSet.getInt("id_solicitud");
                int idPagoProgramado = resultSet.getInt("id_pago_programado");
                double montoPago = resultSet.getDouble("monto_pago");
                Date fechaPago = resultSet.getDate("fecha_pago");

                Factura factura = obtenerFacturaPorId(idFactura);
                SolicitudPago solicitudPago = obtenerSolicitudPagoPorId(idSolicitud);
                PagoProgramado pagoProgramado = obtenerPagoProgramadoPorId(idPagoProgramado);

                PagoFactura pagoFactura = new PagoFactura(idPagoFactura, factura, tipoPago, solicitudPago, pagoProgramado, montoPago, fechaPago);

                pagosFacturas.add(pagoFactura);
            }

            return pagosFacturas;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de movimientos de inventario de la base de datos", e);
        }
    }

    @Override
    public Proveedor obtenerProveedorPorId(int supplierId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM proveedores WHERE id_proveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProveedor = resultSet.getInt("id_proveedor");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                Double lineaCredito = resultSet.getDouble("linea_credito");

                return new Proveedor(idProveedor, nombre, direccion, telefono, lineaCredito);
            }

            // Si no se encuentra el usuario, puedes lanzar una excepción o retornar null, según tu necesidad
            throw new SQLException("No se encontró ningún proveedor con el ID proporcionado");
        } catch (SQLException e) {
            throw new SQLException("Error al consultar obtenerProveedorPorId la base de datos", e);
        }
    }

    @Override
    public double obtenerDeudaTotalPorProveedor(int idProveedor) throws Exception {
        double deudaTotal = 0.0;

        try {
            this.Conectar();
            String consulta = "SELECT SUM(monto_pendiente) AS deuda_total FROM facturas WHERE id_proveedor = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, idProveedor);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                deudaTotal = rs.getDouble("deuda_total");
            }

        } catch (SQLException e) {
            throw new SQLException("Error al obtener la deuda total del proveedor desde la base de datos", e);
        } finally {
            this.Cerrar();
        }

        return deudaTotal;
    }

    @Override
    public List<PagoFactura> obtenerListaPagosFacturasPorIdProveedor(int supplierId) throws Exception {
    try {
        this.Conectar();
        String consulta = "SELECT * FROM pagos_facturas WHERE id_factura IN (SELECT id_factura FROM facturas WHERE id_proveedor = ?)";
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setInt(1, supplierId);
        ResultSet resultSet = statement.executeQuery();

        List<PagoFactura> pagosFacturas = new ArrayList<>();
        while (resultSet.next()) {
            int idPagoFactura = resultSet.getInt("id_pago_factura");
            int idFactura = resultSet.getInt("id_factura");
            String tipoPago = resultSet.getString("tipo_pago");
            int idSolicitud = resultSet.getInt("id_solicitud");
            int idPagoProgramado = resultSet.getInt("id_pago_programado");
            double montoPago = resultSet.getDouble("monto_pago");
                Date fechaPago = resultSet.getDate("fecha_pago");

                Factura factura = obtenerFacturaPorId(idFactura);
                SolicitudPago solicitudPago = obtenerSolicitudPagoPorId(idSolicitud);
                PagoProgramado pagoProgramado = obtenerPagoProgramadoPorId(idPagoProgramado);

                PagoFactura pagoFactura = new PagoFactura(idPagoFactura, factura, tipoPago, solicitudPago, pagoProgramado, montoPago, fechaPago);

                pagosFacturas.add(pagoFactura);
            }

            return pagosFacturas;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de movimientos de inventario de la base de datos", e);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.baseDatos.consultas;

import com.proyecto.baseDatos.GestorBaseDatos;
import com.proyecto.entidades.DetalleFactura;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.interfaces.DAOEncargadoComprasInterfaz;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skiva
 */
public class DAOEncargadoComprasImpl extends GestorBaseDatos implements DAOEncargadoComprasInterfaz {

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

    /*METODOS FACTURA---------------------------------------------------------------------------------------------------------------*/
    //al momento de registrar la factura este se debe registrar el monto pagado en 0, monto pendiente en 0, 
    //y el monto total se calcula automaticamente de los productos registrados montouni x cantidad
    @Override
    public void registrarFactura(Factura invoice) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO proveedores (id_proveedor, fecha_registro, fecha_vencimiento, descripcion, monto_total, monto_pagado, monto_pendiente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, invoice.getIdProveedor());
            statement.setDate(2, (Date) invoice.getFechaRegistro());
            statement.setDate(3, (Date) invoice.getFechaVencimiento());
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
            String consulta = "UPDATE facturas SET id_proveedor = ?, fecha_registro = ?, fecha_vencimiento = ?, descripcion = ?, monto_total = ? WHERE id_factura = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, invoice.getIdProveedor());
            statement.setDate(2, (Date) invoice.getFechaRegistro());
            statement.setDate(3, (Date) invoice.getFechaVencimiento());
            statement.setString(4, invoice.getDescripcion());
            statement.setDouble(5, invoice.getMontoTotal());
            statement.setInt(6, invoice.getIdFactura());
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

    @Override
    public List<Factura> obtenerListaFacturas(String name) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT * FROM facturas";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

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
            throw new SQLException("Error al obtener la lista de facturas de la base de datos", e);
        }
    }

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
                Double saldoPendiente = resultSet.getDouble("saldo_pendiente");

                return new Factura(idFactura, idProveedor, fechaRegistro, fechaVencimiento, descripcion, montoTotal, montoPagado, saldoPendiente);
            }

            // Si no se encuentra el usuario, puedes lanzar una excepción o retornar null, según tu necesidad
            throw new SQLException("No se encontró ninguna factura con el ID proporcionado");
        } catch (SQLException e) {
            throw new SQLException("Error al consultar obtenerFacturaPorId la base de datos", e);
        }
    }

    @Override
    public void registrarProducto(Producto product) throws Exception { // cuando el encargado de compras registra la factura se crea el producto por ende ya es existente
        try {
            this.Conectar();
            String consulta = "INSERT INTO proveedores (id_factura, nombre, descripcion, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
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

    /*VERIFICACIONES-------------------------------------------------------------*/
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

}

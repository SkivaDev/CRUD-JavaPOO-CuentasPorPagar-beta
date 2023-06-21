/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.baseDatos.consultas;

import com.proyecto.baseDatos.GestorBaseDatos;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Proveedor;
import com.proyecto.interfaces.DAOEncargadoComprasInterfaz;
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

    @Override
    public void registrarFactura(Factura invoice) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificarFactura(Factura invoice) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarFactura(int invoiceId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Factura> obtenerListaFacturas(String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Factura obtenerFacturaPorId(int invoiceId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

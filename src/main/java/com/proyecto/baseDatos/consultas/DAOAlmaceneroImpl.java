/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.baseDatos.consultas;

import com.proyecto.baseDatos.GestorBaseDatos;
import com.proyecto.entidades.CategoriaProducto;
import com.proyecto.entidades.DetalleFactura;
import com.proyecto.entidades.Factura;
import com.proyecto.entidades.Inventario;
import com.proyecto.entidades.Producto;
import com.proyecto.entidades.Proveedor;
import com.proyecto.interfaces.DAOAlmaceneroInterfaz;
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
public class DAOAlmaceneroImpl extends GestorBaseDatos implements DAOAlmaceneroInterfaz {

    @Override
    public List<Inventario> obtenerListaInventarios(String productName) throws Exception {
        try {
            this.Conectar();
            String consulta = productName.isEmpty() ? "SELECT * FROM inventario" : "SELECT * FROM inventario WHERE nombre_producto LIKE '%" + productName + "%';";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            List<Inventario> inventarios = new ArrayList<>();
            while (resultSet.next()) {
                int idInventario = resultSet.getInt("id_inventario");
                int idProductoInventario = resultSet.getInt("id_producto_inventariado");
                String nombreProducto = resultSet.getString("nombre_producto");
                int cantidadProductos = resultSet.getInt("cantidad_producto");

                Inventario inventario = new Inventario(idInventario, idProductoInventario, nombreProducto, cantidadProductos);
                inventarios.add(inventario);
            }

            return inventarios;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la lista de inventarios de la base de datos", e);
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
                String nombreProducto = resultSet.getString("nombre");
                String descripcionProducto = resultSet.getString("descripcion");
                int idCategoriaProducto = resultSet.getInt("id_categoria_producto");
                int cantidadTotal = resultSet.getInt("cantidad_total");
                int cantidadIngresada = resultSet.getInt("cantidad_ingresada");
                int cantidadPendiente = resultSet.getInt("cantidad_pendiente");
                double precioUnitario = resultSet.getDouble("precio_unitario");
                double subtotal = resultSet.getDouble("subtotal");

                CategoriaProducto categoriaProducto = obtenerCategoriaProductoPorId(idCategoriaProducto);

                producto = new Producto(idProducto, idFactura, nombreProducto,
                        descripcionProducto, categoriaProducto, cantidadTotal, cantidadIngresada, cantidadPendiente, precioUnitario, subtotal);
            }

            return producto;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la categoria de producto por ID de la base de datos", e);
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
                int idCategoria = resultSet.getInt("id_categoria_producto");
                String nombreCategoria = resultSet.getString("nombre_categoria");
                String descripcionCategoria = resultSet.getString("descripcion_categoria");

                categoriaProducto = new CategoriaProducto(idCategoria, nombreCategoria, descripcionCategoria);
            }

            return categoriaProducto;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la categoria de producto por ID de la base de datos", e);
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<CategoriaProducto> obtenerListaCategoriasProducto(String categoryName) throws Exception {
        try {
            this.Conectar();
            String consulta = categoryName.isEmpty() ? "SELECT * FROM categorias_producto" : "SELECT * FROM categorias_producto WHERE nombre_categoria LIKE '%" + categoryName + "%';";
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
    public boolean categoriaRegistradaEnUso(int productCategoryId) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT COUNT(*) AS total FROM productos WHERE id_categoria_producto = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setInt(1, productCategoryId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int totalRegistros = resultSet.getInt("total");
                return totalRegistros > 0;
            }

            return false;
        } catch (SQLException e) {
            throw new SQLException("Error al verificar si la categoria de producto esta en uso, en la base de datos", e);
        }
    }

    @Override
    public void eliminarCategoriaProducto(int productCategoryId) throws Exception {
        try {
            this.Conectar();
            String consulta = "DELETE FROM categorias_producto WHERE id_categoria_producto = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, productCategoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar la categoria de producto de la base de datos", e);
        }
    }

    @Override
    public boolean existeCategoriaProducto(String productCategoryName) throws Exception {
        try {
            this.Conectar();
            String consulta = "SELECT COUNT(*) AS total FROM categorias_producto WHERE nombre_categoria = ?";
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            statement.setString(1, productCategoryName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int totalRegistros = resultSet.getInt("total");
                return totalRegistros > 0;
            }

            return false;
        } catch (SQLException e) {
            throw new SQLException("Error al verificar las categorias de producto en la base de datos", e);
        }
    }

    @Override
    public void registrarCategoriaProducto(CategoriaProducto productCategory) throws Exception {
        try {
            this.Conectar();
            String consulta = "INSERT INTO categorias_producto (nombre_categoria, descripcion_categoria) VALUES (?, ?)";
            PreparedStatement statement = this.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, productCategory.getNombreCategoria());
            statement.setString(2, productCategory.getDescripcionCategoria());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCategory = generatedKeys.getInt(1);
                productCategory.setIdCategoriaProducto(idCategory);
            } else {
                throw new SQLException("Error al obtener el ID generado para la categoria producto");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al registrar la categoria producto en la base de datos", e);
        }
    }
}

package org.example.inventario_panelero.Service;
import org.example.inventario_panelero.Model.Producto;

import java.util.List;

// Interfaz que define los métodos del servicio para la gestión de productos
public interface ProductoService {
    // Método para crear y guardar un nuevo producto en el sistema
    Producto crearProducto(Producto producto);

    // Método para actualizar los datos de un producto existente según su id
    Producto actualizarProducto(String id, Producto producto);

    // Método para eliminar un producto del sistema usando su id
    void eliminarProducto(String id);

    // Método para registrar el ingreso de stock de un producto
    Producto registrarEntrada(String idProducto, int cantidad);

    // Método para registrar la salida o reducción de stock de un producto
    Producto registrarSalida(String idProducto, int cantidad);

    // Método para obtener la lista completa de productos registrados
    List<Producto> listarProductos();
}
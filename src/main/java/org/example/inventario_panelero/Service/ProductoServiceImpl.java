package org.example.inventario_panelero.Service;

import org.example.inventario_panelero.Model.Categoria;
import org.example.inventario_panelero.Model.Producto;
import org.example.inventario_panelero.Repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    // Constructor que inyecta el repositorio para acceder a la base de datos
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crea un producto validando nombre único, generando ID automático
    // por categoría y asegurando stock inicial válido
    @Override
    public Producto crearProducto(Producto producto) {
        // No permite productos con el mismo nombre
        if (productoRepository.existsByNombreIgnoreCase(producto.getNombre())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe un producto con el nombre: " + producto.getNombre()
            );
        }
        // Genera ID automatico por categoría
        String idGenerado = generarIdProducto(producto.getCategoria());
        producto.setId(idGenerado);

        // Stock inicial seguro
        if (producto.getStock() < 0) {
            producto.setStock(0);
        }
        //guarda
        return productoRepository.save(producto);
    }

    // Actualiza los datos del producto sin permitir modificar el
    // stock directamente
    @Override
    public Producto actualizarProducto(String id, Producto producto) {

        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));
        // No permite modificar el stock
        if (producto.getStock() != existente.getStock()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede modificar el stock del producto"
            );

        }
        if (producto.getNombre() != null) {
            existente.setNombre(producto.getNombre());
        }

        if (producto.getCategoria() != null) {
            existente.setCategoria(producto.getCategoria());
        }

        if (producto.getValor() != null) {
            existente.setValor(producto.getValor());
        }

        return productoRepository.save(existente);
    }

    // Elimina un producto verificando previamente que exista
    // en la base de datos
    @Override
    public void eliminarProducto(String id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Producto no existe"
            );
        }
        productoRepository.deleteById(id);
    }

    // Registra una entrada de inventario aumentando el stock
    // con validación de cantidad positiva
    @Override
    public Producto registrarEntrada(String idProducto, int cantidad) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));
        if (cantidad <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La cantidad debe ser mayor a cero"
            );
        }
        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }

    // Registra una salida de inventario disminuyendo el stock y
    // validando existencia y disponibilidad
    @Override
    public Producto registrarSalida(String idProducto, int cantidad) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        if (cantidad <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La cantidad debe ser mayor a cero"
            );
        }

        if (producto.getStock() < cantidad) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Stock insuficiente"
            );
        }

        producto.setStock(producto.getStock() - cantidad);
        return productoRepository.save(producto);
    }

    // Retorna la lista completa de productos almacenados en el sistema
    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Genera un ID incremental basado en el prefijo de la categoría y
    // la cantidad de productos existentes
    private String generarIdProducto(Categoria categoria) {

        String prefijo = categoria.getPrefijo(); // viene del enum
        long cantidad = productoRepository.countByCategoria(categoria);
        long siguiente = cantidad + 1;

        return prefijo + String.format("%03d", siguiente);
    }
}
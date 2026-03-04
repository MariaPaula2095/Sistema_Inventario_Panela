package org.example.inventario_panelero.Controller;

import org.example.inventario_panelero.Model.Producto;
import org.example.inventario_panelero.Service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



import org.example.inventario_panelero.Model.Producto;
import org.example.inventario_panelero.Service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Requerimiento funcional: RF-06 Reporte de inventario
    @GetMapping("/reportes/inventario")
    public ResponseEntity<List<Producto>> listarInventario() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    // Requerimiento funcional: RF-01 Crear producto
    @PostMapping("/registrarProductos")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto creado = productoService.crearProducto(producto);
        return ResponseEntity.status(201).body(creado);
    }

    // Requerimiento funcional:RF-02 Modificar producto
    @PutMapping("/actualizarProductos/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable String id, @RequestBody Producto producto) {
        productoService.actualizarProducto(id, producto);
        return ResponseEntity.noContent().build();
    }

    // Requerimiento funcional:RF-03 Eliminar producto
    @DeleteMapping("/eliminarProductos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Requerimiento funcional:RF-04 Entrada de inventario
    @PostMapping("/movimientos/entrada")
    public ResponseEntity<Producto> registrarEntrada(
            @RequestParam String idProducto,
            @RequestParam int cantidad) {

        return ResponseEntity.ok(
                productoService.registrarEntrada(idProducto, cantidad)
        );
    }

    // Requerimiento funcional:RF-05 Salida de inventario
    @PostMapping("/movimientos/salida")
    public ResponseEntity<Producto> registrarSalida(
            @RequestParam String idProducto,
            @RequestParam int cantidad) {

        return ResponseEntity.ok(
                productoService.registrarSalida(idProducto, cantidad)
        );
    }
}
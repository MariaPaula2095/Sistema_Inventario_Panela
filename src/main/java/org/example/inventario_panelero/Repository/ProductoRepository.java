package org.example.inventario_panelero.Repository;

import org.example.inventario_panelero.Model.Categoria;
import org.example.inventario_panelero.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
//incluimos JPARepository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    // No permitir productos con el mismo nombre
    boolean existsByNombreIgnoreCase(String nombre);

    // Contar productos por categoría (para generar ID)
    long countByCategoria(Categoria categoria);
}
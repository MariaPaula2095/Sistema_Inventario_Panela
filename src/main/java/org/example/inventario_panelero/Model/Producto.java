package org.example.inventario_panelero.Model;

import jakarta.persistence.*;

//Anotacion de Springboot
@Entity
public class Producto {
    @Id
    @Column(length = 10)
    private String id;   // id alfanumerico AZ001, PV001, etc.
    @Column(unique = true, nullable = false)
    private String nombre;
    private Double valor;
    @Enumerated(EnumType.STRING) //ccategorias
    private Categoria categoria;
    private int stock; //cantidad disponible

    //Constructor vacio
    public Producto() {
    }

    //Constructor con parametros
    public Producto(String id, String nombre, Double valor, Categoria categoria, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.categoria = categoria;
        this.stock = stock;
    }

    // GETTERS and SETTERS
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
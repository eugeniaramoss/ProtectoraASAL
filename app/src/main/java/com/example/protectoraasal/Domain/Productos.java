package com.example.protectoraasal.Domain;

public class Productos {
    private Boolean BestProduct;
    private String descripcion;
    private int id;
    private String nombre;
    private long precio;
    private String rutaImagen;
    private int stock;

    public Productos() {
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Boolean getBestProduct() {
        return BestProduct;
    }

    public void setBestProduct(Boolean bestProduct) {
        BestProduct = bestProduct;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

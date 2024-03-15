package com.example.protectoraasal.Domain;

import java.io.Serializable;

public class Animales implements Serializable {
    private String descripcion;
    private int edad;
    private int estado_id;
    private String fecha_entrada;
    private int id;
    private String nombre;
    private String rutaImagen;
    private String rutaImagen2;
    private String rutaImagen3;
    private String sexo;
    private int tamanio_id;
    private int tipo_id;

    public Animales() {
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
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

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen2() {
        return rutaImagen2;
    }

    public void setRutaImagen2(String rutaImagen2) {
        this.rutaImagen2 = rutaImagen2;
    }

    public String getRutaImagen3() {
        return rutaImagen3;
    }

    public void setRutaImagen3(String rutaImagen3) {
        this.rutaImagen3 = rutaImagen3;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTamanio_id() {
        return tamanio_id;
    }

    public void setTamanio_id(int tamanio_id) {
        this.tamanio_id = tamanio_id;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }
}

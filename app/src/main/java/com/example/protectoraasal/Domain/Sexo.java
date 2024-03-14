package com.example.protectoraasal.Domain;

public class Sexo {
    private int id;
    private String tipo;

    public Sexo() {
    }

    @Override
    public String toString() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

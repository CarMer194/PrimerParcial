package com.example.carlos.administraciondecuentas;

public class Producto {
    String name;
    double precio;

    public Producto(String name,double precio){
        this.name=name;
        this.precio=precio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

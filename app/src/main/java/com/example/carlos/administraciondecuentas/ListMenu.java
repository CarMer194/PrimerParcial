package com.example.carlos.administraciondecuentas;



public class ListMenu {
    private String icono;
    private String titulo;

    public ListMenu(String icono, String titulo){
        this.icono = icono;
        this.titulo = titulo;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIcono() {
        return icono;
    }

    public String getTitulo() {
        return titulo;
    }
}

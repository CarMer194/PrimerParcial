package com.example.carlos.administraciondecuentas.datahandling;

public class Resumen_info {
    private int id;
    private String nombre;
    private double total,totalIngreso,totalGasto;
    private String dia,mes,año;
    private String imagen;

    public Resumen_info(String mes, String año,String dia) {
        this.mes = mes;
        this.año = año;
        this.dia = dia;
        totalIngreso= 0;
        total=0;
        totalGasto=0;
    }

    public Resumen_info(String nombre) {
        this.nombre = nombre;
    }

    public Resumen_info() {
        totalIngreso= 0;
        total=0;
        totalGasto=0;
    }

    public void sumTotalIngresos(double sumando){
        totalIngreso = totalIngreso + sumando;
    }
    public void sumTotal(double sumando){
        total= total + sumando;
    }
    public void sumTotalGastos(double sumando){
        totalGasto = totalGasto + sumando;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTotal() {
        return total;
    }

    public double getTotalIngreso() {
        return totalIngreso;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAño() {
        return año;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

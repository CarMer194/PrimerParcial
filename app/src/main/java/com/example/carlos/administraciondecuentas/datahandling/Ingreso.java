package com.example.carlos.administraciondecuentas.datahandling;

import java.util.HashMap;

/**
 * Created by UCA on 4/5/2018.
 */

public class Ingreso {
    private HashMap<String, String> ingresoProducto;
    private String nombreCuenta;

    public HashMap<String, String> getIngresoProducto() {
        return ingresoProducto;
    }

    public void setIngresoProducto(HashMap<String, String> ingresoProducto) {
        this.ingresoProducto = ingresoProducto;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }
}

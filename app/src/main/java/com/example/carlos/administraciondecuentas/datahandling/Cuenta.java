package com.example.carlos.administraciondecuentas.datahandling;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Cuenta implements Parcelable{
    int id;
    String nombre;
    float gasto;
    float ingreso;
    float total;
    String nombreTitular;
    String[] temp;
    String dia,mes,año;
    ArrayList<Producto> ingresoProductos;
    ArrayList<Producto> gastoProductos;

    public Cuenta(int id, String nombre, String nombreTitular) {
        this.id = id;
        this.nombre = nombre;
        this.nombreTitular = nombreTitular;
        ingresoProductos= new ArrayList<>();
        gastoProductos= new ArrayList<>();
        iniciarFecha();
    }

    public Cuenta(String nombre, String nombreTitular,float total, ArrayList<Producto> Productos,boolean isGastos){
        this.nombre = nombre;
        this.nombreTitular = nombreTitular;
        this.total = total;
        if(isGastos){
            this.ingresoProductos = new ArrayList<>();
            gastoProductos= Productos;
        }
        else{
            this.ingresoProductos = Productos;
            gastoProductos= new ArrayList<>();
        }
        iniciarFecha();

    }
    private void iniciarFecha(){
        Date fecha = new Date();
        temp = fecha.toString().split(" ");
        dia = temp[2];
        mes = temp[1];
        año = temp[5];
    }


    public Cuenta() {
    }

    protected Cuenta(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        gasto = in.readFloat();
        ingreso = in.readFloat();
        total = in.readFloat();
        nombreTitular = in.readString();
        ingresoProductos = in.createTypedArrayList(Producto.CREATOR);
        gastoProductos = in.createTypedArrayList(Producto.CREATOR);
        dia=in.readString();
        mes=in.readString();
        año=in.readString();
    }

    public static final Creator<Cuenta> CREATOR = new Creator<Cuenta>() {
        @Override
        public Cuenta createFromParcel(Parcel in) {
            return new Cuenta(in);
        }

        @Override
        public Cuenta[] newArray(int size) {
            return new Cuenta[size];
        }
    };

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

    public float getGasto() {
        return gasto;
    }

    public void setGasto(float gasto) {
        this.gasto = gasto;
    }

    public float getIngreso() {
        return ingreso;
    }

    public void setIngreso(float ingreso) {
        this.ingreso = ingreso;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public ArrayList<Producto> getIngresoProductos() {
        return ingresoProductos;
    }

    public void setIngresoProductos(ArrayList<Producto> ingresoProductos) {
        this.ingresoProductos = ingresoProductos;
    }

    public ArrayList<Producto> getGastoProductos() {
        return gastoProductos;
    }

    public void setGastoProductos(ArrayList<Producto> gastoProductos) {
        this.gastoProductos = gastoProductos;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeFloat(gasto);
        dest.writeFloat(ingreso);
        dest.writeFloat(total);
        dest.writeString(nombreTitular);
        dest.writeTypedList(ingresoProductos);
        dest.writeTypedList(gastoProductos);
        dest.writeString(dia);
        dest.writeString(mes);
        dest.writeString(año);
    }
}

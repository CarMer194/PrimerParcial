package com.example.carlos.administraciondecuentas.datahandling;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by UCA on 4/5/2018.
 */

public class DataHandler implements Parcelable{
    private ArrayList<Cuenta> cuentas;
    private ArrayList<Producto> inventario;



    public DataHandler(){
        //Definiendo arraylist de cuentas
        cuentas = new ArrayList<Cuenta>();
        cuentas.add(new Cuenta("Cuenta 1", "Josseh", 0, new ArrayList<Producto>(), false));
        //Definiendo el inventario
        inventario = new ArrayList<Producto>();
        inventario.add(new Producto("T075", "Producto 1", 3.1416f, 2.75f,1, "ella.jpg" ));
        inventario.add(new Producto("T076", "Producto 2", 31.14f, 5.75f,1, "ellader0dr1.jpg" ));
        inventario.add(new Producto("T077", "Producto 3", 99.99f, 50.23f,1, "ella.jpg" ));
    }

    // INICIO IMPLEMENTACION PARCELABLE
    protected DataHandler(Parcel in) {
        cuentas= in.createTypedArrayList(Cuenta.CREATOR);
        inventario = in.createTypedArrayList(Producto.CREATOR);
    }

    public static final Creator<DataHandler> CREATOR = new Creator<DataHandler>() {
        @Override
        public DataHandler createFromParcel(Parcel in) {
            return new DataHandler(in);
        }

        @Override
        public DataHandler[] newArray(int size) {
            return new DataHandler[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(cuentas);
        dest.writeTypedList(inventario);
    }

    //FIN DE LA IMPLEMENTACION DE PARCELABLE

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }
    public void setInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }

    public void removeProduct(String productName, int amnt){
        for(int i = 0; i < this.getInventario().size(); i++){
            if(productName == getInventario().get(i).getName()){
                getInventario().get(i).setCantidad(getInventario().get(i).getCantidad() - amnt);
            }
        }
    }

    public void addProduct(String productName, int amnt){
        for(int i = 0; i < this.getInventario().size(); i++){
            if(productName == getInventario().get(i).getName()){
                getInventario().get(i).setCantidad(getInventario().get(i).getCantidad() + amnt);
            }
        }
    }
}


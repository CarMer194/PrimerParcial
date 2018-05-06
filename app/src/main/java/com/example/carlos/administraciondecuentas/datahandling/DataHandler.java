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
        cuentas.add(new Cuenta(1,"cuenta1","Josseh blanco"));
        cuentas.add(new Cuenta(2,"cuenta2","Rodrigo Rovelo"));
        cuentas.add(new Cuenta(3,"cuenta3","Noe Portillo"));


        //Definiendo el inventario
        inventario = new ArrayList<Producto>();
        inventario.add(new Producto("w41fu", "Kerla", 3.1416f, 2.75f,1, "ella.jpg" ));
        inventario.add(new Producto("w41fuD3r0dr1", "Adri", 31.14f, 5.75f,1, "ellader0dr1.jpg" ));
        inventario.add(new Producto("l0l1s4br0s4", "Zoe bb", 99.99f, 50.23f,1, "ella.jpg" ));
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
}

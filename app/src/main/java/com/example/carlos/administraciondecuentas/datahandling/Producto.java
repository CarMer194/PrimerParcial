package com.example.carlos.administraciondecuentas.datahandling;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable{
    String name;
    double precio;

    public Producto(String name,double precio){
        this.name=name;
        this.precio=precio;
    }

    protected Producto(Parcel in) {
        name = in.readString();
        precio = in.readDouble();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(precio);
    }
}

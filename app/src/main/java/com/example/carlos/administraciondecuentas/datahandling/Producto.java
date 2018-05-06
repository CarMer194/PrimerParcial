package com.example.carlos.administraciondecuentas.datahandling;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable{
    String id;
    String name;
    float venta;
    float costo;
    int cantidad;
    String imagen;


    public Producto(String id, String name, float venta, float costo, int cantidad,String imagen) {
        this.id = id;
        this.name = name;
        this.venta = venta;
        this.costo = costo;
        this.cantidad = cantidad;
        this.imagen=imagen;
    }

    public Producto() {
    }

    protected Producto(Parcel in) {
        id = in.readString();
        name = in.readString();
        venta = in.readFloat();
        costo = in.readFloat();
        cantidad = in.readInt();
        imagen = in.readString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVenta() {
        return venta;
    }

    public void setVenta(float venta) {
        this.venta = venta;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeFloat(venta);
        dest.writeFloat(costo);
        dest.writeInt(cantidad);
        dest.writeString(imagen);
    }
}

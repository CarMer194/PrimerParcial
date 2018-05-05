package com.example.carlos.administraciondecuentas.datahandling;

        import java.util.ArrayList;

/**
 * Created by UCA on 4/5/2018.
 */

public class DataHandler {
    protected ArrayList<Producto> productList;
    public DataHandler(){
        productList = new ArrayList<>();
        productList.add(new Producto("Adri", 31.14));
        productList.add(new Producto("Perrow", 13.42));
        productList.add(new Producto("Kerla", 2.00));
    }

    public ArrayList<Producto> getProductList(){
        return productList;
    }

    public void addProduct(Producto producto){
        productList.add(producto);
    }
}

package com.example.carlos.administraciondecuentas.Adapters;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.R;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> productos;
    private DataHandler dataHandler;
    private subTotalListener subTotalListener = null;

    public ProductListAdapter(Context context,DataHandler dataHandler){
        this.context = context;
        this.dataHandler = dataHandler;
        productos = dataHandler.getInventario();
    }
    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_productos, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Producto productoactual = (Producto) getItem(position);
        viewHolder.id.setText(productoactual.getId());
        viewHolder.nombre.setText(productoactual.getName());
        viewHolder.venta.setText(Float.toString(productoactual.getVenta()));
        viewHolder.costo.setText(Float.toString(productoactual.getCosto()));
        viewHolder.cantidad.setText("1000");

        return convertView;
    }
    private class ViewHolder{
        TextView id, nombre, cantidad, venta, costo;

        public ViewHolder(View view){
            id = view.findViewById(R.id.cardproductoid);
            nombre = view.findViewById(R.id.cardproductonom);
            venta = view.findViewById(R.id.cardproductovent);
            cantidad = view.findViewById(R.id.cardproductocant);
            costo = view.findViewById(R.id.cardproductocost);
        }
    }
    public void countTotal(){

    }

    public interface subTotalListener {
        public abstract void onSubTotalUpdate(int total);
    }
}

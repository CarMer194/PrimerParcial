package com.example.carlos.administraciondecuentas;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> productos;
    private subTotalListener subTotalListener = null;
    private DataHandler dataHandler;

    public CustomListAdapter(Context context, ArrayList<Producto> productos){
        this.context = context;
        this.productos = productos;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.cardagregaringreso, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Producto productoactual = (Producto) getItem(position);
        final double precio = productoactual.getVenta();
        viewHolder.name.setText(productoactual.getName());
        viewHolder.precio.setText(String.valueOf(precio));
        viewHolder.cantidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String canti = viewHolder.cantidad.getText().toString();
                    if (!canti.equals("")) {
                        int cant = Integer.parseInt(canti);
                        viewHolder.subtotal.setText(String.valueOf(precio * cant));
                    }
                    canti = null;
                }
            }
        });
        return convertView;
    }
    private class ViewHolder{
        TextView name,precio,subtotal,cantidad;

        public ViewHolder(View view){
            name = view.findViewById(R.id.NuevoIngresoProductName);
            precio = view.findViewById(R.id.NuevoIngresoProductPrice);
            subtotal = view.findViewById(R.id.NuevoIngresoProductSubtotal);
            cantidad = view.findViewById(R.id.NuevoIngresoProductQuantity);
        }
    }
    public void countTotal(){

    }

    public interface subTotalListener {
        public abstract void onSubTotalUpdate(int total);
    }
}

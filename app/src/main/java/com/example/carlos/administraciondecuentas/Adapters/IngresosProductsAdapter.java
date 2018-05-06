package com.example.carlos.administraciondecuentas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.CustomListAdapter;
import com.example.carlos.administraciondecuentas.R;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

import java.util.ArrayList;

public class IngresosProductsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> productos;
    private CustomListAdapter.subTotalListener subTotalListener = null;
    boolean isGastos = false;

    public IngresosProductsAdapter(Context context,ArrayList<Producto> productos){
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
        final IngresosProductsAdapter.ViewHolder viewHolder;
        if(convertView == null) {
            if (!isGastos) {
                convertView = LayoutInflater.from(context).inflate(R.layout.card_agregar_ingreso, parent, false);
            }
            else{
                convertView = LayoutInflater.from(context).inflate(R.layout.card_agregar_ingreso, parent, false);
            }

            viewHolder = new IngresosProductsAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (IngresosProductsAdapter.ViewHolder) convertView.getTag();
        }

        Producto productoactual = (Producto) getItem(position);
        final double precio = productoactual.getPrecio();
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

    public void setGastos(boolean gastos){
        isGastos = gastos;
    }
}

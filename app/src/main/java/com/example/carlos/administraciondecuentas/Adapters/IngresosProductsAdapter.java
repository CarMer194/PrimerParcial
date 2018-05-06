package com.example.carlos.administraciondecuentas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.R;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
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

            convertView = LayoutInflater.from(context).inflate(R.layout.card_ingresos_productos, parent, false);
            viewHolder = new IngresosProductsAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (IngresosProductsAdapter.ViewHolder) convertView.getTag();
        }

        Producto productoactual = (Producto) getItem(position);
        final float precio = productoactual.getVenta();
        viewHolder.producto.setText(productoactual.getName());
        viewHolder.precio.setText(String.valueOf(precio));
        viewHolder.cantidad.setText(String.valueOf(productoactual.getCantidad()));
        viewHolder.subtotal.setText(String.valueOf(productoactual.getCantidad() * precio));

        return convertView;
    }
    private class ViewHolder{
        TextView producto,precio,subtotal,cantidad;

        public ViewHolder(View view){
            producto = view.findViewById(R.id.ing_producto);
            precio = view.findViewById(R.id.ing_precio);
            subtotal = view.findViewById(R.id.ing_subtotal);
            cantidad = view.findViewById(R.id.ing_cantidad);
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

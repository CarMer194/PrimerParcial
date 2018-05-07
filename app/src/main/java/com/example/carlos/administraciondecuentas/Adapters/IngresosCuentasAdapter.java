package com.example.carlos.administraciondecuentas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.R;
import com.example.carlos.administraciondecuentas.datahandling.Cuenta;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class IngresosCuentasAdapter extends RecyclerView.Adapter<IngresosCuentasAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Cuenta> cuentas;
    private int size;
    boolean isGastos = false;

    public IngresosCuentasAdapter(Context context,ArrayList<Cuenta> cuentas){
        this.context = context;
        this.cuentas = cuentas;
        /*size = 0;
        for(Cuenta c:cuentas){
            if(c.getIngresoProductos().size()>0){
                size=size+1;
            }
        }*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_gastos_mostrar_cuentas, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Cuenta cuentaactual = cuentas.get(holder.getAdapterPosition());
        if(cuentaactual.getIngresoProductos() != null) {
            holder.nombreCuenta.setText(cuentaactual.getNombre());
            holder.nombre.setText(cuentaactual.getNombreTitular());
            holder.Total.setText(String.valueOf(cuentaactual.getTotal()));

            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
            holder.fecha.setText(df.format(new Date()));

            holder.lista.setAdapter(new IngresosProductsAdapter(context, cuentaactual.getIngresoProductos()));
        }
    }

    @Override
    public int getItemCount() {
        return cuentas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,nombreCuenta,Total,fecha;
        ImageView imagen;
        ListView lista;

        public ViewHolder(View view){
            super(view);
            nombre = view.findViewById(R.id.card_gastos_nombre);
            nombreCuenta = view.findViewById(R.id.card_gastos_cuenta);
            Total = view.findViewById(R.id.card_gastos_total);
            fecha = view.findViewById(R.id.card_gastos_fecha);
            imagen = view.findViewById(R.id.card_gastos_imagen);
            lista = view.findViewById(R.id.card_gastos_listview);
        }
    }
    public void setGastos(boolean gastos){
        isGastos = gastos;
    }
}

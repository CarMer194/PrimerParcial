package com.example.carlos.administraciondecuentas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.R;
import com.example.carlos.administraciondecuentas.datahandling.Resumen_info;

import java.util.ArrayList;

public class Cuentas_Adapter extends RecyclerView.Adapter<Cuentas_Adapter.Cuentas_AdapterViewHolder> {
    Context mCtx;
    ArrayList<Resumen_info> lista;

    public Cuentas_Adapter(Context mCtx, ArrayList<Resumen_info> lista) {
        this.mCtx = mCtx;
        this.lista = lista;
        System.out.println("eso: "+lista.get(0).getTotal());
    }

    @Override
    public Cuentas_AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.cardcuentas,null);
        return new Cuentas_AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Cuentas_AdapterViewHolder holder, int position) {
        holder.nombre.setText(lista.get(position).getNombre());
        //System.out.println(lista.get(position).getTotal()+" esto");
        holder.total.setText("$"+lista.get(position).getTotal()+" ");


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class Cuentas_AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,total;
        //ImageView imagen;

        public Cuentas_AdapterViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.cue_text3);
            total= itemView.findViewById(R.id.cuenta_text1);
            //imagen= itemView.findViewById(R.id.res_cue_imagen1);
        }
    }
}

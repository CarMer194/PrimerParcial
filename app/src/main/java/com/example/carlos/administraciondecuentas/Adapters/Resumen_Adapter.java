package com.example.carlos.administraciondecuentas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.R;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Resumen_info;

import java.util.ArrayList;
import java.util.List;

public class Resumen_Adapter extends RecyclerView.Adapter<Resumen_Adapter.Resumen_AdapterViewHolder> {
    Context mCtx;
    ArrayList<Resumen_info> lista;
    DataHandler dataHandler;

    public Resumen_Adapter(Context mCtx, ArrayList<Resumen_info> lista) {
        this.mCtx = mCtx;
        this.lista = lista;
    }

    @Override
    public Resumen_AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.resumencard,null);
        return new Resumen_AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Resumen_AdapterViewHolder holder, int position) {
        holder.fecha.setText(lista.get(position).getMes()+" "+lista.get(position).getAño());
        holder.ganancia.setText(lista.get(position).getTotalIngreso()+"");
        holder.perdida.setText(lista.get(position).getTotalGasto()+"");
        double temp= lista.get(position).getTotalIngreso() + lista.get(position).getTotalGasto();
        holder.diferencia.setText(temp+"");


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class Resumen_AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView fecha,ganancia,perdida,diferencia;
        ImageView imagen1,imagen2,imagen3;

        public Resumen_AdapterViewHolder(View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.res_cue_fecha);
            ganancia = itemView.findViewById(R.id.res_cue_ganancia);
            perdida=itemView.findViewById(R.id.res_cue_perdida);
            diferencia=itemView.findViewById(R.id.res_cue_diferencia);
            imagen1=itemView.findViewById(R.id.res_cue_imagen1);
            imagen2=itemView.findViewById(R.id.res_cue_imagen2);
            imagen3=itemView.findViewById(R.id.res_cue_imagen3);

        }
    }
}

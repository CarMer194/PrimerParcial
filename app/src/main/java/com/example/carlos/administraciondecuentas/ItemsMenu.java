package com.example.carlos.administraciondecuentas;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemsMenu extends RecyclerView.Adapter<ItemsMenu.ItemMenuViewHolder> {
    Context mCtx;
    List<ListMenu> lista;

    public ItemsMenu(Context mCtx, List<ListMenu> lista) {
        this.mCtx = mCtx;
        this.lista = lista;
    }

    @Override
    public ItemsMenu.ItemMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.cardmenu,null);
        return null;
    }

    @Override
    public void onBindViewHolder(ItemMenuViewHolder holder, int position) {
        // Setear imagen tambien
        Uri uri= Uri.parse(lista.get(position).getIcono());
        holder.imagen.setImageURI(uri);
        holder.texto.setText(lista.get(position).getTitulo());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemMenuViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView texto;

        public ItemMenuViewHolder(View view){
            super(view);

            imagen=view.findViewById(R.id.cmenu_texto);
            texto=view.findViewById(R.id.cmenu_imagen);
        }


    }
}

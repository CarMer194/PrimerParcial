package com.example.carlos.administraciondecuentas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
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
        return new ItemMenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemMenuViewHolder holder, final int position) {
        // Setear imagen tambien

        Uri uri= Uri.parse(lista.get(position).getIcono());
        holder.imagen.setImageURI(uri);
        holder.texto.setText(lista.get(position).getTitulo());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position){
                    case 3:
                        intent = new Intent(mCtx,SearchableActivity.class);
                        mCtx.startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(mCtx, SearchableActivityJosseh.class);
                        mCtx.startActivity(intent);
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ItemMenuViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView texto;
        CardView card;

        public ItemMenuViewHolder(View view){
            super(view);

            texto=view.findViewById(R.id.cmenu_texto);
            imagen=view.findViewById(R.id.cmenu_imagen);
            card = view.findViewById(R.id.card_menu);
        }


    }
}

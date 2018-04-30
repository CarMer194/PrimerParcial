package com.example.carlos.administraciondecuentas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ItemsMenu adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //System.out.println("EL PACK ES:"+getPackageName());


        ListaM listaM = new ListaM();

        recyclerView = findViewById(R.id.men_recy1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        adapter= new ItemsMenu(this, listaM.getLista());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);




    }


}

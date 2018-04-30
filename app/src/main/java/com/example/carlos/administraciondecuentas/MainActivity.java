package com.example.carlos.administraciondecuentas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
=======
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
>>>>>>> cdfd192b3ba76156e8c3cc6b683ff35cb319938b

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ItemsMenu adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaM listaM = new ListaM();

        recyclerView = findViewById(R.id.men_recy1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        adapter= new ItemsMenu(this, listaM.getLista());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



    }


}

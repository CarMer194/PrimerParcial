package com.example.carlos.administraciondecuentas;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carlos.administraciondecuentas.datahandling.DataHandler;


public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ItemsMenu adapter;
    DataHandler dataHandler;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println("EL PACK ES:"+getPackageName());
        bundle = getIntent().getExtras();
        if(bundle == null){
            dataHandler = new DataHandler();
            Resources res = getResources();
        ListaM listaM = new ListaM(res);

        recyclerView = findViewById(R.id.men_recy1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        adapter= new ItemsMenu(this, listaM.getLista());
        adapter.setDataHandler(dataHandler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);




    }


}
}

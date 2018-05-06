package com.example.carlos.administraciondecuentas;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.carlos.administraciondecuentas.datahandling.DataHandler;

public class GastosActivity extends AppCompatActivity {
    private DataHandler dataHandler;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.gastos_layout);
    }

    public void NuevoGasto(View v){
        Intent intent = new Intent(this,SearchableActivity.class);
        intent.putExtra("gastos",true);
        intent.putExtra("DataHandler", dataHandler);
        startActivity(intent);
    }
}

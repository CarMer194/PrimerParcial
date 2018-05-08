package com.example.carlos.administraciondecuentas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.carlos.administraciondecuentas.datahandling.DataHandler;

public class Resumen_Activity extends AppCompatActivity {
    private DataHandler dataHandler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        setContentView(R.layout.resumen);
    }
}

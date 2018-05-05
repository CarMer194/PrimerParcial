package com.example.carlos.administraciondecuentas;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IngresosActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresos_layout);
    }

    public void NuevoIngreso(View v){
        Intent intent = new Intent(this,SearchableActivity.class);
        intent.putExtra("gastos",false);
        startActivity(intent);
    }
}

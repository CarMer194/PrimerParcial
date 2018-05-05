package com.example.carlos.administraciondecuentas;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GastosActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.gastos_layout);
    }

    public void NuevoGasto(View v){
        Intent intent = new Intent(this,SearchableActivity.class);
        intent.putExtra("gastos",true);
        startActivity(intent);
    }
}

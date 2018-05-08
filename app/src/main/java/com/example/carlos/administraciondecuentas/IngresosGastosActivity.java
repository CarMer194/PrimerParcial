package com.example.carlos.administraciondecuentas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carlos.administraciondecuentas.Adapters.IngresosGastosCuentasAdapter;
import com.example.carlos.administraciondecuentas.datahandling.Cuenta;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

public class IngresosGastosActivity extends AppCompatActivity {
    private DataHandler dataHandler;
    private RecyclerView recyclerView;
    private IngresosGastosCuentasAdapter ingresosGastosCuentasAdapter;
    boolean hasProducts,isGastos;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        isGastos = getIntent().getBooleanExtra("isGastos",false);
        Log.d("Valor de isGastos",String.valueOf(isGastos));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresos_gastos_layout);

        recyclerView = findViewById(R.id.ingresos_recyclerview);
        for(Cuenta c:dataHandler.getCuentas()){
            if(c.getIngresoProductos().size()>0 && isGastos == false){
                hasProducts = true;
                break;
            }
            else if(c.getGastoProductos().size()>0 && isGastos == true){
                hasProducts = true;
                break;
            }
        }
        if(hasProducts) {
            ingresosGastosCuentasAdapter = new IngresosGastosCuentasAdapter(this, dataHandler.getCuentas(),isGastos);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(ingresosGastosCuentasAdapter);
            if(isGastos)
                recyclerView.setBackgroundColor(Color.BLUE);
            else
                recyclerView.setBackgroundColor(Color.YELLOW);
        }

    }

    public void NuevoIngresoGasto(View v){
        Intent intent = new Intent(this,SearchableActivity.class);
        intent.putExtra("gastos",isGastos);
        intent.putExtra("DataHandler", dataHandler);
        startActivityForResult(intent,1);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

                dataHandler = data.getParcelableExtra("result");
                isGastos = data.getBooleanExtra("isGastos",false);
                ingresosGastosCuentasAdapter = new IngresosGastosCuentasAdapter(this, dataHandler.getCuentas(),isGastos);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(ingresosGastosCuentasAdapter);
                if(isGastos)
                    recyclerView.setBackgroundColor(Color.BLUE);
                else
                    recyclerView.setBackgroundColor(Color.YELLOW);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        //retornando nuevo dataHandler
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",dataHandler);
        setResult(2,returnIntent);
        finish();
        super.onBackPressed();
    }
}

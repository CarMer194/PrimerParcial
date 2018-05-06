package com.example.carlos.administraciondecuentas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.carlos.administraciondecuentas.Adapters.IngresosCuentasAdapter;
import com.example.carlos.administraciondecuentas.datahandling.Cuenta;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;

public class IngresosActivity extends AppCompatActivity {
    private DataHandler dataHandler;
    private RecyclerView recyclerView;
    private IngresosCuentasAdapter ingresosCuentasAdapter;
    boolean hasProducts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresos_layout);


        recyclerView = findViewById(R.id.ingresos_recyclerview);
        for(Cuenta c:dataHandler.getCuentas()){
            if(c.getIngresoProductos()!=null){
                hasProducts = true;
                break;
            }
        }
        if(hasProducts) {
            ingresosCuentasAdapter = new IngresosCuentasAdapter(this, dataHandler.getCuentas());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(ingresosCuentasAdapter);
        }

    }

    public void NuevoIngreso(View v){
        Intent intent = new Intent(this,SearchableActivity.class);
        intent.putExtra("gastos",false);
        intent.putExtra("DataHandler", dataHandler);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                dataHandler = data.getParcelableExtra("result");
                ingresosCuentasAdapter = new IngresosCuentasAdapter(this, dataHandler.getCuentas());
                Log.d("Tamano de cuentas IA",String.valueOf(dataHandler.getCuentas().size()));
                for(Cuenta c:dataHandler.getCuentas()){
                    Log.d("entra al for","Pero no al if");
                    if(c.getIngresoProductos()!=null)
                        Log.d("Tamanode lista producto",String.valueOf(c.getIngresoProductos().size()));
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(ingresosCuentasAdapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

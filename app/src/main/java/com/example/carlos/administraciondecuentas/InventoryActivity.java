package com.example.carlos.administraciondecuentas;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;

import com.example.carlos.administraciondecuentas.Adapters.ProductListAdapter;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {
    DataHandler dataHandler;
    ArrayList<Producto> productos;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        dataHandler = getIntent().getParcelableExtra("DataHandler");


        ProductListAdapter adapter = new ProductListAdapter(this, dataHandler);
        ListView items = findViewById(R.id.Lista_productos);
        items.setAdapter(adapter);
    }

    public void NuevoProducto(View v){
        Intent intent = new Intent(this,NuevoProductoActivity.class);
        intent.putExtra("dataHandler",dataHandler);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                dataHandler = data.getParcelableExtra("result");
                ProductListAdapter adapter = new ProductListAdapter(this, dataHandler);
                ListView items = findViewById(R.id.Lista_productos);
                items.setAdapter(adapter);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        //retornando nuevo dataHandler
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",dataHandler);
        setResult(3,returnIntent);
        finish();
        super.onBackPressed();
    }
}

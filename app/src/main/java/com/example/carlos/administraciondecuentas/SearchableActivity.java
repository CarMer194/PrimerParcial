package com.example.carlos.administraciondecuentas;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchableActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        /*Intent srcIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(srcIntent.getAction())){
            String query = srcIntent.getStringExtra(SearchManager.QUERY);

        }*/
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Kerla", 2.00));
        productos.add(new Producto("Adri", 31.14));
        productos.add(new Producto("Perrow", 13.42));
        CustomListAdapter adapter = new CustomListAdapter(this, productos);
        ListView items = findViewById(R.id.List_view_items);
        items.setAdapter(adapter);
    }
}

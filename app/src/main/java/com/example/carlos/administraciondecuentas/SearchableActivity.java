package com.example.carlos.administraciondecuentas;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchableActivity extends ListActivity {
    ArrayList<Producto> productos,escogidos;
    ListView items;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        productos = new ArrayList<>();
        productos.add(new Producto("Kerla", 2.00));
        productos.add(new Producto("Adri", 31.14));
        productos.add(new Producto("Perrow", 13.42));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        Log.d("Component Name",getComponentName().toString());
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doMyQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public void doMyQuery(String query){
        for (Producto p :productos){
            if(p.getName().equals(query)){
                escogidos.add(p);
                items = getListView();
                adapter = new CustomListAdapter(this, escogidos);
                items.setAdapter(adapter);
            }
        }
    }

    public void Cancelar(View v){
        escogidos.clear();
        adapter.notifyDataSetChanged();
        items.notify();
    }
}

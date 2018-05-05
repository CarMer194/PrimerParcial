package com.example.carlos.administraciondecuentas;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

import java.util.ArrayList;

public class SearchableActivityJosseh extends ListActivity {
    DataHandler dataHandler;
    ArrayList<Producto> productos;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable_josseh);
        productos = getIntent().getParcelableArrayListExtra("ProductList");
        Intent srcIntent    = getIntent();
        if(Intent.ACTION_SEARCH.equals(srcIntent.getAction())){
            String query = srcIntent.getStringExtra(SearchManager.QUERY);
        }
        ProductListAdapter adapter = new ProductListAdapter(this, productos);
        ListView items = findViewById(android.R.id.list);
        items.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setSubmitButtonEnabled(true);

        return true;
    }


}

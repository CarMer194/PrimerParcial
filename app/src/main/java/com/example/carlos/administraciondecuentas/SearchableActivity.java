package com.example.carlos.administraciondecuentas;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchableActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Intent srcIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(srcIntent.getAction())){
            String query = srcIntent.getStringExtra(SearchManager.QUERY);

        }
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Kerla", 2.00));
        productos.add(new Producto("Adri", 31.14));
        productos.add(new Producto("Perrow", 13.42));
        CustomListAdapter adapter = new CustomListAdapter(this, productos);
        ListView items = findViewById(R.id.List_view_items);
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

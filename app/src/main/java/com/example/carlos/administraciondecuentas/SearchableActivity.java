package com.example.carlos.administraciondecuentas;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Producto;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SearchableActivity extends AppCompatActivity {
    ArrayList<Producto> productos,escogidos;
    ListView items;
    CustomListAdapter adapter;
    TextView fecha,total;
    boolean isGastos;
    DataHandler dataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        items = findViewById(R.id.List_view_productos);
        escogidos = new ArrayList<>();
        productos = dataHandler.getInventario();

        //Agarrando intent de la activity anterior para proyectar otro layout
        Intent intent = getIntent();
        isGastos = intent.getBooleanExtra("gastos",false);

        //Seteando layout en base al booleano recibido en el intent
        if (!isGastos)
            setContentView(R.layout.activity_searchable_ingresos);
        else
            setContentView(R.layout.activity_searchable_gastos);

        escogidos = new ArrayList<>();

        //seteando el ID la Listview
        items = findViewById(R.id.List_view_productos);


        //seteando fecha
        fecha = findViewById(R.id.Ning_txtview_fecha);
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        fecha.setText(df.format(new Date()));

        //seteando total
        total = findViewById(R.id.Ning_txtview_total);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
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
                if(escogidos.size()==0) {
                    escogidos.add(p);
                    adapter = new CustomListAdapter(this, escogidos);
                    adapter.setGastos(isGastos);
                    items.setAdapter(adapter);
                }
                else{
                    escogidos.add(p);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void Cancelar(View v){
        if(escogidos.size()>0) {
            escogidos.clear();
            adapter.notifyDataSetChanged();
        }
    }
}

package com.example.carlos.administraciondecuentas;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlos.administraciondecuentas.Adapters.CustomListAdapter;
import com.example.carlos.administraciondecuentas.datahandling.Cuenta;
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
    TextView fecha,total,nombreCli;
    boolean isGastos;
    DataHandler dataHandler;
    Spinner spinner;
    TextView cantidad,subtotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        productos = dataHandler.getInventario();

        //Agarrando intent de la activity anterior para proyectar otro layout
        Intent intent = getIntent();
        isGastos = intent.getBooleanExtra("gastos",false);

        //Seteando layout en base al booleano recibido en el intent
        if (!isGastos)
            setContentView(R.layout.activity_searchable_ingresos);
        else
            setContentView(R.layout.activity_searchable_gastos);

        //instanceando los productos escogidos
        escogidos = new ArrayList<>();

        //seteando el ID la Listview
        items = findViewById(R.id.List_view_productos);

        //seteando el Id a nombre
        nombreCli = findViewById(R.id.Ning_edittxt_nameCli);

        //seteando fecha
        fecha = findViewById(R.id.Ning_txtview_fecha);
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        fecha.setText(df.format(new Date()));

        //seteando total
        total = findViewById(R.id.Ning_txtview_total);

        //seteando el spinner
        spinner = findViewById(R.id.Ning_spinner1);
        int size = dataHandler.getCuentas().size();
        String [] cuentas = new String[size];
        for(int i = 0;i<size;i++){
           cuentas[i] = dataHandler.getCuentas().get(i).getNombre();
        }
        ArrayAdapter<String> Arradapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cuentas);
        Arradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(Arradapter);
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
                    for(int i = 0;i<items.getCount();i++) {
                        Log.d("entra al for","si");
                        subtotal = getViewByPosition(i, items).findViewById(R.id.NuevoIngresoProductSubtotal);
                        subtotal.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                String sub = subtotal.getText().toString();
                                String temp = total.getText().toString();
                                String fstring = temp.substring(temp.lastIndexOf('$') + 1);
                                float t = Float.parseFloat(fstring);
                                t = t+Float.parseFloat(sub);
                                String fin = "$ "+String.valueOf(t);
                                total.setText(fin);
                                return false;
                            }
                        });
                    }
                }
                else{
                    if(escogidos.contains(p)){
                        Toast.makeText(this,"No se puede agregar el mismo producto",Toast.LENGTH_LONG).show();
                    }else {
                        escogidos.add(p);
                        adapter.notifyDataSetChanged();
                    }
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

    public void Guardar(View v){
        //Guardando los cambios en base a los textviews y spinner
        boolean si = false,aux = true;
        String cant;
        for(int i = 0;i<items.getCount();i++) {
            cantidad = getViewByPosition(i, items).findViewById(R.id.NuevoIngresoProductQuantity);
            cant = cantidad.getText().toString();
            if (cant.equals("")) {
                Toast.makeText(this, "Favor Ingresar cantidad", Toast.LENGTH_SHORT).show();
                aux = false;
            } if(!cant.equals("")) {
                si = true;
                escogidos.get(i).setCantidad(Integer.parseInt(cant));
            }
        }
        //
        //VERIFICACIONES
        //
        if(items.getCount()>0){
            if (nombreCli.getText().toString().equals("") && isGastos) {
                Toast.makeText(this, "Por favor ingresar un Proveedor", Toast.LENGTH_SHORT).show();
            } else if (nombreCli.getText().toString().equals("") && !isGastos) {
                Toast.makeText(this, "Por favor ingresar un Cliente", Toast.LENGTH_SHORT).show();
            } else if (!nombreCli.getText().toString().equals("")) {
                if(si && aux) {
                    /*
                    ArrayList<Cuenta> cuentas = new ArrayList<>();
                    cuentas = dataHandler.getCuentas();
                    String nombreCuenta = spinner.getSelectedItem().toString();
                    String temp = this.total.getText().toString();
                    String fstring = temp.substring(temp.lastIndexOf('$') + 1);
                    float total = Float.parseFloat(fstring);
                    String nombre = nombreCli.getText().toString();
                    cuentas.add(new Cuenta(nombreCuenta, nombre, total, ;
                    dataHandler.setCuentas(cuentas);*/
                    if(!isGastos){
                        if(dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).getIngresoProductos() == null){
                            dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).setIngresoProductos(new ArrayList<Producto>(escogidos));
                        }else{
                            ArrayList<Producto> previous = dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).getIngresoProductos();
                            ArrayList<Producto> escogido = new ArrayList<>(escogidos);
                            escogido.addAll(previous);
                            dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).setIngresoProductos(escogido);
                        }
                    }else{
                        if(dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).getIngresoProductos() == null){
                            dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).setIngresoProductos(new ArrayList<Producto>(escogidos));
                        }else{
                            ArrayList<Producto> previous = dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).getGastoProductos();
                            ArrayList<Producto> escogido = new ArrayList<>(escogidos);
                            escogido.addAll(previous);
                            dataHandler.getCuentas().get(spinner.getSelectedItemPosition()).setGastoProductos(escogido);
                        }
                    }

                    Toast.makeText(this, "Se ha añadido al historial de ingresos.", Toast.LENGTH_SHORT).show();
                    this.onBackPressed();
                }
            }
        }
        else{
            Toast.makeText(this,"Por favor ingresar al menos un producto",Toast.LENGTH_SHORT).show();
        }

    }

    public void onBackPressed(){
        //retornando nuevo dataHandler
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",dataHandler);
        returnIntent.putExtra("isGastos",isGastos);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        super.onBackPressed();
    }
    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}

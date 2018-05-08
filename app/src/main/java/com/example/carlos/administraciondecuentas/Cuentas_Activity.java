package com.example.carlos.administraciondecuentas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.carlos.administraciondecuentas.Adapters.Cuentas_Adapter;
import com.example.carlos.administraciondecuentas.Adapters.Resumen_Adapter;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Resumen_info;

import java.util.ArrayList;
import java.util.List;

public class Cuentas_Activity extends AppCompatActivity {
    private DataHandler dataHandler;
    private ArrayList<Resumen_info> resumen=new ArrayList<>();
    private Cuentas_Adapter adapter;
    private double total=0;
    TextView textView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        setContentView(R.layout.cuentas);
        llenarLista();
        RecyclerView recyclerView=findViewById(R.id.cuenta_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new Cuentas_Adapter(this,resumen);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        textView=findViewById(R.id.cue_text5);
        textView.setText("$"+total+" ");
    }

    private void llenarLista(){
        int i=dataHandler.getCuentas().size();
        String[] nombres = new String[50];
        int c=0,a=0;
        Resumen_info[] resumen_infos=new Resumen_info[60];
        boolean repe=false;

        for (int j=0;j<i;j++){
            if(c>=1){
                for (int m=0;m<c;m++){
                    if (dataHandler.getCuentas().get(j).getNombre().equalsIgnoreCase(nombres[m])){
                        repe=true;
                    }
                }
            }
            if (!repe){
                nombres[c]=dataHandler.getCuentas().get(j).getNombre();
                c++;
            }

        }
        /*for (int j=0;j<i;j++){
            System.out.println("las cuentas son:"+nombres[j]);
        }*/
        for (int j=0;j<c;j++){
            resumen_infos[a]=new Resumen_info(nombres[j]);
            for (int m=0;m<i;m++){
                if (dataHandler.getCuentas().get(m).getNombre().equalsIgnoreCase(nombres[j])){
                    resumen_infos[a].sumTotalIngresos(dataHandler.getCuentas().get(m).getIngreso());
                    resumen_infos[a].sumTotalGastos(dataHandler.getCuentas().get(m).getGasto());
                }
            }
            resumen_infos[a].sumTotal(resumen_infos[a].getTotalIngreso()+resumen_infos[a].getTotalGasto());
            total= resumen_infos[a].getTotalIngreso()+resumen_infos[a].getTotalGasto();
            resumen.add(resumen_infos[a]);
            a++;
        }




    }
}

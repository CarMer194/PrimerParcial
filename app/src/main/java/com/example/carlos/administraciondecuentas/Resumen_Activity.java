package com.example.carlos.administraciondecuentas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.carlos.administraciondecuentas.Adapters.Resumen_Adapter;
import com.example.carlos.administraciondecuentas.datahandling.Cuenta;
import com.example.carlos.administraciondecuentas.datahandling.DataHandler;
import com.example.carlos.administraciondecuentas.datahandling.Resumen_info;

import java.util.ArrayList;
import java.util.List;

public class Resumen_Activity extends AppCompatActivity {
    private DataHandler dataHandler;
    private ArrayList<Resumen_info> resumens = new ArrayList<>();
    private Resumen_Adapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHandler = getIntent().getParcelableExtra("DataHandler");
        setContentView(R.layout.resumen);
        llernarLista();
        RecyclerView recyclerView=findViewById(R.id.resumen_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new Resumen_Adapter(this,resumens);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void llernarLista(){
        int i=dataHandler.getCuentas().size();
        String[] meses = new String[50];
        String[] anios = new String[50];
        String[] dias= new String[50];
        Resumen_info[] resumen = new Resumen_info[50];
        int c=0,a=0;
        boolean repe=false;

        for (int j=0;j<i;j++){
            if (c>=1){
                System.out.println("c= "+c);
                for (int m=0;m<c;m++){
                    System.out.println("entro");
                    if (dataHandler.getCuentas().get(j).getMes().equalsIgnoreCase(meses[m]) && dataHandler.getCuentas().get(j).getAño().equalsIgnoreCase(anios[m])){
                        repe=true;
                        break;
                    }
                }
            }
            if (!repe){
                meses[c]=dataHandler.getCuentas().get(j).getMes();
                anios[c]= dataHandler.getCuentas().get(j).getAño();
                dias[c]= dataHandler.getCuentas().get(j).getDia();
                c++;
            }
        }

        /*for (int j=0;j<c;j++){
            System.out.println("meses: "+meses[j]+" anios: "+anios[j]);
        }*/

        for (int j=0;j<c;j++){
            resumen[a] = new Resumen_info(meses[j],anios[j],dias[j]);
            for (int m=0;m<i;m++){
                if (dataHandler.getCuentas().get(m).getMes().equalsIgnoreCase(meses[j]) && dataHandler.getCuentas().get(m).getAño().equalsIgnoreCase(anios[j])){
                    resumen[a].sumTotalIngresos(dataHandler.getCuentas().get(m).getIngreso());
                    resumen[a].sumTotalGastos(dataHandler.getCuentas().get(m).getGasto());
                    System.out.println("entro");
                }
            }
            resumen[a].sumTotal(resumen[a].getTotalIngreso()+resumen[a].getTotalGasto());
            resumens.add(resumen[a]);
            a++;

        }

        //System.out.println("prueba resumens: "+resumens.get(0).getTotalGasto()+" gasto: "+resumens.get(0).getTotalIngreso());

    }
}

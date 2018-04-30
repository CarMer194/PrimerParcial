package com.example.carlos.administraciondecuentas;

import java.util.ArrayList;
import java.util.List;

public class ListaM {
    private List<ListMenu> lista;
    private String pack="android.resource://com.carlos.administraciondecuentas/drawable/";

    public ListaM() {
        lista=fillList();
    }

    private ArrayList<ListMenu> fillList(){
        ArrayList<ListMenu> l = new ArrayList<>();
        l.add(new ListMenu(pack+"iconobalendario","Resumen"));
        l.add(new ListMenu(pack+"iconobanco","Cuentas"));
        l.add(new ListMenu(pack+"iconomoneda","Ingresos"));
        l.add(new ListMenu(pack+"iconocarreta","Gastos"));
        l.add(new ListMenu(pack+"icononota","Inventario"));
        return l;
    }

    public List<ListMenu> getLista() {
        return lista;
    }
}

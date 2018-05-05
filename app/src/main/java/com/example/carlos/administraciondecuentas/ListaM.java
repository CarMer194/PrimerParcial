package com.example.carlos.administraciondecuentas;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class ListaM {
    private List<ListMenu> lista;
    private String pack="android.resource://com.example.carlos.administraciondecuentas/drawable/";

    public ListaM(Resources res) {
        lista=fillList(res);
    }

    private ArrayList<ListMenu> fillList(Resources res){
        ArrayList<ListMenu> l = new ArrayList<>();
        l.add(new ListMenu(pack+"iconocalendario",res.getString(R.string.main_summary)));
        l.add(new ListMenu(pack+"iconobanco",res.getString(R.string.main_account)));
        l.add(new ListMenu(pack+"iconomoneda",res.getString(R.string.main_income)));
        l.add(new ListMenu(pack+"iconocarreta",res.getString(R.string.main_expenses)));
        l.add(new ListMenu(pack+"icononota",res.getString(R.string.main_inventory)));
        return l;
    }

    public List<ListMenu> getLista() {
        return lista;
    }
}

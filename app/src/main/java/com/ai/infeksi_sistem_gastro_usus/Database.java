package com.ai.infeksi_sistem_gastro_usus;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Keracunan> listKeracunan = new ArrayList<>();

    public static ArrayList<Keracunan> getListKeracunan(){
        return listKeracunan;
    }
}

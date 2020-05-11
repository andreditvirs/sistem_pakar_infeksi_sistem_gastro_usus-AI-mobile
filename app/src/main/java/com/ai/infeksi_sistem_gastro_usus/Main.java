package com.ai.infeksi_sistem_gastro_usus;

import java.util.ArrayList;

public class Main {
    private static ArrayList<String> hasilNamaKeracunanThreshold = new ArrayList<>();
    private static ArrayList<Float> persenKeracunanThreshold = new ArrayList<>();

    public static ArrayList<Float> cekDiDatabase(ArrayList<Integer> listAnswerBaru){

        // Init database dan method di sistemPakar, Jika sebelumnya sudah di init jangan dinit lagi
        // nanti malah ada dua
        if(Database.getListKeracunan().size() == 0){
            new SistemPakar();
        }

        int sizeOfListGejala = 0;
        // Cari di database dan return nilainya
        ArrayList<Float> listTotalProsentaseKeracunan = new ArrayList<>();
        for (int i = 0; i < Database.getListKeracunan().size(); i++){ //33
            float totalProsentase = 0;
            sizeOfListGejala = Database.getListKeracunan().get(i).getListGejala().size();
            for (int j = 0; j < sizeOfListGejala; j++){ //20
                float prosentaseTemp = 0;
                float counter = 0;
                int sizeOfListIndikator = Database.getListKeracunan().get(i).getListGejala().get(j).getListIndikator().size();
                for (int k = 0; k < sizeOfListIndikator; k++){ //1,2,4,5,...
                    int temp = Database.getListKeracunan().get(i).getListGejala().get(j).getListIndikator().get(k);
                    for (int l = 0; l < listAnswerBaru.size(); l++){ //1,2,4,5
                        int inpBaruBagian = listAnswerBaru.get(l);
                        if (inpBaruBagian == temp){
                            counter+=1;
                        }
                    }
                }
                prosentaseTemp = (counter/(sizeOfListIndikator));
                totalProsentase+=prosentaseTemp;
            }
            listTotalProsentaseKeracunan.add(totalProsentase/(sizeOfListGejala)*100);
        }
        return listTotalProsentaseKeracunan;
    }

    public static String getHasilKeracunanLainnya(ArrayList<Float> listTotalProsentaseKeracunanBaru){
        String hasil = "";
        ArrayList<String> tempNama = new ArrayList<>();
        ArrayList<Float> tempProsentase = new ArrayList<>();
        ArrayList<Keracunan> keracunan =  Database.getListKeracunan();
        for(int i = 0; i < Database.getListKeracunan().size(); i++){
            tempNama.add(Database.getListKeracunan().get(i).getNamaKeracunan());
            tempProsentase.add(listTotalProsentaseKeracunanBaru.get(i));
        }

        for(int i = 0; i < hasilNamaKeracunanThreshold.size(); i++){
            for(int j = 0; j < tempNama.size(); j++){
                if(tempNama.get(j).equals(hasilNamaKeracunanThreshold.get(i))){
                    tempNama.remove(j);
                    tempProsentase.remove(j);
                    System.out.println("=============");
                }
            }
        }

        for(int i = 0; i < tempNama.size(); i++){
            hasil += (tempNama.get(i)+ " ("+tempProsentase.get(i)+"%)\n");
        }

        if(hasil.equals("")){
            return "Tidak ada dugaan lain";
        }
        return hasil;
    }

    public static void setKeracunanThreshold(ArrayList<Float> listTotalProsentaseKeracunanBaru, int threshold){
        ArrayList<Keracunan> keracunan =  Database.getListKeracunan();
        for(int i =0; i < listTotalProsentaseKeracunanBaru.size(); i++){
            if(listTotalProsentaseKeracunanBaru.get(i)>=threshold){
                hasilNamaKeracunanThreshold.add(Database.getListKeracunan().get(i).getNamaKeracunan());
                persenKeracunanThreshold.add(listTotalProsentaseKeracunanBaru.get(i));
            }
        }
    }

    public static ArrayList<String> getHasilNamaKeracunanThreshold(){
        return hasilNamaKeracunanThreshold;
    }

    public static String getPersenKeracunanThreshold(){
        return ""+ persenKeracunanThreshold;
    }

    public static String getHasilKeracunanThreshold(){
        String hasil = "";
        for(int i = 0; i < hasilNamaKeracunanThreshold.size(); i++){
            hasil += (hasilNamaKeracunanThreshold.get(i) + " (" + persenKeracunanThreshold.get(i)+"%)\n");
        }
        if(hasil.equals("")){
            return "-";
        }
        return hasil;
    }
}

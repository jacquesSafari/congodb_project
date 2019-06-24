package org.database;

public class Row {

    private String [] datas;
    private int taille;

    public Row(int taille){
        this.datas = new String[taille];
        this.taille = taille;
    }

    public String[] getDatas() {
        return datas;
    }

    public void setDatas(String[] datas) {
        this.datas = datas;
    }

    public String afficher(String [] donnees){
        String str = "";
        for (int i =0; i < donnees.length; i++ ){
            str = "| "+str + donnees[i]+" |";
        }
        return str;
    }
}

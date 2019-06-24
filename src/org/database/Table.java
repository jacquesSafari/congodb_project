package org.database;

import java.util.ArrayList;

public class Table {
    private String tableName;
    private ArrayList<String> nomChamps;
    private ArrayList<Row> lignes;
    private int length;

    public Table(String name, ArrayList<String> listesChamps) {
        this.tableName = name;
        this.nomChamps = listesChamps;
        this.lignes = new ArrayList<Row>();
        this.length = this.nomChamps.size();
    }

    public String getTableName() {
        return tableName;
    }

    public ArrayList<String> getNomChamps() {
        return nomChamps;
    }

    public ArrayList<Row> getLignes() {
        return lignes;
    }

    public void ajouterRow(Row row){
        this.lignes.add(row);
    }

    public int getLength() {
        return length;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setNomChamps(ArrayList<String> nomChamps) {
        this.nomChamps = nomChamps;
    }

    public void setLignes(ArrayList<Row> lignes) {
        this.lignes = lignes;
    }

    public void select(Table table){
        for(int i = 0; i<table.getLength();i++){
            System.out.print("| "+table.getNomChamps().get(i) +" |");
        }
        System.out.println("\n-------------------------");
        for(int i =0; i < table.getLignes().size(); i++){
            System.out.println(table.getLignes().get(i).afficher(table.getLignes().get(i).getDatas()));
        }
    }
}

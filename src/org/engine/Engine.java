package org.engine;

import org.database.Database;
import org.database.Table;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cette classe est le moteur de requette sql
 * tous la verification de toutes les requettes se fera dans cette classe
 *
 * @author Benoni Zed
 */
public class Engine {
    /**
     * le tableau requette est charger de prendre chaque mot cle
     * de la requette SQL decouper en un tableau
     */
    private String requette[];
    /**
     * On crée un objet Database qui va représenter la base de donnée en cours
     */
    private Database db = new Database();

    /**
     * Construction d'un moteur par défaut
     */
    public Engine() {
    }

    /**
     * Construction d'un moteur sql en lui passant une requette
     *
     * @param _requette_
     */
    public Engine(String[] _requette_) {
        this.requette = _requette_;
    }

    /**
     * retourne la reference du tab requette
     *
     * @return
     */
    public String[] getRequette() {
        return requette;
    }

    /**
     * Donne une valeur au tableau requette
     *
     * @param requette
     */
    public void setRequette(String[] requette) {
        this.requette = requette;
    }

    /**
     * Verifie si la clause create est destinée à :
     * une database, une table
     *
     * @param query
     */
    public void typeOfQueryInCreate(String[] query) {
        if (query[1].equals("database")) {
            db.createDatabase(query[2]);
        }
        if (query[1].equals("table")){
            System.out.println("> Saisir les champs de la table séparer par un espace");
            Scanner scanner = new Scanner(System.in);
            String champs = scanner.nextLine();
            String nom = query[2];
            query = champs.split(" ");
            db.createTable(db,prepareTable(nom,query));
        }
    }

    public Table prepareTable(String nom, String[] str) {
        String nomTable = nom;
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i < str.length ; i++){
            list.add(str[i]);
        }
        Table table = new Table(nomTable, list);
        System.out.println("Taille table = "+table.getLength());
        return table;
    }

    /**
     * Verifie si la requette de suppression est destinée à :
     * une table, une database ou un champ
     *
     * @param query
     */
    public void typeOfQueryInDrop(String[] query) {
        if (query[1].equals("database")) {

            if (db != null) {
                db.dropDatabase();
                db = null;
            }
        }
    }

    /**
     * Verifie si la clause alter est destinée à :
     * une database, une table ou un champ
     *
     * @param query
     */
    public void typeOfQueryInAlter(String[] query) {
        if (query[1].equals("database")) {
            if (db != null) {
                db.alterDatabase(query[2]);
            }
        }
    }

    /**
     * Chargement de la base de données
     *
     * @param nameOfDb
     */
    public void inUseQuery(String nameOfDb) {
        db = db.loadDatabase(nameOfDb);
        if (db != null){
            System.out.println("Chargement de la base de donnée " + db.getPathToDatabase() + " réussie");
        }
    }

    public void inInsertQuery(String[] querry){
        int taille = 0;
        String nomTable = querry[2];
        String [] values = querry[4].split("[( ,)]");
        for (int j=0; j<values.length;j++)
            if(!values[j].isEmpty())
                taille++;

        String [] datas = new String[taille];
        taille = 0;

        for(int i =0; i<values.length; i++){
            if(values[i].isEmpty()){}
            else {
                datas[taille] = values[i];
                System.out.println(datas[taille++]);
            }
        }
        this.db.insertInTable(this.db,nomTable,datas);
    }

    public void inSelectQuerry(String [] querry) {
        String nomTable = querry[3];
        db.select(this.db, nomTable);
    }
}

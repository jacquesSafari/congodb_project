package org.engine;

import org.database.Database;

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
     * une database, une table ou un champ
     *
     * @param query
     */
    public void typeOfQueryInCreate(String[] query) {
        if (query[1].equals("database")) {
            db.createDatabase(query[2]);
        }
        if (query[1].equals("table"))
            System.out.println("Tentative de creation d'une table");
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
        if (db != null)
            System.out.println("Chargement de la base de donnée " + db.getPathToDatabase() + " réussie");
        else
            db = new Database();
    }
}

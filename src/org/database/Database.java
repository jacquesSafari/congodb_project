package org.database;

import java.io.File;

public class Database {

    File dir;
    private String pathToDatabase;


    /**
     * Constructeur de l'objet Database
     */
    public Database() {
    }

    public String getPathToDatabase() {
        return pathToDatabase;
    }

    /**
     * Fonction de creation d'une base de données.
     *
     * @param name
     */
    public void createDatabase(String name) {
        this.pathToDatabase = "db_files\\" + name;
        dir = new File(this.pathToDatabase);
        dir.mkdirs();
        System.out.println("La creation de la base de donnee a été effectuer avec succees");
    }

    public void dropDatabase() {
        if (dir.delete())
            System.out.println("Suppression effectuée avec succes");
        else
            System.out.println("Erreur : database inexistant");
    }

    public void alterDatabase(String name) {
        this.pathToDatabase = "db_files\\" + name;
        dir = new File(this.pathToDatabase);
        if (dir.renameTo(dir))
            System.out.println("Modification effectuée avec succes");
        else
            System.out.println("Message -> Erreur !!! : Base de données inexistant");
    }

    /**
     * permet de charger une base donnée lorsque l'on fait appel a la  clause use
     *
     * @param name
     * @return
     */
    public Database loadDatabase(String name) {
        this.pathToDatabase = "db_files\\" + name;
        dir = new File(pathToDatabase);
        if (dir.exists())
            return this;
        else
            System.out.println("Message -> Erreur !!! : Base de données inéxistant");
        return null;
    }
}
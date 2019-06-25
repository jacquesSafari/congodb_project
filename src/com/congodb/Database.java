/*
    Just for managing Database
*/

package com.congodb;
import  java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;

public class Database {

    private String databasePath = "/home/jeanluckabulu/Documents/congodb_project/out/production/congodb_project/com/";

    public String getDatabasePath() {
        return databasePath;
    }

    // Create a new Database File
    public  boolean CreateDatabase(String name){
        // Create a new Database with Java

        // ! change This Directory as my Systeme
        File file = new File( databasePath + name);

        if(!file.exists()){
            if (file.mkdir()){
                System.out.println("Directory is created");

                try{
                    File structure =  new File( getDatabasePath() + name + "/" + "structure.txt");
                    System.out.println("Fichier Creer !");
                    PrintWriter pw = new PrintWriter(new FileOutputStream(structure, true));
                    pw.close();
                }
                catch (Exception e){}

                return  true;
            }
            else {
                System.out.println("Failed to create directory");
                return  false;
            }
        }
        else {
            // throw new Error
            System.out.println("Database already exist !");
        }
        return  false;
    }

    boolean DeleteDatabase(String name){
        // Delete a Database with Java
        // String[] databaseDirectory = databasePath.list();

        File bdd_directory = new File(databasePath + name);

        if(!bdd_directory.exists()){
            // To try sometimes
        }

        String[] contents = bdd_directory.list();
        for (String s:contents){
            File currentFile = new File(bdd_directory.getPath(), s);
            currentFile.delete();
        }
        bdd_directory.delete();

     return  false;
    }

}

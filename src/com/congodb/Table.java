/*
    Just for managing Tables
*/

package com.congodb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Table {

    Database databasepath = new Database();


    void CreateTable(String databaseName , String tableName){
        //PrintWriter pw = new PrintWriter(new FileOutputStream(database, true));

        try{
            File database = new File(databasepath.getDatabasePath() + databaseName + "/" + tableName + ".txt" );
            PrintWriter pw = new PrintWriter(new FileOutputStream(database, true));
            pw.close();
        }
        catch (Exception e){}

        System.out.println("Systeme created successfully");
    }

    void ReadTable(){}

    void UpdateTable(){}

    void DeleteTable(){}

    void CreateStructure(String databaseName, String tableName, int colomnNumber){
        
    }


}

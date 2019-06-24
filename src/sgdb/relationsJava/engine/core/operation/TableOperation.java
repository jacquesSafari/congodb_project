package org.pbreakers.engine.core.operation;

import org.pbreakers.engine.core.file.FileUtil;
import org.pbreakers.engine.core.gson.GsonInstance;
import org.pbreakers.engine.persistance.Database;
import org.pbreakers.engine.persistance.Table;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class TableOperation {

    private static FileReader getFileWriter(String databasePath) {
        try {
            return new FileReader(databasePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean create(String databasePath, Table table) {
        // Get operation from file
        FileReader fileReader = getFileWriter(databasePath);
        if (fileReader == null) return false;

        Database database = GsonInstance.getInstance().fromJson(fileReader, Database.class);

        // Add table to the operation
        database.add(table);
        String newDatabaseContent = GsonInstance.getInstance().toJson(database);

        // Rewrite the file
        try {
            FileUtil.writeInFile(databasePath, newDatabaseContent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean delete(String databasePath, Table table) {
        // Get operation from file
        FileReader fileReader = getFileWriter(databasePath);
        if (fileReader == null) return false;

        Database database = GsonInstance.getInstance().fromJson(fileReader, Database.class);

        // delete table to the operation
        database.remove(table);
        String newDatabaseContent = GsonInstance.getInstance().toJson(database);

        // Rewrite the file
        try {
            FileUtil.writeInFile(databasePath, newDatabaseContent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean updateTable(Database databasePath, Table old) {
        // TODO:
        return false;
    }
}

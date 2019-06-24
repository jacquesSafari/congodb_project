package sgdb.relationsJava.engine.core.operation;

import sgdb.relationsJava.engine.core.file.FileUtil;
import sgdb.relationsJava.engine.core.gson.GsonInstance;
import sgdb.relationsJava.engine.persistance.Database;

import java.io.File;

public class DatabaseOperation {

    public static boolean createDatabasee(Database database) {


        File databaseFile = new File(database.getDatabasePath());
        if (databaseFile.exists()) {
            new Exception("File exist").printStackTrace();
            return false;
        } else {
            try {
                boolean fileIsCreated = databaseFile.createNewFile();

                if (fileIsCreated) {
                    String json = GsonInstance.getInstance().toJson(database);
                    FileUtil.writeInFile(database.getDatabasePath(), json);
                }

                return fileIsCreated;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static boolean deleteDatabase(Database database) {
        String fileName = database.getNom();
        String filePath = "operation/" + fileName + ".json";

        File databaseFile = new File(filePath);
        if (databaseFile.exists()) {
            return databaseFile.delete();
        } else {
            return false;
        }
    }

    public static boolean updateDatabasee(String newName, String oldName) {

        File newFile = new File("operation/" + newName + ".json");
        File oldFile = new File("operation/" + oldName + ".json");


        return oldFile.renameTo(newFile);
    }
}

package org.database;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;

public class Database {

    private File dir;
    private String pathToDatabase;


    /**
     * Constructeur de l'objet Database
     */
    public Database() {
    }

    public String getPathToDatabase() {
        return pathToDatabase;
    }

    public File getDir() {
        return dir;
    }

    /**
     * Fonction de creation d'une base de données.
     *
     * @param name
     */
    public void createDatabase(String name) {
        this.pathToDatabase = "db_files/" + name;
        dir = new File(this.pathToDatabase);
        dir.mkdirs();
        System.out.println("La creation de la base de donnee a été effectuer avec succees");
    }
    /**
     * Permet la supression d'une base de données (dossier) et toutes ses tables(fichiers xml)
     */
    public void dropDatabase() {
        if (dir.delete())
            System.out.println("Suppression effectuée avec succes");
        else
            System.out.println("Erreur : database inexistant");
    }

    /**
     * Permet pour l'instant uniquement la modification du nom de la base de donnée
     * @param name
     */
    public void alterDatabase(String name) {
        this.pathToDatabase = "db_files/" + name;
        dir = new File(this.pathToDatabase);
        if (dir.renameTo(dir))
            System.out.println("Modification effectuée avec succes");
        else
            System.out.println("Message -> Erreur !!! : Base de données inexistant");
    }

    /**
     * permet de charger une base donnée lorsque l'on fait appel a la  clause use
     * cela veut dire que toutes les requêtes se passeront directement sur le dossier
     * Database ooncerner
     * @param name
     * @return
     */
    public Database loadDatabase(String name) {
        this.pathToDatabase = "db_files/" + name;
        dir = new File(pathToDatabase);
        if (dir.exists())
            return this;
        else
            System.out.println("Message -> Erreur !!! : Base de données inéxistant");
        return null;
    }

    /**
     * Permet la creation d'une table en l'occurence un fichier xml
     * il est bien-sur nécessaire de charger une base de donnée (use) dans l'instance (db_files)
     * avant le lancement de cette requette. sinon celle ci génère une exception.
     * @param database
     * @param table
     */
    public void createTable(Database database, Table table){
        XStream stream = new XStream(new DomDriver());
        File file = new File(database.getPathToDatabase()+'/'+table.getTableName()+".xml");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                stream.toXML(table, fos);
            } finally {
                fos.close();
            }
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertInTable(Database database, String nomTable, String [] datas){
        Table table = null;
        File file = new File(database.getPathToDatabase()+'/'+nomTable+".xml");
        if((file.exists())){
            table = selectInTable(database, nomTable, file);
            if(table.getLength() == datas.length){
                Row row = new Row(datas.length);
                row.setDatas(datas);
                table.ajouterRow(row);
                createTable(database,table);
            }else
                return;
        }
    }

    public Table selectInTable(Database database, String nomTable, File file){
        System.out.println("Debut de lecture");
        try {
            XStream stream = new XStream(new DomDriver());
            FileInputStream fis = new FileInputStream(file);

            try{
                Table table = (Table) stream.fromXML(fis);
                return table;
            }finally {
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return null;
    }

    public void select(Database db, String nomTable) {
        File file = new File(db.getPathToDatabase()+"/"+nomTable+".xml");
        if(file.exists()){
            Table table = selectInTable(db, nomTable,file);
            table.select(table);
        }

    }
}
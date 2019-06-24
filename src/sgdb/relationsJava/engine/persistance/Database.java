package sgdb.relationsJava.engine.persistance;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Database {
    private String databasePath;
    private String nom;
    private Set<Table> tables = new TreeSet<>();


    public String getDatabasePath() {
        return databasePath;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Database database = (Database) o;
        return Objects.equals(nom, database.nom) &&
                Objects.equals(tables, database.tables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, tables);
    }

    public Database(String nom) {
        this.nom = nom;
        databasePath = "operation/" + getNom() + ".json";
    }

    public void add(Table table) {
        tables.add(table);
    }

    public void remove(Table table) {
        tables.remove(table);
    }
}


package objects;

import java.util.List;

public class Database {
    
    private int         id;
    private String      name;
    private List<Table> tables;


    public Database() {}

    public Database(int id, String name, List<Table> tables) {
            this.id     = id;
            this.name   = name;
            this.tables = tables;
    }

    public Database(int id, String name) {
            this.id     = id;
            this.name   = name;
    }



    public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public List<Table> getTables() {
            return tables;
    }

    public void setTables(List<Table> tables) {
            this.tables = tables;
    }
}

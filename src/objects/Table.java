
package objects;

import java.util.List;


public class Table {
    
    private int             id;
    private String          name;
    private List<Attribute> attributes;

    public Table() {}

    public Table(int id, String name, List<Attribute> attributes) {
            this.id         = id;
            this.name       = name;
            this.attributes = attributes;
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

    public List<Attribute> getAttributes() {
            return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
    }
}

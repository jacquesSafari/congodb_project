
package objects;

public class Attribute {
    
    private int         id;
    private String      name;
    private Type        type;
    private boolean     isPrimary;
    private int         length;
    private boolean     autoIncrement;
    private String      comment;

    public Attribute() {}

    public Attribute(
                    int id,
                    String name,
                    Type type,
                    boolean isPrimary,
                    int length,
                    boolean autoIncrement,
                    String comment) {

            this.id             = id;
            this.name           = name;
            this.type           = type;
            this.isPrimary      = isPrimary;
            this.length         = length;
            this.autoIncrement  = autoIncrement;
            this.comment        = comment;
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

    public Type getType() {
            return type;
    }

    public void setType(Type type) {
            this.type = type;
    }

    public boolean isPrimary() {
            return isPrimary;
    }

    public void setPrimary(boolean isPrimary) {
            this.isPrimary = isPrimary;
    }

    public int getLength() {
            return length;
    }

    public void setLength(int length) {
            this.length = length;
    }

    public boolean isAutoIncrement() {
            return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
            this.autoIncrement = autoIncrement;
    }

    public String getComment() {
            return comment;
    }

    public void setComment(String comment) {
            this.comment = comment;
    }
}

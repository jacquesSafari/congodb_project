import java.io.File;
import java.io.IOException;
public class Storage
{
    Storage()
    {

    }
    protected String name;
    protected String path;
    protected String baseDonnee;
    public String createdb(String name)
    {

        this.name = name;
        
        File db = new File(name);
        if(db.exists())
        {
           path = "Database exist";
        }
        else if(!db.exists())
        {
            db.mkdir();
            path = db.getAbsolutePath();
        }
        else
        {
            path = "What'is it???";
        }
        return path;
    }
    public String createTable(String name) throws IOException
    {
        this.name = name; 
        File table = new File(name+".xml");
        try
        {
            if(table.exists())
            {
                path = "This table exist";
            }
            else if(!table.exists())
            {
                String path = table.getAbsolutePath();
                boolean createFile = table.createNewFile(); 
                System.out.println("Absolute path : "+path);
                return path;
            }
            else
            {
                path = "What's it";
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return path;
    }
}
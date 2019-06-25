import java.io.*;
import java.io.IOException;

public class Element
{
    public Element(String nameTab) throws IOException
    {
        
        File f = new File(nameTab+".xml");
        PrintWriter out = new PrintWriter(new FileWriter(f));
        out.println("<donnee>\n");
        out.println("</donnee>");
        out.close();
    }
}
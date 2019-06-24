package sample.core;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Comment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Database{

    public static String path;

    static 
    {
        
        path = "C:\\" ;

    }

    private int id ;
    private String nom;
    private List<Table> tables = new ArrayList<Table>();

    public Database(String nom)
    {

        this.nom = nom;    
        
    }

    public Database()
    {

    }

    public int getId()
    {

        return this.id;

    }

    public String getNom()
    {

        return this.nom;

    }


    public void setNom(String nom)
    {
        this.nom = nom;
    }
   
    public void createDirecory()
    {
        String chemin = Database.path+"db";
        File dossier = new File(chemin);
        
        if(!(dossier.isDirectory()))
        {
            dossier.mkdir();
        }

    }
    public boolean createDB(){

        createDirecory();
        System.out.println("ENTREZ UNE REQUETTE");
        System.out.println("Creer une base de donnees\n");

        Scanner sc = new Scanner(System.in);
        String requete = sc.nextLine();
        String separateur = " ";        
        String[] requete = requete.split(separateur);

        if(!((requete[0]+" "+requete[1]).equals("CREATE DATABASE")))
        {
            System.out.println("Requete inconnue!!");
            System.exit(0);
        }

        String nom = requete[2];
        String chemin = Database.path+"db\\"+nom;
        File folder = new File(chemin);

        if(!(folder.isDirectory()))
        {            
           folder.mkdir();        
            return true;
        }
        else
        {
            System.out.println("la base de donnees existe deja");
            return false;
        }       
        
    }

    public static boolean dorpDatabase(String nom)
    {

        String chemin = Database.path+"db\\"+nom;
        File folder = new File(chemin);

        if((folder.isDirectory()))
        {
            folder.delete();
            return true;
        }
        else
        {
            return false;
        }
        
    }

   
    
    


}
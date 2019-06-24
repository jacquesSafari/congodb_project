package sgbd15.Kernerl;
//=========================
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
//========================================================================
public class Database{
    
    public static String path;

    static {
        //path = "C:\\Users\\elsa\\Documents\\NetBeansProjects\\congodb_project\\Sgbd15\\src\\sgbd15\\Databases" ;
        path = "C:\\" ;
    }

    private int id ;
    private String nom;
    private List<Table> tables = new ArrayList<Table>();

    public Database(String nom){
        this.nom = nom;        
    }

    public Database(){

    }

    public int getID(){
        return this.id;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }
    public void createFolder(){
        String chemin = Database.path+"Databases";
        File dossier = new File(chemin);
        if(!(dossier.isDirectory())){
            dossier.mkdir();
        }

    }
    public boolean createDB(){

        createFolder();
        System.out.println("Tapez une requete ici : \n==========================");
        System.out.println(" exmple *CREATE DATABASE nom*\n");

        Scanner sc = new Scanner(System.in);
        String requete = sc.nextLine();

        String separateur = " ";        
        String[] t_requete = requete.split(separateur);

        if(!((t_requete[0]+" "+t_requete[1]).equals("CREATE DATABASE"))){
            System.out.println("pas reconnue !!");
            System.exit(0);
        }
        String nom = t_requete[2];
        String chemin = Database.path+"Databases\\"+nom;

        File folder = new File(chemin);
        if(!(folder.isDirectory())){            
           //si le folder n'existe pas deja on le cree
           folder.mkdir();
           /*
          */            
            return true;
        }
        else{
            System.out.println("Une base de donnees porte ce nom");
            return false;
        }       
        
    }
//==========================================================================================
    public static boolean dropDB(String nom){
        String chemin = Database.path+"Databases\\"+nom;
        File folder = new File(chemin);
        if((folder.isDirectory())){
            folder.delete();
            return true;
        }
        else{
            return false;
        }
        
    }

   
    
    


}
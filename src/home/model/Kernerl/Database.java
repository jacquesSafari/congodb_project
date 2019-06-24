package home.model.Kernerl;

//import packages required
//=========================
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import home.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
//==============================================\\
//pour la modification
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Comment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
//==========================================================\\
//pour les collections
import java.util.List;
import java.util.ArrayList;
//le flux d'entre
import java.util.Scanner;
//le fichier
import java.io.*;

public class Database{
    
  //  public static String path =     "/home/elisee/Documents/Programmation/Dossier_java/JAVA/Projet_sgbdr/congodb_project/Databases/";
    public static String path;

    static {
        //path = "C:\\Users\\AnanielDesk\\Desktop\\programmationG2\\java\\src\\sample\\Databases\\" ;
        path = "C:\\" ;
    }

    private int id ;
    private String nom;
    private List<Table> tables = new ArrayList<Table>();

    public Database(String nom){
        this.nom = nom;        
    }

    public                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  Database(){

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

    //methode qui cree le dossier
    public void createFolder(){
        String chemin = Database.path+"Databases";
        File dossier = new File(chemin);
        if(!(dossier.isDirectory())){
            dossier.mkdir();
        }

    }
    public boolean createDBSQL(String request){

        createFolder();
        //chemin d'acces au dossier de base des donnees
        System.out.println("Tapez une requete de ce type:\n==========================");
        System.out.println("*CREATE DATABASE nom*\n");

        //Scanner sc = new Scanner(System.in);
        String requete = request;

        String separateur = " ";        
        String[] t_requete = requete.split(separateur);

        if(!((t_requete[0]+" "+t_requete[1]).equals("CREATE DATABASE"))){
            System.out.println("Requete inconnue!!");
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
            System.out.println("Conflit des noms: Une autre base de donnees porte ce nom");
            return false;
        }       
        
    }

    public boolean createDB(String nom1){

        createFolder();
        //chemin d'acces au dossier de base des donnees

        String requete = nom1;

        String separateur = " ";
        String[] t_requete = requete.split(separateur);

        String nom2 = t_requete[2];
        String chemin = Database.path+"Databases\\"+nom2;

        File folder = new File(chemin);
        if(!(folder.isDirectory())){
            //si le folder n'existe pas deja on le cree
            folder.mkdir();
            /*
             */
            return true;
        }
        else{

            String msgErr ="Ce nom existe deja!!!";
            System.out.println("Conflit des noms: Une autre base de donnees porte ce nom");
            return false;
        }

    }

    //recherche database
    public ObservableList<Database> getAllDB(){
        String chemin = Database.path+"Databases";
        File folder = new File(chemin);
        ObservableList<Database > list = FXCollections.observableArrayList();
        for(File nom : folder.listFiles()){
            if(nom.isDirectory()){
                String name = nom.getName();list.add(new Database(name));
            }
        }
        return list;

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

    public void dropMDB(String nom){
        String chemin = Database.path+"Databases\\"+nom;
        File folder = new File(chemin);
        if((folder.isDirectory())){
            folder.delete();
        }

    }

    //creer une table
   
    
    


}
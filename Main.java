import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
public class Main
{
    
    public Main()
    {

    }
    public static void main(String[] args)throws IOException
    {
        System.out.println("1.CREATE DATABASE");
        System.out.println("2.CREATE TABLE");
        System.out.println("3.INSERTION");
        String requete1 = "CREATE DATABASE";
        String requete2 = "CREATE TABLE";
        
        Scanner choix = new Scanner(System.in);
        System.out.println("Operer un choix : ");
        int nb = choix.nextInt();
        if(nb == 1)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Query ==>");
            String str = sc.nextLine();
            String[] tab = str.split(" ");
            //System.out.println("Coool : "+Arrays.toString(tab));  
            if(str.equals(requete1+" "+(tab[2])))
            {
                String path;
                
                Storage db = new Storage();
                path = tab[2];
                System.out.println("Successful of database : "+path);
                db.createdb(path);
            }
            else
            {
                System.out.println("Error");
            }
        }
        if(nb == 2)
        {
            Storage f = new Storage();
            Scanner create = new Scanner(System.in);
            System.out.print("Connextion a la base de donnees(Nom_BD) => ");
            String bdonne = create.nextLine();
            String d = bdonne;
            File bdonne1 = new File(d);
            if(bdonne1.exists())
            {
                System.out.print("Query ==> ");
                String table = create.nextLine();
                String[] tableau = table.split(" ");
                if((requete2+" "+(tableau[2])).equals(table))
                {
                    if(f.createdb(bdonne).equals("Database exist") && f.createTable(bdonne+"/"+tableau[2]) != ("This table exist"))
                    {
                        String save = f.createTable(bdonne+"/"+tableau[2]);
                        Element element = new Element(bdonne+"/"+tableau[2]);
                        System.out.println("Successful of create table : "+tableau[2]);
                    }
                    else
                    {
                        System.out.println("Error");
                    }
                }
                else
                {
                    System.out.println("Requete imompatible");
                }
            }
            else
            {
                System.out.println("Le nom de la base de donnees n 'existe pas'");
            }
            
        }
        if(nb == 3)
        {
            Ajout ajout = new Ajout();
            ajout.processInsert();
            System.out.println("Comment");

        }
    }

}
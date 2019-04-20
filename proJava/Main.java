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
        String requete1 = "CREATE DATABASE";
        String requete2 = "CREATE TABLE";
        Scanner choix = new Scanner(System.in);
        System.out.println("Operer un choix : ");
        int nb = choix.nextInt();
        if(nb == 1)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Requete of create db (maj) ==>");
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
            System.out.println("Connextion a la base de donnees(Nom_BD) => ");
            String bdonne = create.nextLine();
            System.out.println("requete of Create Table (maj)=> ");
            String table = create.nextLine();
            String[] tableau = table.split(" ");
            if((requete2+" "+(tableau[2])).equals(table))
            {
                if(f.createdb(bdonne).equals("Database exist") && f.createTable(bdonne+"/"+tableau[2]) != ("This table exist"))
                {
                    String save = f.createTable(bdonne+"/"+tableau[2]);
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
    }

}
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
public class Insertion
{
    //public static void main(String [] args)
    public String[][] insert()
    {
        String insert1 = "insert";
        String into1 = "into"; 
        String values1 = "values";
        do
        {
            try
            {
                Scanner scan = new Scanner(System.in);
                System.out.println("Nom de la BD =>");
                String base = scan.nextLine();
                File d = new File(base);
                //System.out.println(base);
                if(d.exists())
                {
                    String path = d.getAbsolutePath(); 
                    System.out.println("Query nnn ==> ");
                    String input = scan.nextLine();
                    String[] tab = input.split(" ");
                    String insert2 = tab[0];
                    String conv1 = insert2.toLowerCase();
                    String into2 = tab[1];
                    String conv2 = into2.toLowerCase();
                    String table = tab[2];
                    String target = tab[3];
                    String target1 = target.replace(")","");
                    String target2 = target1.replace("(","");
                    String values2 = tab[4];
                    String conv3 = values2.toLowerCase();  
                    String conv4 = values2.toLowerCase(); 
                    String data = tab[5].replace(")","");
                    String data1 = data.replace("(","");
                    String dataDelete = data1.replace("'","");
                    String dataDelete1 = dataDelete.replace("'","");
                    File fichier = new File(path+"/"+table+".xml");
                    String fichie = (path+"/"+table);
                    System.out.println(fichier);
                    String targetDelete = target2.replace("'","");
                    String targetDelete1 = targetDelete.replace("'","");
                    if(insert1.equals(conv1) && into1.equals(conv2) && values1.equals(conv3))
                    {
                        if(fichier.exists())
                        {           
                            String[] targetData = targetDelete1.split(",");
                            String[] valueData = dataDelete1.split(",");
                            String[] file_tab = {fichie};
                            //Verification de target et de valeurs
                            if((Arrays.toString(targetData).length()) != (Arrays.toString(valueData).length()))
                            {
                                String tableauFinal[][] = {targetData,valueData,file_tab};
                                return tableauFinal;
                            }
                            else
                            {
                                System.out.println("Error le nombre champs ne correspondent pas aux valeurs");
                            }
                        } 
                        else
                        {
                            System.out.println("This table is not exists");
                        }
                    }
                    else
                    {
                        System.out.println("Veuillez retaper votre requete");
                    }
                }
                else
                {
                    System.out.println("La base de données n'existe pas");
                }
            }
            catch(ArrayIndexOutOfBoundsException ex)
            {
                System.out.println("Retapez votre requete en respectant la syntsxe");
            }
        }while(true);
        
    }
}
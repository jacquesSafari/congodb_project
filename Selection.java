import java.util.Scanner;
import java.util.Arrays;
public class Selection
{
    public static void main(String[] args)
    {
        String select1 = "select";
        String from1 = "from";
        try
        {
            do
            {
                System.out.println("Query ==> ");
                Scanner sc = new Scanner(System.in);
                String inputQuery = sc.nextLine();
                String[] querySplit = inputQuery.split(" ");
                //String[] champ = querySplit.split(",");
                String select2 = querySplit[0].toLowerCase();
                String from2 = querySplit[2].toLowerCase();
                if(select1.equals(select2) && from1.equals(from2))
                {
                    System.out.println("Split Query : "+Arrays.toString(querySplit));
                    break;
                }
                else
                {
                    System.out.println("Verifiez votre syntaxe de la requete");
                }
            }while(true);
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("Verifiez votre requete");
        }
    }
}
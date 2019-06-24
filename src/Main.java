import org.engine.Engine;

import java.util.Scanner;

/**
 * La classe principale
 *
 * @author Benoni Zed
 */
public class Main {
    /**
     * Moteur de requette sql
     */
    static Engine engine = new Engine();

    public static void main(String[] args) {
        display();
        System.out.println("Pour quitter appuiyer sur q ou Q puis valider");

        String array[];
        int i = 1;
        do {
            String requette;
            Scanner scanner = new Scanner(System.in);
            System.out.print(">  ");
            requette = scanner.nextLine();
            //requette = requette.trim();
            requette = requette.toLowerCase();
            array = requette.split(" ");

            char caract = requette.charAt(0);
            if (caract == 'q') {
                System.out.println("Exit amorcée");
                i = 0;
                break;
            }

            switch (checkQuery(array[0])) {
                case 1:
                    engine.inSelectQuerry(array);
                    break;
                case 2:
                    if (array.length == 3) {
                        engine.setRequette(array);
                        engine.typeOfQueryInCreate(engine.getRequette());
                        break;
                    } else {
                        System.out.println("Message -> La creation d'une base de donnee ne prend que 3 mot cle");
                        System.out.println("CREATE DATABASE NOM_DB \nNOM_DB : est le nom que vous donner a la base de donnees");
                    }
                    break;
                case 3:
                    if (array.length == 3) {
                        engine.setRequette(array);
                        engine.typeOfQueryInAlter(engine.getRequette());
                    } else {
                        System.out.println("Message -> La modification d'une base de données ne prend que 3 mot cle");
                        System.out.println("ALTER TABLE NOM_DB\nNOM_DB : est le nom de la base de données à modifier");
                    }
                    break;
                case 4:
                    if (array.length == 3) {
                        engine.setRequette(array);
                        engine.typeOfQueryInDrop(engine.getRequette());
                    } else {
                        System.out.println("Message -> La suppression d'une base de données ne prend que 3 mot cle");
                        System.out.println("DROP DATABASE NOM_DB\nNOM_DB : est le nom de la base de données à supprimer");
                    }
                    break;
                case 5:
                    if (array.length == 2)
                        engine.inUseQuery(array[1]); //Chargement d'une Base de données existante
                    else {
                        System.out.println("Message -> L'utilisation d'une base de donnée ne prend que 2 mot clé");
                        System.out.println("USE NOM_DB\nNOM_DB : est le nom de la base de donner que l'on veut utiliser");
                    }
                    break;
                case 6:
                    engine.inInsertQuery(array);
                    break;
                case 0:
                    System.out.println("Message -> Requette incorrecte ou mal saisie");
                    break;
            }
        } while (i == 1);
    }

    /**
     * verification de la clause saisie par l'utilisateur
     *
     * @param query
     * @return
     */
    public static int checkQuery(String query) {
        if (query.equals("select"))
            return 1;
        if (query.equals("create"))
            return 2;
        if (query.equals("alter"))
            return 3;
        if (query.equals("drop"))
            return 4;
        if (query.equals("use"))
            return 5;
        if(query.equals("insert"))
            return 6;
        return 0;
    }

    public static void display(){
        System.out.println(" _______________________________________________________________________ ");
        System.out.println("|                         Welcome to congo_db project                   |");
        System.out.println("|                               Java SE Project                         |");
        System.out.println("|                            Prof : Mr Jacques SAFARI                   |");
        System.out.println("| Réaliser par le groupe 24 :                                           |");
        System.out.println("|                                                  ZAINA KAZADI Josianne|");
        System.out.println("|                                                 ZONGWE BENONI Benjamin|");
        System.out.println("|                            -- ESIS 2019 --                            |");
        System.out.println("|                             by IntelliJ IDEA                          |");
        System.out.println("|_______________________________________________________________________|" + "\n");
    }
}

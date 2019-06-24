package mysgbdfxml;


import java.io.*;
import java.util.Scanner;

import static mysgbdfxml.ConversionStringToJson.serialisation;

public class Main {

    public static void main(String[] args) throws IOException {

//
//
//        Champs nouveauChamps = new Champs("Id","INT");
//        Champs nouveauChamps1 = new Champs("Nom","String");
//        Champs nouveauChamps3 = new Champs("Postnom","String");
//        Champs nouveauChamps4 = new Champs("telephone","String");
//
//        Nouveautable table = new Nouveautable("Professeur");
//        table.addChamp(nouveauChamps,nouveauChamps1,nouveauChamps3,nouveauChamps4);
//       table.addEnreg();
//       table.addEnreg();
//        //table.modifierNomTable("etudiant");
//
//        ConversionStringToJson.serialisation(table,"fichier.json");
////        table.miseAJourdonne("oi","gaetanoi");
////        ConversionStringToJson.serialisation(table,"fichier.json");
//        Nouveautable nouveautable = ConversionStringToJson.deserialisation("fichier.json");
//
//        if (nouveautable==null){System.out.println("Objet null");}
//               // ConversionStringToJson.deserialisation("fichier.json");
//               // nouveautable.miseAJourdonne("oi","GaetanTable");
//        nouveautable.addEnreg();
//        System.out.println(ConversionStringToJson.deserialisation("fichier.json").nomTable);
//        ConversionStringToJson.serialisation(nouveautable,"fichier.json");
//        //System.out.println(ConversionStringToJson.deserialisation("fichier.json"));
//
////
////        String response;
////        Scanner scanner = new Scanner(System.in);



GestionTable.afficheTout();

         /* Table essaie1 = new Table ("01","Gaetan","Lwamba","sulubika");
         Table essaie2 = new Table ("02","Paul","Lubala","true");
        Table essaie3 = new Table ("01","eddysha","Jabulanie","frere");
        //Nouveautable pret = new Nouveautable ("Professeur",new Table[]{essaie1,essaie2},new Table[]{essaie3});
        //System.out.println(pret.professeur[0].champ[0].nom1);
         //System.out.println (ConversionStringToJson.conversionToJson (essaie1));
        // serialisation (ConversionStringToJson.conversionToJson (pret));
         //serialisation (ConversionStringToJson.conversionToJson (essaie2));
         //System.out.println (ConversionStringToJson.deserialisation ().nom1);
        Nouveautable table = ConversionStringToJson.deserialisation("fichier.json");
        String non= table.nomTable;
        String nomConpa = "Professeur";
        System.out.println(table.nomTable);

      if (non.equals(nomConpa)){
        int i = table.professeur.length;
        for (int j=0; j<i; j++){

                System.out.println(table.professeur[j].nom+" "+table.professeur[j].id);
                 System.out.println(table.nomTable);
            }
        }
       else System.out.println("Gaetan est le meilleur");
       */


       // System.out.println (ConversionStringToJson.deserialisation("fichier.json").nom2);


    }
}


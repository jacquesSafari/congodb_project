package mysgbdfxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static mysgbdfxml.ConversionStringToJson.serialisation;

public class GestionTable {
    
    public static boolean verifierBaseDesdonnees (String mon_base){
        String bd="/Users/Gaetan Lwamba/Desktop/Esis/sgbd/bd/"+mon_base+".json";
        boolean bool= false;
        File f1 = new File (bd);
        if(f1.exists()) bool = true;
        return bool;
    }
    public static  boolean creationBaseDesDonnees( String mon_base) throws IOException {
        String bd="/Users/Gaetan Lwamba/Desktop/Esis/sgbd/bd/"+mon_base+".json";
        
        
        boolean bool= false;
        File fichier = new File(bd) ;
        if (fichier.createNewFile())bool=true;
        return bool;
    }
    public static  boolean renomerBaseDesDonnes( String nom_Actuel, String nouveauNom) throws IOException {

        boolean bool= false;
        File fichier = new File(nom_Actuel) ;
        if(fichier.renameTo (new File (nouveauNom)))bool=true;
        return bool;
    }
    public static  void supprimerBaseDesDonnes( String nom_base) throws IOException {
       // boolean bool= false;
        String bd="/Users/Gaetan Lwamba/Desktop/Esis/sgbd/bd/"+nom_base;

        File fichier = new File(bd) ;
        fichier.delete ();
        //return bool;
    }
    public static ObservableList<Nouveautable> afficheListeBasse() {
        String bd="/Users/Gaetan Lwamba/Desktop/Esis/sgbd/bd/";
        File repertoire = new File(bd);
        String liste[] = repertoire.list();
        ObservableList<Nouveautable> mesbd = FXCollections.observableArrayList();

        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                System.out.println(liste[i]);
               // mesbd.add(liste[i]);

            }

        } else {
            System.err.println("A Liste est vide");
        }
        return mesbd;

    }
    public static ObservableList<Nouveautable> afficheTout(){
        String bd="/Users/Gaetan Lwamba/Desktop/Esis/sgbd/bd/";
        File folder = new File(bd);
        ObservableList<Nouveautable > list = FXCollections.observableArrayList();
        String liste [] = folder.list();
        if (liste!=null){
            for (int i =0; i < liste.length;i++){
                System.out.println(liste[i]);
                list.add(new Nouveautable(liste[i]));
            }
        }
//
//
//        for(File nom : file.listFiles()){
//            if(nom.isDirectory()){
//                String name = nom.getName();
//                list.add(new Nouveautable(name));
//                System.out.println(name);
//            }
//        }
        return list;

    }
    /*public static  boolean modifiercontenuetable( String nom_table, String nouveauNom) throws IOException {
        boolean bool= false;
        Nouveautable table = ConversionStringToJson.deserialisation(nom_table);
        //table.professeur[0].nom=nouveauNom;
        serialisation (ConversionStringToJson.conversionToJson (table));
        return bool;
    }*/

}

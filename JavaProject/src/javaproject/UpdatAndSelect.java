package javaproject;

import java.util.ArrayList;


import Data.Colonne;
import Data.Message;

public class UpdatAndSelect {

	public static void updat(String query) {
		//TODO
		String[] requette=query.split(" ");
		String nameTable=requette[1];
		if(requette.length>=4) {
			if(requette[2].toUpperCase().equals("SET")) {
				boolean where=query.contains("WHERE");
				int indexD=query.indexOf("SET ");
				int indexF=query.indexOf(";");
				int indexf=query.indexOf("WHERE");
				ArrayList<String> colonnes=new ArrayList<String>();
				ArrayList<String> values=new ArrayList<String>();
				Message.Alert(" "+query.contains(";"));
				if(!where && query.contains(";") && query.contains("SET")) {
					String value=query.substring(indexD, indexF);
					String[] contenu=value.split(" ");
					for(int b=0;b<contenu.length;b++) {
						int indexOne=contenu[b].indexOf("=");
						int indexTwo=contenu[b].indexOf(",");
						int indexTree=contenu[b].indexOf(";");
						
						if(contenu[b].contains("=") && contenu[b].contains(",") ) {
							String column=contenu[b].substring(0,indexOne);
							String valeur=contenu[b].substring(indexOne+1,indexTwo);
							Message.Alert(column);
							Message.Alert(valeur);
							colonnes.add(column);
							values.add(valeur);
						}
						if(contenu[b].contains("=") && contenu[b].contains(";") ) {
							String column=contenu[b].substring(0,indexOne);
							String valeur=contenu[b].substring(indexOne+1,indexTree);
							Message.Alert(column);
							Message.Alert(valeur);
							colonnes.add(column);
							values.add(valeur);
						}
					}
				}
				
				if(where) {
					String value=query.substring(indexD, indexf);
					String[] contenu=value.split(" ");
				}
			}
		}
		// update nametable set colonne=valeur, colonne=valeur, colonne=valeur, colonne=valeur;
		// where 
	}	
	
	public static ArrayList<String> select(String query) {
		
		ArrayList<String> chaine=new ArrayList<>();
		String[] requette=query.split(" ");
		if(requette.length>=3) {
			
			int point =requette[3].indexOf(".");
			int piont =requette[3].indexOf(";");
			boolean cond1=(point!=-1?true:false);
			boolean cond2=(piont!=-1?true:false);
			
		if (requette[1].equals("*") && requette[2].toUpperCase().equals("FROM") && cond1 && cond2) {
			
			String 	nameDataBase=requette[3].substring(0, point);
			String  nameTable=requette[3].substring(point+1,piont);
			chaine=Colonne.select(nameDataBase, nameTable);
		}}
		for (String str : chaine ) {
    		Message.Alert(str);
    	}
		return chaine;
		//select * from database.table;
	}
}

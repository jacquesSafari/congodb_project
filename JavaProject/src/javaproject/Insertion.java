package javaproject;


import java.util.ArrayList;

import Data.Champ;
import Data.Message;

public class Insertion {

	
	public static void insertInTable(String query) {
		
		String[] requette=query.split(" ");
		
		if(requette.length>=4) {
			
			ArrayList<String> keysValues=new ArrayList<>();
			int point =requette[2].indexOf(".");
		
			String[] keys=requette[3].split(",");
			int depart=requette[3].indexOf("(");
			int fin=requette[3].indexOf(")");
			
		if(fin!=-1 && depart!=-1 && point!=-1) {
			String 	bdd=requette[2].substring(0, point);
			String  table=requette[2].substring(point+1);
			String value=requette[3].substring(depart+1, fin);
			String[] values=value.split(",");
		
		if(requette[1].toUpperCase().equals("INTO") && query.contains("VALUES")) {
			if(keys.length==values.length) {
				for(int stop=0;stop<keys.length;stop++) {
					keysValues.add(values[stop]);
				}
				Champ.insert(bdd, table, keysValues);
			}
			else {
				Message.Alert("Nombre colonne incompatible avec le Nombre de valeurs");
			}
		}
			// insert into database.table values(value,value)
		}
	}
}

	// ******************** delete **********************
	
	public static void delete(String query) {
		
		String[] requette=query.split(" ");
		
		// delete from database.table where colonne=valeur and colonne=valeur;
		//*****************condition********************
		boolean cond1=requette[0].toUpperCase().equals("DELETE");
		boolean cond2=query.contains("WHERE");
		boolean cond3=requette[1].toUpperCase().equals("FROM");
		
		if(requette.length>=3) {
		int point =requette[3].indexOf(".");
		boolean cond4=(point!=-1)? true:false;
		
		
		if(query.contains("where") || cond2) {
			//String[] condition="suite apr�s";
		}
		else if( cond1 && !cond2 && cond3 && cond4){
			String 	bdd=requette[3].substring(0, point);
			String  table=requette[3].substring(point+1);
			Data.Champ.delete(bdd, table);
		}
		else {
			Message.Alert("Error de syntaxe");
			}
		}
	}
}

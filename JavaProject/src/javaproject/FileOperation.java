package javaproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileOperation {
	
	public static ArrayList<String> lire(String fichier) {
		String mot;
		ArrayList<String> keysWords=new ArrayList<>();
		try {
			FileReader file=new FileReader(fichier);
			BufferedReader reader=new BufferedReader(file);
			
			while((mot = reader.readLine()) != null) {
				keysWords.add(mot);
			}
			reader.close();
			return keysWords;
			
		}
		catch(Exception e) {
			System.out.println(e.getCause()+"\n"+e.getMessage()+"\t"+e.getClass());
			return null;
		}
	}
//***************************************************************************************
public static void ecrire(ArrayList<String> Words,String namefile) {
	
	try {
		BufferedWriter write=new BufferedWriter(new FileWriter(namefile));
		for(String word : Words) {
			write.write(word);
			write.newLine();
		}
		write.close();
		
	}
	catch(Exception e) {
		System.out.println(e.getCause()+"\n"+e.getMessage());
		
	}
}

public static void deleteFile(String file) {
	
	File fichier=new File(file);
	boolean exist=fichier.exists();
	if(exist) {
		fichier.delete();
	}
}

}

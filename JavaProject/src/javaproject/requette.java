package javaproject;
import javaproject.UpdatAndSelect;
import javaproject.Insertion;
import java.util.ArrayList;
import java.util.Scanner;

import Data.Message;

public class requette {
	
	static int code=-1;
	
	public static String query(String requetteX) {
		int decode=requette.decodeurInitial(requetteX);
                String resultat="";
		if(decode!=-1 && decode>0) {
			switch(decode) {
			case 1:
				resultat=Message.Alert("Alter ne pas encore fini");
				break;
			case 2:
				try {
				resultat=CreateQuery.CreateDataBaseOrTable(requetteX);
				break;
				}
				catch(Exception e) {
					resultat=Message.Error(e);
					break;
				}
			case 3:
				Insertion.delete(requetteX);
				break;
			case 4:
				CreateQuery.dropDataBaseOrTable(requetteX);
				break;
			case 5:
				Insertion.insertInTable(requetteX);
				break;
			case 6:
				UpdatAndSelect.select(requetteX);
				break;
			case 7:
				resultat=Message.Alert("Update ne pas encore fini");
				break;
			default:
				resultat=Message.Alert("Syntax Error");
			}
		}
		else {
			resultat=Message.Alert("Respectez la syntax svp !");
		}
	return resultat;
	}

public static void main(String []args) {
		Scanner scanner;
		scanner=new Scanner(System.in);
		boolean value=false;
		do {
			
			Message.Alert("Taper votre requette : ");
			String query=scanner.nextLine();
			requette.query(query);
			value=Message.Menu();
		}while(value);
		
		
	}
	
	
public static int decodeurInitial(String requette) {
		
		
		String[] query=requette.split(" ");
		
		ArrayList<String> begin=new ArrayList<>();
		ArrayList<String> keyWords=new ArrayList<>();
		
		begin.addAll(FileOperation.lire("File\\service\\begin.txt"));
		keyWords.addAll(FileOperation.lire("File\\service\\keys.txt"));
		boolean bool=requette.endsWith(";");
		
		for(int zero=0; zero<begin.size();zero++) {
			boolean reponse=query[0].toUpperCase().equals(begin.get(zero));
			if(reponse && bool) {
				int value=zero+1;
				code=value;
			}
		}
		return code;
	}

	public static int count(String chaine,char caractere) {
		char[] chars=chaine.toCharArray();
		int count=0;
		for(char a : chars) {
			if(a==caractere)
				count++;
		}
		return count;
	}
}

package javaproject;


import java.util.ArrayList;


import Data.Champ;
import Data.Message;
import Data.Table;

public class CreateQuery {
	
	static ArrayList<String> keyWords=new ArrayList<>();
	static ArrayList<String> databaseExists=new ArrayList<>();
	static ArrayList<String> ElementType=new ArrayList<String>();
	static ArrayList<String> alphabet=new ArrayList<String>();
        static String retour;
public static String CreateDataBaseOrTable(String requette1) {
	
	keyWords.addAll(FileOperation.lire("File\\service\\keys.txt"));
	databaseExists.addAll(FileOperation.lire("File\\service\\database.txt"));
	alphabet.addAll(FileOperation.lire("File\\service\\alphabet.txt"));
	
	boolean start=true;
	boolean and=requette1.endsWith(";");
	String[] query=requette1.split(" ");
	if(query.length>=3 && and) {
		
		if(query[1].toUpperCase().equals("DATABASE")) {
			String namedb=query[2].replace(";", "");
			namedb.trim();
			String car=namedb.substring(0);
			if(keyWords.contains(namedb) || databaseExists.contains(namedb) || alphabet.contains(car.toLowerCase())) 
				start=false;
		
			if(start) {
				Data.DataBase.createDataBase(namedb);
                                retour=Message.Alert("La base de donnees "+namedb+" a ete cree avec succes");
				databaseExists.add(namedb);
				FileOperation.ecrire(databaseExists, "File\\service\\database.txt");
			}
			else 
				retour=Message.Alert("Le mot "+namedb+" Est soit un mot reserv�, soit il est d�j� pris");
			
		}
       //----------------*********----------- TABLE ----------------******-------------//
		
		else if(query[1].equals("TABLE")) {
			
			int compteA=requette.count(requette1,'(');
			int count2=requette.count(requette1,')');
			
			ElementType.addAll(FileOperation.lire("File\\service\\type.txt"));
			
			boolean cond4= (compteA==count2) ? true : false;
			
			int point =query[2].indexOf(".");
			String 	namedatabase=query[2].substring(0, point);
			String  nametable=query[2].substring(point+1);
			if(nametable.contains("("))
				nametable=nametable.substring(0,nametable.indexOf("("));
			
			boolean Column=false;
			boolean Type=false;
			ArrayList<String> colonnes=new ArrayList<>();
			ArrayList<String> types=new ArrayList<>();
			boolean value=false;
			boolean val=false;
				String[] mots= requette1.split(" ");
				if(requette1.contains("(") && requette1.contains(";") && cond4){
						int indexe=requette1.indexOf("( ");
						int indiceF=requette1.lastIndexOf(")");
						String trans=requette1.substring(indexe+1,indiceF);
						retour=Message.Alert(trans);
						trans=trans.trim();
						String[] Mots=trans.split(" ");
						value=mots[2].contains("(");
						val=mots[3].contains("(");
				
				for(String mot : Mots) {
					//CREATE TABLE Gestion45.Java( nameTable varchar(30), age int);
					if(value || val) {
						
						Column=true;
						value=false;
						val=false;
					}
					else if(Column) {
						String colonne=mot;
						String stop=null;
						if(colonne.contains("(")) {
							String index=colonne.substring(0, colonne.indexOf("("));
							if(!ElementType.contains(index)) {
								retour=Message.Alert("Le type "+colonne+" est invalide");
									break;
							}
						}
						else {
							if(!ElementType.contains(colonne)) {
								retour=Message.Alert("Le type "+colonne+" est invalide");
									break;
							}
						}
						Column=false;
						Type=true;
						
					}
					else if(Type) {
						String type=mot;
						String initial=type.substring(0);
						if(alphabet.contains(initial.toLowerCase())) {
							Column=true;
							Type=false;
						}
						else 
							break;
					}
					else {
						retour=Message.Alert("aucun");
					}
					if(Column) {
						int indice=mot.indexOf(",");
						if(indice != -1)
							colonnes.add(mot.substring(0,indice));
						else
							colonnes.add(mot);
					}
					if(Type) {
						int indice=mot.indexOf(",");
						if(indice != -1) 
							types.add(mot.substring(0,indice));
						else
							types.add(mot);
						
					}
				}
			ArrayList<Data.Champ> Colonnes=new ArrayList<Champ>();
			ArrayList<Data.Table> Table=new ArrayList<Table>();
			
			if(types.size()==colonnes.size()) {
				
				for(int i=0; i<types.size();i++) {
					Colonnes.add(new Champ(types.get(i),colonnes.get(i),"NULL"));
				}
				Table.add(new Table(nametable,Colonnes));
				Data.Table.addTable(namedatabase,Table);
				retour=Message.Alert("la table est "+nametable+" est cr�ee avec succ�s");
			}
			else {
				retour=Message.Alert("nombre colonne != nombre type *** nom de table incorrecte");
			}
		}
	}
}
	else {
		retour=Message.Alert("Erreur SQL");
	}
        return retour;
}
	
	
	public static String dropDataBaseOrTable(String requette) {
		
		databaseExists.addAll(FileOperation.lire("File\\service\\database.txt"));
		
		boolean start=false;
		String[] query=requette.split(" ");
		if(query.length>=3) {
			
			if(query[1].toUpperCase().equals("DATABASE")) {
				
				String namedb=query[2].replace(";", "");
				if(databaseExists.contains(namedb)) 
					start=true;
				if(start) {
					namedb.trim();
					Data.DataBase.dropDataBase (namedb);
					databaseExists.remove(namedb);
					FileOperation.ecrire(databaseExists, "File\\service\\database.txt");
					FileOperation.deleteFile("File\\database\\"+namedb+".xml");
				}
				else {
					retour=Message.Alert("Erreur SQL");
				}
			}
		//----------*********---------- TABLE --------***********-----------//
			
			else if(query[1].toUpperCase().equals("TABLE")) {
				String namedatabase="";
				String nametable="";
				int 	pointY =query[2].indexOf(".");
				int 	pointX =query[2].indexOf(";");
				
				if( pointX!=-1 && pointY!=-1 ) {
					namedatabase=query[2].substring(0, pointY);
					nametable=query[2].substring(pointY+1,pointX);
					retour=Message.Alert(nametable);
				}
				if(databaseExists.contains(namedatabase)) 
					start=true;
				if(start) {
					Data.Table.dropTable(namedatabase, nametable);
				}
				else {
					retour=Data.Message.Alert("the table is not exist");
				}
			}
			
			else
				retour=Data.Message.Alert("Syntax error");
		}
                return retour;
	}
        
}

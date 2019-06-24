package Data;

import java.io.File;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class DataBase {

    
    static DocumentBuilderFactory DBF;
    static DocumentBuilder DocBuild;
    static Document document;
    static TransformerFactory transformerFactory;
    static Transformer transformer;
    static DOMSource source;
    static StreamResult sortie;
    static Element champElement;
    static Element bdd,tableElement;
    
    static ArrayList<Champ> champs=new ArrayList<Champ>();
    static ArrayList<Table> tab=new ArrayList<Table>();
    static String retour;
    public static void main(String[] args ) {
    	
    	champs.add(new Champ("int","matricule","16kk131"));
    	champs.add(new Champ("string","nom","kapiamba"));
    	champs.add(new Champ("string","prenom","kayombo"));
    	champs.add(new Champ("int","age","20"));
    	
    	ArrayList<Champ> ch=new ArrayList<Champ>();
    	ch.add(new Champ("double","prix","4500.00"));
    	ch.add(new Champ("String","designation",""));
    	
    	tab.add(new Table("Produit",ch));
    	tab.add(new Table("Medecin",champs));
    	tab.add(new Table("Escrop",champs));
    	tab.add(new Table("Employe",champs));
    	tab.add(new Table("Malade",champs));
    	
    	ArrayList<String> chaine=new ArrayList<String>();
    	ArrayList<String> chaineX=new ArrayList<String>();
    	
    	chaine.add("15kk121");
    	chaine.add("mutombo");
    	chaine.add("cecile");
    	chaine.add("19");
    	
    	chaineX=Colonne.select("Gestion45", "Escrop");
    	//Table.dropTable("F:\\Gestion","Medecin");
    	//DataBase.createDataBase("Gestion45");
    	//DataBase.dropDataBase("F:\\Gestion");
    	//Champ.insert("Gestion45","Escrop",chaine);
    	//Table.addTable("Gestion45", tab);
    	//Champ.delete("Gestion45", "Employe");
    	for (String str : chaineX ) {
    		Message.Alert(str);
    	}
    }
    
    
public String getName() {
		return name;
	}
public void setName(String name) {
		this.name = name;
	}
    private String name;


public static String createDataBase(String namedb, ArrayList<Table> table) {
	DomCopy data=new DomCopy();
	data.newDataBase(namedb,table);
	return "cr�ation Data Base "+namedb+" � r�ussie";}

public static String createDataBase(String namedb) {
    String retour="biens";
    try{
        
        DBF=DocumentBuilderFactory.newInstance();
        DocBuild=DBF.newDocumentBuilder();
        document=DocBuild.newDocument();
        
        Text text=document.createTextNode("Null");
        bdd=document.createElement("DataBase");
        bdd.setAttribute("name",namedb);
        bdd.appendChild(text);
        document.appendChild(bdd);
    	
    	transformerFactory =TransformerFactory.newInstance();
        transformer =transformerFactory.newTransformer();
        source = new DOMSource(document);
        sortie = new StreamResult(new File("File\\database\\"+namedb+".xml"));
       
        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indentamount", "2");
        transformer.transform(source, sortie);
        retour="Creation database "+namedb+" r�eussie";
    	}
   catch(Exception e) {
	   retour=(e.getMessage()+"\n"+e.getCause());
   }
    return retour;
}

public static void dropDataBase(String namefile) {
    
	try {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document documentModify=builder.newDocument();
		
		transformerFactory =TransformerFactory.newInstance();
        transformer =transformerFactory.newTransformer();
        source = new DOMSource(documentModify);
        String file=new String("File\\database\\"+namefile+".xml");
        System.out.println(file);
        sortie = new StreamResult(new File(file));
       
        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indentamount", "2");
        transformer.transform(source, sortie);
	}
	catch(Exception e) {
		System.out.println(e.getMessage()+"\n"+e.getCause());
		}
}

}

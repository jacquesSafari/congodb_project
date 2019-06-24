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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;



public class DomCopy {
	
	static DocumentBuilderFactory DBF;
    static DocumentBuilder DocBuild;
    static Document document;
    static TransformerFactory transformerFactory;
    static Transformer transformer;
    static DOMSource source;
    static StreamResult sortie;
    static Element champElement;
    static Element bdd,tableElement;
   

public static void deleteDom(String nameDataBase,String nameTable) {
Element table,champ,cellule;
	
	try {
				
	//................................. Copy DOM ................................
				
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			String file=new String("File\\database\\"+nameDataBase+".xml");
		      
			Document documentSource =builder.parse(new File(file));
			Document documentDest=builder.newDocument();
				
				
			Element racineSource=documentSource.getDocumentElement();
			Element racineDest=documentDest.createElement(racineSource.getNodeName());
			racineDest.setAttribute("name",racineSource.getAttribute("name"));
			
			NodeList Tables =racineSource.getChildNodes();
			
			boolean True;
			
			if(Tables.getLength()>0) {
				for(int data=0; data<Tables.getLength();data++) {
					if(Tables.item(data).getNodeType()==Node.ELEMENT_NODE) {
						
						table=documentDest.createElement(Tables.item(data).getNodeName());
						table.setAttribute("name", ((Element) Tables.item(data)).getAttribute("name"));
						
						NodeList champs=Tables.item(data).getChildNodes();
						
						if(champs.getLength()>0) {
						for(int dat=0; dat<champs.getLength();dat++) {
							if(champs.item(dat).getNodeType()==Node.ELEMENT_NODE) {
								//System.out.println(champs.item(dat).getNodeName());
								champ=documentDest.createElement(champs.item(dat).getNodeName());
								//System.out.println(champ.getNodeName());
								champ.setAttribute("type", ((Element) champs.item(dat)).getAttribute("type"));
								champ.setAttribute("name", ((Element) champs.item(dat)).getAttribute("name"));
								
								table.appendChild(champ);
								NodeList cellules=champs.item(dat).getChildNodes();
								if( cellules.getLength()>0) {
									for(int da=0; da< cellules.getLength();da++) {
										if(Tables.item(da).getNodeType()==Node.ELEMENT_NODE) {
											True=((Element) Tables.item(data)).getAttribute("name").equals(nameTable);
											//System.out.println("valeur trouver "+True);
											if(!True) {
											cellule=documentDest.createElement(cellules.item(da).getNodeName());
											//System.out.println(cellule.getNodeName());
											cellule.setAttribute("contenu", ((Element)  cellules.item(da)).getAttribute("contenu"));
											
											champ.appendChild(cellule);}
											else {
										         Text text=documentDest.createTextNode("NULL");
										         champ.appendChild(text);}
											
											}
										}
									}
								}
							}
						}racineDest.appendChild(table);
					}
				}
			}	
	documentDest.appendChild(racineDest);
	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	
	
			transformerFactory =TransformerFactory.newInstance();
	        transformer =transformerFactory.newTransformer();
	        source = new DOMSource(documentDest);
	        String File=new String("File\\database\\"+nameDataBase+".xml");
	        System.out.println("suppression effectu�e");
	        System.out.println(file);
	        sortie = new StreamResult(new File(File));
	       
	        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
	        
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indentamount", "2");
	        transformer.transform(source, sortie);
	}
		catch(Exception e) {
			System.out.println(e.getMessage()+"\n"+e.getCause());}
}
public void newDataBase(String namedb,ArrayList<Table> tables){
	
boolean start=true;

if (start) {	
try{
        
        DBF=DocumentBuilderFactory.newInstance();
        DocBuild=DBF.newDocumentBuilder();
        document=DocBuild.newDocument();

        bdd=document.createElement("DataBase");
        bdd.setAttribute("name",namedb);
        document.appendChild(bdd);
        for(Table table : tables) {
		
		String nameTable=table.getNom();
		ArrayList<Champ> champs=table.getListChamps();
		
		tableElement=document.createElement("Table");
		
		tableElement.setAttribute("name", nameTable);
		
		for(Champ champ : champs) {
			
			champElement=document.createElement("Colonne");
			champElement.setAttribute("type",champ.getType());
			champElement.setAttribute("name", champ.getName());
			Element contenuElement =document.createElement("cellule");
			contenuElement.setAttribute("contenu", champ.getCellule());
			champElement.appendChild(contenuElement);
			
			tableElement.appendChild(champElement);
		}
		bdd.appendChild(tableElement);
	}
        
		transformerFactory =TransformerFactory.newInstance();
        transformer =transformerFactory.newTransformer();
        source = new DOMSource(document);
        sortie = new StreamResult(new File(namedb+".xml"));
       
        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indentamount", "2");
        transformer.transform(source, sortie);}
catch(Exception e) {System.out.println(e.getMessage()+"\n"+e.getCause());}}
else {System.out.println("ce nom existe d�j�");}}
 }
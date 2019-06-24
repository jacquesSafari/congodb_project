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

public class Table {
	private ArrayList<Champ> ListChamps=new ArrayList<Champ>();
	
	static DocumentBuilderFactory DBF;
    static DocumentBuilder DocBuild;
    static Document document;
    static TransformerFactory transformerFactory;
    static Transformer transformer;
    static DOMSource source;
    static StreamResult sortie;
    
    
	public ArrayList<Champ> getListChamps() {
		return ListChamps;
	}

	private String nom;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}	
	public void addChamp(Champ champ) {
		ListChamps.add(champ);
	}
	
	public Table(String name, ArrayList<Champ> ListChamp) {
		this.nom=name;
		this.ListChamps=ListChamp;
	}
	
public static void dropTable(String nameDataBase,String nameTable) {
		
	Element table,champ,cellule;
				
	try {
					
//............................... Copy DOM ...............................
					
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			String file=new String("File\\database\\"+nameDataBase+".xml");
			      
			Document documentSource =builder.parse(new File(file));
			Document documentDest=builder.newDocument();
					
					
			Element racineSource=documentSource.getDocumentElement();
			Element racineDest=documentDest.createElement(racineSource.getNodeName());
			racineDest.setAttribute("name",racineSource.getAttribute("name"));
			NodeList Tables =racineSource.getChildNodes();
			documentDest.appendChild(racineDest);
			int value=documentSource.getChildNodes().getLength();
			boolean False=true;
			if(value>0) {	
				if(Tables.getLength()>0) {
					for(int data=0; data<Tables.getLength();data++) {
						if(Tables.item(data).getNodeType()==Node.ELEMENT_NODE) {
							boolean True=((Element) Tables.item(data)).getAttribute("name").equals(nameTable);
							if(True) {False=false;}
							if(!True) {
							table=documentDest.createElement(Tables.item(data).getNodeName());
							table.setAttribute("name", ((Element) Tables.item(data)).getAttribute("name"));
							//System.out.println(racineDest.appendChild(table));
							
							NodeList champs=Tables.item(data).getChildNodes();
							//System.out.println(Tables.item(data).getChildNodes().getLength());
							if(champs.getLength()>0) {
							for(int dat=0; dat<champs.getLength();dat++) {
								if(Tables.item(dat).getNodeType()==Node.ELEMENT_NODE) {
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
												//System.out.println( cellules.item(da).getNodeName());
												cellule=documentDest.createElement(cellules.item(da).getNodeName());
												//System.out.println(cellule.getNodeName());
												cellule.setAttribute("contenu", ((Element)  cellules.item(da)).getAttribute("contenu"));
												
												champ.appendChild(cellule);
												}
											}
										}
									}
								}
							}racineDest.appendChild(table);
						}
						else System.out.println("Suppression de la table "+nameTable+" r�uessie");
					}
				}
			}	
			if (False) {System.out.println("La table "+nameTable+" n'existe pas dans base de donn�e");}	
//............................ copy DOM ........................................
					transformerFactory =TransformerFactory.newInstance();
			        transformer =transformerFactory.newTransformer();
			        source = new DOMSource(documentDest);
			        String File=new String("File\\database\\"+nameDataBase+".xml");
			        System.out.println(file);
			        sortie = new StreamResult(new File(File));
			       
			        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			        
			        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			        transformer.setOutputProperty("{http://xml.apache.org/xslt}indentamount", "2");
			        transformer.transform(source, sortie);
		}
		else {
				System.out.println("la base de donn�es"+nameDataBase+" n'existe pas");
		}
	}
		
		catch(Exception e) {
			Message.Alert(e.getMessage());e.getCause();
			Message.Alert("Cette base de donn�e n'existe pas");
}}
	
public static void alterTable(String nameDataBase, String Table, ArrayList<Champ> ListChamp) {
		//TODO
	}

public static void addTable(String nameDataBase, ArrayList<Table> tablesX ) {
	Element table,champ,cellule;
	
	try {
				
	//............................... Copy DOM ...............................
				
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			String file=new String("File\\database\\"+nameDataBase+".xml");
		      
			Document documentSource =builder.parse(new File(file));
			Document documentDest=builder.newDocument();
				
				
			Element racineSource=documentSource.getDocumentElement();
			Element racineDest=documentDest.createElement(racineSource.getNodeName());
			racineDest.setAttribute("name",racineSource.getAttribute("name"));
			
			NodeList Tables =racineSource.getChildNodes();
		
			if(Tables.getLength()>0) {
				for(int data=0; data<Tables.getLength();data++) {
					if(Tables.item(data).getNodeType()==Node.ELEMENT_NODE) {
				
						table=documentDest.createElement(Tables.item(data).getNodeName());
						table.setAttribute("name", ((Element) Tables.item(data)).getAttribute("name"));
						
						NodeList champs=Tables.item(data).getChildNodes();
						
						if(champs.getLength()>0) {
						for(int dat=0; dat<champs.getLength();dat++) {
							if(Tables.item(dat).getNodeType()==Node.ELEMENT_NODE) {
								
								champ=documentDest.createElement(champs.item(dat).getNodeName());
								System.out.println(champ.getNodeName());
								champ.setAttribute("type", ((Element) champs.item(dat)).getAttribute("type"));
								champ.setAttribute("name", ((Element) champs.item(dat)).getAttribute("name"));
								
								table.appendChild(champ);
								NodeList cellules=champs.item(dat).getChildNodes();
								if( cellules.getLength()>0) {
									for(int da=0; da< cellules.getLength();da++) {
										if(Tables.item(da).getNodeType()==Node.ELEMENT_NODE) {
											
											cellule=documentDest.createElement(cellules.item(da).getNodeName());
											cellule.setAttribute("contenu", ((Element)  cellules.item(da)).getAttribute("contenu"));
											
											champ.appendChild(cellule);
											}
										}
									}
								}
							}
						}racineDest.appendChild(table);
					}
				}
			}	
	
	//.................................  Ajout de tables ..................................
			
	int depart=racineDest.getChildNodes().getLength();
			Element champElement,tableElement;
			for(Table tb : tablesX) {
				
				String nameTableX=tb.getNom();
				ArrayList<Champ> champsX=tb.getListChamps();
				
				tableElement=documentDest.createElement("Table");
				tableElement.setAttribute("name", nameTableX);
				
				for(Champ chp : champsX) {
					
					champElement=documentDest.createElement("Colonne");
					champElement.setAttribute("type",chp.getType());
					champElement.setAttribute("name",chp.getName());
					Element contenuElement =documentDest.createElement("cellule");
					contenuElement.setAttribute("contenu", chp.getCellule());
					champElement.appendChild(contenuElement);
					tableElement.appendChild(champElement);
				}
				racineDest.appendChild(tableElement);
			}
	
	int arrive=racineDest.getChildNodes().getLength();
	if(depart!=arrive && depart>arrive) {System.out.println("Ajout de "+tablesX.size()+" effectuer avec succes");}
	documentDest.appendChild(racineDest);
	
	// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
			
				transformerFactory =TransformerFactory.newInstance();
		        transformer =transformerFactory.newTransformer();
		        source = new DOMSource(documentDest);
		        String File=new String("File\\database\\"+nameDataBase+".xml");
		       
		        sortie = new StreamResult(new File(File));
		       
		        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
		        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.setOutputProperty("{http://xml.apache.org/xslt}indentamount", "2");
		        transformer.transform(source, sortie);
		}
			catch(Exception e) {
				Message.Alert(e.getMessage());e.getCause();
				Message.Alert("Cette base de donn�e n'existe pas");
			}}

}

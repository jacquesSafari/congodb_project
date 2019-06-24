package Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

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


public class Champ {
	
	private String type;
	private ArrayList<String> contenu;
	private String cellule;
	public ArrayList<String> getContenu() {
		return contenu;
	}
	public void setContenu(ArrayList<String> contenu) {
		this.contenu = contenu;
	}

	private String name;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Champ(String type2, String name2, ArrayList<String> contenu) {
		// TODO Auto-generated constructor stub
		this.type=type2;
		this.name=name2;
		this.setContenu(contenu);
	}
	public Champ(String type2, String name2, String contenu) {
		this.type=type2;
		this.name=name2;
		this.setCellule(contenu);
	}
	//---------------------------------------------------------------------------------------------
	
	public static void insert(String nameDataBase,String nameTable,ArrayList<String> keysValues) {
		
		Element table,champ,cellule;
		ArrayList<Element> Contenus=new ArrayList<Element>();
		
		Iterator <String> values= keysValues.iterator();
		
		
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
				
				while(values.hasNext()) {
					Element colonne=documentDest.createElement("cellule");
					colonne.setAttribute("contenu",(String) values.next());
					Contenus.add(colonne);
					
				}
				boolean True;
				int i=0;
				if(Tables.getLength()>0) {
					for(int data=0; data<Tables.getLength();data++) {
						if(Tables.item(data).getNodeType()==Node.ELEMENT_NODE) {
							
							table=documentDest.createElement(Tables.item(data).getNodeName());
							table.setAttribute("name", ((Element) Tables.item(data)).getAttribute("name"));
							
							NodeList champs=Tables.item(data).getChildNodes();
							True=((Element) Tables.item(data)).getAttribute("name").equals(nameTable);
							if(champs.getLength()>0) {
							for(int dat=0; dat<champs.getLength();dat++) {
								if(champs.item(dat).getNodeType()==Node.ELEMENT_NODE) {
									
									champ=documentDest.createElement(champs.item(dat).getNodeName());
								
									champ.setAttribute("type", ((Element) champs.item(dat)).getAttribute("type"));
									champ.setAttribute("name", ((Element) champs.item(dat)).getAttribute("name"));
									
									table.appendChild(champ);
									NodeList cellules=champs.item(dat).getChildNodes();
									
									if( cellules.getLength()>0) {
										for(int da=0; da< cellules.getLength();da++) {
											if(cellules.item(da).getNodeType()==Node.ELEMENT_NODE) {
												cellule=documentDest.createElement(cellules.item(da).getNodeName());
												if(!((Element) cellules.item(da)).getAttribute("contenu").equals("NULL")) {
													cellule.setAttribute("contenu", ((Element)  cellules.item(da)).getAttribute("contenu"));
													champ.appendChild(cellule);
													}
												}
											}
										}
									if(True) {
										if(i<Contenus.size() && Contenus.size()<=champs.getLength()) {
												champ.appendChild(Contenus.get(i));
												i++;
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
				System.out.println("il ya de betise");}
	}
		
	
	public static void delete(String nameDataBase,String nameTable) {
		DomCopy.deleteDom(nameDataBase, nameTable);
	}
	
	
	public String getCellule() {
		return cellule;
	}
	public void setCellule(String cellule) {
		this.cellule = cellule;
	}
	static DocumentBuilderFactory DBF;
    static DocumentBuilder DocBuild;
    static Document document;
    static TransformerFactory transformerFactory;
    static Transformer transformer;
    static DOMSource source;
    static StreamResult sortie;
}

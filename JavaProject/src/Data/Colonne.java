package Data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Colonne {
	
	private String contenu;
	static DocumentBuilderFactory DBF;
    static DocumentBuilder DocBuild;
    static Document document;
    static TransformerFactory transformerFactory;
    static Transformer transformer;
    static DOMSource source;
    static StreamResult sortie;

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Colonne(String contenu) {
		this.setContenu(contenu);
	}
	
	public static ArrayList<String> select(String nameDataBase, String nameTable) {
		try {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		String file=new String("File\\database\\"+nameDataBase+".xml");
		ArrayList<String> NameColonne=new ArrayList<String>();
		ArrayList<String> Values=new ArrayList<String>();
	      
		Document documentSource =builder.parse(new File(file));
		NodeList Tables=documentSource.getDocumentElement().getChildNodes();
		int value=documentSource.getChildNodes().getLength();
		boolean False=true;
		if(value>0) {	
			if(Tables.getLength()>0) {
				for(int data=0; data<Tables.getLength();data++) {
					if(Tables.item(data).getNodeType()==Node.ELEMENT_NODE) {
						boolean True=((Element) Tables.item(data)).getAttribute("name").equals(nameTable);
						if(True) {
							False=false;
							NodeList champs=Tables.item(data).getChildNodes();
						//System.out.println(Tables.item(data).getChildNodes().getLength());
						if(champs.getLength()>0) {
						for(int dat=0; dat<champs.getLength();dat++) {
							if(Tables.item(dat).getNodeType()==Node.ELEMENT_NODE) {
								
								NameColonne.add(((Element) champs.item(dat)).getAttribute("name"));
								NodeList cellules=champs.item(dat).getChildNodes();
								
								if( cellules.getLength()>0) {
									for(int da=0; da< cellules.getLength();da++) {
										if(Tables.item(da).getNodeType()==Node.ELEMENT_NODE) {
											Values.add(((Element)  cellules.item(da)).getAttribute("contenu"));
											Message.Alert(((Element)  cellules.item(da)).getAttribute("contenu"));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
		
	if (False) {Message.Alert("La table "+nameTable+" n'existe pas dans base de donn�e");}	
	
		StringBuffer colonne=new StringBuffer();
		int step=NameColonne.size();
		for(String namecolonne : NameColonne) 
		{
			colonne.append(namecolonne);
			colonne.append("      ");
			
			Message.Alert(new String(colonne));
		}
		int i=0;
		ArrayList<String> chaine=new ArrayList<>();
		colonne.append("\n");
		colonne.append("----------------------------------------------");
		chaine.add(new String(colonne));
		while(i<NameColonne.size()) {
			int j=i;
			StringBuffer colonneX=new StringBuffer();
			while(j<Values.size()) {
				Message.Alert(Values.get(j));
				colonneX.append(Values.get(j));	
				colonneX.append("     ");
				j+=step-1;
				Message.Alert(new String(colonneX));
			}
			chaine.add(new String(colonneX));
			i++;
		}
		Message.Alert("selection effectu�e");
		return chaine;
	
		}

		catch(Exception e) {
			Message.Alert("�chec de selection");
			Message.Error(e);
			return null;
		}
			
	}
	
	public static void update(String nameDataBase,String nameTable) {
		try {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		String file=new String("File\\database\\"+nameDataBase+".xml");
		Document documentSource =builder.parse(new File(file));
		Document documentDest=builder.newDocument();
		//Message.Alert(documentSource.importNode(arg0, arg1));
		
		
		}
		catch(Exception e) {
			
		}
	}
}

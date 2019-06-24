package sgbd15.Kernerl;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 import java.util.List;
 import java.util.ArrayList;
 import java.util.Scanner;
 import java.io.*;
public class Table{
     private String nom;
     private List<Field> champs = new ArrayList<Field>();

     public Table(String nom){
         this.nom = nom;
     }     
    public Table(){
        
    }

     public String getNom(){
         return this.nom;
     }
     public List<Field> getField(){
         return this.champs;
     }
     public void setNom(String nom){
         this.nom = nom;
     }
     public void addField(Field champ){
         this.champs.add(champ);
     }

     public void createTableFieldFolder(){
         String chemin = Database.path+"Databases\\fieldsTable\\";
         File tableFields= new File(chemin);
         if(!(tableFields.isDirectory())){
             tableFields.mkdir();
         }
     }
     
     public boolean createTable(String nomDb){       
        
        System.out.println("Tapez une requete ici :\n==========================");
        System.out.println(" exemple *CREATE TABLE nom ( champ1 type1 champ2 type2 )*\n");
        Scanner sc = new Scanner(System.in);
        String requete = sc.nextLine();
        String separateur = " ";        
        String[] t_requete = requete.split(separateur);

        int size_tab = t_requete.length;
        String[] t_champs = new String[size_tab-5];
        String[] t_types = new String[(size_tab-5)/2];
        
        if(!((t_requete[0]+" "+t_requete[1]).equals("CREATE TABLE"))) {
            System.out.println("Mot inconnue!!");
            System.exit(0);
        }

        if(!((t_requete[3]+t_requete[size_tab-1]).equals("()"))){
            System.err.println("Mauvaise requete !\n Mettez des espaces => nom_table ( nomchamp type1 )");
            System.exit(0);
        }

        for(int i = 4 ;i < (size_tab-1) ;i++){
            t_champs[i-4] = t_requete[i];
        }       

        int size_tab_champ = t_champs.length;
        if(!(size_tab_champ%2 == 0)){
            System.out.println("Chaque champ doit avoir un type");
            System.exit(0);
        }
        int indiceOfType = 0;
        for(int k = 0; k < (size_tab_champ-1);k+=2){
            t_types[indiceOfType] = t_champs[k+1];                        
            indiceOfType++;
        }       
        
        for (String type : t_types) {
            if(!(isValidType(type))){
                System.out.println("Error! Le type: "+type+" n'est pas disponible!");
                System.exit(0);
            }
        }       
        
        String chemin = Database.path+"Databases\\"+nomDb+"\\"+t_requete[2]+".xml";
        File table = new File(chemin);
        if(!(table.exists())){
            try {
                 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();                
                 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                 Document doc = dBuilder.newDocument();
                 Element rootElement = doc.createElement("table_"+t_requete[2]);
                 doc.appendChild(rootElement);  

                String fileToSaveFields = "_file.txt";
                createTableFieldFolder();
                PrintWriter impression = new PrintWriter(new FileWriter(Database.path+"Databases\\fieldsTable\\"+t_requete[2]+fileToSaveFields));
            
                for(int j = 0;j < (t_champs.length-1);j=j+2){   
                    impression.println(t_champs[j]);
                    impression.println(t_champs[j+1]);               
                }
                impression.flush();
                 //===========================================================
                TransformerFactory transformerFactory = TransformerFactory.newInstance();                
                Transformer transformer = transformerFactory.newTransformer();                
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(chemin));                
                transformer.transform(source, result);                
                System.out.println("La table "+t_requete[2]+" est creee dans la BDD "+nomDb);               
                
            } catch (Exception e) {
                 e.printStackTrace();
            }
            return true;
        }          
        else{
            System.out.println("Cette table existe deja dans "+Database.path+nomDb+"/");
            return false;
        }
    }
    //=========================================================
    public boolean isValidType(String typeGiven){
       String[] types ={"int","string","double","float","short"};
       for (String var : types) {
           if(var.equals(typeGiven)){
               return true;
           }
       }
       return false;
    }
   
    public void insertData(String nomDb,String nomTable){
         
        String chemin = checkingFolderAndFile(nomDb,nomTable);
        
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
        try {        
            int id = 1;
            final DocumentBuilder builder = factory.newDocumentBuilder();  
            final Document document = builder.parse(new File(chemin)); 
            final Element racine = document.getDocumentElement();  
            final Element conteneur  = document.createElement(nomTable);            
            NodeList nList = document.getElementsByTagName(nomTable);
            if(nList.getLength()<1){
                conteneur.setAttribute("id", String.valueOf(1));
            }
            else{
                int sizeOfList = nList.getLength();
                int indice = sizeOfList - 1;
                Node nNode = nList.item(indice);
                Element precedanteElement = (Element) nNode;
                String precedanteAttribut = precedanteElement.getAttribute("id");
                id += Integer.valueOf(precedanteAttribut);
                conteneur.setAttribute("id", String.valueOf(id));
            }
            
                        
            racine.appendChild(conteneur);

            BufferedReader flux = new BufferedReader(new FileReader(Database.path+"Databases\\fieldsTable/"+nomTable+"_file.txt"));
            //======================================
            String line;
            int count = 0;
            List<String> fields = new ArrayList<String>();
            while((line = flux.readLine()) != null){
               fields.add(line); 
               count++;               
            }            
            
            String[] champEtType = new String[count];
            String phrase = "";
            for (String ligne : fields) {
                phrase += ligne+" ";
            }
            champEtType = phrase.split(" ");           
            for(int j = 0;j < (champEtType.length-1);j=j+2){
                Scanner sc = new Scanner(System.in);
                System.out.println("Entrez le "+champEtType[j]); 
                String value = sc.nextLine();  
                final Element champ = document.createElement(champEtType[j]);
                champ.setAttribute("type", champEtType[j+1]);
                champ.appendChild(document.createTextNode(value));
                conteneur.appendChild(champ);                   
                
            }            
            
            //========================================

            
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    
            final Transformer transformer = transformerFactory.newTransformer();

            final DOMSource source = new DOMSource(document);

            final StreamResult sortie = new StreamResult(new File(chemin));           

            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(source, sortie);  

        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }        
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }        
    }
    public String checkingFolderAndFile(String nomDb, String nomTable){
        String chemin = Database.path+"Databases\\"+nomDb+"\\"+nomTable+".xml";
        File fichier = new File(chemin);
        File db = new File(Database.path+"Databases\\"+nomDb);

        if(!(db.isDirectory())){
            System.out.println("Cette DB n'existe pas");
            System.exit(0);
        }
        if(!(fichier.exists())){
            System.out.println("Cette table n'existe pas");
            System.exit(0);
        }
        return chemin;
    }

     public String selectData(String nomDb,String nomTable){
         String chemin = checkingFolderAndFile(nomDb,nomTable);

         return "";
     }
     public void updateData(String nomDb, String nomTable){
        String chemin =  checkingFolderAndFile(nomDb,nomTable);
         try {

             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.parse(chemin);
             //===============================================================
             final Element data = doc.getDocumentElement();
             NodeList nList = data.getElementsByTagName(nomTable);
             Scanner sc = new Scanner(System.in);
             System.out.println("Entrez le champ ou vous voulez changer la valeur:");
             String field = sc.nextLine();
             System.out.println("Entrer la nouvelle valeur : ");
             String value = sc.nextLine();
             System.out.println("id:");
             String idNode = sc.nextLine();
             char idItem = idNode.charAt(0);
             boolean isDone = false;
             for (int v = 0; v < nList.getLength(); v++) {
                 Node nNode = nList.item(v);
                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                     Element element = (Element) nNode;
                     String forId = element.getAttribute("id");
                     char idChild = forId.charAt(0);
                    if (idChild == idItem){
                        isDone = true;
                        Node nodeToUpdate = element.getElementsByTagName(field).item(0);
                        nodeToUpdate.setTextContent(value);
                        break;
                    }
                 }
             }
             if(isDone){
                 System.out.println("Bien");
             }
             else{
                 System.out.println("Pas trouve");
             }
             writeContentInXmlFile(chemin,doc);

         } catch (ParserConfigurationException e) {
             e.printStackTrace();
         }
         catch (SAXException e) {

             e.printStackTrace();
         } catch (IOException e) {

             e.printStackTrace();
         }
     }
     //===============================================================
     public void deleteData(String nomDb, String nomTable){
        String chemin = checkingFolderAndFile(nomDb,nomTable);
         try {

             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.parse(chemin);

             final Element data = doc.getDocumentElement();
             NodeList nList = data.getElementsByTagName(nomTable);

             Scanner sc = new Scanner(System.in);
             System.out.println("Entrez l'id de l'element:");
             String idNode = sc.nextLine();
             char idItem = idNode.charAt(0);
             boolean isDone = false;
             //bouclons
             for (int v = 0; v < nList.getLength(); v++) {
                 Node nNode = nList.item(v);
                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                     Element element = (Element) nNode;
                     String forId = element.getAttribute("id");
                     char idChild = forId.charAt(0);
                     if (idChild == idItem){
                         isDone = true;
                         element.getParentNode().removeChild(element);
                         break;
                     }
                 }
             }
             if(isDone){
                 System.out.println("Fait");
             }
             else{
                 System.out.println("id non trouve");
             }
             writeContentInXmlFile(chemin,doc);

         } catch (ParserConfigurationException e) {
             e.printStackTrace();
         }catch (SAXException e) {
             e.printStackTrace();
         } catch (IOException e) {

             e.printStackTrace();
         }
     }

     public void writeContentInXmlFile(String chemin,Document doc){
         try{
             TransformerFactory transformerFactory = TransformerFactory
                     .newInstance();
             Transformer transformer = transformerFactory.newTransformer();
             DOMSource source = new DOMSource(doc);
             StreamResult result = new StreamResult(new File(chemin));
             transformer.transform(source, result);
         }
         catch (TransformerException e){
             e.printStackTrace();
         }

     }
     


 }
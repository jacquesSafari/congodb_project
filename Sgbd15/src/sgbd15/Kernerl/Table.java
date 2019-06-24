package sample.Kernerl;

/*
*Ce fichier contient tous ce qui concerne la creation
*et les operations sur des tables
*/


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

 //pour les collections
 import java.util.List;
 import java.util.ArrayList;
 //pour les flux d'entree
 import java.util.Scanner;
 //pour les io
 import java.io.*;

 //class
public class Table{
     //fields
     
     private String nom;
     //this table belongs to this DB     t<Field>();
     private List<Field> champs = new ArrayList<Field>();

     //constructer
     public Table(String nom){
         this.nom = nom;
         //this.id = ++Table.nbreInstance;
     }     

    //default construct
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
         String chemin = Database.path+"Databases\\tableFields\\";
         File tableFields= new File(chemin);
         if(!(tableFields.isDirectory())){
             tableFields.mkdir();
         }
     }
     
     public boolean createTable(String nomDb){       
        
        System.out.println("Tapez une requete de ce type:\n==========================");
        System.out.println("*CREATE TABLE nom ( champ1 type1 champ2 type2 )*\n");

        //recuperation de la requete
        Scanner sc = new Scanner(System.in);
        String requete = sc.nextLine();

        //transformations de la requete en tableau
        String separateur = " ";        
        String[] t_requete = requete.split(separateur);

        int size_tab = t_requete.length;
        //le tableau qui contiendra les champs et les types
        String[] t_champs = new String[size_tab-5];
        //un tableau  uniquement pour les types pour y ajouter des contraintes
        String[] t_types = new String[(size_tab-5)/2];
        
        //verifions si le mot cle est correct
        if(!((t_requete[0]+" "+t_requete[1]).equals("CREATE TABLE"))) {
            System.out.println("Mot cle inconnue!!");
            System.exit(0);
        }

        //verifions si les espaces entre les() sont respectes
        if(!((t_requete[3]+t_requete[size_tab-1]).equals("()"))){
            System.err.println("Error requete!\nveuillez mettre des espaces => nom_table ( nomchamp type1 )");
            System.exit(0);
        }

        //recuperer les champs et leurs types dans un tableau
        for(int i = 4 ;i < (size_tab-1) ;i++){
            t_champs[i-4] = t_requete[i];
        }       

        //verifion si le nombre d'elements entre les() est paire
        int size_tab_champ = t_champs.length;
        if(!(size_tab_champ%2 == 0)){
            System.out.println("Chaque champ doit etre lie a un type");
            System.exit(0);
        }
        //recuperons les types
        int indiceOfType = 0;
        for(int k = 0; k < (size_tab_champ-1);k+=2){
            t_types[indiceOfType] = t_champs[k+1];                        
            indiceOfType++;
        }       
        
        //verifons si les types fourinis sont valable(disponible)
        for (String type : t_types) {
            if(!(isValidType(type))){
                System.out.println("Error! Le type: "+type+" n'est pas disponible!");
                System.exit(0);
            }
        }       
        
        //=============si tout est bon passons a xml================================
        String chemin = Database.path+"Databases\\"+nomDb+"\\"+t_requete[2]+".xml";
        File table = new File(chemin);
        if(!(table.exists())){
            try {
                //========le constructeur des document=================================
                 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();                
                 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                 Document doc = dBuilder.newDocument();

                 //==========l'element racine================
                 Element rootElement = doc.createElement("table_"+t_requete[2]);
                 doc.appendChild(rootElement);  

                 //la terminaison de tous les fichiers contenant les champs
                String fileToSaveFields = "_file.txt";
                createTableFieldFolder();
                PrintWriter impression = new PrintWriter(new FileWriter(Database.path+"Databases\\tableFields\\"+t_requete[2]+fileToSaveFields));
            
                for(int j = 0;j < (t_champs.length-1);j=j+2){   
                    impression.println(t_champs[j]);
                    impression.println(t_champs[j+1]);               
                }
                impression.flush();
                 //=========finaliser toute chose==================
                TransformerFactory transformerFactory = TransformerFactory.newInstance();                
                Transformer transformer = transformerFactory.newTransformer();                
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(chemin));                
                transformer.transform(source, result);                
                System.out.println("La table "+t_requete[2]+" est creee aves succes dans la BDD "+nomDb);               
                
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
    
    //=========methode de verification============
    public boolean isValidType(String typeGiven){
       //types diponible
       String[] types ={"int","string","double","float","short"};
       for (String var : types) {
           if(var.equals(typeGiven)){
               return true;
           }
       }
       return false;
    }

     //table operations     
    public void insertData(String nomDb,String nomTable){
         
        String chemin = checkingFolderAndFile(nomDb,nomTable);
        
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();         
        //essayons d'acceder au fichier et d'y inserer des donnees
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

            //connection au fichier et ouverture en mode lecture
            BufferedReader flux = new BufferedReader(new FileReader(Database.path+"Databases\\tableFields/"+nomTable+"_file.txt"));
            //======================================
            String line;
            int count = 0;
            List<String> fields = new ArrayList<String>();
            while((line = flux.readLine()) != null){
               fields.add(line); 
               count++;               
            }            
            
            //tableau des champs et des types
            String[] champEtType = new String[count];
            String phrase = "";
            for (String ligne : fields) {
                phrase += ligne+" ";
            }
            champEtType = phrase.split(" ");           
            for(int j = 0;j < (champEtType.length-1);j=j+2){
                Scanner sc = new Scanner(System.in);
                System.out.println("Entrez le "+champEtType[j]); 
                //valeur du champ
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

                    

            //formatage

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                

            //sortie

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

     //update item
     public void updateData(String nomDb, String nomTable){
        String chemin =  checkingFolderAndFile(nomDb,nomTable);
         try {

             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.parse(chemin);
             // Get the root element
            // Node data= doc.getFirstChild();
             final Element data = doc.getDocumentElement();
             NodeList nList = data.getElementsByTagName(nomTable);
             Scanner sc = new Scanner(System.in);
             System.out.println("Entrez le champ dont vous voulez changer la valeur:");
             String field = sc.nextLine();
             System.out.println("Entrer la nouvelle valeure");
             String value = sc.nextLine();
             System.out.println("id:");
             String idNode = sc.nextLine();
             char idItem = idNode.charAt(0);
             //bouclons
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
                 System.out.println("Done");
             }
             else{
                 System.out.println("id not found");
             }
             //write content in xml file
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
     //methode delete
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
                 System.out.println("Done");
             }
             else{
                 System.out.println("id not found");
             }
            //write content in xml file
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
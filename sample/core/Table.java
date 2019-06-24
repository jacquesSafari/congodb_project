package sample.core;
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

     public Table(String nom)
     {
         this.nom = nom;
     }     

    public Table()
    {
        
    }

     public String getNom()
     {
         return this.nom;
     }

     public List<Field> getField()
     {
         return this.champs;
     }

     public void setNom(String nom)
     {
         this.nom = nom;
     }

     public void addField(Field champ)
     {
         this.champs.add(champ);
     }

     public void createFiedToTable()
     {
         String cheminComplet = Database.path+"bd\\tableFields\\";
         File tableFields= new File(cheminComplet);
         if(!(tableFields.isDirectory()))
         {
             tableFields.mkdir();
         }
     }
     
     public boolean createTable(String nombasededata){       
        
        System.out.println("CREER LA TABLE");
        
        Scanner sc = new Scanner(System.in);
        String requete = sc.nextLine();
        String separateur = " ";        
        String[] query = requete.split(separateur);

        int size_tab = query.length;
        String[] = new String[size_tab-5];
        String[] types = new String[(size_tab-5)/2];

        if(!((query[0]+" "+query[1]).equals("CREATE TABLE"))) 
        {

            System.out.println("cette clause nexite pas");
            System.exit(0);

        }

        if(!((query[3]+query[size_tab-1]).equals("()")))
        {
            System.err.println("erreur mettez les espaces entre vos champs");
            System.exit(0);
        }

        for(int i = 4 ;i < (size_tab-1) ;i++)
        {
            champs[i-4] = query[i];
        }       

        int size_tab_champ =.length;

        if(!(size_tab_champ%2 == 0))
        {
            System.out.println("Chaque champ doit avoir  un type");
            System.exit(0);
        }
        
        int indiceOfType = 0;

        for(int k = 0; k < (size_tab_champ-1);k+=2)
        {
            types[indiceOfType] =[k+1];                        
            indiceOfType++;
        }       
        
        
        for (String type : types) 
        {
            if(!(leTypeDeDonnees(type)))
            {
                System.out.println("Error! Le type: "+type+" n'est pas disponible!");
                System.exit(0);
            }
        }       
        
        String cheminComplet = Database.path+"bd\\"+nombasededata+"\\"+query[2]+".xml";
        File table = new File(cheminComplet);

        if(!(table.exists()))
        {
            try {
               
                 
                DocumentBuilderFactory basededataFactory = DocumentBuilderFactory.newInstance();                
                DocumentBuilder dBuilder =basededata.newDocumentBuilder();
                Document doc = dBuilder.newDocument();
                Element root = doc.createElement("table_"+query[2]);
                doc.appendChild(root);  
                String fichierContenatLesChamps = "_table.txt";
                createFiedToTable();
                PrintWriter impression = new PrintWriter(new FileWriter(Database.path+"bd\\tableFields\\"+query[2]+fichierContenatLesChamps));
            
                for(int j = 0;j < .length-1);j=j+2)
                {   
                    impression.println[j]);
                    impression.println[j+1]);               
                }

                impression.flush();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();                
                Transformer transformer = transformerFactory.newTransformer();                
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(cheminComplet));                
                transformer.transform(source, result);                
                System.out.println("La table "+query[2]+" est creee aves succes dans la Base de donnees "+nombasededata);               
                
            } 
            catch (Exception e) 
            {
                 e.printStackTrace();
            }
            return true;
        }          
        else
        {
            System.out.println("Cette table existe deja dans "+Database.path+nombasededata+"/");
            return false;
        }
    }
    

    public boolean leTypeDeDonnees(String typeGiven){
       
       String[] types ={"int","string","double","float","short"};
       for (String var : types) {
           if(var.equals(typeGiven)){
               return true;
           }
       }
       return false;
    }

        
    public void insertDataInTable(String nombasededata,String nomTable)
    {
         
        String cheminComplet = checkFolderAndFile(nombasededata,nomTable);
        
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();         
        
        try {

            int id = 1;
            final DocumentBuilder builder = factory.newDocumentBuilder();  
            final Document document = builder.parse(new File(cheminComplet)); 
            final Element racine = document.getDocumentElement();  
            final Element content  = document.createElement(nomTable);            
            NodeList nList = document.getElementsByTagName(nomTable);

            if(nList.getLength()<1)
            {

                content.setAttribute("id", String.valueOf(1));

            }
            else
            {

                int sizeOfList = nList.getLength();
                int indice = sizeOfList - 1;
                Node nNode = nList.item(indice);
                Element precedanteElement = (Element) nNode;
                String precedanteAttribut = precedanteElement.getAttribute("id");
                id += Integer.valueOf(precedanteAttribut);
                content.setAttribute("id", String.valueOf(id));

            }
            
                        
            racine.appendChild(content);

            BufferedReader flux = new BufferedReader(new FileReader(Database.path+"bd\\tableFields/"+nomTable+"_table.txt"));
            String line;
            int count = 0;
            List<String> fields = new ArrayList<String>();

            while((line = flux.readLine()) != null)
            {
               fields.add(line); 
               count++;               
            }            
            
            String[] champEtType = new String[count];
            String phrase = "";

            for (String ligne : fields) 
            {
                phrase += ligne+" ";
            }

            champEtType = phrase.split(" ");

            for(int j = 0;j < (champEtType.length-1);j=j+2)
            {

                Scanner sc = new Scanner(System.in);
                System.out.println("Entrez le "+champEtType[j]); 
                String value = sc.nextLine();  
                final Element champ = document.createElement(champEtType[j]);
                champ.setAttribute("type", champEtType[j+1]);
                champ.appendChild(document.createTextNode(value));
                content.appendChild(champ); 

                
            }            
            
            
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    
            final Transformer transformer = transformerFactory.newTransformer();

            final DOMSource source = new DOMSource(document);

            final StreamResult sortie = new StreamResult(new File(cheminComplet));           

                    
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, sortie);  

        }

        catch (final ParserConfigurationException e) 
        {

            e.printStackTrace();

        }
        catch (TransformerConfigurationException e) 
        {

            e.printStackTrace();

        }
        catch (TransformerException e) 
        {

            e.printStackTrace();

        }        
        catch (final SAXException e) 
        {

            e.printStackTrace();

        }
        catch (final IOException e) 
        {

            e.printStackTrace();

        }  

    }


    public String checkFolderAndFile(String nombasededata, String nomTable)
    {

        String cheminComplet = Database.path+"bd\\"+nombasededata+"\\"+nomTable+".xml";
        File fichier = new File(cheminComplet);
        File db = new File(Database.path+"bd\\"+nombasededata);

        if(!(db.isDirectory()))
        {

            System.out.println("Cette DB n'existe pas");
            System.exit(0);

        }

        if(!(fichier.exists()))
        {

            System.out.println("Cette table n'existe pas");
            System.exit(0);

        }

        return cheminComplet;
    }

     public String selectData(String nombasededata,String nomTable)
     {
         
         String cheminComplet = checkFolderAndFile(nombasededata,nomTable);

         return "";

     }

    
     public void updateData(String nombasededata, String nomTable)
     {

        String cheminComplet =  checkFolderAndFile(nombasededata,nomTable);

         try {

             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.parse(cheminComplet);
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
             boolean cestVrai = false;
             for (int v = 0; v < nList.getLength(); v++) 
             {
                 Node nNode = nList.item(v);

                 if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                 {
                     Element element = (Element) nNode;
                     String forId = element.getAttribute("id");
                     char idChild = forId.charAt(0);

                    if (idChild == idItem)
                    {
                        cestVrai = true;
                        Node nodeToUpdate = element.getElementsByTagName(field).item(0);
                        nodeToUpdate.setTextContent(value);
                        break;
                    }
                 }
             }
             if(cestVrai)
             {
                 System.out.println("Done");
             }
             else
             {
                 System.out.println("id not found");
             }
             
             ecrireLesContenus(cheminComplet,doc);

         } 
        catch (ParserConfigurationException e) 
        {

             e.printStackTrace();

        }
        catch (SAXException e)
        {

            e.printStackTrace();

        } 
        catch (IOException e) 
        {

            e.printStackTrace();

        }
        
     }
     
     public void deleteDataIndatabase(String nombasededata, String nomTable)
     {
        String cheminComplet = checkFolderAndFile(nombasededata,nomTable);

         try {

             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.parse(cheminComplet);

             final Element data = doc.getDocumentElement();
             NodeList nList = data.getElementsByTagName(nomTable);

             Scanner sc = new Scanner(System.in);
             System.out.println("Entrez l'id de l'element:");
             String idNode = sc.nextLine();
             char idItem = idNode.charAt(0);
             boolean cestVrai = false;
          
             for (int v = 0; v < nList.getLength(); v++) 
             {
                 Node nNode = nList.item(v);
                 if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                 {
                     Element element = (Element) nNode;
                     String forId = element.getAttribute("id");
                     char idChild = forId.charAt(0);
                     if (idChild == idItem)
                     {
                         cestVrai = true;
                         element.getParentNode().removeChild(element);
                         break;
                     }
                 }
             }

             if(cestVrai)
             {

                 System.out.println("Done");

             }
             else
             {

                 System.out.println("id not found");

             }
            
            ecrireLesContenus(cheminComplet,doc);

         } 
         catch (ParserConfigurationException e) 
         {

             e.printStackTrace();

         }
         catch (SAXException e) 
         {

             e.printStackTrace();

         } 
         catch (IOException e) 
         {

             e.printStackTrace();

         }

     }

     public void ecrireLesContenus(String cheminComplet,Document document)
     {

        try
        {
             
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
             Transformer transformer = transformerFactory.newTransformer();
             DOMSource source = new DOMSource(document);
             StreamResult result = new StreamResult(new File(cheminComplet));
             transformer.transform(source, result);

         }
         catch (TransformerException e)
         {

             e.printStackTrace();

         }

     }
     


 }
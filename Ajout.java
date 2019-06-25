import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Comment;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import java.io.File;
import org.xml.sax.SAXException;
import java.io.IOException;
public class Ajout
{
    public void processInsert()
    //public static void main(String [] args)
    {
        Insertion call = new Insertion();
        String[][] rec = call.insert();
        String[] target = rec[0];
        String[] value = rec[1];
        String nomFichier = rec[2][0];
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            String echape = target[0].replace("\"","");
            String echape1 = echape.replace("\"","");
            String echapeValue = value[0].replace("\"","");
            String echapeValue1 = echapeValue.replace("\"","");
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(nomFichier+".xml"));
            final Element racine = document.getDocumentElement();
            final Element personne = document.createElement("indentifiant");
            personne.setAttribute(echape1,echapeValue1);
            for(int i = 1;i <= (target.length)-1;i++)
            {
                String label = target[i].replace("\"","");
                String label1 = label.replace("\"","");
                String donnee = value[i].replace("\"","");
                String donnee1 = donnee.replace("\"","");
                final Element etiquette = document.createElement(label1);
                personne.appendChild(etiquette);
                racine.appendChild(personne);
                etiquette.appendChild(document.createTextNode(donnee1));
            }
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source = new DOMSource(document);
            final StreamResult sortie = new StreamResult(new File(nomFichier+".xml"));
          
             transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
             transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
             transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");

             transformer.setOutputProperty(OutputKeys.INDENT,"yes");
             transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
             
             transformer.transform(source,sortie);;
             System.out.println("La sauvegarde s'est effectue avec succes!!");
        }
        catch(final ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch(final TransformerConfigurationException e)
        {
            e.printStackTrace();
        }
        catch(final TransformerException e)
        {
            e.printStackTrace();
        }
        catch(final SAXException e)
        {
            e.printStackTrace();
        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }
}
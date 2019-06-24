package sgdb.pbreakers.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Comment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import sgdb.relationsJava.engine.core.operation.TableOperation;
import sgdb.relationsJava.engine.persistance.Attribut;
import sgdb.relationsJava.engine.persistance.Database;
import sgdb.relationsJava.engine.persistance.Table;

public class Main extends Application {
    public static void main(String[] args) {
        Database db = new Database("main");
        Table t1 = new Table("etudiant");
        Table t2 = new Table("cour");

        Attribut a1 = new Attribut("nom", Attribut.Type.CHAINE, Attribut.Constraint.UNIQUE);
        Attribut a2 = new Attribut("id", Attribut.Type.ENTIER, Attribut.Constraint.UNIQUE);

        t1.addAttribut(a1);
        t1.addAttribut(a2);

        db.add(t1);
        db.add(t2);

        System.out.println(TableOperation.create(db.getDatabasePath(), t1));
        System.out.println(TableOperation.create(db.getDatabasePath(), t2));
        System.out.println(TableOperation.delete(db.getDatabasePath(), t1));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("view/home.fxml"));

        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setTitle("SGBRR");
        stage.show();
    }
}

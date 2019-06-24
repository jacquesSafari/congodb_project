package home.controller;

import home.model.Kernerl.Database;
import home.model.Kernerl.Field;
import home.model.Kernerl.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;

public class TableETcahamp {

    private final String nom;

    public TableETcahamp(String nom){

        this.nom = nom;

        Stage tab = new Stage();
        Scene createTable,modifieTable;


        TextArea sqlText = new TextArea("CREATE TABLE produit ( nomProduit int idProduit string )");

        Button create = new Button("Creer");
        String requet = sqlText.getText();
        create.setOnAction(e -> createTableField(requet));

        Button modifieTab = new Button("Modifier");
        Button deleteTab = new Button("supprimer");
        VBox box1 = new VBox(10);
        HBox h1 = new HBox(40);
        HBox h2 = new HBox(40);
        HBox h3 = new HBox(40);
        h1.getChildren().addAll(sqlText,create);
        h3.getChildren().addAll(modifieTab,deleteTab);
        TableView<Table> viewTab = new TableView<>();
        TableView<Field> viewField = new TableView<>();



        TableColumn nameTable = new TableColumn("Nom des Table");
        nameTable.setMinWidth(200);
        nameTable.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn nameField = new TableColumn("Nom des Champs");
        nameField.setMinWidth(200);
        nameField.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn typeField = new TableColumn("Type de Champ");
        typeField.setMinWidth(200);
        typeField.setCellValueFactory(new PropertyValueFactory<>("type"));

        box1.getChildren().addAll(h1,viewTab,h3);

        createTable = new Scene(box1,800,300);

        tab.setScene(createTable);
        tab.setTitle("Table et champs");
        tab.show();


    }
    public void createTableField(String request){

        String req = request;
        Table tbl = new Table();
        tbl.createTable(nom,req);

    }


}

package home.controller;

import home.model.Kernerl.Database;
import home.model.Kernerl.Table;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TreeView<?> treeDB;

    @FXML
    private TableView<Database> tblVdb;

    @FXML
    private TextField txtNomDB;

    @FXML
    private Button btnCreerDB;

    @FXML
    private TableColumn<Database, String> tblNomDB;

    @FXML
    private TableColumn<String, Integer> tblNombretable;

    @FXML
    private ComboBox<?> cbxRecherche;

    @FXML
    private Button btnsupDB;

    @FXML
    private Button btnmodifierDB;
    @FXML
    private TextField txtSQL;

    @FXML
    private Button btnCreateSQL;


    private Database db = new Database();
    private ObservableList<Database> maListDB,dbselected,alldbItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         maListDB = db.getAllDB();
        for (Database e : maListDB) {
            String x = e.getNom();
            System.out.println(x);
        }

        tblNomDB.setCellValueFactory(new PropertyValueFactory<>("nom"));
        //tblNombretable.setCellValueFactory(new PropertyValueFactory<>("nbreTable"));
        tblVdb.setItems(maListDB);





    }

    @FXML
    private void handleclickeds(ActionEvent click){

        if(click.getSource() == btnCreerDB){
            String nDB = txtNomDB.getText();
            db.createDB("CREATE DATABASE "+nDB);

            maListDB= db.getAllDB();
            tblNomDB.setCellValueFactory(new PropertyValueFactory<>("nom"));
            //tblNombretable.setCellValueFactory(new PropertyValueFactory<>("nbreTable"));
            tblVdb.setItems(maListDB);
            tblVdb.getItems().add(db);
            txtNomDB.clear();



        }
        else if(click.getSource() == btnsupDB){

            alldbItems = tblVdb.getItems();
            maListDB = db.getAllDB();
            dbselected = tblVdb.getSelectionModel().getSelectedItems();
            int index = tblVdb.getSelectionModel().getSelectedIndex();

            Database myDb = alldbItems.get(index);

            dbselected.forEach(alldbItems::remove);

            for(Database d : maListDB){d.dropMDB(myDb.getNom());}



        }
        else if(click.getSource() == btnmodifierDB){

            alldbItems = tblVdb.getItems();
            maListDB = db.getAllDB();
            dbselected = tblVdb.getSelectionModel().getSelectedItems();
            int index = tblVdb.getSelectionModel().getSelectedIndex();

            Database myDb = alldbItems.get(index);

            //dbselected.forEach(alldbItems::remove);
            TableETcahamp view1 = new TableETcahamp(myDb.getNom());














        }
        else if(click.getSource() == btnCreateSQL){
            String nDB = txtSQL.getText();
            db.createDBSQL(nDB);
            maListDB= db.getAllDB();
            tblNomDB.setCellValueFactory(new PropertyValueFactory<>("nom"));
            //tblNombretable.setCellValueFactory(new PropertyValueFactory<>("nbreTable"));
            tblVdb.setItems(maListDB);
            tblVdb.getItems().add(db);
            txtSQL.clear();


        }


    }

    public void createTable(){

    }




}


























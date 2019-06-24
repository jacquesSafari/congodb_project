/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mysgbdfxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.behavior.ListViewBehavior;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ListView;

/**  
 *
 * @author ANGELO
 */
public class FXMLDocumentController implements Initializable {
   
//    @FXML
//    private void handleButtonAction(MouseEvent event) {
//        System.out.println("You clicked me!");
//
//        if(event.getTarget() == btnLogin){
//            System.out.println("login nnnn");
//            homePanel.setVisible(true);
//            loginPanel.setVisible(false);
//        }
//    }
    
    
    @FXML Pane loginPane = new Pane();
    @FXML Pane homePane,panel_tablecreee = new Pane();
    @FXML Pane mesbd = new Pane();
    @FXML Pane add_bd = new Pane();
    @FXML ImageView deconnexion=new ImageView();
    @FXML TextField user = new TextField();
    @FXML PasswordField pwd =new PasswordField();
    @FXML Button mesbd_retour,mesbd_supprimer =new Button();
    @FXML Button retour_in_add_bd =new Button();
    @FXML Button creer_in_add_bd, btn_nouvelle_oui,btn_nouvelle_non =new Button();
    @FXML TextField txt_nombd = new TextField();
    @FXML Pane panel_bdexiste, panel_bdcreer = new Pane();
    @FXML Label txt_bdexiste,txt_bdcreer = new Label();
    @FXML TextField txt_nomtable,txt_champ1,txt_champ2,txt_champ3,txt_champ4,txt_champ5 = new TextField();
    @FXML TextField txt_type1,txt_type2,txt_type3,txt_type4,txt_type5 =new TextField();
    @FXML Pane panel_addchamp = new Pane();
    @FXML TableColumn<Nouveautable,String> colon_A =new TableColumn<>();
    @FXML TableView table_view =new TableView();



    /**
     *
     * @throws Exception
     */
    String bd;
    @FXML
    public void connexion() throws Exception 
    {
        String log = user.getText();
        String mdp = pwd.getText();
        if("a".equals(log) && "a".equals(mdp)){
            homePane.setVisible(true);
            loginPane.setVisible(false);
        }
        else{
            System.out.println("mysgbdfxml.FXMLDocumentController.deconnexion()");
        }
       
    }
    @FXML
    public void deconnexion() throws Exception 
    {
        homePane.setVisible(false);
        loginPane.setVisible(true);
       
    }
    @FXML
    public void mesbd_retour() throws Exception 
    {
        homePane.setVisible(true);
        mesbd.setVisible(false);
       
    }
    @FXML
    public void mesbd_supprimer() throws Exception
    {
        ObservableList <Nouveautable> bon, bok,bos;
        Nouveautable bbbb;


        bon=GestionTable.afficheTout();
        bok = table_view.getItems();
        bos = table_view.getSelectionModel().getSelectedItems();

        int index = table_view.getSelectionModel().getSelectedIndex();
        bbbb= bok.get(index);
        bos.forEach(bok::remove);
        GestionTable.supprimerBaseDesDonnes(bbbb.getNom());

//        for(Nouveautable d : bon){
//            System.out.println(bbbb.getNom());}

//        ObservableList dbselected = table_view.getSelectionModel().getSelectedItems();
//
//        table_view.setItems(b);
//        table_view.getItems().addAll();
//
//        homePane.setVisible(true);
//        mesbd.setVisible(false);
//
//
//        alldbItems = tblVdb.getItems();
//        maListDB = db.getAllDB();
//        dbselected = tblVdb.getSelectionModel().getSelectedItems();
//        int index = tblVdb.getSelectionModel().getSelectedIndex();
//
//        Database myDb = alldbItems.get(index);
//
//        dbselected.forEach(alldbItems::remove);
//
//        for(Database d : maListDB){d.dropMDB(myDb.getNom());}
//



    }
    @FXML
    public void mesbd() throws Exception
    {
        ObservableList <Nouveautable> b=GestionTable.afficheTout();
        for (Nouveautable e : b){
            String x = e.getNom();
            System.out.println(x+"OKKKKOKOKOK");
        }
        colon_A.setCellValueFactory(new PropertyValueFactory<>("nom"));
        table_view.setItems(b);
        table_view.getItems().addAll();
        homePane.setVisible(false);
        mesbd.setVisible(true);

//
//        String nDB = txtNomDB.getText();
//                    db.createDB("CREATE DATABASE "+nDB);
//
//            maListDB= db.getAllDB();
//                    tblNomDB.setCellValueFactory(new PropertyValueFactory<>("nom"));
//            //tblNombretable.setCellValueFactory(new PropertyValueFactory<>("nbreTable"));
//                    tblVdb.setItems(maListDB);
//                    tblVdb.getItems().add(db);
//                    txtNomDB.clear();

    }
    @FXML
    public void   bd_tablecreer_oui() throws Exception
    {
        panel_tablecreee.setVisible(false);

    }
    @FXML
    public void   bd_tablecreer_non() throws Exception
    {
        panel_addchamp.setVisible(false);
        panel_tablecreee.setVisible(false);
        mesbd.setVisible(true);

    }

    @FXML
    public void new_bd() throws Exception 
    {
        homePane.setVisible(false);
        add_bd.setVisible(true);
       
    }

    @FXML
    public void bd_existe() throws Exception 
    {
        panel_bdexiste.setVisible(false);
        
       
    }
    
    @FXML
    public void retour_in_add_bd() throws Exception 
    {
        add_bd.setVisible(false);
        homePane.setVisible(true);
        
       
    }
    @FXML
    public void creer_in_add_bd() throws Exception 
    {
        bd=txt_nombd.getText();
        if (GestionTable.verifierBaseDesdonnees(bd)){
            txt_bdexiste.setText("La base de donnees "+bd+" existe !");
            panel_bdexiste.setVisible(true);
            System.out.println("bd existe");
            
        
        }else{
        
        txt_bdcreer.setText("La base de donnees "+bd+" a été créee !");
        panel_bdcreer.setVisible(true);
        GestionTable.creationBaseDesDonnees(bd);
        System.out.println("bd creee");
        
        };
       
    }
    
    @FXML
    public void affiche_ajoute_champ(){
        panel_bdcreer.setVisible(false);
        panel_addchamp.setVisible(true);
    
    }
    
    @FXML
    public void ajoute_table() throws Exception 
    {
        bd="C:\\Users\\ANGELO\\Documents\\NetBeansProjects\\mySGBDfxml\\bd\\"+bd+".json";
        Nouveautable table = new Nouveautable(txt_nomtable.getText());
        Champs champ1 =new Champs(txt_champ1.getText(), txt_type1.getText());
        Champs champ2 =new Champs(txt_champ2.getText(), txt_type2.getText());
        Champs champ3 =new Champs(txt_champ3.getText(), txt_type3.getText());
        Champs champ4 =new Champs(txt_champ4.getText(), txt_type4.getText());
        Champs champ5 =new Champs(txt_champ5.getText(), txt_type5.getText());
        table.addChamp(champ1,champ2, champ3,champ4,champ5);
        System.out.println(bd);
        ConversionStringToJson.serialisation(table,bd);

        
        System.out.println("donees ajouter avec success");
        //homePane.setVisible(false);
        panel_tablecreee.setVisible(true);
       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    
    
}

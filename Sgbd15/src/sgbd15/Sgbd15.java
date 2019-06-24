/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd15;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sgbd15.Kernerl.*;
/**
 *
 * @author elsa
 */
public class Sgbd15 {


    public static void main(String[] args) {
        
        //Database db = new Database();
        //boolean is_created = db.createDB();
        /*if(is_created){
            System.out.println("Enfin ca marhe");
        }
        else{
            System.out.println("ca marche pas !!");
        }*/
        Table tb = new Table();
        tb.createTable("elsa");
        tb.insertData("user","enfant");
        //tb.updateData("user","enfant");
        //tb.deleteData("user","enfant");
        //launch(args);

    }
    
}

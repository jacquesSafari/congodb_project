package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Kernerl.*;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*
        Database db = new Database();
        boolean is_created = db.createDB();
        if(is_created){
            System.out.println("C'est fait");
        }
        else{
            System.out.println("Not done");
        }
        //Table tb = new Table();
       // tb.insertData("ville","commune");*/
        Table tb = new Table();
       // tb.createTable("maison");
      //  tb.insertData("maison","enfant");
        tb.updateData("maison","enfant");
        //tb.deleteData("maison","enfant");
        //launch(args);

    }
}

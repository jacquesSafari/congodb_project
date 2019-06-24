package sgdb.pbreakers.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

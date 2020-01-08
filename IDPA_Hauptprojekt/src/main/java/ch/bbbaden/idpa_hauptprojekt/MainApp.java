package ch.bbbaden.idpa_hauptprojekt;

import ch.bbbaden.idpa_hauptprojekt.Datatransfer.Database;
import static ch.bbbaden.idpa_hauptprojekt.Datatransfer.Database.createNewDatabase;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createNewDatabase();
        Database d = new Database();
        d.createDBStructure();
        Brain.getInstance().setDt(d);
        Brain.getInstance().currentUsername = Brain.getInstance().getDt().getLastLogin();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Login Wirtschaftsquiz");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        launch(args);
    }

}

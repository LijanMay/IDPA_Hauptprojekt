package ch.bbbaden.idpa_hauptprojekt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginTeacher.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        window.setTitle("Eingeloggt");
        window.setScene(scene);

        window.show();

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLRegister.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        window.setTitle("Registrierung");
        window.setScene(scene);

        window.show();

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}

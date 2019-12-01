package ch.bbbaden.idpa_hauptprojekt;

import ch.bbbaden.idpa_hauptprojekt.Datatransfer.Textfile;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatatransferInterface d = new Textfile();
        Brain.getInstance().setDt(d);
     }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        boolean correctLogin = false;
        //Status 0 = Lehrer 1 = Schüler
        int status;
        //Mit Klasse und liste oder nur Liste arbeiten tendenzielle nur Liste
        for(int i = 0; i < users.length();i++){
            if(users.username == username.getText()|| users.email == username.getText()){
                if(users.password == password.getText())
                    correctLogin = true;
                break;
            }
        }
        if(correctLogin){
           status = users.getStatus;
        }
        
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoggedInTeacher.fxml"));

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
        setHide(true);
    }

    public void setHide(boolean hide) {
        if (hide) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.hide();
        } else {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.show();
        }

    }
}

package ch.bbbaden.idpa_hauptprojekt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorLabel;
    @FXML
    private Label WIQI;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Brain.getInstance().setController(this);
        username.setText(Brain.getInstance().currentUsername);
    }

    private ArrayList<HashMap<String, String>> users = new ArrayList<>();

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        try {
            users = Brain.getInstance().getDt().getUser();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Status 0 = Lehrer 1 = Schüler
        int status = 3;
        //Mit Klasse und liste oder nur Liste arbeiten tendenzielle nur Liste
        for (int i = 0; i < users.size(); i++) {
            System.out.println("prename: " + users.get(i).get("prename"));
            System.out.println(users.get(i).get("surname"));
            if (users.get(i).get("username").equals(username.getText()) || users.get(i).get("email").equals(username.getText())) {
                System.out.println(password.getText());
                if (users.get(i).get("password").equals(password.getText())) {
                    status = Integer.parseInt(users.get(i).get("status"));
                }
            }
        }

        password.clear();
        Brain.getInstance().currentUsername = username.getText();
        //für Testzwecke
        if (username.getText().equals("admin")) {
            status = 0;
        } else if (username.getText().equals("user")) {
            status = 1;
        }

        if (status == 0) {
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoggedInTeacher.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            window.setTitle("Lehrer");
            window.setScene(scene);
            Brain.getInstance().setLoggedInLehrer(window);
            window.setOnCloseRequest(event1 -> {
                handleCloseTeacher();
                event1.consume();
                //   event.consume();
            });

            window.show();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        } else if (status == 1) {
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoggedInSchueler.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            window.setTitle("Schüler");
            window.setScene(scene);

            Brain.getInstance().setLoggedInStudent(window);
            window.setOnCloseRequest(event1 -> {
                handleCloseStudent();
                event1.consume();
                //   event.consume();
            });

            window.show();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        } else {
            errorLabel.setText("Benutzername und oder Passwort falsch");
            errorLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        password.clear();
        username.clear();

        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLRegister.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        window.setTitle("Registrierung");
        window.setScene(scene);

        window.setOnCloseRequest(event1 -> {
            Brain.getInstance().hideController(false);
            //   event.consume();
        });

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
            username.setText(Brain.getInstance().currentUsername);
        }

    }

    public void handleCloseTeacher() {
        String[] options = new String[]{"Ausloggen", "Beenden", "Abbrechen"};
        int response = JOptionPane.showOptionDialog(null, "Wie wollen Sie Beenden?", "Beenden",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            Brain.getInstance().closeLoggedInLehrer();
            Brain.getInstance().hideController(false);
        } else if (response == 1) {
            Brain.getInstance().closeLoggedInLehrer();
        }
    }

    public void handleCloseStudent() {
        String[] options = new String[]{"Ausloggen", "Beenden", "Abbrechen"};
        int response = JOptionPane.showOptionDialog(null, "Wie wollen Sie Beenden?", "Beenden",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            Brain.getInstance().closeLoggedInStudent();
            Brain.getInstance().hideController(false);
        } else if (response == 1) {
            Brain.getInstance().closeLoggedInStudent();
        }
    }

    public void handleLogoutStudent() {
        String[] options = new String[]{"Ausloggen", "Abbrechen"};
        int response = JOptionPane.showOptionDialog(null, "Wollen Sie sich wirklich ausloggen?", "Logout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            Brain.getInstance().closeLoggedInStudent();
            Brain.getInstance().hideController(false);
        }
    }

    public void handleLogoutTeacher() {
        String[] options = new String[]{"Ausloggen", "Abbrechen"};
        int response = JOptionPane.showOptionDialog(null, "Wollen Sie sich wirklich ausloggen?", "Logout",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0) {
            Brain.getInstance().closeLoggedInLehrer();
            Brain.getInstance().hideController(false);
        }
    }
}

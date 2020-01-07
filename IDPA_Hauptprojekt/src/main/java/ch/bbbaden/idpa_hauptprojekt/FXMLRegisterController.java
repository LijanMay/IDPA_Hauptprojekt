/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author denni
 */
public class FXMLRegisterController implements Initializable {

    @FXML
    private Button register;
    @FXML
    private Button cancel;
    @FXML
    private Label WIQI;
    @FXML
    private TextField user;
    @FXML
    private TextField pswd;
    @FXML
    private TextField pswd2;
    @FXML
    private TextField prename;
    @FXML
    private TextField mail;
    @FXML
    private TextField surname;
    @FXML
    private RadioButton rbTeacher;
    @FXML
    private RadioButton rbStudent;

    //Variablen
    private boolean isStudent = true;
    int length;
    private boolean errors = true;
    ArrayList<HashMap<String, String>> t = new ArrayList<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup group = new ToggleGroup();
        rbStudent.setToggleGroup(group);
        rbStudent.setSelected(true);
        rbTeacher.setToggleGroup(group);
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        length = pswd.getText().length();
        //t.add(Brain.getInstance().getDt().getUser());

        filter();

        if (errors) {
        } else if (!errors) {
            filter();
            Brain.getInstance().getDt().createUser(user.getText(), surname.getText(), prename.getText(), mail.getText(), pswd.getText(), isStudent);
            Brain.getInstance().currentUsername = user.getText();
            Brain.getInstance().hideController(false);
            Stage st = (Stage) surname.getScene().getWindow();
            st.close();
        }
    }

    private void showAlert(String trigger) {
        if (null != trigger) {
            switch (trigger) {
                case "empty":
                    JOptionPane.showMessageDialog(null, "Alle Pflichtfelder müssen ausgefüllt werden");
                    break;
                case "mail":
                    JOptionPane.showMessageDialog(null, "Die Email entspricht nicht den Voraussetzungen");
                    break;
                case "existinguser":
                    JOptionPane.showMessageDialog(null, "Der Benutzername existiert bereits");
                    break;
                case "existingmail":
                    JOptionPane.showMessageDialog(null, "Die eingegebene Email wird bereits verwendet");
                    break;
                case "length":
                    JOptionPane.showMessageDialog(null, "Das Passwort muss mehr als 5 Zeichen beinhalten");
                    break;
                case "confirmpassword":
                    JOptionPane.showMessageDialog(null, "Passwörter stimmen nicht überein");
                    break;
                default:
                    break;
            }
        }
    }

    private void filter() {
        String[] fields = {prename.getText(), surname.getText(), user.getText(), mail.getText(), pswd.getText(), pswd2.getText()};
        boolean noerror = true;
        for (String field : fields) {
            if (field.isEmpty()) {
                showAlert("empty");
                noerror = false;
                break;
            }
            if (!isValid(mail.getText())) {
                showAlert("mail");
                noerror = false;
                break;
            }
            for (int i = 0; i < t.size(); i++) {
                System.out.println(t.get(i).get("username"));
                if (t.get(i).get("username").equals(user.getText())) {
                    showAlert("existinguser");
                    noerror = false;
                }
                if (t.get(i).get("mail").equals(mail.getText())) {
                    showAlert("existingmail");
                    noerror = false;
                }
            }

            if (length < 6) {
                showAlert("length");
                noerror = false;
                break;
            }
            if (pswd2.getText().equals(pswd.getText()) == false) {
                showAlert("confirmpassword");
                noerror = false;
                break;
            }
        }
        if (noerror) {
            errors = false;
        }
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        Brain.getInstance().hideController(false);
        Stage st = (Stage) surname.getScene().getWindow();
        st.close();
    }

    private void username() {
        user.setText(surname.getText() + "." + prename.getText());
    }

    @FXML
    private void handleWritesname(KeyEvent event) {
        username();
    }

    @FXML
    private void handleWritepname(KeyEvent event) {
        username();
    }

    @FXML
    private void handleTeacher(ActionEvent event) {
        isStudent = false;
    }

    @FXML
    private void handleStudent(ActionEvent event) {
        isStudent = true;
    }

}

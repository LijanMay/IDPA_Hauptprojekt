/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author denni
 */
public class FXMLRegisterController implements Initializable {

    private String pname;
    private String sname;
    private String username;
    private String email;
    private String password;
    private String confirmpassword;
    private String autouser;
    boolean confirmcorrect = true;
    boolean isempty = false;

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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void handleRegister(ActionEvent event) {
        pname = prename.getText();
        sname = surname.getText();
        username = user.getText();
        email = mail.getText();
        password = pswd.getText();
        confirmpassword = pswd2.getText();

        boolean namecorrect = true;
        boolean passcorrect = true;

        if (surname.getText().isEmpty()) {
            isempty = true;
            showAlert("Name");
        } else if (surname.getText().length() > 20) {
            showAlert("Name");
        } else if (prename.getText().isEmpty()) {
            isempty = true;
            showAlert("Vorname");
        } else if (prename.getText().length() > 20) {
            showAlert("Vorname");
        } else if (user.getText().isEmpty()) {
            isempty = true;
            showAlert("Benutzername");
        } else if (user.getText().length() > 20) {
            showAlert("Benutzername");
        } else if (mail.getText().isEmpty()) {
            isempty = true;
            showAlert("Email");
        } else if (mail.getText().length() > 30) {
            showAlert("Email");
        } else if (pswd.getText().isEmpty()) {
            isempty = true;
            showAlert("Passwort");
        } else if (pswd.getText().length() > 100) {
            showAlert("Passwort");
        }

        if (confirmpassword.equals(password) == false) {
            confirmcorrect = false;
            showAlert("Passwort");
        }
//        try {
//            sa.InitSocket("84.74.61.42", 1757);
//            sa.send("register:" + namefield.getText() + ";" + BCrypt.hashpw(passwordfield.getText(), BCrypt.gensalt(12)));// send username and hashed password to server
//            check();
//        } catch (IOException ex) {
//            System.out.println("Not connected to server! Please try using a VPN.");
//        }
    }

    private void showAlert(String name) {
        if (isempty = true) {
            JOptionPane.showMessageDialog(null, name + " muss ausgefüllt werden");
            isempty = false;
        } else {
            JOptionPane.showMessageDialog(null, name + " entspricht nicht den Voraussetzungen");
        }
        if (confirmcorrect = false) {
            JOptionPane.showMessageDialog(null, name + " stimmt nicht überein");
        }

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

}

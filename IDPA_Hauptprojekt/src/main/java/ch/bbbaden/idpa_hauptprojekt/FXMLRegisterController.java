/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private boolean isTeacher = false;
    int length;

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
        pname = prename.getText();
        sname = surname.getText();
        username = user.getText();
        email = mail.getText();
        password = pswd.getText();
        confirmpassword = pswd2.getText();
        length = pswd.getText().length();

        filter();

        Brain.getInstance().getDt().createUser(username, sname, pname, email, password, isTeacher);
    }

    private void showAlert(String trigger) {
        if (trigger == "length") {
            JOptionPane.showMessageDialog(null, "Das Passwort muss mehr als 5 Zeichen beinhalten");
        } else if (trigger == "confirmpassword") {
            JOptionPane.showMessageDialog(null, "Passwörter stimmen nicht überein");
        } else if (trigger == "empty") {
            JOptionPane.showMessageDialog(null, "Alle Pflichtfelder müssen ausgefüllt werden");
        }
    }

    private void filter() {
        String[] fields = {prename.getText(), surname.getText(), user.getText(), mail.getText(), pswd.getText(), pswd2.getText()};

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isEmpty()) {
                showAlert("empty");
                break;
            }
            if (length < 6) {
                showAlert("length");
                break;
            }
            if (confirmpassword.equals(password) == false) {
                showAlert("confirmpassword");
                break;
            }
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


    @FXML
    private void handleTeacher(ActionEvent event) {
        isTeacher = true;
    }

    @FXML
    private void handleStudent(ActionEvent event) {
        isTeacher = false;
    }

}

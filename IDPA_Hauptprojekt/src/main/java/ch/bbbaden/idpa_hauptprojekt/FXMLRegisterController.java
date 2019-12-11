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

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

/**
 * FXML Controller class
 *
 * @author denni
 */
public class LoginTeacherController implements Initializable {

    @FXML
    private Button chooseTopic;
    @FXML
    private Button newTopic;
    @FXML
    private Button expandTopic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("test");
    }    

    @FXML
    private void handleChooseTopic(ActionEvent event) {
    }

    @FXML
    private void handleNewTopic(ActionEvent event) {
    }

    @FXML
    private void handleExpandTopic(ActionEvent event) {
    }
    
}

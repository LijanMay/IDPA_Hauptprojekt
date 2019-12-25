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
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author denni
 */
public class LoggedInSchuelerController implements Initializable {

    @FXML
    private ListView<String> listQuizes;
    @FXML
    private Button startQuiz;
    @FXML
    private Button insertQuiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void handleStartQuiz(ActionEvent event) {
    }

    @FXML
    private void handleInsertQuiz(ActionEvent event) {
        Brain.getInstance().getDt().insertQuiz();
    }
    
}

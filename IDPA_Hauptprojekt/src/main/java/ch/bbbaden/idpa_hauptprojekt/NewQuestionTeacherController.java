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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author natalie1703
 */
public class NewQuestionTeacherController implements Initializable {

    @FXML
    private Button createQuestion;
    @FXML
    private RadioButton multipleChoice;
    @FXML
    private RadioButton insertAnswer;
    @FXML
    private RadioButton trueFalse;
    @FXML
    private TextArea textarea;
    @FXML
    private Spinner<Integer> questionSpinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hanldeCreateQuestion(ActionEvent event) {
        // Frage mit eigenem Neuen Fenster erstellen mit for loop für anzahl mulitple choice fragen etc
        
    }

    @FXML
    private void handleMultipleChoice(ActionEvent event) {
    }

    @FXML
    private void hanldeInserAnswer(ActionEvent event) {
    }

    @FXML
    private void handleTrueFalse(ActionEvent event) {
    }
    
}

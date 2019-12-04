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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    private Label WIQI;
    @FXML
    private Spinner<Integer> spinner;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideButton(false);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 3);
        spinner.setValueFactory(valueFactory);
        spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
    }    

    @FXML
    private void hanldeCreateQuestion(ActionEvent event) {
        // Frage mit eigenem Neuen Fenster erstellen mit for loop für anzahl mulitple choice fragen etc
       if(textarea.getText().equals("")){
           System.out.println("yes");
       }else{
           System.out.println("fuck");
       }
    }

    @FXML
    private void handleMultipleChoice(ActionEvent event) {
        insertAnswer.setSelected(false);
        trueFalse.setSelected(false);
        hideButton(true);
    }

    @FXML
    private void hanldeInserAnswer(ActionEvent event) {
        multipleChoice.setSelected(false);
        trueFalse.setSelected(false);
        hideButton(true);
    }

    @FXML
    private void handleTrueFalse(ActionEvent event) {
        insertAnswer.setSelected(false);
        multipleChoice.setSelected(false);
        hideButton(true);
    }
    
    private void hideButton(boolean hide){
        createQuestion.setVisible(hide);
    }
    
}

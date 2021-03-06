/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dennis
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
    private String topic;
    private ArrayList<String> answers = new ArrayList<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        topic = Brain.getInstance().getLit().getChoosenTopic();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 3);
        spinner.setValueFactory(valueFactory);
        spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
    }
    
    @FXML
    private void hanldeCreateQuestion(ActionEvent event) {
        //Brain.getInstance().getDt().addQuestion(String, int, ArrayList) String ist die Frage int 0 = multiple choice frage; int 1 = true false frage
        //int 2 = Frage mit eingabe vom Benutzer int 3 = irgendwas ging schief
        boolean close = false;
        if (textarea.getText().trim().equals("")) {
        } else {
            if (multipleChoice.selectedProperty().get()) {
                answers.clear();
                boolean writeInDB = true;
                String dialog = "Geben Sie die richtige Antwort ein.";
                for (int i = 0; i < spinner.getValue(); i++) {
                    if (writeInDB == false) {
                        break;
                    }
                    String input;
                    if (i != 0) {
                        dialog = "Geben Sie eine falsche Antwort ein";
                    }
                    do {
                        input = JOptionPane.showInputDialog(null, dialog,
                                "Antwort",
                                JOptionPane.PLAIN_MESSAGE);
                        if (input == null) {
                            writeInDB = false;
                            break;
                        }
                    } while (input.trim().equals(""));
                    answers.add(input);
                }
                if (writeInDB) {
                    Brain.getInstance().getDt().addQuestion(textarea.getText(), 0, answers, topic);
                    close = true;
                }
                
            } else if (trueFalse.selectedProperty().get()) {
                answers.clear();
                boolean writeInDB = true;
                for (int i = 0; i < 2; i++) {
                    if (writeInDB == false) {
                        break;
                    }
                    String input;
                    String dialog1 = "Geben Sie die richtige Antwort ein.";
                    if (i != 0) {
                        dialog1 = "Geben Sie die falsche Antwort ein";
                    }
                    do {
                        input = JOptionPane.showInputDialog(null, dialog1,
                                "Antwort",
                                JOptionPane.PLAIN_MESSAGE);
                        if (input == null) {
                            writeInDB = false;
                            break;
                        }
                    } while (input.trim().equals(""));
                    answers.add(input);
                }
                if (writeInDB) {
                    Brain.getInstance().getDt().addQuestion(textarea.getText(), 1, answers, topic);
                    close = true;
                }
            } else if (insertAnswer.selectedProperty().get()) {
                answers.clear();
                String input;
                do {
                    input = JOptionPane.showInputDialog(null, "Geben Sie die richtige Antwort ein.",
                            "Antwort",
                            JOptionPane.PLAIN_MESSAGE);
                    if (input == null) {
                        break;
                    }
                } while (input.trim().equals(""));
                if (input != null) {
                    answers.add(input);
                    Brain.getInstance().getDt().addQuestion(textarea.getText(), 2, answers, topic);
                    close = true;
                }
                
            }
        }
        if (close) {
            ArrayList<String> test = new ArrayList<>();
            try {
                test = Brain.getInstance().getDt().getQuestionsToTopic(topic);
                System.out.println(test);
            } catch (SQLException ex) {
                Logger.getLogger(NewQuestionTeacherController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = (Stage) createQuestion.getScene().getWindow();
            stage.close();
            Brain.getInstance().getLit().setHide(false);
        }
    }
    
    @FXML
    private void handleMultipleChoice(ActionEvent event) {
        insertAnswer.setSelected(false);
        trueFalse.setSelected(false);
    }
    
    @FXML
    private void hanldeInserAnswer(ActionEvent event) {
        multipleChoice.setSelected(false);
        trueFalse.setSelected(false);
    }
    
    @FXML
    private void handleTrueFalse(ActionEvent event) {
        insertAnswer.setSelected(false);
        multipleChoice.setSelected(false);
    }
    

    
}

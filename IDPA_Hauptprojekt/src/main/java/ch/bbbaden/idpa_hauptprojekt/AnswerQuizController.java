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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dennis
 */
public class AnswerQuizController implements Initializable {

    @FXML
    private ListView<String> listRounds;
    @FXML
    private Button nextQuestion;
    @FXML
    private Label questionCount;
    @FXML
    private Label correctlySolvedQuestions;
    @FXML
    private Label incorrectlySolvedQuestions;
    private ArrayList<String[]> questions = new ArrayList<>();
    private ArrayList<String[]> finishedQuestions = new ArrayList<>();
    private ArrayList<String[]> wrongFinishedQuestions = new ArrayList<>();
    private boolean quizStarted = false;
    private int question = 0;
    private int solvedCorrectly = 0;
    private int solvedIncorrectly = 0;
    private boolean firstRound = true;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Brain.getInstance().setAqc(this);
        questions = Brain.getInstance().getDt().getQuestionsforQuiz(Brain.getInstance().getLis().getCurrentQuiz());
        question = questions.size();
        questionCount.setText("Noch zu beantwortende Fragen: " + question);

    }

    @FXML
    private void handleNextQuestion(ActionEvent event) {
        if (quizStarted) {
            askQuestion();
        }

    }

    @FXML
    private void handleRedoWholeQuiz(ActionEvent event) {
        if (quizStarted) {
            askQuestion();
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Wollen Sie das Quiz abbrechen? \nIhr Fortschritt geht dabei verloren.", "Abbrechen",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (input == 0) {
            Brain.getInstance().hideLis(false);
            Stage stage = (Stage) nextQuestion.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void handleStartQuiz(ActionEvent event) {
        quizStarted = true;
        askQuestion();
    }

    private void askQuestion() {
        boolean solvedcorrectly = false;
        try {
            String[] s = questions.get(0);

            if (solvedcorrectly) {
                solvedCorrectly += 1;                
                finishedQuestions.add(s);
                questions.remove(0);
            } else {
                solvedIncorrectly += 1;
                finishedQuestions.add(s);
                wrongFinishedQuestions.add(s);
                questions.remove(0);
            }
            updateQuestionCount();
        } catch (Exception e) {
            firstRound = false;
            String[] options = new String[]{"Falsch beantwortete Fragen wiederholen", "Alle Fragen wiederholen", "Quiz Beenden"};
            int response = JOptionPane.showOptionDialog(null, "Wollen Sie sich wirklich ausloggen?", "Logout",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response == 0) {
                questions = wrongFinishedQuestions;
            } else if (response == 1) {
                questions = finishedQuestions;
            } else {
                Brain.getInstance().hideLis(false);
                Stage stage = (Stage) nextQuestion.getScene().getWindow();
                stage.close();
            }
        }

    }

    private void updateQuestionCount() {
        int toSolve = question - solvedCorrectly - solvedIncorrectly;
        questionCount.setText("Noch zu beantwortende Fragen: " + toSolve);
        correctlySolvedQuestions.setText("" + solvedCorrectly);
        incorrectlySolvedQuestions.setText("" + solvedIncorrectly);
    }
}

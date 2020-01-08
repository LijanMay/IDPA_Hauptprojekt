/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
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
    private ObservableList<String> items;
    private ArrayList<String> rounds = new ArrayList<>();
    private int round = 0;

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
            int input = JOptionPane.showConfirmDialog(null, "Wollen Sie das Quiz abbrechen? \nIhr Fortschritt geht dabei verloren.", "Abbrechen",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (input == 0) {
                for (int i = 0; i < finishedQuestions.size(); i++) {
                    questions.add(finishedQuestions.get(i));
                }
                finishedQuestions.clear();
                wrongFinishedQuestions.clear();
                quizStarted = false;
            }
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
        if (quizStarted) {

        } else {
            quizStarted = true;
            askQuestion();
        }
    }

    private void askQuestion() {
        boolean solvedcorrectly = false;
        try {
            String[] s = questions.get(0);
            switch (s[0].toLowerCase()) {
                case "satzantwort":
                    String eingabe = JOptionPane.showInputDialog(null, s[1],
                            "Frage",
                            JOptionPane.PLAIN_MESSAGE);
                    if (eingabe.trim().toLowerCase().equals(s[2].toLowerCase())) {
                        solvedcorrectly = true;
                    }
                    break;
                case "richtigfalsch":
                    String[] options1 = new String[]{s[2], s[3]};
                    int response = JOptionPane.showOptionDialog(null, s[1], "Frage",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options1, options1[0]);
                    System.out.println(response);
                    if (response == 0) {
                        solvedcorrectly = true;
                    }

                    break;
                case "multiplechoice":
                    //randomize Questions
                    int ca = 0;
                    boolean first0 = true;
                    ArrayList<String> ques = new ArrayList<>();
                    for (int d = 0; d < s.length - 2; d++) {
                        ques.add(s[d]);
                    }
                    String[] options = new String[ques.size()];
                    for (int i = ques.size(); i >= 0; i--) {
                        Random rnd = new Random();
                        int get = rnd.nextInt(i);
                        options[i] = ques.get(get);
                        ques.remove(get);
                        if (get == 0) {
                            if (first0) {
                                ca = i;
                            }
                        }
                    }

                    int response2 = JOptionPane.showOptionDialog(null, s[1], "Frage",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options, options[0]);

                    if (response2 == ca) {
                        solvedcorrectly = true;
                    }

                    break;
                default:
                    throw new AssertionError();
            }

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
            round++;
            rounds.add("Runde " + round + ": Richtig beantwortet: " + solvedCorrectly + " | Falsch beantwortet: " + solvedIncorrectly);
            items = FXCollections.observableArrayList(rounds);
            listRounds.setItems(items);

            firstRound = false;
            String[] options = new String[]{"Falsch beantwortete Fragen wiederholen", "Alle Fragen wiederholen", "Quiz Beenden"};
            int response = JOptionPane.showOptionDialog(null, "Wollen Sie sich wirklich ausloggen?", "Logout",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            quizStarted = false;
            solvedCorrectly = 0;
            solvedIncorrectly = 0;

            if (response == 0) {
                for (int i = 0; i < wrongFinishedQuestions.size(); i++) {
                    questions.add(wrongFinishedQuestions.get(i));
                }
                wrongFinishedQuestions.clear();
                question = questions.size();
            } else if (response == 1) {
                for (int i = 0; i < finishedQuestions.size(); i++) {
                    questions.add(finishedQuestions.get(i));
                }
                finishedQuestions.clear();
                question = questions.size();
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
        correctlySolvedQuestions.setText("Richtig beantwortet: " + solvedCorrectly);
        incorrectlySolvedQuestions.setText("Falsch beantwortet: " + solvedIncorrectly);
    }

}

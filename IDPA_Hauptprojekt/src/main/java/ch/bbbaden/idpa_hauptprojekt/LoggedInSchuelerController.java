/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
    @FXML
    private Label WIQI;

    private ObservableList<String> items;

    private String currentQuiz;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Brain.getInstance().setLis(this);
        try {
            // TODO
            items = FXCollections.observableArrayList(Brain.getInstance().getDt().getQuizes());
        } catch (SQLException ex) {
            Logger.getLogger(LoggedInSchuelerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listQuizes.setItems(items);
        listQuizes.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Your action here
            startQuiz.setText("Quiz " + newValue + " starten");
            currentQuiz = newValue;
        });
    }

    @FXML
    private void handleStartQuiz(ActionEvent event) throws IOException {
        if (currentQuiz != null) {
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AnswerQuiz.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            window.setTitle("Quiz Beantworten");
            window.setScene(scene);
            window.setOnCloseRequest(event1 -> {
                Brain.getInstance().hideLis(false);
            });
            setHide(true);
            window.show();

        }

    }

    @FXML
    private void handleInsertQuiz(ActionEvent event) {
        Brain.getInstance().getDt().insertQuiz();
    }

    public String getCurrentQuiz() {
        return currentQuiz;
    }

    public void setHide(boolean hide) {
        if (hide) {
            Stage stage = (Stage) startQuiz.getScene().getWindow();
            stage.hide();
        } else {
            Stage stage = (Stage) startQuiz.getScene().getWindow();
            stage.show();
        }

    }

    @FXML
    private void handleLogoutStudent(ActionEvent event) {
        Brain.getInstance().getController().handleLogoutStudent();
    }
}

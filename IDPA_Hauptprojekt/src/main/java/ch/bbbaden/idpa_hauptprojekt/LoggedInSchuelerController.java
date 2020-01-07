/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label WIQI;

    private ObservableList<String> items;
    
    private String windowName;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
            windowName = newValue;
        });
    }

    @FXML
    private void handleStartQuiz(ActionEvent event) {
        if(windowName != null){
            
        }

    }

    @FXML
    private void handleInsertQuiz(ActionEvent event) {
        Brain.getInstance().getDt().insertQuiz();
    }

}

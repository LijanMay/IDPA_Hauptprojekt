/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author denni
 */
public class LoggedInTeacherController implements Initializable {

    @FXML
    private Button chooseTopic;
    @FXML
    private Button newTopic;
    private Button expandTopic;
    @FXML
    private ListView<String> listviewTeacher;
    @FXML
    private Label topicLabel;
    @FXML
    private Label WIQI;

    private ObservableList<String> items;
    private String windowName;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Brain.getInstance().setLit(this);
       //updateTopics();
        listviewTeacher.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Your action here
            chooseTopic.setText("Frage zu " + newValue + " hinzufügen");
            windowName = newValue;
        });

    }

    @FXML
    private void handleChooseTopic(ActionEvent event) throws IOException {
        if (listviewTeacher.getSelectionModel().getSelectedItem() != null) {
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewQuestionTeacher.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            window.setTitle("Frage zu " + windowName + " hinzufügen");
            window.setScene(scene);
            window.setOnCloseRequest(event1 -> {
                Brain.getInstance().hideLit(false);
            });
            setHide(true);
            window.show();
        }

    }

    //Themengebiete = uebergebiete   Fragen = untergeordnet zu Themengebiete
    @FXML
    private void handleNewTopic(ActionEvent event) {
        String input;
        do {
            input = JOptionPane.showInputDialog(null, "Geben Sie den Namen des Themengebietes ein",
                    "Themengebiet",
                    JOptionPane.PLAIN_MESSAGE);
            if (input == null) {
                break;
            }
        } while (input.trim().equals(""));
        if (input != null) {
            Brain.getInstance().getDt().addTopic(input);
            updateTopics();
        }

    }

    @FXML
    private void handleCreateQuiz(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(""));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        window.setTitle("Quiz Zusammenstellen");
        window.setScene(scene);
        window.setOnCloseRequest(event1 -> {
            Brain.getInstance().hideLit(false);
        });
        setHide(true);
        window.show();

    }

    public void setHide(boolean hide) {
        if (hide) {
            Stage stage = (Stage) chooseTopic.getScene().getWindow();
            stage.hide();
        } else {
            Stage stage = (Stage) chooseTopic.getScene().getWindow();
            stage.show();
        }

    }

    private void updateTopics() {
        try {
            items = FXCollections.observableArrayList(Brain.getInstance().getDt().getTopics());
        } catch (SQLException ex) {
            Logger.getLogger(LoggedInTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listviewTeacher.setItems(items);
        listviewTeacher.setOrientation(Orientation.VERTICAL);

        // list.getSelectionModel().selectedItemProperty()
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
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
    @FXML
    private Button expandTopic;
    @FXML
    private ListView<String> listviewTeacher;
    @FXML
    private Label topicLabel;

    private Brain br;
    @FXML
    private Label WIQI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        br = Brain.getInstance();
        listviewTeacher.setVisible(false);
        topicLabel.setVisible(false);
        chooseTopic.setVisible(false);
    }

    @FXML
    private void handleChooseTopic(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewQuestionTeacher.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        window.setTitle("Eingeloggt");
        window.setScene(scene);

        window.show();
        setHide(true);

    }

    //Themengebiete = uebergebiete   Fragen = untergeordnet zu Themengebiete
    @FXML
    private void handleNewTopic(ActionEvent event) {
        String input;
        do {
            input = JOptionPane.showInputDialog(null, "Geben Sie den Namen des Themengebietes ein",
                    "Themengebiet",
                    JOptionPane.PLAIN_MESSAGE);
        } while (input.equals(""));
        if (input.length() > 0) {
            br.getDt().addTopic(input);
        }

    }

    @FXML
    private void handleExpandTopic(ActionEvent event) {
        listviewTeacher.setVisible(true);
        topicLabel.setVisible(true);
    }

    @FXML
    private void handleCreateQuiz(ActionEvent event) {
    }

    public void setHide(boolean hide) {
        if (hide) {
            Stage stage = (Stage) expandTopic.getScene().getWindow();
            stage.hide();
        } else {
            Stage stage = (Stage) expandTopic.getScene().getWindow();
            stage.show();
        }

    }

    @FXML
    private void changecolor(DragEvent event) {
        
    }

}

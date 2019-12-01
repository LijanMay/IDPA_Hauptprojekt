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
import javafx.scene.control.ListView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listviewTeacher.setVisible(false);
        topicLabel.setVisible(false);
        chooseTopic.setVisible(false);
    }    

    @FXML
    private void handleChooseTopic(ActionEvent event) {
    }

    
    //Themengebiete = uebergebiete   Fragen = untergeordnet zu Themengebiete
    @FXML
    private void handleNewTopic(ActionEvent event) {
        CreateGuis cg = new CreateGuis();
        cg.createTopic();
        
    }

    @FXML
    private void handleExpandTopic(ActionEvent event) {
        listviewTeacher.setVisible(true);
        topicLabel.setVisible(true);
    }

    @FXML
    private void handleCreateQuiz(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import ch.bbbaden.idpa_hauptprojekt.Datatransfer.Database;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author maxde
 */
public class CreateQuizController implements Initializable {

    @FXML
    private TextField TFQN;
//    Textfeld für den Namen des Quizes

    @FXML
    private ListView LVTG;
//    ListViewThemengebiete

    @FXML
    private ListView LVF;
//    ListViewFragen

    @FXML
    private ListView LVDF;
//    ListViewDeineFragen

    @FXML
    private Button BTGAdd;
//    Themengebiet dazufügen

    @FXML
    private Button BFAdd;
//   Frage dazufügen

    @FXML
    private Button BFDel;
//   Frage Löschen

    @FXML
    private Button BDone;
//   Done   

    @FXML
    private Button BFAddAll;
//    alle Fragen hinzufügen
    
    Database db = new Database();
    private ObservableList<String> items;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTopics();
    }

//    adds questions to ListViewFragen
    @FXML
    private void addTG(ActionEvent event) throws SQLException {
        String topic = (String) LVTG.getSelectionModel().getSelectedItem();
        ArrayList<String> questions = new ArrayList<>();
        questions = Brain.getInstance().getDt().getQuestionsToTopic(topic);
        for (String o : questions) {
                LVF.getItems().add(o);
            }
        LVTG.getItems().remove(topic);

    }

//    adds question to ListViewDeineFragen
    @FXML
    private void addFrage(ActionEvent event) {
        try {
            Object selected = LVF.getSelectionModel().getSelectedItem();
            moveFromTo(LVF, LVDF, selected);
        } catch (Exception e) {
            System.out.println("nothing selected");
        }
    }

//    adds all questions from LVF to LVDF
    @FXML
    private void addAlleFragen(ActionEvent event) {
        try {
            Object[] questions = LVF.getItems().toArray();
            for (Object o : questions) {
                moveFromTo(LVF, LVDF, o);
            }
        } catch (Exception e) {
            System.out.println("no questions to add");
        }
    }

//    deletes question from LVDF
    @FXML
    private void deleteFrage(ActionEvent event) {
        try {
            Object selected = LVDF.getSelectionModel().getSelectedItem();
            moveFromTo(LVDF, LVF, selected);
        } catch (Exception e) {
            System.out.println("nothing to remove");
        }
    }
    
//    submits the quiz to the DB
    @FXML
    private void quizFertig(ActionEvent event) {
        String name = TFQN.getText();
        ArrayList<String> questions = (ArrayList<String>) LVDF.getItems();
        Brain.getInstance().getDt().createQuiz(name, questions);
    }

//    move an entry from one listview to another
    private void moveFromTo(ListView lv1, ListView lv2, Object o) {
        if (o != null) {
            lv2.getItems().add(o);
            lv1.getItems().remove(o);
        }

    }
    
//    updates LVTG to have all the topics in the DB
    private void updateTopics() {
        try {
            items = FXCollections.observableArrayList(Brain.getInstance().getDt().getTopics());
        } catch (SQLException ex) {
            Logger.getLogger(LoggedInTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LVTG.setItems(items);
        LVTG.setOrientation(Orientation.VERTICAL);
    }

}

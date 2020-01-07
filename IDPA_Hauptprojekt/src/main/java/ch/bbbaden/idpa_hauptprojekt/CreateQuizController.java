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
<<<<<<< Updated upstream
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
=======
>>>>>>> Stashed changes
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

    Database db = new Database();
    private ObservableList<String> items;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
<<<<<<< Updated upstream
//        Brain.getInstance().getDt().addTopic("test");
        updateTopics();
=======
        brain.getDt().addTopic("test");
        ArrayList topics = null;
        try {
            topics = brain.getDt().getTopics();
        } catch (SQLException ex) {
            Logger.getLogger(CreateQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        ArrayList topics = db.getTopics();
        try {
            for (Object o : topics) {
                LVTG.getItems().add(o);
            }
        } catch (Exception e) {
            System.out.println("Error while adding topics to ListView: " + e);
        }

>>>>>>> Stashed changes
    }

//    adds question to ListViewFragen
    @FXML
    private void addTG(ActionEvent event) {
        try {
            Object selected = LVTG.getSelectionModel().getSelectedItem();
            moveFromTo(LVTG, LVF, selected);
        } catch (Exception e) {
            System.out.println("no TG selected");
        }
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

//    move an entry from one listview to another
    private void moveFromTo(ListView lv1, ListView lv2, Object o) {
        if (o != null) {
            lv2.getItems().add(o);
            lv1.getItems().remove(o);
        }

    }
    
    private void updateTopics(){
        try {
            items = FXCollections.observableArrayList(Brain.getInstance().getDt().getTopics());
        } catch (SQLException ex) {
            Logger.getLogger(LoggedInTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LVTG.setItems(items);
        LVTG.setOrientation(Orientation.VERTICAL);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LVTG.getItems().add("Test");
        LVTG.getItems().add("Test2");
        LVTG.getItems().add("Test3");
        LVTG.getItems().add("Test4");
    }

//    adds question to ListViewFragen
    @FXML
    private void addTG(ActionEvent event) {
        try {
            Object selected = LVTG.getSelectionModel().getSelectedItem();
            moveTo(LVTG, LVF, selected);
        } catch (Exception e) {
            System.out.println("no TG selected");
        }
    }

//    adds question to ListViewDeineFragen
    @FXML
    private void addFrage(ActionEvent event) {
        try {
            Object selected = LVF.getSelectionModel().getSelectedItem();
            moveTo(LVF, LVDF, selected);
        } catch (Exception e) {
            System.out.println("nothing selected");
        }
    }

//    adds all questions from LVF to LVDF
    @FXML
    private void addAlleFragen(ActionEvent event) {
        try {
            ObservableList<Object> questions;
            questions = LVF.getSelectionModel().getSelectedItems();
            for(Object o : questions){
                moveTo(LVF, LVDF, o);
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
            moveTo(LVDF, LVF, selected);
        } catch (Exception e) {
            System.out.println("nothing to remove");
        }
    }

//    move an entry from one listview to another
    private void moveTo(ListView lv1, ListView lv2, Object o) {
        if (o != null) {
            lv2.getItems().add(o);
            lv1.getItems().remove(o);
        }

    }

}

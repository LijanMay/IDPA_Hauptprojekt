/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.net.URL;
import java.util.ResourceBundle;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

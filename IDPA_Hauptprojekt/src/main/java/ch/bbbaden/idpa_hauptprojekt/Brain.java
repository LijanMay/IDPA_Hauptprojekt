/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import ch.bbbaden.idpa_hauptprojekt.Datatransfer.Database;
import javafx.stage.Stage;

/**
 *
 * @author Dennis
 */
public class Brain {

    private static Brain instance;

    private Brain() {
    }
    public static Brain getInstance() {
        if (Brain.instance == null) {
            Brain.instance = new Brain();
        }
        return Brain.instance;
    }

    private Stage datatransferinterface;
    private Stage loggedInLehrer;
    private Database dt;
    private LoggedInTeacherController lit;
    private FXMLController controller;
    //Am anfang aus lastLogin von Datenbank lesen um Nutzername automatisch einzufügen für angenehmeres Login
    public String currentUsername = "admin";

    public Stage getDatatransferinterface() {
        return datatransferinterface;
    }

    public Stage getLoggedInLehrer() {
        return loggedInLehrer;
    }

    public void setLoggedInLehrer(Stage loggedInLehrer) {
        this.loggedInLehrer = loggedInLehrer;
    }

    
    
    public Database getDt() {
        return dt;
    }

    public void setDt(Database dt) {
        this.dt = dt;
    }

    public LoggedInTeacherController getLit() {
        return lit;
    }

    public void setLit(LoggedInTeacherController lit) {
        this.lit = lit;
    }
    
    public void hideLit(boolean hide){
        lit.setHide(hide);
    }

    public FXMLController getController() {
        return controller;
    }

    public void setController(FXMLController controller) {
        this.controller = controller;
    }
    
    public void hideController(boolean hide){
        controller.setHide(hide);
    }
    
    public void closeLoggedInLehrer(){
        loggedInLehrer.close();
    }

}

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

    private Stage loggedInLehrer;
    private Stage loggedInStudent;
    private Database dt;
    private LoggedInTeacherController lit;
    private LoggedInSchuelerController lis;
    private AnswerQuizController aqc;
    private FXMLController controller;
    //Am anfang aus lastLogin von Datenbank lesen um Nutzername automatisch einzufügen für angenehmeres Login
    public String currentUsername = "user";

    public AnswerQuizController getAqc() {
        return aqc;
    }

    public void setAqc(AnswerQuizController aqc) {
        this.aqc = aqc;
    }

    public Stage getLoggedInStudent() {
        return loggedInStudent;
    }

    public void setLoggedInStudent(Stage loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }

    public LoggedInSchuelerController getLis() {
        return lis;
    }

    public void setLis(LoggedInSchuelerController lis) {
        this.lis = lis;
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

    public void hideLit(boolean hide) {
        lit.setHide(hide);
    }

    public void hideLis(boolean hide) {
        lis.setHide(hide);
    }

    public FXMLController getController() {
        return controller;
    }

    public void setController(FXMLController controller) {
        this.controller = controller;
    }

    public void hideController(boolean hide) {
        controller.setHide(hide);
    }

    public void closeLoggedInLehrer() {
        Brain.instance.dt.logout(currentUsername);
        loggedInLehrer.close();
    }

    public void closeLoggedInStudent() {
        Brain.instance.dt.logout(currentUsername);
        loggedInStudent.close();
    }

}

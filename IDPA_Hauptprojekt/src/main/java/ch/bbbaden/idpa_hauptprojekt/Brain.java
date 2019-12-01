/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

/**
 *
 * @author Dennis
 */
public class Brain {

    private static Brain instance;

    // Verhindere die Erzeugung des Objektes über andere Methoden
    private Brain() {
    }
    // Eine Zugriffsmethode auf Klassenebene, welches dir '''einmal''' ein konkretes 
    // Objekt erzeugt und dieses zurückliefert.

    public static Brain getInstance() {
        if (Brain.instance == null) {
            Brain.instance = new Brain();
        }
        return Brain.instance;
    }

    
    private DatatransferInterface dt;
    private LoggedInTeacherController lit;
    private FXMLController controller;

    public DatatransferInterface getDt() {
        return dt;
    }

    public void setDt(DatatransferInterface dt) {
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
    

}

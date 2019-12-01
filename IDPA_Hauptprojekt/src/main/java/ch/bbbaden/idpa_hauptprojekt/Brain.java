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

    public DatatransferInterface getDt() {
        return dt;
    }

    public void setDt(DatatransferInterface dt) {
        this.dt = dt;
    }

}

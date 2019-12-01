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
public class CreateGuis {
    
    public void createTopic() {
        //Fenster machen
        
        //Datatransfer file
        DatatransferInterface df = Brain.getInstance().getDt();
        // String aus dem Fenster uebergen
        df.addTopic("");
        
        
    }
}

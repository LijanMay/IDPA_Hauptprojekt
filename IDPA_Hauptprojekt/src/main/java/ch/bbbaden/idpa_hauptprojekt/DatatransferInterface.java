/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import java.util.ArrayList;

/**
 *
 * @author denni
 */
public interface DatatransferInterface {
    
    public void createUser();
    public void getData();
    public void createQuiz();
    public void addTopic(String topic);
    public void addQuestion(String question, int type, ArrayList<String> answers);
    
   
}

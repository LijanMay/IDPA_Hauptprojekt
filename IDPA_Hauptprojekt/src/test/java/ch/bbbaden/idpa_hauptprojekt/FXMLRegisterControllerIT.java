/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt;

import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasText;

/**
 *
 * @author marin
 */
public class FXMLRegisterControllerIT extends ApplicationTest {

    private static final String LABEL_SURNAME = "#surname";
    private static final String LABEL_PRENAME = "#prename";
    private static final String LABEL_USERNAME = "#user";
    private static final String LABEL_MAIL = "#mail";
    private static final String LABEL_PASSWORD = "#pswd";
    private static final String LABEL_CONFIRMPASSWORD = "#pswd2";

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new MainApp().start(stage);
    }
    
//    @Test
//    public void blankfields(){
//        verifyThat(LABEL_USERNAME, hasText(""));
//        verifyThat(LABEL_MAIL, hasText(""));
//        verifyThat(LABEL_PASSWORD, hasText(""));
//        verifyThat(LABEL_CONFIRMPASSWORD, hasText(""));
//    }
    
    @Test
    public void success() {
        clickOn("#registerButton");
        doubleClickOn(LABEL_SURNAME).write("Rytz");
        doubleClickOn(LABEL_PRENAME).write("Natalie");
        doubleClickOn(LABEL_USERNAME).write("Natalieeee");
        doubleClickOn(LABEL_MAIL).write("natalie.rytz@gmail.com");
        doubleClickOn(LABEL_PASSWORD).write("123456");
        doubleClickOn(LABEL_CONFIRMPASSWORD).write("123456");
        clickOn("#rbTeacher");
        clickOn("#register");
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.idpa_hauptprojekt.Datatransfer;

import ch.bbbaden.idpa_hauptprojekt.DatatransferInterface;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dennis
 */
public class Database implements DatatransferInterface {

    @Override
    public void createUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTopic(String topic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void createNewDatabase(String wiqiDB) throws ClassNotFoundException, SQLException {
 
        String url = "jdbc:sqlite:C:\\Users\\lijan\\GitHub\\IDPA_Hauptprojekt\\" + wiqiDB;
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }else{
                System.out.println("edasd");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}

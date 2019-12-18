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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dennis
 */
public class Database {

    private static Connection conn;

    public void createUser(String prename, String surname, String username, String password, String email, boolean isTeacher) {
        final String sql = "CREATE TABLE IF NOT EXISTS Benutzer ("
                + "ID INTEGER PRIMARY KEY,"
                + "Benutzername STRING NOT NULL, "
                + "Name STRING NOT NULL,"
                + "Vorname STRING NOT NULL,"
                + "Email STRING NOT NULL,"
                + "istLehrer BOOLEAN NOT NULL,"
                + ")";

        try (Statement stm = Database.conn.createStatement()) {
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        final String sqlInsert = "INSERT INTO Benutzer (Benutzername, Name, Vorname, Email, Passwort, istLehrer) VALUES (?, ?, ?, ?, ?, ?)";

        try (Statement stm = Database.conn.createStatement()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, surname);
            ps.setString(3, prename);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(2, (isTeacher ? "1" : "0"));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public void createQuiz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addTopic(String topic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void connect() {
        if (conn == null) {
            try {
                String url = "jdbc:sqlite:C:.\\" + "wiqiDB.db";
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(url);
            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public static void createNewDatabase() throws ClassNotFoundException, SQLException {
        connect();
        try {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");

            } else {
                System.out.println("edasd");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addQuestion(String question, int type, ArrayList<String> answers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<String> getTopics() {
        ArrayList<String> t = new ArrayList<>();

        return t;
    }

    public static Connection getConnection() {
        connect();
        return conn;
    }

    public ArrayList<String> getUser() throws SQLException {
        ArrayList<String> t = new ArrayList<>();

        String query = "SELECT * FROM Benutzer";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        int columns = rs.getMetaData().getColumnCount();

        while (rs.next()) {
            for (int i = 1; i < columns; i++) {
                t.add(rs.getString(i));
            }
        }

        return t;
    }

}

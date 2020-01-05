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

        final String sqlInsert = "INSERT INTO Benutzer (Benutzername, Name, Vorname, Email, Passwort, istLehrer) VALUES (?, ?, ?, ?, ?, ?)";

        try (Statement stm = Database.conn.createStatement()) {
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
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

        final String sqlInsert = "INSERT INTO Topics (ID, Name) VALUES (?)";

        try (Statement stm = Database.conn.createStatement()) {
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, topic);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void connect() {
        if (conn == null) {
            try {
                String url = "jdbc:sqlite:C:.\\" + "wiqiDB";
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

    public ArrayList<String> getTopics() throws SQLException {

        ArrayList<String> t = new ArrayList<>();
        String query = "SELECT * FROM Topics";
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

    public void insertQuiz() {
        // aus TXT oder so die Statements lesen und in die Datenbank fügen
    }

    //noch falsch
    public ArrayList<String> getQuizes() throws SQLException {
        ArrayList<String> t = new ArrayList();

        String query = "SELECT Topic FROM Quizes";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        int columns = rs.getMetaData().getColumnCount();

        while (rs.next()) {
            for (int i = 1; i < columns; i++) {
                t.add(rs.getString(i));
            }
        }
        t.add("fuck");
        t.add("my");
        t.add("life");

        return t;
    }

    public void createDBStructure() {
        String dbName = "wiqiDB";
        
//        String query0 = "create database if not exists wiqiDB";
//        
//        String query1 = "use wiqiDB";
//        
        String query2 = "create table if not exists Thema ("
                + "id integer primary key,"
                + "Benutzer id foreign key,"
                + "Name string not null"
                + ")";
        
        String query3 = "create table if not exists Benutzer (" 
                + "id integer primary key, "
                + "Benutzername string not null, "
                + "Name string not null, "
                + "Vorname string not null, "
                + "Email string not null, "
                + "Passwort string not null, "
                + "istLehrer boolean not null"
                + ")";
        
        String query4 = "create table if not exists Satzantwort ("
                + "id integer primary key, "
                + "Fragen id foreign key, "
                + "Satzantwort string not null"
                + ")";
        
        String query5 = "create table if not exists RichtigFalsch ("
                + "id integer primary key, "
                + "Fragen id foreign key, "
                + "Antwort boolean not null"
                + ")";
        
        String query6 = "create table if not exists MultipleChoice ("
                + "id integer primary key, "
                + "Fragen id foreign key, "
                + "Richtig string not null, "
                + "Falsch1 string not null, "
                + "Falsch2 string not null, "
                + "Falsch3 string not null"
                + ")";
        
        String query7 = "create table if not exists Fragen2Thema ("
                + "id integer primary key, "
                + "Fragen id foreign key, "
                + "Thema id foreign key"
                + ")";
        
        String query8 = "create table if not exists Fragen ("
                + "id integer primary key, "
                + "Frage id string not null "
                + ")";
        
        //String query0 = "CREATE DATABASE IF NOT EXISTS " + dbName;

        //String query1 = "USE " + dbName;

//        String query2 = "CREATE TABLE IF NOT EXISTS Topics ("
//                + "ID INTEGER PRIMARY KEY,"
//                + "Name STRING NOT NULL, "
//                + ")";

//        String query3 = "CREATE TABLE IF NOT EXISTS Benutzer ("
//                + "ID INTEGER PRIMARY KEY,"
//                + "Benutzername STRING NOT NULL, "
//                + "Name STRING NOT NULL,"
//                + "Vorname STRING NOT NULL,"
//                + "Email STRING NOT NULL,"
//                + "istLehrer BOOLEAN NOT NULL,"
//                + ")";

        Statement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
//            stmt.addBatch(query0);
//            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            stmt.executeBatch();
            conn.commit();
            stmt.close();
            System.out.println("Database successfully created or just existing");
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try (Statement stm = Database.conn.createStatement()) {
//            stm.execute(sql1);
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}

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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            ps.setString(1, prename);
            ps.setString(2, surname);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.setString(6, (isTeacher ? "1" : "0"));
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

        final String sqlInsert = "INSERT INTO Thema (Name) VALUES (?)";

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

    public void addQuestion(String question, int type, ArrayList<String> answers, String topic) {
        final String sqlInsert = "INSERT INTO Fragen (Frage, Antwort) VALUES (?, ?)";
        final String sqlInsert2 = "insert into Fragen2Thema (Fragen_id, Thema_id) VALUES (?, ?)";
        final String query1 = "SELECT * FROM Thema";
        final String query2 = "SELECT * FROM Fragen";
        int topicid = 0;
        int questionid = 0;
        try {
            Statement stm1 = conn.createStatement();
            ResultSet rs = stm1.executeQuery(query1);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i < columns; i++) {
                    if (topic.equals(rs.getString("name"))) {
                        topicid = rs.getInt("id");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (type) {
            case 0:
                //multiple choice
                String wronganswers = "Falsch1";
                String values = "?";
                for (int i = 2; i < answers.size(); i++) {
                    wronganswers += ", Falsch" + i;
                    values += ", ?";
                }
                final String sqlInsert31 = "insert into MultipleChoice (Fragen_id, Richtig, " + wronganswers + ") VALUES (?, ?, " + values + ")";
                try (Statement stm = Database.conn.createStatement()) {
                    PreparedStatement ps1 = conn.prepareStatement(sqlInsert);
                    ps1.setString(1, question);
                    ps1.setString(2, answers.get(0));
                    ps1.executeUpdate();

                    PreparedStatement ps2 = conn.prepareStatement(sqlInsert31);
                    ps2.setInt(1, questionid);
                    ps2.setString(2, answers.get(0));
                    for (int d = 1; d < answers.size(); d++) {
                        ps2.setString(d + 2, answers.get(d));
                    }
                    ps2.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case 1:
                //true false
                final String sqlInsert32 = "insert into RichtigFalsch (Fragen_id, Richtig, Falsch) VALUES (?, ?, ?)";
                try (Statement stm = Database.conn.createStatement()) {
                    PreparedStatement ps1 = conn.prepareStatement(sqlInsert);
                    ps1.setString(1, question);
                    ps1.setString(2, answers.get(0));
                    ps1.executeUpdate();

                    PreparedStatement ps2 = conn.prepareStatement(sqlInsert32);
                    ps2.setInt(1, questionid);
                    ps2.setString(2, answers.get(0));
                    ps2.setString(3, answers.get(1));
                    ps2.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case 2:
                //frage mit antwort von benutzer
                final String sqlInsert33 = "insert into Satzantwort (Fragen_id, satzantwort) VALUES (?, ?)";
                try (Statement stm = Database.conn.createStatement()) {
                    PreparedStatement ps1 = conn.prepareStatement(sqlInsert);
                    ps1.setString(1, question);
                    ps1.setString(2, answers.get(0));
                    ps1.executeUpdate();

                    PreparedStatement ps2 = conn.prepareStatement(sqlInsert33);
                    ps2.setInt(1, questionid);
                    ps2.setString(2, answers.get(0));
                    ps2.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            default:
                JOptionPane.showMessageDialog(null, "Irgendetwas ging schief");
        }
        try {
            //get questionid
            Statement stm2 = conn.createStatement();
            ResultSet rs1 = stm2.executeQuery(query2);
            int columns1 = rs1.getMetaData().getColumnCount();
            while (rs1.next()) {
                for (int i = 1; i < columns1; i++) {
                    if (question.equals(rs1.getString("frage"))) {
                        questionid = rs1.getInt("id");
                    }
                }
            }
            //connect topic with question
            PreparedStatement ps = conn.prepareStatement(sqlInsert2);
            ps.setInt(1, questionid);
            ps.setInt(2, topicid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> getTopics() throws SQLException {

        ArrayList<String> t = new ArrayList<>();
        String query = "SELECT * FROM Thema";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        int columns = rs.getMetaData().getColumnCount();

        while (rs.next()) {
            for (int i = 1; i < columns; i++) {
                t.add(rs.getString("name"));
            }
        }

        return t;
    }

    public static Connection getConnection() {
        connect();
        return conn;
    }

    public ArrayList<HashMap<String, String>> getUser() throws SQLException {
        ArrayList<HashMap<String, String>> t = new ArrayList<>();
        String query = "SELECT * FROM Benutzer";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        int columns = rs.getMetaData().getColumnCount();

        while (rs.next()) {

            HashMap<String, String> s = new HashMap<>();
            for (int i = 1; i < columns + 1; i++) {
                s.put("prename", rs.getString("vorname"));
                s.put("surname", rs.getString("name"));
                s.put("username", rs.getString("benutzername"));
                s.put("email", rs.getString("email"));
                s.put("password", rs.getString("passwort"));
                s.put("status", rs.getString("istLehrer"));
            }
            t.add(s);
        }

        return t;
    }

    public void insertQuiz() {
        // aus TXT oder so die Statements lesen und in die Datenbank fügen
    }

    //noch falsch
    public ArrayList<String> getQuizes() throws SQLException {
        ArrayList<String> t = new ArrayList();

        String query = "SELECT name FROM Quizes";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        int columns = rs.getMetaData().getColumnCount();

        while (rs.next()) {
            for (int i = 1; i < columns; i++) {
                t.add(rs.getString(i));
            }
        }
        t.add("test");
        return t;
    }

    public ArrayList<String[]> getQuestionsforQuiz(String quiz) {
        ArrayList<String[]> t = new ArrayList();
        String[] s;
        String questionType = "satzantwort";

        if (questionType.toLowerCase().equals("satzantwort")) {
            s = new String[3];
            s[0] = "questiontype";
            s[1] = "question";
            s[2] = "answer";
        } else if (questionType.toLowerCase().equals("richtigfalsch")) {
            s = new String[4];
            s[0] = "questiontype";
            s[1] = "question";
            s[2] = "answer";
            s[3] = "wronganswer";
        } else {
            int wrongAnswers = 0;
            s = new String[wrongAnswers + 3];
            s[0] = "questiontype";
            s[1] = "question";
            s[2] = "answer";
            for(int i = 0; i < wrongAnswers; i++){
                s[i+3] = "wronganswer" + i;
            }

        }
        t.add(s);
        return t;
    }

    public ArrayList<String> getQuestionsToTopic(String topic) throws SQLException {
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> questionsid = new ArrayList<>();
        int topicId = 0;
        String query1 = "SELECT * FROM Thema";
        String query2 = "select * from fragen2thema";
        String query3 = "select * from fragen";
        Statement stm1 = conn.createStatement();
        ResultSet rs1 = stm1.executeQuery(query1);
        int columns1 = rs1.getMetaData().getColumnCount();

        Statement stm2 = conn.createStatement();
        ResultSet rs2 = stm2.executeQuery(query2);
        int columns2 = rs2.getMetaData().getColumnCount();

        Statement stm3 = conn.createStatement();
        ResultSet rs3 = stm3.executeQuery(query3);
        int columns3 = rs3.getMetaData().getColumnCount();

        while (rs1.next()) {
            ArrayList<String> t = new ArrayList<>();
            for (int i = 1; i < columns1; i++) {
                t.add(rs1.getString("id"));
                t.add(rs1.getString("Name"));
            }
            if (t.get(1).equals(topic)) {
                topicId = Integer.parseInt(t.get(0));
            }
        }

        while (rs2.next()) {
            HashMap<String, String> s = new HashMap<>();
            for (int i = 1; i < columns2; i++) {
                s.put("ThemaID", rs2.getString("thema_id"));
                s.put("FragenID", rs2.getString("fragen_id"));
            }
            if (Integer.parseInt(s.get("ThemaID")) == topicId) {
                questionsid.add(s.get("FragenID"));
            }

        }

        while (rs3.next()) {
            HashMap<String, String> q = new HashMap<>();
            for (int i = 1; i < columns3; i++) {
                q.put("ID", rs3.getString("id"));
                q.put("frage", rs3.getString("frage"));
            }
            for (int d = 0; d < questionsid.size(); d++) {
                if (questionsid.get(d).equals(q.get("ID"))) {
                    questions.add(q.get("frage"));
                }
            }
        }

        return questions;
    }

    public void createDBStructure() {
        /*
    String query1 = "drop table topics";
        String query0 = "drop table benutzer";   
            batch 0            + "Benutzer id foreign key,"
        
        
        batch 2        + "Fragen id foreign key, "
        batch 3    + "Fragen id foreign key, "
        
        batch 4  + "Fragen id foreign key, "
        batch 5  + "Fragen id foreign key, "
                + "Thema id foreign key"
         */
        String query88 = "drop table Fragen2Thema";
        String query10 = "drop table thema";
        String query00 = "drop table benutzer";
        String query8 = "drop table Fragen";
        String query9 = "drop table Satzantwort";
        String query99 = "drop table RichtigFalsch";
        String query77 = "drop table MultipleChoice";
        String query66 = "drop table Quizes";

        String query0 = "create table if not exists Thema ("
                + "id integer primary key, "
                + "Name string not null "
                + ")";

        String query1 = "create table if not exists Benutzer ("
                + "id integer primary key, "
                + "Benutzername string not null, "
                + "Name string not null, "
                + "Vorname string not null, "
                + "Email string not null, "
                + "Passwort string not null,"
                + "istLehrer boolean not null "
                + ")";

        String query2 = "create table if not exists Satzantwort ("
                + "id integer primary key, "
                + "Fragen_id integer not null, "
                + "Satzantwort string not null "
                + ")";

        String query3 = "create table if not exists RichtigFalsch ("
                + "id integer primary key, "
                + "Fragen_id integer, "
                + "Richtig String not null,"
                + "Falsch String not null "
                + ")";

        String query4 = "create table if not exists MultipleChoice ("
                + "id integer primary key, "
                + "Fragen_id integer, "
                + "Richtig string not null, "
                + "Falsch1 string not null, "
                + "Falsch2 string not null, "
                + "Falsch3 string, "
                + "Falsch4 string, "
                + "Falsch5 string, "
                + "Falsch6 string, "
                + "Falsch7 string, "
                + "Falsch8 string, "
                + "Falsch9 string "
                + ")";

        String query5 = "create table if not exists Fragen2Thema ("
                + "id integer primary key, "
                + "Fragen_id integer, "
                + "Thema_id integer "
                + ")";

        String query6 = "create table if not exists Fragen ("
                + "id integer primary key, "
                + "Frage string not null, "
                + "Antwort string not null "
                + ")";

        String query7 = "create table if not exists Quizes ("
                + "id integer primary key, "
                + "name string not null "
                + ")";

        Statement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
//            stmt.addBatch(query88);
//            stmt.addBatch(query00);
//            stmt.addBatch(query66);
//            stmt.addBatch(query77);
//            stmt.addBatch(query99);
//            stmt.addBatch(query9);
//            stmt.addBatch(query8);
//            stmt.addBatch(query10);
            stmt.addBatch(query0);
            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            stmt.addBatch(query4);
            stmt.addBatch(query5);
            stmt.addBatch(query6);
            stmt.addBatch(query7);
            stmt.executeBatch();
            conn.commit();
            stmt.close();
            System.out.println("Database successfully created or just existing");
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

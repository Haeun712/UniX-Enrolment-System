package seng2050;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SemesterDAOImpl implements SemesterDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/unix";
    private static final String USER = "root";
    private static final String PASSWORD = "P@ssword1";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Semester getSemesterBySemesterID(int semesterID) {
        String sql = "SELECT * FROM semester WHERE semesterID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, semesterID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Semester(rs.getInt("semesterID"), rs.getInt("semester"), 
                    rs.getInt("year"), rs.getBoolean("openForEnrolment"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();
        String sql = "SELECT * FROM semester";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                semesters.add(new Semester(rs.getInt("semesterID"), rs.getInt("semester"), 
                        rs.getInt("year"), rs.getBoolean("openForEnrolment")));
            }

            rs.close();
            stmt.close();
            conn.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semesters;
    }
}


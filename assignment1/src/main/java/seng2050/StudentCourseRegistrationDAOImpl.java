package seng2050;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseRegistrationDAOImpl implements StudentCourseRegistrationDAO {

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
    public List<String> getCurrentEnroledCourseIDs(String stdNo, int semesterID) {
        List<String> courseIDs = new ArrayList<>();
        String sql = "SELECT courseID FROM studentcourseregistration WHERE stdNo = ? AND semesterID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stdNo);
            stmt.setInt(2, semesterID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courseIDs.add(rs.getString("courseID"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseIDs;
    }

}

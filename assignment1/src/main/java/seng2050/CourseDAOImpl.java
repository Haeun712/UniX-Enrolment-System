package seng2050;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

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
    public Course getCourseByCourseID(String courseID) {
        String sql = "SELECT * FROM course WHERE courseID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course(rs.getString("courseID"), rs.getString("cName"), rs.getInt("credits"));
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
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                courses.add(new Course(rs.getString("courseID"), rs.getString("cName"), 
                        rs.getInt("credits")));
            }

            rs.close();
            stmt.close();
            conn.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}


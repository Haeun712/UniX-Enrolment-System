package seng2050;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.w3c.dom.Document;

public class StudentCourseRegistrationDAOImpl implements StudentCourseRegistrationDAO {

    private static DataSource datasource;

    static {

        try {
            // Reading database configuration parameters    
            File file = new File("databaseConfig.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            String jdbc = document.getElementsByTagName("jdbcDriver").item(0).getTextContent();
            String databaseURL = document.getElementsByTagName("databaseURL").item(0).getTextContent();
            String usr = document.getElementsByTagName("username").item(0).getTextContent();
            String pwd = document.getElementsByTagName("password").item(0).getTextContent();
     
            // Setting the connection pool properties
            PoolProperties p = new PoolProperties();
            p.setUrl(databaseURL);
            p.setDriverClassName(jdbc);
            p.setUsername(usr);
            p.setPassword(pwd);
            // You can set additional pool properties
            p.setMaxActive(100); // Maximum number of connections in the pool
    
            // Setting the data source with the pool properties defined above
            datasource = new DataSource();
            datasource.setPoolProperties(p);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
      

    }
    
    @Override
    public List<String> getCurrentEnroledCourseIDs(String stdNo, int semesterID) {
        List<String> courseIDs = new ArrayList<>();
        String sql = "SELECT courseID FROM studentcourseregistration WHERE stdNo = ? AND semesterID = ?";
        try (Connection conn = datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stdNo);
            stmt.setInt(2, semesterID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courseIDs.add(rs.getString("courseID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseIDs;
    }

    @Override
    public List<String> getAllEnroledCourseIDs (String stdNo) {
        List<String> courseIDs = new ArrayList<>();
        String sql = "SELECT courseID FROM studentcourseregistration WHERE stdNo = ?";
        try (Connection conn = datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stdNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courseIDs.add(rs.getString("courseID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseIDs;
    }

    @Override
    public int getEnroledStudentCount(String courseID, int semesterID) {
        String sql = "SELECT COUNT(*) AS studentCount FROM studentcourseregistration WHERE courseID = ? AND semesterID = ?";
        try (Connection conn = datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseID);
            stmt.setInt(2, semesterID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("studentCount");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    @Override
    public String enrolCourse(String stdNo, String courseID, int semesterID) {
        String sql = "INSERT INTO studentcourseregistration (stdNo, courseID, semesterID) VALUES (?, ?, ?)";
        try (Connection conn = datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stdNo);
            stmt.setString(2, courseID);
            stmt.setInt(3, semesterID);
            stmt.executeUpdate();   

            return courseID;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

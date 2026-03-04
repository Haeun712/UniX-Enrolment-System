package seng2050;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.w3c.dom.Document;

public class CourseDAOImpl implements CourseDAO {

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
    public Course getCourseByCourseID(String courseID) {
        String sql = "SELECT * FROM course WHERE courseID = ?";
        try (Connection conn = datasource.getConnection();
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
        try (Connection conn = datasource.getConnection();
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

    @Override
    public List<String> getAssumedKnowledgeByCourseID(String courseID) {
        List<String> assumedknowledge = new ArrayList<>();
        String sql = "SELECT * FROM assumedknowledge WHERE courseID = ?";
        try (Connection conn = datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseID);
            ResultSet rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                assumedknowledge.add(rs.getString("assumedKnowledge"));
            }

            rs.close();
            stmt.close();
            conn.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assumedknowledge;
    }

    @Override
    public List<String> getPrerequisiteByCourseID(String courseID) {
        List<String> prerequisite = new ArrayList<>();
        String sql = "SELECT * FROM prerequisiteknowledge WHERE courseID = ?";
        try (Connection conn = datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseID);
            ResultSet rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                prerequisite.add(rs.getString("preReqKnowledge"));
            }

            rs.close();
            stmt.close();
            conn.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prerequisite;
    }
}


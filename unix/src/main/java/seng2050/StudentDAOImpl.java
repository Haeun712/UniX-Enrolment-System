package seng2050;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.w3c.dom.Document;

public class StudentDAOImpl implements StudentDAO {

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
    public Student getStudentByStdNo(String stdNo) {
        String sql = "SELECT * FROM student WHERE stdNo = ?";
        try (Connection conn = datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stdNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(rs.getString("stdNo"), rs.getString("givenNames"), 
                    rs.getString("lastName"), rs.getString("passwordHash"), rs.getDouble("passwordSalt"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

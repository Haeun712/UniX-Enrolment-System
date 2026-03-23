package seng2050;

import java.io.Serializable;
import java.util.Objects;

public class Course implements  Serializable {
    private String  courseID;
    private String courseName;
    private int credits;
    
    //Constructors
    public Course () {}

    public Course (String  courseID, String courseName,int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    //Getters and Setters
    public String getCourseID() { return courseID; }
    public void setCourseID (String courseID) { this.courseID = courseID; }

    public String getCourseName() { return courseName; }
    public void setCourseName (String courseName) { this.courseName = courseName; }

    public int getCredits() { return credits; }
    public void setCredits (int credits) { this.credits = credits; }

     @Override
    public boolean equals(Object obj) {
        // Check if the object is compared with itself
        if (this == obj) return true;

        // Check if the object is an instance of Course
        if (!(obj instanceof Course)) return false;

        // Typecast obj to Course so that we can compare data members
        Course other = (Course) obj;

        // Compare the courseID for equality
        return Objects.equals(this.courseID, other.courseID);
    }

}

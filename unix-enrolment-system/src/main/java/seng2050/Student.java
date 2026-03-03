package seng2050;

import java.io.Serializable;

public class Student implements  Serializable {
    private String stdNo;
    private String lastname;
    private String givenNames;
    private String passwordHash;
    
    //Constructors
    public Student () {}

    public Student (String stdNo, String lastname,String givenNames, String passwordHash) {
        this.stdNo = stdNo;
        this.givenNames = givenNames;
        this.lastname = lastname;
        this.passwordHash = passwordHash;
    }

    //Getters and Setters
    public String getStdNo() { return stdNo; }
    public void setStdNo (String stdNo) { this.stdNo = stdNo; }

    public String getGivenNames() { return givenNames; }
    public void setGivenNames (String givenNames) { this.givenNames = givenNames; }

    public String getLastName() { return lastname; }
    public void setLastName (String lastname) { this.lastname = lastname; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash (String passwordHash) { this.passwordHash = passwordHash; }

}

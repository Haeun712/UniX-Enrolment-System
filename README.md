# UniX Enrolment System Web

## Overview
The **UniX Enrolment System** is a web-based application that allows students to enrol in or drop courses for an open semester.

This project was developed using **Java Servlets, JSP, and JavaBeans**, following the **MVC (Model-View-Controller)** and **DAO (Data Access Object)** design patterns to ensure a scalable and maintainable architecture.

---

## 📚 Features

- **Secure Authentication**
  - Password hashing (no plaintext passwords stored)
  - HTTPS communication (self-signed certificates)

- **Semester Selection**
  - Students can enrol only in open semesters
 


- **Course Enrolment**
  - Enrol in available courses for a selected semester
  - Drop enrolled courses

- 📏 **Business Rules Enforcement**
  - Maximum **40 units per semester**
  - Warning for missing assumed knowledge
  - Error if prerequisites are not satisfied
  - Course enrolment limited by capacity (max-capacity)

- 🔍 **Validation & Feedback**
  - Clear error and warning messages during enrolment

---

## 🛠 Technologies Used

- Java (Servlets, JSP, JavaBeans)  
- Apache Tomcat (Web Server & Connection Pooling)  
- JDBC (Database Connectivity)
- MySQL (Database)
- JUnit (Testing)
- Figma (UI Wireframes & Wireflow)  
- Visual Studio Code  

---

## 🎨 Wireframes

Wireframes were designed using **Figma**, including:

- Login Page  
- Semester Selection Page  
- Course Enrolment Page  
- Validation and Feedback Messages

[View Wireflow](Requirements/Final%20Wireflow.pdf)

---

## ⚙️ System Design

The application follow **the Model-View-Controller (MVC)** and **Data Access Object (DAO)** design
patterns

[View Sequence Diagram](Design/Final%20Sequence%20Diagram.pdf)

**Sequence Flow**

1. User logs in  
2. System authenticates credentials  
3. Student selects a semester  
4. Student searches and enrols in courses  
5. System validates:
   - Prerequisites
   - Assumed knowledge (warning)
   - Unit limit (≤ 40 units)
   - Course capacity
   - Course availability for the selected semester
   - Whether the student is already enrolled in the selected semester  
6. Enrolment result is displayed with course enrolment records for the semester

---

## 🔐 Security

- Password hashing (no plaintext storage)
- Database & certificate credentials stored in configuration files 
- Configuration files excluded via `.gitignore`
- HTTPS communication using self-signed certificates

---

## ⚠️ Limitations

- No live demo (requires local database setup)
- Self-signed HTTPS may trigger browser warnings
- No admin functionality
> **Note:** The system was implemented using the provided database schema.


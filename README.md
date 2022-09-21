# Soen387-App

40134064 - Nicolo Tabar
40057671 - Maamar Kouadri
40111868 - William Robinson

### Installation Instructions

1. Install the following dependencies:
    1. Run DB schema provided from assignment 2 to have access to the database sample data.
    2. From the database tab on the right of the screen, edit the connection data to have the following configurations:

        Name: poll_db@localhost
        Host: localhost     Port:3306
        User: root
        Password: 
        Database: poll_db
        URL: jdbc:mysql://localhost:3306/poll_db

    3. javax.mail (https://javaee.github.io/javamail/)
        - To install javax.mail, open File > Project Structure > Libraries
            Hit the + button > Java > directory where javax.mail jar file is.
        - reload project.

3. run mySQL on port 3306 
4. run Tomcat 10.0.11

### Project Description

This project is a Full Stack web-based poll application system. It allows users to log in , create a profile, and vote in a poll. The whole point of this app is to demonstrate how we can create a Frontend that will communicate with the Java server/servlet through the HTTP Get and Post verbs/methods and manipulate the database. 

#### Technologies used :
Frontend: JSP,Html,Css , JavaScript , Bootstrap  
Backend language: Java / Java Servlet
Database : SQL 

#### Programming Concepts:
•	Object Oriented Programming

•	Dao design Pattern 

•	SQL database and CRUD operations

•	Database Design can be found in : Soen387-App/Assignment3/Diagram/Entinty.JPG

#### The assignment consists of two main  parts: 

#### 1) User/Poll Management 
•	User Log In

•	User change/forget Password 

•	Email Verification

•	Poll manager  (manages, consults results of  polls using PIN#)

•	Business Layer containing the classes that will hold the data 

•	Java Servlet that provides server-side control 
#### 2) Database 
•	Tables : users, poll, vote , choice, haspoll UML can be found at path: Soen387-App/Assignment3/Diagram/Entinty.JPG

•	Queries : 
• Create/Update Poll
• Create/Update Votes
• Read/Retrieve Users
• Read/Retrieve User Pin  and more , all queries implementations can be found at  path  : Assignment3/src/main/java/JDBC/daoimpl/UserDaoImpl.java







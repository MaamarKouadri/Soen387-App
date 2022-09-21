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

#### Test

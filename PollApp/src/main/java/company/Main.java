package company;

import com.pollapp.jpa.UserJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("PollApp");

    public static void main(String[] args) throws Exception {
        // write your code here
        //testPollManager();
        //testPasswordHash();
        testEntityManagerFactory();
    }

    static public void testEntityManagerFactory() {
        create(23,"swag","swag@email.com");
        ENTITY_MANAGER_FACTORY.close();
    }

    static public void create(long id, String userName, String email) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();

            // Begin the transaction
            transaction.begin();

            // Create a new userJPA object
            UserJPA user = new UserJPA();
            user.setId(id);
            user.setUserName(userName);
            user.setEmail(email);

            // Save the userJPA object
            manager.persist(user);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }


    static public void testPasswordHash() {
        User a = new User("User1","116");

        String Password ="Busquet";

        String cryptedPassword =  a.HashFunction(Password);

        System.out.println("Original Password: " +  Password);

        System.out.println("Crypted Password: " +  cryptedPassword);
    }

    static public void testPollManager() throws Exception {
        PollManager pm = new PollManager();

        int choiceSize = 5;
        int userSize = 50;

        Choice[] choices = new Choice[choiceSize];
        for(int i = 0; i < choiceSize; i++){
            Choice c = new Choice("choice" + i, "description" + i);
            choices[i] = c;
        }

        User[] users = new User[userSize];
        for(int i = 0; i < userSize; i++){
            User u = new User(User.PARTICIPANT,"uniqueId" + i);
            users[i] = u;
        }

        pm.CreatePoll("myName","myQuestion",choices,users);
        pm.RunPoll();
        pm.vote(users[8],choices[2]);
        pm.ReleasePoll();
        pm.getPollResults();
        try {
            pm.downloadPollDetails();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public void testPoll(){

        int choiceSize = 5;
        int userSize = 50;

        Choice[] choices = new Choice[choiceSize];
        for(int i = 0; i < choiceSize; i++){
            Choice c = new Choice("choice" + i, "description" + i);
            choices[i] = c;
        }

        User[] users = new User[userSize];
        for(int i = 0; i < userSize; i++){
            User u = new User(User.PARTICIPANT,"uniqueId" + i);
            u.vote(getRandomChoice(choices));
            users[i] = u;
        }
        Poll p = new Poll("myTitle","myQuestion",choices,users);

        p.getPollResults();
        try {
            p.downloadPollResults();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public Choice getRandomChoice(Choice[] choices) {
        Random rand = new Random();
        int size = choices.length;
        return choices[rand.nextInt(size)];
    }
}

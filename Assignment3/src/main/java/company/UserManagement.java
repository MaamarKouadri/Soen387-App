package company;

import UserManagementInterface.IUserManagament;
import entity.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

public class UserManagement implements IUserManagament {
    private static final int MAX_UID_LENGTH = 30;
    private UsersEntity currentUser;
    private boolean hasSignedUp = false;
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    @Override
    public void signUp(String username, String email) {
        String verificationToken = generateToken();
        try {
            JavaMailUtil.sendMail("nicosaidhai@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void forgotPassword(String email) {

    }

    @Override
    public boolean emailVerification(String email) {
        return VerifyEmail(email);
    }

    @Override
    public int changePassword(String email, String password) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();

            // Begin the transaction
            transaction.begin();

            try {
                int execution = manager.createNativeQuery("UPDATE users  SET UserPassword = ?  WHERE Email =?")
                        .setParameter(1, password).
                        setParameter(2, email).
                        executeUpdate();
                System.out.println("The Password has been updated or no ?" + execution);
                if(execution == 0) {
                    return -1;
                }
            }
            catch(Exception e ) {
                String message = e.getMessage();
                System.out.println("The Password has been updated or no ?" + message);
            }


            // Commit the transaction
            transaction.commit();

            // Create a new userJPA object
//            UserJPA user = new UserJPA();
//            user.setId(id);
//            user.setUserName(userName);
//            user.setEmail(email);


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
        return 0;
    }


    // create user only once password has been provided
    private void createUser(String username, String email, String password) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();

            // Begin the transaction
            transaction.begin();

            // Create a new userJPA object
            UsersEntity user = new UsersEntity();
            user.setUserName(username);
            user.setEmail(email);

            // hash password
            user.setUserPassword(HashFunction(password));

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

    private String generateToken() {
        Random r = new Random();
        StringBuilder str = new StringBuilder();
        String chars="abcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < MAX_UID_LENGTH; i++) {
            str.append(chars.charAt(r.nextInt(chars.length())));
        }
        return str.toString();
    }

    // New HashFunction
    public String HashFunction(String password) {
        String hashed ="";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            //update the md object
            md.update(password.getBytes(), 0, password.length());
            //output the MD5 equivalent
            //System.out.println(new BigInteger(1, md.digest()).toString(16));
            hashed = new BigInteger(1, md.digest()).toString(16);
            return hashed;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashed;
    }

    private static boolean  VerifyEmail(String email){
        boolean EmailIsHere = false;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();

            // Begin the transaction
            transaction.begin();
            String custEmail="";

            List list = manager.createQuery(
                            "SELECT email FROM UsersEntity c WHERE c.email LIKE :custName")
                    .setParameter("custName", email)
                    .setMaxResults(10)
                    .getResultList();

            // Commit the transaction
            transaction.commit();
            int size = list.size();

            for(Object s : list){
                String data = s.toString();
                System.out.println(s);
            }

            if(size >0)
                return true;

            else
                return false;


            // Create a new userJPA object
//            UserJPA user = new UserJPA();
//            user.setId(id);
//            user.setUserName(userName);
//            user.setEmail(email);



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


        return EmailIsHere;

    }
}
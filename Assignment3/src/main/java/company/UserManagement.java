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
    public UsersEntity currentUser;
    private boolean hasSignedUp = false;
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    @Override
    public void signUp(String username, String email) {
        currentUser = new UsersEntity();
        //String verificationToken = generateToken();
        try {
            currentUser.setUserId(generateId());
            currentUser.setUserName(username);
            currentUser.setEmail(email);
            JavaMailUtil.sendMailCreate(username, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int forgotPassword(String email) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        // Get a transaction
        transaction = manager.getTransaction();

        // Begin the transaction
        transaction.begin();

        List list = manager.createNativeQuery("SELECT PasswordChange FROM users WHERE Email = ? AND PasswordChange = ? ").
                setParameter(1, email).
                setParameter(2, "Yes").
                getResultList();

        if(list.isEmpty()) {
            currentUser = new UsersEntity();
            try {
                currentUser.setEmail(email);
                JavaMailUtil.sendMailForgetPassword(email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }
        return 0;
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
                List list = manager.createNativeQuery("SELECT PasswordChange FROM users WHERE Email = ? AND PasswordChange = ? ").
                        setParameter(1, email).
                        setParameter(2, "Yes").
                        getResultList();

                if (list.size() == 1){
                    System.out.println("User has already updated their password");
                    return -1;

                }else {

                    try {
                        int execution2 = manager.createNativeQuery("UPDATE users  SET UserPassword = ?, PasswordChange = ? WHERE Email =?")
                                .setParameter(1, password).
                                setParameter(2, "Yes").
                                setParameter(3, email).
                                executeUpdate();
                        if (execution2 == 1) {
                            System.out.println("Password has been succefully updated");

                        }
                        System.out.println("The Password has been updated or no ?" + execution2);
                    } catch (Exception e) {
                        String message = e.getMessage();
                        System.out.println("The Password has been updated or no ?" + message);
                        return -1;
                    }
                }
            } catch (Exception e) {
                String message = e.getMessage();
                System.out.println("The Password has been updated or no ?" + message);
                return -1;
            }
            // Commit the transaction
            transaction.commit();

        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
            return -1;
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return 0;
    }

    public void setPassword(String password) {
        if(currentUser != null) {
            createUser(currentUser.getUserName(),currentUser.getEmail(), password);
        }
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

            // hash password
            currentUser.setUserPassword(password);
            // Save the userJPA object
            manager.persist(currentUser);

            int i = manager.createNativeQuery("UPDATE users SET PasswordChange = ? WHERE Email = ?")
                    .setParameter(1, "No").
                    setParameter(2, email).
                    executeUpdate();

            if(i == 1) {
                System.out.println("Updated passwordChange");
            } else {
                System.out.println("PasswordChange was not updated");
            }

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


    private int oldChangePassword(String email, String password) {
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

    private int generateId() {
        return (int) ((Math.random() * (10000000)));
    }
}
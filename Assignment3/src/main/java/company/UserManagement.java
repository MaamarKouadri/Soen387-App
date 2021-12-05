package company;

import UserManagementInterface.IUserManagament;
import entity.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Random;

public class UserManagement implements IUserManagament {
    private static final int MAX_UID_LENGTH = 30;
    private UsersEntity currentUser;

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    @Override
    public void signUp(String username, String email) {
        String verificationToken = generateToken();
    }

    @Override
    public void forgotPassword(String email) {

    }

    @Override
    public boolean emailVerification() {
        return false;
    }

    @Override
    public void changePassword(String password) {
        if(currentUser!=null && false) {
            EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                // Get a transaction
                transaction = manager.getTransaction();

                // Begin the transaction
                transaction.begin();
                currentUser.setUserPassword(password);

                // Save the userJPA object
                manager.persist(currentUser);

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
}
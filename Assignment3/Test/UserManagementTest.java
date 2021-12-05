import company.User;
import company.UserManagement;
import entity.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

public class UserManagementTest {
    UserManagement userManagement = new UserManagement();

    @BeforeClass
    public static void testDatabaseConnection() {
        System.out.println("Testing database connection");
        EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        // Get a transaction
        transaction = manager.getTransaction();

        // Begin the transaction
        transaction.begin();

        try {

            // Create a new userJPA object
            UsersEntity user = new UsersEntity();
            user.setUserName("username");
            user.setEmail("email");

            // hash password
            user.setUserPassword("password");

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

    @Test
    public void testChangePasswordExists() {
        boolean emailExists = userManagement.emailVerification("hotdog@gmail.com");
        System.out.println(emailExists);
        if(emailExists) {
            assertTrue("hello world", true);
        } else {
            assertTrue("Error:Email was not valid.", false);
        }
    }

    @Test
    public void testChangePasswordEmailDoesntExist(){
        int val = userManagement.changePassword("email@fake.com","password123");

        if( val <= 0 ) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }
    @Test
    public void testWrongEmail() {
        boolean emailExists = userManagement.emailVerification("email@fake.com");
        assertTrue(!emailExists);
    }
}

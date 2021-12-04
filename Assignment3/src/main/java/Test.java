import entity.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
    public static void main(String[] args) {



        int ID = 133;
        String userName = "Carl";
        String email = "Carl@live.ca";

        create(ID,userName,email);

    }
    static public void testEntityManagerFactory() {
        create(23,"swag","swag@email.com");
        ENTITY_MANAGER_FACTORY.close();
    }

    static public void create(int id, String userName, String email) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();

            // Begin the transaction
            transaction.begin();

            // Create a new userJPA object
//            UserJPA user = new UserJPA();
//            user.setId(id);
//            user.setUserName(userName);
//            user.setEmail(email);
            UsersEntity user = new UsersEntity();
            user.setId(5);
            user.setUserId(id);
            user.setUserName(userName);
            user.setEmail(email);
            user.setUserPassword("sdasd34234kjhk24");

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
}

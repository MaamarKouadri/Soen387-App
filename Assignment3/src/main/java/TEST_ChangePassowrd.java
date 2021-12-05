import entity.UsersEntity;
import jakarta.persistence.*;

import java.util.List;

public class TEST_ChangePassowrd {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
    public static void main(String[] args) {

        int ID = 4546;
        String userName = "Maamar3";
        String email = "Maamar3@live.ca";

        //create(ID,userName,email);
        boolean isthere = VerifyEmail("Goku@myhero.com");

        System.out.println("Is the email entered there ? " +isthere );

        ChangePassword("Goku@myhero.com","Chicken");

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

    public static boolean  VerifyEmail(String email){

        boolean EmailIsHere = false;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();

            // Begin the transaction
            transaction.begin();
            String custEmail="";

            List list=   manager.createQuery(
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

    public static void ChangePassword(String email ,String password){
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

                }else {

                    try {
                        int execution2 = manager.createNativeQuery("UPDATE users  SET UserPassword = ?, PasswordChange = ? WHERE Email =?")
                                .setParameter(1, password).
                                setParameter(2, "Yes").
                                setParameter(3, email).
                                executeUpdate();
                        System.out.println("The Password has been updated or no ?" + execution2);
                    } catch (Exception e) {
                        String message = e.getMessage();
                        System.out.println("The Password has been updated or no ?" + message);
                    }
               System.out.println("Password has been succefully updated"); }
            } catch (Exception e) {
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


    }
}

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
    public void signUp(String email, String username) {
        String verificationToken = generateToken();
        try {
            JavaMailUtil.sendMail("dummy.for.school.23@gmail.com", "Will");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
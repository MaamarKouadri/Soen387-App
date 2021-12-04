package company;

import UserManagementInterface.IUserManagament;

import java.util.Random;

public class UserManagement implements IUserManagament {
    private static final int MAX_UID_LENGTH = 30;

    @Override
    public void signUp(String username, String email) {
        String verificationToken = generateToken();
    }

    @Override
    public void forgotPassword() {

    }

    @Override
    public boolean emailVerification() {
        return false;
    }

    @Override
    public void changePassword() {

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
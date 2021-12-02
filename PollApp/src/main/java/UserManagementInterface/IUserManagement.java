package UserManagementInterface;

public interface IUserManagement {
    void signUp(String username, String email);
    void forgotPassword();
    boolean emailVerification();
    void changePassword();
}

package UserManagementInterface;

public interface IUserManagament {
    void signUp(String username, String email);
    void forgotPassword();
    boolean emailVerification();
    void changePassword();
}

package UserManagementInterface;

public interface IUserManagament {
    void signUp(String username, String email);
    int forgotPassword(String email);
    boolean emailVerification(String email);
    int changePassword(String password, String email);
}

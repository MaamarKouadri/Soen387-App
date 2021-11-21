package JDBC.dao;

import company.Poll;
import company.User;;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

// com.example.dao.UserDAO (Data Access Object) can do CRUD operations, it can Create, Retreive, Updata, Delete from our table.
public interface UserDAO {

    User getUser(String id) throws SQLException;
    Set<User> getAllUsers();
    ArrayList<Poll> ListOfPollsByUsers( String UserId);
    ArrayList<Poll> ListOfActivePolls( );
    String GetUserName(String UserId);
    // Come back here
    void UpdatePoll(String NewChoiceName, String PollId, String ChoiceName);
    int NumberOfUserChoice( String PollId,String ChoiceID);

    public Poll getPoll(String id);
    boolean verifyPinExistance(String s) ;
    boolean verifyPollIDExistance(String s) ;
/*


    User getUserByUserNameAndPassword();

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

*/
}
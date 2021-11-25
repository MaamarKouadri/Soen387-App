package JDBC.dao;

import company.Poll;
import company.User;;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

// com.example.dao.UserDAO (Data Access Object) can do CRUD operations, it can Create, Retreive, Updata, Delete from our table.
public interface UserDAO {

    User getUser(String userId) throws SQLException;
    Set<User> getAllUsers();
    ArrayList<Poll> ListOfPollsByUsers( String UserId);
    ArrayList<Poll> ListOfActivePolls( );
    String GetUserName(String UserId);
    // Come back here
    void UpdatePoll(String NewChoiceName, String PollId, String ChoiceName);
    int NumberOfUserChoice( String PollId,String ChoiceID);

    Poll getPoll(String id);
    boolean verifyPinExistance(String pin, String pollid);
    boolean verifyPollIDExistance(String s) ;
    boolean isHasPollActive(String pollId, String userId);
    boolean isDeleted(String pollId);

    ArrayList<String> getVotes(String pollID);
    boolean hasVotes(String pollID);
    void insertVote(String pollID, String pin, int choiceId);
    void updateVote(String pollID, String pin, int choiceId);
    void deleteVotes(String pollID);

    void CreatePoll(String pollID, String name,String Choices, String question, String timestamp,String userID);
    void insertChoice(String pollID,String ChoiceName, String ChoiceID);
    void insertHasPoll(String pollID,int userID);
    String FindUserID(String UserName);

    void UpdatePollInstance(String pollID, String name,String Choices, String question, String timestamp, String state);
    void UpdateChoice(String PollID , String ChoiceName, String ChoiceID );
    void UpdatePollState(String pollID, String state);
    void UpdatePollisDeleted(String pollID, String isDeleted);
    ArrayList<String>  GetChoiceID(String PollID);
/*


    User getUserByUserNameAndPassword();

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

*/
}
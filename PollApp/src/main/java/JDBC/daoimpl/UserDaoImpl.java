package JDBC.daoimpl;

import company.Choice;
import company.Poll;
import company.State;
import company.User;
import JDBC.dao.UserDAO;
import JDBC.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UserDaoImpl implements UserDAO {
    private static final int MAX_UID_LENGTH = 10;
    static int ChoiceID = 0;
    @Override
    public User getUser(String id) {
        // DB connection
        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE UserName=?");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                //com.example.model.User
//                user = new User();
//                user.setUserId( rs.getInt("id") );
//                user.setUsername( rs.getString("username") );
//                user.setFirstName( rs.getString("first_name") );
//                user.setLastName( rs.getString("Last_name") );
//                user.setPassword( rs.getString("pass") );
//                user.setGender( rs.getString("age") );
//                user.getStatus( rs.getInt("status") );
//                return user;

                // It's more convenient to make a separate method to extract user data from result set as we'd use it in many methods.
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Set<User> getAllUsers() {

        Connection connection = DBConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            Set users = new HashSet();

            while(rs.next())
            {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }

            return users;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    /**
     * Come back for this function to create a function that extracts the list of Poll
     */
    public ArrayList<Poll> ListOfPollsByUsers(String UserId) {

        ArrayList<Poll> ListOfPolls = new ArrayList<Poll>();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement  stmt = connection.prepareStatement("SELECT pollId FROM `haspoll`,`users` WHERE UserName =? and users.UserId=haspoll.UserId and isActive=\"Yes\"");
            //vegeta
            stmt.setString(1,UserId);
            ResultSet rs = stmt.executeQuery();
        //Find Poll will return a list of Users by Poll
            while (rs.next()) {
                //Vegeto Come back here

                String PollID = rs.getString("PollId");
                System.out.println("Poll ID is " + PollID +" with length " + PollID.length());
                //Select distinct(PollId) as PollId from poll where PollId= "john";
                stmt = connection.prepareStatement("SELECT * FROM poll WHERE PollId=?");
                stmt.setString(1,PollID);
                ResultSet rs2 = stmt.executeQuery();
                while(rs2.next()) {
                    Poll user = createPollObject(rs2);

                    System.out.println("The Poll " + user);
                    ListOfPolls.add(user);
                }
            }
          //  return ListOfPolls;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return ListOfPolls;
    }

    @Override
    /**
     * Returns List of not Active Polls
     */
    public ArrayList<Poll> ListOfActivePolls() {
        ArrayList<Poll> ListOfPolls = new ArrayList<Poll>();
        Connection connection = DBConnection.getConnection();
        try {
            System.out.println("Before haspoll ");
            PreparedStatement stmt = connection.prepareStatement("SELECT PollId FROM `haspoll`WHERE isActive =?");
            stmt.setString(1,"No");
            ResultSet rs =stmt.executeQuery();
            System.out.println("After haspoll3 ");
            while(rs.next())
            {
                String PollId= rs.getString("PollId");
                System.out.println(PollId);
                PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM `poll`WHERE PollId =?");
                stmt2.setString(1,PollId);
                ResultSet rs2 =stmt2.executeQuery();
                //Vegeto Come back here
                System.out.println("After haspoll4 ");

                while(rs2.next()) {
                    Poll user = createPollObject(rs2);
                    ListOfPolls.add(user);
                    System.out.println("After haspoll4 ");
                }
            }

            //  return ListOfPolls;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return ListOfPolls;
    }

    @Override
    /**
     * Returns the username based on the ID s
     */
    public String GetUserName(String UserId) {

        String name = "";
        // DB connection
        Connection connection = DBConnection.getConnection();

        try {
             System.out.println("Name " + name);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM users WHERE UserId=" + UserId);

            if(rs.next())
            {
                name =  rs.getString("username");
                      return name;
                //com.example.model.User
//                user = new User();
//                user.setUserId( rs.getInt("id") );
//                user.setUsername( rs.getString("username") );
//                user.setFirstName( rs.getString("first_name") );
//                user.setLastName( rs.getString("Last_name") );
//                user.setPassword( rs.getString("pass") );
//                user.setGender( rs.getString("age") );
//                user.getStatus( rs.getInt("status") );
//                return user;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return name;
    }

    @Override
    /**
     * Ths function will update the poll
     */
    public void UpdatePoll(String NewChoiceName, String PollId, String ChoiceName) {

        // DB connection
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Choice SET ChoiceName =? WHERE PollId =? AND ChoiceName = ?");

            ps.setString(1,NewChoiceName);
            ps.setString(2, PollId);
            ps.setString(3, ChoiceName);
            int i = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }


    }

    @Override
    /**
     * Returns the number of times a choice has been selected  depending on Poll ID and Choice ID
     */
    public int NumberOfUserChoice(String PollId, String ChoiceID) {

        int NumberOfTimesSelected =0;

        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(choiceID) FROM vote  WHERE pollId =? AND choiceID =?");
            stmt.setString(1,PollId);
            stmt.setString(2,ChoiceID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String NumberOFtimes = rs.getString("Count(choiceID)");
                NumberOfTimesSelected = Integer.parseInt(NumberOFtimes);
            }
            return NumberOfTimesSelected;
            //com.example.model.User
//                user = new User();
//                user.setUserId( rs.getInt("id") );
//                user.setUsername( rs.getString("username") );
//                user.setFirstName( rs.getString("first_name") );
//                user.setLastName( rs.getString("Last_name") );
//                user.setPassword( rs.getString("pass") );
//                user.setGender( rs.getString("age") );
//                user.getStatus( rs.getInt("status") );
//                return user;

                // It's more convenient to make a separate method to extract user data from result set as we'd use it in many methods.


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }



        return NumberOfTimesSelected;
    }

    @Override
    // Finds a Poll by ID
    public Poll getPoll(String id) {
        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement  stmt = connection.prepareStatement("SELECT * from poll users WHERE PollId=?");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {

                // It's more convenient to make a separate method to extract user data from result set as we'd use it in many methods.
                return createPollObject(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    /**
     * This function will verify the existence of the Pin
     */
    public boolean verifyPinExistance(String pin, String pollid) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM vote WHERE pin = ? AND PollId = ?");
            // Write Sql querie
            stmt.setString(1,pin);
            stmt.setString(2,pollid);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                String pid = rs.getString("PollId");
                if (pid.length() > 0)
                    return true;
                else
                    return false;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

       return false;
    }

    @Override
    /**
     * Verifies the POLL ID  Existance in the POLL table
     */
    public boolean verifyPollIDExistance(String s) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(PollId) FROM poll where PollId=?");
           stmt.setString(1,s);
            // Write Sql querie
            ResultSet rs = stmt.executeQuery();
           while(rs.next()) {
               int count = Integer.parseInt(rs.getString("Count(PollId)"));

               if (count > 0)
                   return true;
               else
                   return false;
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }


    // check whether user created the given poll, and if its active
    @Override
    public boolean isHasPollActive(String pollId, String userId) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM hasPoll, users WHERE users.UserId=haspoll.UserId AND isActive= \"Yes\" AND users.UserName=?AND hasPoll.PollId=?");
            stmt.setString(1,userId);
            stmt.setString(2,pollId);
            // Write Sql querie
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                String pid = rs.getString("PollId");
                if (pid.length() <= 0)
                    return false;
                else
                    return true;
            } else {
                return false;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public ArrayList<String> getVotes(String pollID) {
        Connection connection = DBConnection.getConnection();
        ArrayList<String> votes = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT choiceName FROM vote,choice WHERE vote.pollid=? AND vote.choiceID = choice.ChoiceID AND vote.PollId = choice.PollId");
            stmt.setString(1,pollID);

            ResultSet rs2 = stmt.executeQuery();
            while(rs2.next()) {
                String vote = rs2.getString("choiceName");
                votes.add(vote);
            }
            return votes;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return votes;
    }

    @Override
    public boolean hasVotes(String pollID) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(PollId) FROM vote where PollId=?");
            stmt.setString(1,pollID);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int count = Integer.parseInt(rs.getString("Count(PollId)"));
                return count > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public void insertVote(String pollID, String pin, int choiceId) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO vote (PollId,Pin,choiceID) values(?,?,?)");
            stmt.setString(1,pollID);
            stmt.setString(2,pin);
            stmt.setString(3,String.valueOf(choiceId));

            // Write Sql querie
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateVote(String pollID, String pin,  int choiceId) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE vote SET choiceID = ? WHERE Pin = ? AND PollId = ?");
            stmt.setString(1,String.valueOf(choiceId));
            stmt.setString(2,pin);
            stmt.setString(3,pollID);

            // Write Sql querie
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteVotes(String pollID) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM vote WHERE PollId=?");
            stmt.setString(1,pollID);
            // Write Sql querie
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void CreatePoll(String pollID, String name,String Choices, String question, String timestamp,String UserName) {
        Connection connection = DBConnection.getConnection();
        try {
            String query = "INSERT INTO `poll` (`PollName`, `Question`, `Choices`, `PollId`, `isDelete`, `timeStamp`) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO `poll` (`PollName`, `Question`, `Choices`, `PollId`, `isDelete`, `timeStamp`, `state`) VALUES  (?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,name);
            stmt.setString(2,question);
            stmt.setString(3,Choices);
            stmt.setString(4,pollID);
            stmt.setString(5,"No");
            stmt.setString(6,timestamp);
            stmt.setString(7,"Created");
            // Write Sql querie
            stmt.executeUpdate();

            ChoiceID++;
            String [] choices = Choices.split(",");

            for(String s : choices) {
                insertChoice(pollID, s,String.valueOf(ChoiceID));
                ChoiceID++;
            }

            String UserID ="";
            //  ResultSet rs = stmt.executeQuery();
            int userID = GetUserID(UserName);
            insertHasPoll(pollID,userID);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void insertChoice(String pollID, String ChoiceName, String ChoiceID) {

        Connection connection = DBConnection.getConnection();
        try {
            String query = "INSERT INTO `poll` (`PollName`, `Question`, `Choices`, `PollId`, `isDelete`, `timeStamp`) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO `choice` (`PollId`, `ChoiceID`, `choiceName`) VALUES (?, ?, ?)");
            stmt.setString(1,pollID);
            stmt.setString(2,ChoiceID);
            stmt.setString(3,ChoiceName);

            // Write Sql querie
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertHasPoll(String pollID, int userID) {
        Connection connection = DBConnection.getConnection();
        try {
            String query = "INSERT INTO `poll` (`PollName`, `Question`, `Choices`, `PollId`, `isDelete`, `timeStamp`) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement("  INSERT INTO `haspoll` (`PollId`, `UserId`, `isActive`) VALUES (?, ?, ?)");
            stmt.setString(1,pollID);
            stmt.setString(2,Integer.toString(userID));
            stmt.setString(3,"Yes");

            // Write Sql querie
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void UpdatePollInstance(String pollID, String name,String Choices, String question, String timestamp, String state) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(" UPDATE poll SET state=?, Question =? ,PollName =? , Choices =? ,timeStamp=?  WHERE PollId =? ");
            stmt.setString(1,state);
            stmt.setString(2,question);
            stmt.setString(3,name);
            stmt.setString(4,Choices);
            stmt.setString(5,timestamp);
            stmt.setString(6,pollID);

            // Write Sql querie
            stmt.executeUpdate();

            // Updating the choice
            String [] MyChoices = Choices.split(",");
            //Make sure than number of choices is same than number of choice ID
            ArrayList<String> IDs = GetChoiceID(pollID);

            int index =0;
            for(String c: MyChoices) {
                //(String PollID, String ChoiceName ,String ChoiceID)
                UpdateChoiceID(pollID,c,IDs.get(index));
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch( SQLException e){
                e.printStackTrace();
            }
        }




    }

    @Override
    public void UpdateChoice(String PollID, String ChoiceName ,String ChoiceID) {

        // Createa a function that resturns the Choice ID one by one
        Connection connection = DBConnection.getConnection();

        /*
         update a poll instance from pollid (question, name, timestamp, isDeleted=True)
         */

        try {

            PreparedStatement stmt = connection.prepareStatement(" UPDATE choice SET choiceName =? Where PollId =? and ChoiceID =? ");
            stmt.setString(1,ChoiceName);
            stmt.setString(2,PollID);
            stmt.setString(3,ChoiceID);


            // Write Sql querie
            stmt.executeUpdate();

            /*
            while(rs.next()) {
                ResultSet rs = stmt.executeQuery();

            }
            */

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void UpdatePollState(String pollID, String state) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE poll SET state=? WHERE PollId=?");
            stmt.setString(1,state);
            stmt.setString(2,pollID);

            // Write Sql querie
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void UpdatePollisDeleted(String pollID, String isDeleted) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE poll SET isDelete=? WHERE PollId=?");
            stmt.setString(1,isDeleted);
            stmt.setString(2,pollID);

            // Write Sql querie
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<String> GetChoiceID(String PollID) {

        Connection connection = DBConnection.getConnection();
        ArrayList<String> IDs = new ArrayList<String>();
        try {

            PreparedStatement stmt = connection.prepareStatement("Select ChoiceID from choice where PollId=?");
            stmt.setString(1,PollID);
            ResultSet rs = stmt.executeQuery();

            boolean end = false;
            while(rs.next())
            {
                String myId = rs.getString("ChoiceID");
                IDs.add(myId);
                // It's more convenient to make a separate method to extract user data from result set as we'd use it in many methods.
            }

            return IDs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return IDs;
    }

    public void UpdateChoiceID(String PollID,String Choice, String ChoiceID) {


        Connection connection = DBConnection.getConnection();

        /*
         update a poll instance from pollid (question, name, timestamp, isDeleted=True)
         */

        try {

            PreparedStatement stmt = connection.prepareStatement(" UPDATE Choice SET choiceName =? WHERE PollId =? AND ChoiceId =?");
            stmt.setString(1,Choice);
            stmt.setString(2,PollID);
            stmt.setString(3,ChoiceID);

            // Write Sql querie
            stmt.executeUpdate();

            /*
            while(rs.next()) {
                ResultSet rs = stmt.executeQuery();

            }
            */

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }



    }

    @Override
    public String FindUserID(String UserName) {
        String query ="SELECT pollId FROM `haspoll`,`users` WHERE UserName =? and users.UserId=haspoll.UserId";

        Connection connection = DBConnection.getConnection();
        String UserId ="";
        try {

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,UserName);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                UserId = rs.getString("pollId");
                return UserId;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return UserId;
    }

    public int GetUserID(String Username) {

        int ID =0;
        Connection connection = DBConnection.getConnection();

        try {

            PreparedStatement stmt = connection.prepareStatement("SELECT UserId FROM users WHERE UserName=?");
            stmt.setString(1,Username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {

                int ID2 = rs.getInt("UserId");
                System.out.println("ID is " + ID);
                // It's more convenient to make a separate method to extract user data from result set as we'd use it in many methods.
                return ID2;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return ID;
    }

    /*
    @Override
    public User getUserByUserNameAndPassword() {
        // TODO: Try it yourself
        // Hint : Use 'AND' in your query
        return null;
    }

    @Override
    public boolean insertUser(User user) {

        Connection connection = DBConnection.getConnection();

        try {
            String query = "INSERT INTO user_details (username,first_name,last_name,gender,password,status) VALUES (?, ?, ?, ?, ?, ?)";
            // Passing Statement.RETURN_GENERATED_KEYS to make getGeneratedKeys() work
            PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,user.getUniqueId());


            int i = ps.executeUpdate();

            if(i == 1) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // generatedKeys() checkout this method it's doing interesting job
                        user.setUserId(generatedKeys.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE user_details SET username=?, first_name=?, last_name=?, gender=?, password=?, status=? WHERE user_id=?");

            ps.setString(1,user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getPassword());
            ps.setInt(6,user.getStatus());
            ps.setInt(7,user.getUserId());

            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {

        Connection connection = DBConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM user_details WHERE user_id=" + id);

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }
*/
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {

      String UserId = ( rs.getString("UserID") );
        User user = new User(UserId);
        return user;
    }


     Poll createPollObject( ResultSet rs){


         try {

             System.out.println("Entering the creating Poll object method ");
             String PollName = rs.getString("PollName");

             System.out.println("Poll Name is " + PollName);
             String Question = rs.getString("Question");
             System.out.println("Question is " + Question);
             String Choices = rs.getString("Choices");
             System.out.println("Choices is " + Choices);
             String [] ChoiceArray =Choices.split(",");
             Choice [] TheChoices = new Choice[ChoiceArray.length];

             int index =0;

             for(String a : ChoiceArray){
                 TheChoices[index] = new Choice(a,"Choice "+(index+1));
                 index++;
             }
             String PollId = rs.getString("PollId");
             String state = rs.getString("state");
             if(state == null) {
                 state = "Created";
             }

             Poll poll = new Poll(PollName,Question,TheChoices,PollId);
             switch(state) {
                 case "Running":
                     poll.setStatus(State.Running);
                     break;
                 case "Created":
                     poll.setStatus(State.Created);
                     break;
                 case "Released":
                     poll.setStatus(State.Released);
                     break;
                 default:
                     poll.setStatus(State.Created);
             }
              return poll;
         }
         catch (SQLException e) {
             e.printStackTrace();
         }

        return null;

     }

    ArrayList<Poll>FindPolls(ResultSet rs){
        ArrayList<Poll> List = new ArrayList<>();
        Connection connection2 = DBConnection.getConnection();

        try {
            Statement stmt2 = connection2.createStatement();
            while (rs.next()) {
                //Vegeto Come back here

                String PollID = rs.getString("PollId");
                System.out.println("Poll ID is " + PollID);
                //("SELECT * FROM poll WHERE PollId=" + PollID)
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM poll");

                while(rs2.next()) {
                    Poll user = createPollObject(rs2);
                    System.out.println("The Poll " + user);
                    List.add(user);
                }
            }
        }
       catch (SQLException e) {
        e.printStackTrace();
      } finally {
          try {
            connection2.close();
         } catch(SQLException e){
            e.printStackTrace();
         }
    }

        return List;
    }

    public String generateUID() {
        Random r = new Random();
        StringBuilder str = new StringBuilder();
        String chars="abcdefghjkmnpqrstvexyz0123456789";
        for (int i = 0; i < MAX_UID_LENGTH; i++) {
            str.append(chars.charAt(r.nextInt(chars.length())));
        }
        return str.toString();
    }
}

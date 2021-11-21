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
import java.util.Set;

public class UserDaoImpl implements UserDAO {

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
            PreparedStatement  stmt = connection.prepareStatement("SELECT pollId FROM `haspoll` WHERE UserId =?");
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
     * This function will verify the existance of the Pin
     */
    public boolean verifyPinExistance(String pin) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT Count(UserId) FROM haspoll where UserId=?");
            // Write Sql querie
            stmt.setString(1,pin);
            ResultSet rs = stmt.executeQuery();
          //buu
            while(rs.next()) {
                String count = (rs.getString("Count(UserId)"));
                System.out.println("The pin is " + pin);
                System.out.println("Count is " + count);
                int Count = Integer.parseInt(count);

                if(Count >0)
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

             /*
             for(String a : ChoiceArray){

                 TheChoices[index] = new Choice(a,"Choice "+(index+1));
                 System.out.println("------------------------------------------------");
                 System.out.println("Choice is " + TheChoices[index] );
                 System.out.println("------------------------------------------------");
                 index++;
             }
*/
             String PollId = rs.getString("PollId");

              Poll poll = new Poll(PollName,Question,TheChoices,PollId);
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

}

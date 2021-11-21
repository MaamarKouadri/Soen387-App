package com.example.pollapp;

import java.io.*;
import java.util.*;

import JDBC.daoimpl.UserDaoImpl;
import JDBC.db.DBConnection;
import company.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;
    private Choice [] ChoiceArray;
    private PollManager Poll;
    private User [] UserArray;
    private String UserTostring;
    private int NumberOfVisits = 0;
    private ArrayList<User> Array = new ArrayList<User>() ;
    private ArrayList<Choice> ArrayChoice = new ArrayList<Choice>() ;
    private String ErrorMessage;
    private boolean isError;

    public void init() {
        isError = false;
        Poll = new PollManager();
        message = "We have just created a user";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        boolean Testing  = (boolean)session.getAttribute("Testing");
        PrintWriter out = response.getWriter();
        if(Testing ) {
            // This is the tool used to manipulate the database
            UserDaoImpl DatabaseManager = new UserDaoImpl();
            try {

                out.println("We are testing");

                // Trying to retrieve all the Users in the Database
            Set<User> set =    DatabaseManager.getAllUsers();
            int index = 1;

            for(User s : set){
               out.println();
                out.println("----------");
               out.println("\r\n");
                out.println(s);
                out.println("\r\n");
                out.println("----------");
                out.println();

            }
            out.println("We are done get All Users");
                out.println();

                out.println("We are testing get user by ID");
                out.println('\n');
                out.println("----------");
                out.println("----------");
               User a = DatabaseManager.getUser("dasdasf");

                out.println("We are Printing the following user ");
                out.println("----------");
                out.println("----------");
                if(a != null)
                out.println(a);
                else
                    out.println("There is not user to print ");
                out.println("----------");
                out.println("----------");

                out.println(" We are testing the method list of polls");
                out.println("----------");
                out.println("----------");
              ArrayList<Poll> List =  DatabaseManager.ListOfPollsByUsers("4");

              for(Poll p : List){
                  out.println();
                  out.println("----------");
                  out.println("\r\n");
                  out.println(p);
                  out.println("\r\n");
                  out.println("----------");
                  out.println();
              }


                out.println(" We are testing the method list of active polls");
                out.println("----------");
                out.println("----------");

                ArrayList<Poll> List2 =  DatabaseManager.ListOfActivePolls();

                for(Poll p : List2){
                    out.println();
                    out.println("----------");
                    out.println("\r\n");
                    out.println(p);
                    out.println("\r\n");
                    out.println("----------");
                    out.println();
                }

                out.println(" We are testing getting the User Name by ID");
                out.println("----------");
                out.println("----------");

               String name = DatabaseManager.GetUserName("1");
               out.println(name);

                out.println(" Now we are trying update poll");
                out.println("----------");
                out.println("----------");
              //  void UpdatePoll(String NewChoiceName, String PollId, String ChoiceName);
                DatabaseManager.UpdatePoll("Maamar","Air2021","Sunwing");

                out.println(" Testing Number of User choice");
                out.println("----------");
                out.println("----------");
               int NumberOfchoice= DatabaseManager.NumberOfUserChoice("Air2021","2");

               out.println(NumberOfchoice);

                out.println(" Testing GetPoll");
                out.println("----------");
                out.println("----------");
               Poll poll =  DatabaseManager.getPoll("Air2021");
                out.println(poll);


                out.println(" Verifying Pin existance");
                out.println("----------");
                out.println("----------");

                boolean verifyPinExistance = DatabaseManager.verifyPinExistance("9");
                boolean verifyPinExistance2 = DatabaseManager.verifyPinExistance("10");

                out.println("Does pin number 9 exist ? " + verifyPinExistance);
                out.println("Does pin number 10 exist ? " + verifyPinExistance2);


                out.println(" Verifying Poll ID existence");
                out.println("----------");
                out.println("----------");

              boolean VerifyPoll1 =   DatabaseManager.verifyPollIDExistance("Air2021");

              boolean VerifyPoll2 =   DatabaseManager.verifyPollIDExistance("sfa");


              out.println("Is Air2021 in the database  " + VerifyPoll1);

                out.println("Is sfa in the database  " + VerifyPoll2);

            }
            catch(Exception e){

                String message = e.getMessage();
                out.println(message);
            }
            // Here try all the methods and check the output



        }
        response.setContentType("text/html");

        //getServletContext().getRequestDispatcher("/includes/User.jsp").include(request,response);

        NumberOfVisits++;

        // reset error
        isError = false;



        boolean UserVisited  = (boolean)session.getAttribute("user");
        boolean PollVisited  = (boolean)session.getAttribute("Poll");
        boolean VoteVisited  = (boolean)session.getAttribute("vote");
        boolean PollManagementVisited =  (boolean)session.getAttribute("PollManagement");
        boolean HiddenManagementVisited =  (boolean)session.getAttribute("HiddenManagementSystem");
        boolean PollUpdateVisited =  (boolean)session.getAttribute("updatePoll");
        boolean AccessPollVisited =  (boolean)session.getAttribute("accessPoll");
        boolean AccessListPollsVisited =  (boolean)session.getAttribute("accessListPolls");
        boolean ListPollsVisited =  (boolean)session.getAttribute("listPolls");

        // perform appropriate action based on req/res
        if(UserVisited) {
            createUserPage(UserVisited, request, out, response, session);
        }
        if(PollVisited) {
            createPollPage(PollVisited, request, out, response, session);
        }
        if(PollUpdateVisited) {
            updatePollPage(PollUpdateVisited, request, out, response, session);
        }
        if(AccessPollVisited) {
            accessPoll(VoteVisited, request, out, response, session);
        }
        if(VoteVisited) {
            votePage(VoteVisited, request, out, response, session);
        }
        if(request.getParameter("displayResults") != null) {
            displayResultsPage(true, request, out, response, session);
        }
        if(PollManagementVisited) {
            pollManagementPage(PollManagementVisited, request, out, response, session);
        }
        if(HiddenManagementVisited) {
            hiddenManagementPage(HiddenManagementVisited, request, out, response, session);
        }
        if(AccessListPollsVisited) {
            accessListPollsPage(AccessListPollsVisited, request, out, response, session);
        }
        if(ListPollsVisited) {
            listPollsPage(ListPollsVisited, request, out, response, session);
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        // Releasing the Poll here.
        PrintWriter out = response.getWriter();
        String data = "Hello";
        //cchange the patth of the file
        String Choices;
        String Choices2;
        String Choicesx;
        String Choices2x;
        String[] ChoiceArrx;
        String[] ChoiceArr2x;
        try {

            session = request.getSession();
            Choicesx = session.getAttribute("FinalArray").toString();
            ChoiceArrx = Choicesx.split(",");
            Choices2x = session.getAttribute("FinalArray2").toString();
           ChoiceArr2x = Choices2x.split(",");

            int size = 0;
            int size2 = 0;

            size = ChoiceArrx.length;
            size2 = ChoiceArr2x.length;
             Choices =" Je suis la";

            //Choices2 =" Je suis Vegeta sdad";

            String[] ChoiceArr = {"Blue","Red"};
            String[] ChoiceArr2 = {"1","2"};
            //BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\maama\\OneDrive\\Bureau\\Soen387-App\\PollApp\\TheResultsFinal.txt"));

            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Nicolo pt 2\\Desktop\\School\\University\\6. FALL2021\\SOEN387\\finalResults.txt"));
            bw.write("This is our results File");
            bw.write("\n");
            bw.write("\n");

            out.println("This is our results File");
            out.println("\n");
            out.println("\n");

            int sum = 0;
            for (String s : ChoiceArr2) {

                sum += Integer.parseInt(s);
            }
            // Processing downlaod you only need those two arrays

            //((PollManager) session.getAttribute("PollObject")).downloadPollDetails();

            // write name and
            bw.write(Poll.getPoll().toString());
            out.println(Poll.getPoll().toString());
            int sum2 = 0;
            for (int j = 1; j < ChoiceArr2x.length; j++) {
                sum2+=Integer.parseInt(ChoiceArr2x[j]);
            }

            for (int i = 1; i < ChoiceArrx.length; i++) {
               double value = Double.parseDouble(ChoiceArr2x[i]) / sum2;
                double roundOff = (double) Math.round(value * 100) / 100;

                bw.write("Choice :" + ChoiceArrx[i] + "  Number of votes received: " + ChoiceArr2x[i] + " Percentage: " + roundOff +" %");
                bw.write("\n");

                out.println("Choice :" + ChoiceArrx[i] + "  Number of votes received: " + ChoiceArr2x[i] + " Percentage: " + roundOff +" %");
                out.println("\n");
            }
            bw.close();

            response.setContentType("text/plain");
            response.setHeader("Content-disposition", "attachment; filename=TheResultsFinal.txt");
             //Not sure about this one verify
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
            //Expires the cache control
            response.setHeader("Expires","-1");
            ServletOutputStream out3 = response.getOutputStream();
            out3.println("Vegeta is King");

            out.close();
            out3.close();
            // Releasing the Poll here.
            ((PollManager) session.getAttribute("PollObject")).ClosePoll();

            return;


        } catch (Exception ex) {
            return;
        }
        // Hello

    }

    public void destroy() {
    }

    public void createUserPage(boolean userVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        if(userVisited &&  request.getParameter("UserID") != null

                && request.getParameter("UserType") != null ){

            String ID = request.getParameter("UserID").toString();
            String type  = request.getParameter("UserType").toString();
            User currentUser = new User(type, ID);
            // check if a poll manager already exists in the system
            for(User u: Array) {
                if(u.getType().equals(User.POLL_MANAGER) && currentUser.getType().equals(User.POLL_MANAGER)) {
                    ErrorMessage = "Only one poll manager is allowed.";
                    session.setAttribute("ErrorMessage",ErrorMessage);
                    response.sendRedirect("ErrorHandling.jsp");
                    isError = true;
                }
            }

            // only send redirect if no error
            if(!isError) {
                Array.add(currentUser);
                response.sendRedirect("User.jsp");
            }
        }
    }

    public void createPollPage(boolean pollVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        if(Poll.getPollCreated()) {
            ErrorMessage = "Only one poll may be created at a time.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;
        } else {
            String PollID  = request.getParameter("PollUserID").toString();

            UserArray = Array.toArray(new User[0]);

            boolean isPollManager = false;

            // get poll manager access
            for(User a : UserArray){
                if(a.getType().contains(User.POLL_MANAGER)
                        && a.getUniqueId().contentEquals(PollID)
                ) {
                    isPollManager = true;
                }
            }

            // check if user is poll manager
            if (!isPollManager) {
                ErrorMessage = "You must be a poll manager to create a poll.";
                session.setAttribute("ErrorMessage",ErrorMessage);
                response.sendRedirect("ErrorHandling.jsp");
                isError = true;
            } else{
                if( pollVisited && request.getParameter("PollName")!= null
                        && request.getParameter("PollChoice") != null
                        && request.getParameter("Description") !=null
                        && request.getParameter("PollQuestion") != null &&
                        request.getParameter("PollUserID") != null
                ) {
                    String PollName = request.getParameter("PollName").toString();
                    String PollChoice  = request.getParameter("PollChoice").toString();


                    String DescriptionChoice  = request.getParameter("Description").toString();

                    String PollQuestion = request.getParameter("PollQuestion").toString();
                    session.setAttribute("PollQuestion", PollQuestion);
                    String[] arrOfChoices = PollChoice.split(",");

                    String[] arrOfDescription = DescriptionChoice.split(",");

                    boolean SameSize = false;

                    if(arrOfChoices.length == arrOfDescription.length ) {
                        SameSize = true;

                        session.setAttribute("Choices",PollChoice);
                        session.setAttribute("DescriptionChoices",DescriptionChoice);
                    }
                    if(SameSize){
                        ArrayChoice = new ArrayList<>();
                        for(int i = 0 ; i < arrOfChoices.length ; i++){

                            Choice c = new Choice(arrOfChoices[i],arrOfDescription[i]);
                            ArrayChoice.add(c);
                        }

                    }

                    ChoiceArray = ArrayChoice.toArray(new Choice[0]);

                    String Users = "The Users are " + "\n";

                    for(User a :UserArray ){
                        Users += a.toString() +"\n";
                    }

                    clearUserData();
                    Poll = new PollManager();
                    Poll.CreatePoll(PollName,PollQuestion,ChoiceArray, UserArray);
                    request.getSession().setAttribute("PollObject", Poll);
                }
            }
        }
    }

    public void updatePollPage(boolean pollUpdateVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        if(!Poll.getPollCreated()) {
            ErrorMessage = "Please create poll before updating poll.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;

        } else {
            if( pollUpdateVisited && request.getParameter("PollName")!= null
                    && request.getParameter("PollChoice") != null
                    && request.getParameter("Description") !=null
                    && request.getParameter("PollQuestion") != null &&
                    request.getParameter("PollUserID") != null
            ) {
                String PollName = request.getParameter("PollName").toString();
                String PollChoice  = request.getParameter("PollChoice").toString();

                String PollID  = request.getParameter("PollUserID").toString();

                String DescriptionChoice  = request.getParameter("Description").toString();

                String PollQuestion = request.getParameter("PollQuestion").toString();
                session.setAttribute("PollQuestion", PollQuestion);
                String[] arrOfChoices = PollChoice.split(",");

                String[] arrOfDescription = DescriptionChoice.split(",");

                boolean SameSize = false;

                if(arrOfChoices.length == arrOfDescription.length ) {
                    SameSize = true;

                    session.setAttribute("Choices",PollChoice);
                    session.setAttribute("DescriptionChoices",DescriptionChoice);
                }
                if(SameSize){
                    ArrayChoice = new ArrayList<>();
                    for(int i = 0 ; i < arrOfChoices.length ; i++){

                        Choice c = new Choice(arrOfChoices[i],arrOfDescription[i]);
                        ArrayChoice.add(c);
                    }

                }

                UserArray = Array.toArray(new User[0]);

                boolean isPollManager = false;
                for(User a : UserArray){
                    if(a.getType().contains(User.POLL_MANAGER)
                            && a.getUniqueId().contentEquals(PollID)
                    ) {
                        isPollManager = true;

                    }
                }

                ChoiceArray = ArrayChoice.toArray(new Choice[0]);

                String Users = "The Users are " + "\n";

                for(User a :UserArray ){

                    Users += a.toString() +"\n";
                }

                if(isPollManager) {
                    out.println("Is it the correct Creator ? " + isPollManager);
                    out.println("The users are " + Users);
                    out.println("The Poll will be created and will be running");
                    out.println("Size of Choices " + arrOfChoices.length );
                    out.println("Size of Description " + arrOfDescription.length);
                    out.println("Are they the same Size " + SameSize);
                    out.println(PollChoice);
                    out.println("\n");
                    out.println("\n");
                    out.println(DescriptionChoice);
                    clearUserData();
                    if(!Poll.UpdatePoll(PollName,PollQuestion,ChoiceArray,UserArray)) {
                        ErrorMessage = "Can only update from running or created state.";
                        session.setAttribute("ErrorMessage",ErrorMessage);
                        response.sendRedirect("ErrorHandling.jsp");
                        isError = true;
                    }
                    request.getSession().setAttribute("PollObject",Poll);
                    // only send redirect if no error
                    if(!isError)
                        response.sendRedirect("Vote.jsp");
                }
                else
                {
                    ErrorMessage = "You must be a poll manager to create a poll.";
                    session.setAttribute("ErrorMessage",ErrorMessage);
                    response.sendRedirect("ErrorHandling.jsp");
                    isError = true;
                }
            }
        }
    }

    public void clearUserData() {
        for (User u: UserArray) {
            u.reset();
        }
    }

    public void accessPoll(boolean accessPollVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        // TODO vvv current values are hard coded
        // TODO get poll id from database
        // TODO get pin id from database

        //If the poll Id exists,  if PIN provided in the database
        // Function1
        String pollID = request.getParameter("PollID").toString();
        String pin = request.getParameter("PIN").toString();

        // check if poll id is in database (not yet implemented)
        if(pollID.length() == 0) {
            ErrorMessage = "The provided Poll ID is invalid.";
            session.setAttribute("ErrorMessage", ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;
        // data valid
        } else {

            // check if pin was optionally provided, then user wants to update their vote
  // Poll =  new PollManager();   Get poll object form poll Id
            if(pin.length() == 0) {

                // todo check if it exists in database
                //check if the pin  , exits in vote and check  remove from poll table
                if(pin.length() == 0) {

                    // TODO pass pin # to vote.jsp
                    //
                    // TODO get poll object from database based on current pin #, send to vote.jsp page
                    response.sendRedirect("Vote.jsp");

                // provided pin # is not in database, throw an error
                } else {
                    ErrorMessage = "The provided PIN # is invalid.";
                    session.setAttribute("ErrorMessage", ErrorMessage);
                    response.sendRedirect("ErrorHandling.jsp");
                    isError = true;
                }
            } else {
                // pin was not provided, a new anonymous user is voting, so generate a new pin
                pin = generatePin();
                  // Set  question for Poll
                // TODO get poll object from database, send to vote.jsp page
                response.sendRedirect("Vote.jsp");
            }
        }
    }

    public void votePage(boolean voteVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        /* TODO
            1.
            2. store choice into poll database
            3.
        */
        String userVoteChoice = request.getParameter("UserVoteChoice").toString();
        String pollID = request.getParameter("PollID").toString();
        String pin = request.getParameter("PIN").toString();

        // get selected choice
        Choice c = ChoiceArray[0];
        for(Choice a : ChoiceArray){
            if(a.getChoice().contentEquals(userVoteChoice))
                c = a;
        }

        if(!Poll.vote(pin,c)){
            ErrorMessage = "Can only vote in running state.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;
        }

        response.sendRedirect("Vote.jsp");
        /*
        if(!Poll.getPollCreated()) {
            ErrorMessage = "Please create poll before voting.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;
        }
        else if (voteVisited && request.getParameter("VoteUserID")!= null
                &&  request.getParameter("VoteUserType")!= null
        ) {

            String VotUser = request.getParameter("VoteUserID").toString();

            String VoteUserType = request.getParameter("VoteUserType").toString();


            // Now we Iterate through the User array to notify that this user has Voted
            int index =0;
            Choice c = ChoiceArray[0];
            for(Choice a : ChoiceArray){
                if(a.getChoice().contentEquals(VoteUserType))
                    c = a;
            }

            for(User a : UserArray){
                if(a.getUniqueId().contentEquals(VotUser)) {
                    if(!Poll.vote(a,c)){
                        ErrorMessage = "Can only vote in running state.";
                        session.setAttribute("ErrorMessage",ErrorMessage);
                        response.sendRedirect("ErrorHandling.jsp");
                        isError = true;
                    }
                }
            }

            response.sendRedirect("Vote.jsp");
        }
         */
    }

    public void getPollResults(HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {

        try {
            HashMap pollResults = Poll.getPollResults();

            String ChoicesSplit = "";
            String ChoicesNumberOfTimes ="";

            for ( Object choice : pollResults.keySet() ) {
                ChoicesSplit += "," + ((Choice) choice).getChoice();
                ChoicesNumberOfTimes += "," +pollResults.get(((Choice) choice));
            }


            request.getSession().setAttribute("FinalArray",ChoicesSplit);
            request.getSession().setAttribute("FinalArray2",ChoicesNumberOfTimes);



            // only send redirect if no error
            if(!isError)
                response.sendRedirect("DisplayResults.jsp");
        } catch(Exception e){
            ErrorMessage = "Can only get poll results vote in released state.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;
        }
    }

    public void displayResultsPage(boolean displayResults, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        if(!Poll.getPollCreated()) {
            ErrorMessage = "Please create poll before voting.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;
        } else {
            getPollResults(request, out, response, session);
        }
    }

    public void pollManagementPage(boolean hiddenManagementVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        /*
        * todo: get poll from database
         */

        if(!Poll.getPollCreated()) {
            ErrorMessage = "Please create poll before managing poll.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;

        } else {
            String userId = request.getParameter("PollManagementID").toString();
            String pollId = request.getParameter("PollManagementSystemID").toString();

            /*
            * todo: check if user id is in database, since every user is now a poll manager
             */
            String valid = ""; // not implemented yet
            if(userId != valid) {
                response.sendRedirect("HiddenManagementSystem.jsp");
            } else {
                ErrorMessage = "Invalid user id.";
                session.setAttribute("ErrorMessage",ErrorMessage);
                response.sendRedirect("ErrorHandling.jsp");
                isError = true;
            }
        }
    }

    public void hiddenManagementPage(boolean HiddenManagementVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {

        if(!Poll.getPollCreated()) {
            ErrorMessage = "Please create poll before managing poll.";
            session.setAttribute("ErrorMessage",ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;

        } else {

            if(HiddenManagementVisited) {
                String action = request.getParameter("PollAction").toString();

                if(action.contentEquals("Unrelease Poll")){
                    if(!isError && ((PollManager) session.getAttribute("PollObject")).unreleasePoll()){
                        response.sendRedirect("HiddenManagementSystem.jsp");
                    } else {
                        ErrorMessage = "Can only unrelease poll in released state.";
                        session.setAttribute("ErrorMessage",ErrorMessage);
                        response.sendRedirect("ErrorHandling.jsp");
                        isError = true;
                    }
                }
                if(action.contentEquals("Run Poll")){
                    if(!isError && ((PollManager) session.getAttribute("PollObject")).RunPoll()){
                        response.sendRedirect("HiddenManagementSystem.jsp");
                    } else {
                        ErrorMessage = "Can only run poll from running or created state.";
                        session.setAttribute("ErrorMessage",ErrorMessage);
                        response.sendRedirect("ErrorHandling.jsp");
                        isError = true;
                    }
                }
                if(action.contentEquals("Release Poll")){
                    if(!isError && ((PollManager) session.getAttribute("PollObject")).ReleasePoll()){
                        response.sendRedirect("HiddenManagementSystem.jsp");
                    } else {
                        ErrorMessage = "Can only release poll from running state.";
                        session.setAttribute("ErrorMessage",ErrorMessage);
                        response.sendRedirect("ErrorHandling.jsp");
                        isError = true;
                    }
                }
                if(action.contentEquals("Clear Poll")){
                    if(!isError && ((PollManager) session.getAttribute("PollObject")).ClearPoll()){
                        clearUserData();
                        response.sendRedirect("HiddenManagementSystem.jsp");
                    } else {
                        ErrorMessage = "Cannot clear poll from created state.";
                        session.setAttribute("ErrorMessage",ErrorMessage);
                        response.sendRedirect("ErrorHandling.jsp");
                        isError = true;
                    }
                }
                if(action.contentEquals("Close Poll")){
                    if(!isError && ((PollManager) session.getAttribute("PollObject")).ClosePoll()){
                        clearUserData();
                        response.sendRedirect("HiddenManagementSystem.jsp");
                    } else {
                        ErrorMessage = "Can only close poll from released state.";
                        session.setAttribute("ErrorMessage",ErrorMessage);
                        response.sendRedirect("ErrorHandling.jsp");
                        isError = true;
                    }
                }
            }
        }
    }

    public void accessListPollsPage(boolean accessListPollsVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) throws IOException {
        String userID = request.getParameter("PollUserID").toString();

        // check if user id is in database (not yet implemented)
        if(userID.length() == 0) {
            ErrorMessage = "The provided user ID is invalid.";
            session.setAttribute("ErrorMessage", ErrorMessage);
            response.sendRedirect("ErrorHandling.jsp");
            isError = true;
        } else {
            // todo get list of polls created by user id from database, send to ListPolls.jsp
            response.sendRedirect("ListPolls.jsp");
        }
    }

    public void listPollsPage(boolean listPollsVisited, HttpServletRequest request, PrintWriter out, HttpServletResponse response, HttpSession session) {
    }
    // randomly generates a 6 digit pin number
    public String generatePin() {
        int i = new Random().nextInt(999999) + 100000;
        return Integer.toString(i);
    }
}
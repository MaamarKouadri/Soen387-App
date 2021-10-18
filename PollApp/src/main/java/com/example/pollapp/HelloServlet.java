package com.example.pollapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import company.Choice;
import company.PollManager;
import company.State;
import company.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    /*

     <a href="DisplayResults.jsp"  class="btn btn-dark  btn-lg"  role="button" data-bs-toggle="button" onclick="window.location='DisplayResults.jsp'">Display Result</a>
            <a href="DownLoadResults.jsp"  class="btn btn-dark  btn-lg"  role="button" data-bs-toggle="button" onclick="window.location='DownLoadResults.jsp'">Download Results</a>

     */
    private String message;
    private Choice [] ChoiceArray;
    private PollManager Poll;
    private User [] UserArray;
    private String UserTostring;
    private int NumberOfVisits = 0;
    private ArrayList<User> Array = new ArrayList<User>() ;
    private ArrayList<Choice> ArrayChoice = new ArrayList<Choice>() ;
    private String ErrorMessage;

    public void init() {
        message = "We have just created a user";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //getServletContext().getRequestDispatcher("/includes/User.jsp").include(request,response);
        PrintWriter out = response.getWriter();
        NumberOfVisits++;

        HttpSession session = request.getSession();
        boolean UserVisited  = (boolean)session.getAttribute("user");
        boolean PollVisited  = (boolean)session.getAttribute("Poll");
        boolean VoteVisited  = (boolean)session.getAttribute("vote");
        boolean HiddenManagement =  (boolean)session.getAttribute("HiddenManagement");
           if(UserVisited &&  request.getParameter("UserID") != null

           && request.getParameter("UserType") != null ){

               String ID = request.getParameter("UserID").toString();
               String type  = request.getParameter("UserType").toString();
               User user1 = new User(type,ID);

               out.println("<html><body>");
               out.println("Header.jsp");
               out.println("<h1>" + message + "</h1>");
               out.println("<h1>" + user1.toString() + "</h1>");
               out.println("</body></html>");

               Array.add(user1);
               response.sendRedirect("User.jsp");
           }
// NewCode
           if( PollVisited && request.getParameter("PollName")!= null
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

               String[] arrOfChoices = PollChoice.split(",");

               String[] arrOfDescription = DescriptionChoice.split(",");

               boolean SameSize = false;

               if(arrOfChoices.length == arrOfDescription.length ) {
                   SameSize = true;
                   session.setAttribute("Choices",PollChoice);
                   session.setAttribute("DescriptionChoices",DescriptionChoice);
               }
               if(SameSize){

                   for(int i = 0 ; i < arrOfChoices.length ; i++){

                       Choice c = new Choice(arrOfChoices[i],arrOfDescription[i]);
                       ArrayChoice.add(c);
                   }

               }

                UserArray = Array.toArray(new User[0]);

                     boolean CorrectCreator = false;
               for(User a : UserArray){
                   if(a.getType().contains("PollManager")
                   && a.getUniqueId().contentEquals(PollID)
                   ) {
                       CorrectCreator = true;

                   }
               }

                ChoiceArray = ArrayChoice.toArray(new Choice[0]);

               String Users = "The Users are " + "\n";

               for(User a :UserArray ){

                   Users += a.toString() +"\n";
               }

                       if(CorrectCreator) {
                           out.println("Is it the correct Creator ? " + CorrectCreator);
                           out.println("The users are " + Users);
                           out.println("The Poll will be created and will be running");
                           out.println("Size of Choices " + arrOfChoices.length );
                           out.println("Size of Description " + arrOfDescription.length);
                           out.println("Are they the same Size " + SameSize);
                            out.println(PollChoice);
                            out.println("\n");
                           out.println("\n");
                           out.println(DescriptionChoice);

                           Poll = new PollManager();
                           Poll.CreatePoll(PollName,PollQuestion,ChoiceArray,UserArray);
                           request.getSession().setAttribute("PollObject",Poll);
                           Poll.RunPoll();
                           response.sendRedirect("Vote.jsp");

                       }
                       else
               {
                   ErrorMessage = "This is not the correct PassCode we cannot create the poll";
                   session.setAttribute("ErrorMessage",ErrorMessage);
                   response.sendRedirect("ErrorHandling.jsp");
               }
           }

           // Here We would Handle the Vote

            if(VoteVisited && request.getParameter("VoteUserID")!= null
            &&  request.getParameter("VoteUserType")!= null
            ) {

                String VotUser = request.getParameter("VoteUserID").toString();

                String VoteUserType = request.getParameter("VoteUserType").toString();


                // Now we Iterate through the User array to notify that this user has Voted
                      int index =0;
                String Description ="";
                for(Choice a : ChoiceArray){
                   if(a.getChoice().contentEquals(VoteUserType))
                       Description =VoteUserType;
                }

                 Choice c = new Choice(Description,VotUser);
                for(User a : UserArray){
                      if(a.getUniqueId().contentEquals(VotUser))
                          a.vote(c);
                }

                 boolean EveryBodyHasVoted = true;

                for(User a : UserArray){
                    if(a.gethasVoted() == false)
                        EveryBodyHasVoted = false;
                }

                if(EveryBodyHasVoted){

                    out.println("EveryBody has Voted");
                    for(User a : UserArray){
                        out.println("User with ID " +  a.getUniqueId() + " has voted " + a.getChoiceSelected());
                    }

                    int [] ChoicesSelected = new int [ChoiceArray.length];

                    int count=0;
                    int index2 = 0;

                        for(Choice ch :ChoiceArray){
                            for(User a : UserArray){
                            if(a.getChoiceSelected().getChoice().contentEquals(ch.getChoice()))
                                count++;
                        }
                            ChoicesSelected[index2] = count;
                            index2++;
                            count=0;
                    }

                        String ChoicesSplit ="";
                        String ChoicesNumberOfTimes ="";

                        for(Choice cs : ChoiceArray){

                            ChoicesSplit+="," + cs.getChoice();
                        }

                    for(int cs : ChoicesSelected){

                        ChoicesNumberOfTimes+="," + cs;
                    }

                        for(int i = 0 ; i < ChoiceArray.length ; i++){

                            out.println( "Choice " + ChoiceArray[i] + " has been selected " + ChoicesSelected[i] +" times");
                            out.print("\n");
                            out.print("\n");
                            out.print("\n");
                            out.println("---------------------------------");

                        }


                    request.getSession().setAttribute("FinalArray",ChoicesSplit);
                    request.getSession().setAttribute("FinalArray2",ChoicesNumberOfTimes);


                    ((PollManager) session.getAttribute("PollObject")).ClearPoll();
                    response.sendRedirect("DisplayResults.jsp");
                }

                else{

                    out.println("Not EveryBody has Voted");
                    response.sendRedirect("Vote.jsp");
                }

            }


            if(HiddenManagement ){
                String action = request.getParameter("PollAction").toString();

                if(action.contentEquals("Unrelease Poll")){
                    ((PollManager) session.getAttribute("PollObject")).unreleasePoll();
                    response.sendRedirect("HiddenManagementSystem.jsp");
                }
                if(action.contentEquals("Clear Poll")){
                    ((PollManager) session.getAttribute("PollObject")).ClearPoll();
                    response.sendRedirect("HiddenManagementSystem.jsp");
                }
                if(action.contentEquals("Update Poll")){
                    response.sendRedirect("HiddenManagementSystem.jsp");

                }
                if(action.contentEquals("Run Poll")){

                    ((PollManager) session.getAttribute("PollObject")).RunPoll();
                    response.sendRedirect("HiddenManagementSystem.jsp");
                }
                if(action.contentEquals("Release Poll")){

                    ((PollManager) session.getAttribute("PollObject")).ReleasePoll();
                    response.sendRedirect("HiddenManagementSystem.jsp");
                }
                if(action.contentEquals("Close Poll")){

                    ((PollManager) session.getAttribute("PollObject")).ClosePoll();
                    response.sendRedirect("HiddenManagementSystem.jsp");
                }
            }


    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        // Releasing the Poll here.
        ((PollManager) session.getAttribute("PollObject")).ReleasePoll();
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\maama\\OneDrive\\Bureau\\Soen387-App\\PollApp\\TheResultsFinal.txt"));

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

            for (int i = 1; i < ChoiceArrx.length; i++) {
               double value = Double.parseDouble(ChoiceArr2x[i]) / sum;
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
}
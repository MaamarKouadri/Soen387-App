package com.example.pollapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import company.Choice;
import company.PollManager;
import company.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private Choice [] ChoiceArray;
    private User [] UserArray;
    private String UserTostring;
    private int NumberOfVisits = 0;
    private ArrayList<User> Array = new ArrayList<User>() ;
    private ArrayList<Choice> ArrayChoice = new ArrayList<Choice>() ;

    public void init() {
        message = "We have just created a user";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //getServletContext().getRequestDispatcher("/includes/User.jsp").include(request,response);
        PrintWriter out = response.getWriter();
        NumberOfVisits++;

        HttpSession session = request.getSession();
        boolean UserVisited  = (boolean)session.getAttribute("user");
        boolean PollVisited  = (boolean)session.getAttribute("Poll");
        boolean VoteVisited  = (boolean)session.getAttribute("vote");

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

               //Creating a new Poll Manager

             //  Poll OurPOLL = new Poll(PollName,PollQuestion,ChoiceArray,UserArray);

                       if(CorrectCreator) {
                           out.println("Is it the correct Creator ? " + CorrectCreator);
                           out.println("The users are " + Users);
                           out.println("The Poll will be created and will be running");
                           out.println("Size of Choices " + arrOfChoices.length );
                           out.println("Size of Description " + arrOfDescription.length);
                           out.println("Are they the same Size " + SameSize);
                         //  String MyChoices = request.getParameter("Choices");
                         //  String [] arrODescriptionChoice = MyChoices.split(",");
                            out.println(PollChoice);
                            out.println("\n");
                           out.println("\n");
                           out.println(DescriptionChoice);

                           PollManager Poll = new PollManager();
                           Poll.CreatePoll(PollName,PollQuestion,ChoiceArray,UserArray);
                           Poll.RunPoll();
                           response.sendRedirect("Vote.jsp");
                          // Poll.setStatus()
                           /*
                           String MyChoices = request.getParameter("Choices");
                           String [] arrODescriptionChoice = MyChoices.split(",");
                          for(String s :arrODescriptionChoice ){
                              out.println(s);
                          }

*/
                       }

                       else
               {
                   out.println("This is not the correct User we cannot create the poll");
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



                    response.sendRedirect("DisplayResults.jsp");
                }

                else{

                    out.println("Not EveryBody has Voted");
                    response.sendRedirect("Vote.jsp");
                }

            }
        //HttpSession session = request.getSession();
        //int k  = (int)session.getAttribute("k");

        /*



        User user1 = new User(type,ID);

        Array.add(user1);

        User [] UserArray = Array.toArray(new User[0]);

        Choice [] ChoiceArray = ArrayChoice.toArray(new Choice[0]);

            String Users = "The Users are " + "\n";

            for(User a :UserArray ){

                Users += a.toString() +"\n";
            }
         UserTostring = user1.toString();

            //Creating a new Poll Manager

        PollManager Poll = new PollManager();

        Poll.CreatePoll(PollName,PollQuestion,ChoiceArray,UserArray);

       String UserC ="We have crated a new user , with ID " + ID +" of type" + type;
        message=UserC + " Number of visits " + NumberOfVisits;
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1>" + UserTostring + "</h1>");
        out.println("<h1>" + Users + "</h1>");
        out.println("<h1>" + PollName + "</h1>");
        out.println("<h1>" + PollQuestion + "</h1>");
        out.println("<h1>" + PollChoice + "</h1>");
        out.println("select name='UserType' id='UserType' ");
        out.println("</body></html>");
*/
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            FileWriter myWriter = new FileWriter("Results.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            //  request.getSession().setAttribute("TextFile",myWriter);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String data ="Something";
        try {
            File myObj = new File("Results.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                 data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + data + "</h1>");
        out.println("</body></html>");





    }

    public void destroy() {
    }
}
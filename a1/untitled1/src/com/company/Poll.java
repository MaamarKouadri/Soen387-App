package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Poll {

    // data members
    private String name;
    private String question;
    private Choice[] choices;
    private State status;
    private User[] users;


    // constructors
    public Poll() {
        this.status = State.Initialized;
    }
    public Poll(String name, String question, Choice[] choices, User[] users) {
        this.name = name;
        this.question = question;
        this.choices = choices;
        this.status = State.Created;
        this.users = users;
    }

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Choice[] getChoices() {
        return this.choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }

    public State getStatus() {
        return this.status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    /**
     * casts vote of current user
     * @param u current user voting
     * @param c vote of the user
     */
    public void vote(User u, Choice c) {

        // find user in users array
        for(User user: users) {

            // compare unique ids to find the given user
            if(u.getUniqueId().equals(user.getUniqueId())) {

                // found user, assign its vote in the users array
                user.vote(c);
                break;
            }
        }
    }

    /**
     *
     * @return
     */
    public HashMap<Choice,Integer> getPollResults(){
        HashMap<Choice,Integer> results = new HashMap<>();

        // initialize key values of hashmap
        for(Choice c: choices) {
            results.put(c,0);
        }

        // collect all selected choices from users
        for (User u: users) {
            if(u.hasVoted)
                results.put(u.getChoiceSelected(),results.get(u.getChoiceSelected()) + 1);
        }

        return results;
    }

    /**
     *
     * @throws FileNotFoundException
     */
    public void downloadPollResults() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(this.getName()) + ".txt");
        pw.write("Name: " + this.getName() + "\n");
        pw.write("Question: " + this.getQuestion() + "\n");

        HashMap<Choice,Integer> results = getPollResults();
        for(Choice c: choices){
            pw.write("Choice " + c.getChoice() + " has " + results.get(c) + " votes.\n");
        }
        pw.flush();
        pw.close();

    }

    public void clear() {
        this.choices = null;
    }

    public void close() {
        this.clear();
        this.users = null;
    }
}

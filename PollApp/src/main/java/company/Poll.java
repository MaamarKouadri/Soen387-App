package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

public class Poll {

    private static final int MAX_UID_LENGTH = 10;

    // data members
    private String uid;
    private String name;
    private String question;
    private Choice[] choices;
    private State status;
    private User[] users;


    // constructors
    public Poll() {
        this.uid = generateUID();
        this.status = State.Initialized;
    }

    public Poll(String name, String question, Choice[] choices , String Uid) {
        this.uid = Uid;
        this.name = name;
        this.question = question;
        this.choices = choices;
        this.status = State.Created;
    }

    public Poll(String name, String question, Choice[] choices, User[] users) {
        this.uid = generateUID();
        this.name = name;
        this.question = question;
        this.choices = choices;
        this.status = State.Created;
        this.users = users;
    }

    // getters & setters
    public String getUid() {return uid;}

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

    public String generateUID() {
        Random r = new Random();
        StringBuilder str = new StringBuilder();
        String chars="abcdefghjkmnpqrstvexyz0123456789";
        for (int i = 0; i < MAX_UID_LENGTH; i++) {
            str.append(chars.charAt(r.nextInt(chars.length())));
        }
        return str.toString();
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
     * casts vote of anonymous user
     * @param pin unique id for user to poll
     * @param c vote of the anonymous user
     */
    public void vote(String pin, Choice c) {

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
    public boolean downloadPollResults() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(this.getName()) + ".txt");
        pw.write("Name: " + this.getName() + "\n");
        pw.write("Question: " + this.getQuestion() + "\n");

        HashMap<Choice,Integer> results = getPollResults();
        for(Choice c: choices){
            pw.write("Choice " + c.getChoice() + " has " + results.get(c) + " votes.\n");
        }
        pw.flush();
        pw.close();
        return true;
    }

    public void clear() {
        if(this.status == State.Running) {
            this.choices = null;
        } else if(this.status == State.Released) {
            this.status = State.Created;
            this.choices = null;
            this.name = null;
            this.question = null;
        }
    }

    public void close() {
        this.clear();
        this.users = null;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nQuestion: " + this.question + "\n";

    }
}

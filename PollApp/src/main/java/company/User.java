package company;

import java.sql.Timestamp;

public class User {
    public static String POLL_MANAGER = "poll_manager";
    public static String PARTICIPANT = "participant";

    private String uniqueId;
    private String type; // participant/poll_manager
    private Choice choiceSelected;
    private Timestamp timeSelected;
    public boolean hasVoted;



    // constructor
    public User(String type, String uniqueId) {
        this.uniqueId = uniqueId;
        this.type = type;
    }

    // getters
    public Choice getChoiceSelected() {
        return this.choiceSelected;
    }

    public Timestamp getTimeSelected() {
        return this.timeSelected;
    }

    public String getType() {
        return this.type;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public boolean gethasVoted() {
        return this.hasVoted;
    }

    public void vote(Choice choice) {
        hasVoted = true;
        this.choiceSelected = choice;
        this.timeSelected = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {

        String UserC ="This is user number   " + uniqueId +" of type " + type;
        return UserC;
    }


    public void reset() {
        this.choiceSelected = null;
        this.timeSelected = null;
        this.hasVoted= false;
    }
}



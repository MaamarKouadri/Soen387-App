package com.company;

import java.sql.Timestamp;

public class User {
    public static String POLL_MANAGER = "poll_manager";
    public static String PARTICIPANT = "participant";
    private static boolean pollManagerExists = false; // only 1 poll manager allowed

    private String uniqueId;
    private String type; // participant/poll_manager
    private Choice choiceSelected;
    private Timestamp timeSelected;
    public boolean hasVoted;



    // constructor
    public User(String type, String uniqueId) {
        this.uniqueId = uniqueId;
        if(type.equals(POLL_MANAGER)) {
            if(!pollManagerExists) {
                this.type = type;
                pollManagerExists = true;
            } else {
                // todo throw error
                System.out.println("poll manager already exists!");
            }
        } else {
            this.type = type;
        }
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

    public void vote(Choice choice) {
        hasVoted = true;
        this.choiceSelected = choice;
        this.timeSelected = new Timestamp(System.currentTimeMillis());
    }
}



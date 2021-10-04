package com.company;

public class Choice {
    /*
     * private members
     */
    private String choice;
    private String description;

    /*
     * Constructors
     */
    Choice() {}
    Choice(String choice) {
        this.choice = choice;
    }
    Choice(String choice, String description) {
        this.choice = choice;
        this.description = description;
    }

    /*
    * Getters/Setters
    */
    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

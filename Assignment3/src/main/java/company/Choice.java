package company;

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
    public Choice(String choice, String description) {
        this.choice = choice;
        this.description = description;
    }

    /*
    * Getters/Setters
    */
    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {this.choice = choice;}

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "choice='" + choice + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

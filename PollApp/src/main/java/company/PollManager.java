package company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class PollManager {

    private boolean debug = true;

    private Poll poll;
    private Boolean isPollCreated = false;

    public PollManager() {
        poll = new Poll();
    }

    /**
     * initializes the poll
     * @param name name of the poll
     * @param question question of the poll
     * @param choices array of choices
     * @param users array of users currently participating in poll
     */
    public void CreatePoll(String name, String question, Choice[] choices, User[] users) {

        // only 1 allowed in the system
        if(!this.isPollCreated) {
            this.isPollCreated = true;
            poll = new Poll(name,question,choices, users);
            // code goes here
            if(debug)
                System.out.println("creating poll");
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! CreatePoll()");
        }
    }

    /**
     * updates poll data
     * @param name name of the poll
     * @param question question of the poll
     * @param choices array of choices
     * @param users array of users currently participating in poll
     */
    public void UpdatePoll(String name, String question, Choice[] choices, User[] users) {
        if(this.poll.getStatus().equals(State.Running) || this.poll.getStatus().equals(State.Created)) {
            poll = new Poll(name, question, choices, users);
            if(debug)
                System.out.println("updating poll");
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! UpdatePoll()");
        }
    }

    /**
     * clears poll data
     */
    public void ClearPoll() {
        if(this.poll.getStatus().equals(State.Running) || this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("clearing poll");

            this.poll.clear();

            // put back released poll into created state
            if(this.poll.getStatus().equals(State.Released)) {
                this.poll.setStatus(State.Created);
            }
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! ClearPoll()");
        }
    }

    /**
     * closes and clears all associated data from poll
     */
    public void ClosePoll() {
        if(this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("closing poll");

            this.poll.close();
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! ClosePoll()");
        }
    }

    /**
     * changes poll status from created to running
     */
    public void RunPoll() {
        if(this.poll.getStatus().equals(State.Created)) {
            if(debug)
                System.out.println("running poll");

            this.poll.setStatus(State.Running);
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! RunPoll()");
        }
    }

    /**
     * changes poll status from running to released
     */
    public void ReleasePoll() {
        if(this.poll.getStatus().equals(State.Running)) {
            if(debug)
                System.out.println("releasing poll");

            this.poll.setStatus(State.Released);
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! ReleasePoll()");
        }
    }

    /**
     * changes poll status to from released to running
     */
    public void unreleasePoll() {
        if(this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("un-releasing poll");

            this.poll.setStatus(State.Running);
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! unreleasePoll()");
        }
    }

    /**
     * Allows a user to vote
     * @param participant user submitting choice
     * @param choice participants choice
     */
    public void vote(User participant, Choice choice) {
        if(this.poll.getStatus().equals(State.Running)) {
            if(debug)
                System.out.println(participant.getUniqueId() + " selected choice: " + choice.getChoice());

            this.poll.vote(participant,choice);
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! vote()");
        }
    }

    /**
     * gets number of votes per poll question
     */
    public HashMap<Choice,Integer> getPollResults() {
        if(debug)
            System.out.println("getting poll results");

        // only get results if in released state
        if(!this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("error occurred! getPollResults()");
        }

        return this.poll.getPollResults();
    }

    // TODO (PrintWriter output, String filename) not needed in method signature..?
    /**
     * downloads results of poll details
     */
    public void downloadPollDetails() throws FileNotFoundException {
        if(this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("downloading poll results");

            this.poll.downloadPollResults();
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! downloadPollDetails()");
        }
    }


    @Override
    public String toString() {
        return "PollManager{" +
                "debug=" + debug +
                ", poll=" + poll +
                ", isPollCreated=" + isPollCreated +
                '}';
    }
}

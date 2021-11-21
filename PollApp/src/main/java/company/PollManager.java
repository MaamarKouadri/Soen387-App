package company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class PollManager {

    private boolean debug = true;

    private Poll poll;

    public Boolean getPollCreated() {
        return isPollCreated;
    }

    public void setPollCreated(Boolean pollCreated) {
        isPollCreated = pollCreated;
    }

    private Boolean isPollCreated = false;

    public PollManager() {
        poll = new Poll();
    }

    public PollManager(Poll p) {

        isPollCreated= true;
        poll = p;
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
    public boolean UpdatePoll(String name, String question, Choice[] choices, User[] users) {
        if(this.poll.getStatus().equals(State.Running) || this.poll.getStatus().equals(State.Created)) {
            poll = new Poll(name, question, choices, users);
            if(debug)
                System.out.println("updating poll");
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! UpdatePoll()");
            return false;
        }
    }

    /**
     * clears poll data
     */
    public boolean ClearPoll() {
        if(this.poll.getStatus().equals(State.Running) || this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("clearing poll");

            this.poll.clear();

            // put back released poll into created state
            if(this.poll.getStatus().equals(State.Released)) {
                this.poll.setStatus(State.Created);
            }
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! ClearPoll()");
            return false;
        }
    }

    /**
     * closes and clears all associated data from poll
     */
    public boolean ClosePoll() {
        if(this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("closing poll");

            this.poll.close();
            this.isPollCreated = false;
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! ClosePoll()");
            return false;
        }
    }

    /**
     * changes poll status from created to running
     */
    public boolean RunPoll() {
        if(this.poll.getStatus().equals(State.Created) || this.poll.getStatus().equals(State.Running)) {
            if(debug)
                System.out.println("running poll");

            this.poll.setStatus(State.Running);
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! RunPoll()");
            return false;
        }
    }

    /**
     * changes poll status from running to released
     */
    public boolean ReleasePoll() {
        if(this.poll.getStatus().equals(State.Running)) {
            if(debug)
                System.out.println("releasing poll");

            this.poll.setStatus(State.Released);
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! ReleasePoll()");
            return false;
        }
    }

    /**
     * changes poll status to from released to running
     */
    public boolean unreleasePoll() {
        if(this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("un-releasing poll");

            this.poll.setStatus(State.Running);
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! unreleasePoll()");

            return false;
        }
    }

    /**
     * Allows a user to vote
     * @param participant user submitting choice
     * @param choice participants choice
     */
    public boolean vote(User participant, Choice choice) {
        if(this.poll.getStatus().equals(State.Running)) {
            if(debug)
                System.out.println(participant.getUniqueId() + " selected choice: " + choice.getChoice());

            this.poll.vote(participant,choice);
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! vote()");
            return false;
        }
    }

    /**
     * Allows an anonymous user to vote
     * @param pin unique ID associated to poll
     * @param choice participants choice
     */
    public boolean vote(String pin, Choice choice) {
        if(this.poll.getStatus().equals(State.Running)) {
            if(debug)
                System.out.println(pin + " selected choice: " + choice.getChoice());

            this.poll.vote(pin, choice);
            return true;
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! vote()");
            return false;
        }
    }

    /**
     * gets number of votes per poll question
     */
    public HashMap<Choice,Integer> getPollResults() throws Exception {
        if(debug)
            System.out.println("getting poll results");

        // only get results if in released state
        if(!this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("error occurred! getPollResults()");
            throw new Exception();
        }

        return this.poll.getPollResults();
    }

    // TODO (PrintWriter output, String filename) not needed in method signature..?
    /**
     * downloads results of poll details
     */
    public boolean downloadPollDetails() throws FileNotFoundException {
        if(this.poll.getStatus().equals(State.Released)) {
            if(debug)
                System.out.println("downloading poll results");

            return this.poll.downloadPollResults();
        } else {
            // throw error
            if(debug)
                System.out.println("error occurred! downloadPollDetails()");
            return false;
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


    public Poll getPoll() {
        return poll;
    }
}

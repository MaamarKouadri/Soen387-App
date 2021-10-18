package company;

import java.io.FileNotFoundException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        testPollManager();
    }

    static public void testPollManager() {
        PollManager pm = new PollManager();

        int choiceSize = 5;
        int userSize = 50;

        Choice[] choices = new Choice[choiceSize];
        for(int i = 0; i < choiceSize; i++){
            Choice c = new Choice("choice" + i, "description" + i);
            choices[i] = c;
        }

        User[] users = new User[userSize];
        for(int i = 0; i < userSize; i++){
            User u = new User(User.PARTICIPANT,"uniqueId" + i);
            users[i] = u;
        }

        pm.CreatePoll("myName","myQuestion",choices,users);
        pm.RunPoll();
        pm.vote(users[8],choices[2]);
        pm.ReleasePoll();
        pm.getPollResults();
        try {
            pm.downloadPollDetails();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public void testPoll(){

        int choiceSize = 5;
        int userSize = 50;

        Choice[] choices = new Choice[choiceSize];
        for(int i = 0; i < choiceSize; i++){
            Choice c = new Choice("choice" + i, "description" + i);
            choices[i] = c;
        }

        User[] users = new User[userSize];
        for(int i = 0; i < userSize; i++){
            User u = new User(User.PARTICIPANT,"uniqueId" + i);
            u.vote(getRandomChoice(choices));
            users[i] = u;
        }
        Poll p = new Poll("myTitle","myQuestion",choices,users);

        p.getPollResults();
        try {
            p.downloadPollResults();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public Choice getRandomChoice(Choice[] choices) {
        Random rand = new Random();
        int size = choices.length;
        return choices[rand.nextInt(size)];
    }
}

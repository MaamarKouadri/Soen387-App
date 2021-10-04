package com.company;

import java.io.PrintWriter;

public class PollManager {

    // restrict functions with state
    private enum State {
        Created,
        Running,
        Released
    }

    private State state;
    private Boolean isPollCreated = false;

    public void CreatePoll(String name, String question, Choice[] choices) {
        state = State.Created;
        if(!isPollCreated) {
            isPollCreated = true;
            // code goes here
        } else {
            // throw error
        }
    }

    public void UpdatePoll(String name, String question, Choice[] choices) {
        if(state.equals(State.Running)) {
            // code goes here
        } else {
            // throw error
        }
    }

    public void ClearPoll() {
        if(state.equals(State.Running) || state.equals(State.Released)) {
            // code goes here
        } else {
            // throw error
        }
    }

    public void ClosePoll() {
        if(state.equals(State.Released)) {
            // code goes here
        } else {
            // throw error
        }
    }

    public void RunPoll() {
        if(state.equals(State.Created)) {
            // code goes here
        } else {
            // throw error
        }
    }

    public void ReleasePoll() {
        if(state.equals(State.Running)) {
            // code goes here
        } else {
            // throw error
        }
    }

    public void unreleasePoll() {
        if(state.equals(State.Released)) {
            // code goes here
        } else {
            // throw error
        }
    }


    public void vote(String participant, Choice choice) {
        if(state.equals(State.Running)) {
            // code goes here
        } else {
            // throw error
        }
    }

    public void getPollResults() {
        if(state.equals(State.Released)) {
            // code goes here
        } else {
            // throw error
        }
    }

    // TODO why is there a ref keyword
    public void downloadPollDetails(PrintWriter output, String filename) {
        if(state.equals(State.Released)) {
            // code goes here
        } else {
            // throw error
        }
    }
}

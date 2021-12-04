package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ChoiceEntityPK implements Serializable {
    private String pollId;
    private String choiceId;

    @Column(name = "PollId")
    @Id
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    @Column(name = "ChoiceID")
    @Id
    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChoiceEntityPK that = (ChoiceEntityPK) o;

        if (pollId != null ? !pollId.equals(that.pollId) : that.pollId != null) return false;
        if (choiceId != null ? !choiceId.equals(that.choiceId) : that.choiceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pollId != null ? pollId.hashCode() : 0;
        result = 31 * result + (choiceId != null ? choiceId.hashCode() : 0);
        return result;
    }
}

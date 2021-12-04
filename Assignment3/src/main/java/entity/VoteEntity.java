package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vote", schema = "naruto2", catalog = "")
@IdClass(VoteEntityPK.class)
public class VoteEntity {
    private int pin;
    private String pollId;
    private String choiceId;

    @Id
    @Column(name = "Pin")
    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Id
    @Column(name = "PollId")
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    @Id
    @Column(name = "choiceID")
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

        VoteEntity that = (VoteEntity) o;

        if (pin != that.pin) return false;
        if (pollId != null ? !pollId.equals(that.pollId) : that.pollId != null) return false;
        if (choiceId != null ? !choiceId.equals(that.choiceId) : that.choiceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pin;
        result = 31 * result + (pollId != null ? pollId.hashCode() : 0);
        result = 31 * result + (choiceId != null ? choiceId.hashCode() : 0);
        return result;
    }
}

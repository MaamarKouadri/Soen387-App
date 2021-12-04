package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "choice", schema = "naruto2", catalog = "")
@IdClass(ChoiceEntityPK.class)
public class ChoiceEntity {
    private String pollId;
    private String choiceId;
    private String choiceName;

    @Id
    @Column(name = "PollId")
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    @Id
    @Column(name = "ChoiceID")
    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    @Basic
    @Column(name = "choiceName")
    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChoiceEntity that = (ChoiceEntity) o;

        if (pollId != null ? !pollId.equals(that.pollId) : that.pollId != null) return false;
        if (choiceId != null ? !choiceId.equals(that.choiceId) : that.choiceId != null) return false;
        if (choiceName != null ? !choiceName.equals(that.choiceName) : that.choiceName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pollId != null ? pollId.hashCode() : 0;
        result = 31 * result + (choiceId != null ? choiceId.hashCode() : 0);
        result = 31 * result + (choiceName != null ? choiceName.hashCode() : 0);
        return result;
    }
}

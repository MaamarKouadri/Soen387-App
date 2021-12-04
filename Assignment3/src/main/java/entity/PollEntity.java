package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "poll", schema = "naruto2", catalog = "")
public class PollEntity {
    private String pollName;
    private String question;
    private String choices;
    private String pollId;
    private String isDelete;
    private String timeStamp;
    private String state;

    @Basic
    @Column(name = "PollName")
    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    @Basic
    @Column(name = "Question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "Choices")
    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    @Id
    @Column(name = "PollId")
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    @Basic
    @Column(name = "isDelete")
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column(name = "timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PollEntity that = (PollEntity) o;

        if (pollName != null ? !pollName.equals(that.pollName) : that.pollName != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (choices != null ? !choices.equals(that.choices) : that.choices != null) return false;
        if (pollId != null ? !pollId.equals(that.pollId) : that.pollId != null) return false;
        if (isDelete != null ? !isDelete.equals(that.isDelete) : that.isDelete != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pollName != null ? pollName.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (choices != null ? choices.hashCode() : 0);
        result = 31 * result + (pollId != null ? pollId.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}

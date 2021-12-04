package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class HaspollEntityPK implements Serializable {
    private String pollId;
    private int userId;

    @Column(name = "PollId")
    @Id
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    @Column(name = "UserId")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HaspollEntityPK that = (HaspollEntityPK) o;

        if (userId != that.userId) return false;
        if (pollId != null ? !pollId.equals(that.pollId) : that.pollId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pollId != null ? pollId.hashCode() : 0;
        result = 31 * result + userId;
        return result;
    }
}

package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "haspoll", schema = "naruto2", catalog = "")
@IdClass(HaspollEntityPK.class)
public class HaspollEntity {
    private String pollId;
    private int userId;
    private String isActive;

    @Id
    @Column(name = "PollId")
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    @Id
    @Column(name = "UserId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "isActive")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HaspollEntity that = (HaspollEntity) o;

        if (userId != that.userId) return false;
        if (pollId != null ? !pollId.equals(that.pollId) : that.pollId != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pollId != null ? pollId.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}

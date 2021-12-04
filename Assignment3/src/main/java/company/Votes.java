package company;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Votes {

    private Vote[] vote;

    public Votes(Vote[] vote) {
        this.vote = vote;
    }

    public Votes() {
        vote=null;
    }

    public Vote[] getVote() {
        return vote;
    }

    @XmlElement
    public void setVote(Vote[] vote) {
        this.vote = vote;
    }
}

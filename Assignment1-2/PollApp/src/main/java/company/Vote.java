package company;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vote {
    private int id;

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int count;

    public Vote() {
        name = "";
        count = 0;
    }

    public Vote(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Vote(String name, int count,int id) {
        this.id=id;
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public void setCount(int count) {
        this.count = count;
    }
}
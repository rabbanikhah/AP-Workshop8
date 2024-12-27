import java.io.Serializable;

public class Note implements Serializable {
    private String topic;
    private String date;
    private String text;

    public Note(String topic, String date, String text) {
        this.topic = topic;
        this.date = date;
        this.text = text;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

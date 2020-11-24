package task2;

import java.util.UUID;

public class Record {

    public UUID id;
    public String text;
    public WritingUtensil writingTool;

    public Record(String text, WritingUtensil writingTool){
        id = UUID.randomUUID();
        this.text = text;
        this.writingTool = writingTool;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WritingUtensil getWritingTool() {
        return writingTool;
    }

    public void setWritingTool(WritingUtensil writingTool) {
        this.writingTool = writingTool;
    }

    public UUID getId() {
        return id;
    }
}

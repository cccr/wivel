package today.wivel.root.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import today.wivel.root.data.Auditable;

import java.util.List;

@Document
public class Note extends Auditable<String> {
    @Id
    private String id;

    private String text;

    List<Tag> tags;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
package today.wivel.root.data.domain;

import org.springframework.data.annotation.Id;
import today.wivel.root.data.Auditable;

public class Note extends Auditable<String> {
    @Id
    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
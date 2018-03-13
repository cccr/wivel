package today.wivel.root.data.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "text", types = {Note.class})
public interface PublicNote {
    String getId();
    String getText();

    List<Tag> getTags();

    @Value("#{target.createdBy.id}")
    String getCreatedById();

    @Value("#{target.createdBy.name}")
    String getCreatedByName();
}

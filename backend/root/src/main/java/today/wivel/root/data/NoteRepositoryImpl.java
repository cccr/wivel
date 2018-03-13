package today.wivel.root.data;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import today.wivel.root.data.domain.Note;

public class NoteRepositoryImpl implements NoteRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int updateText(String id, String text) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("text", text);

        WriteResult result = mongoTemplate.updateFirst(query, update, Note.class);

        if(result!=null)
            return result.getN();
        else
            return 0;
    }
}

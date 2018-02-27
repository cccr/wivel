package today.wivel.root.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import today.wivel.root.data.domain.Note;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByAuthor(@Param("author") String name);
    List<Note> findByTagsName(@Param("tag") String tagName);
}
package today.wivel.root.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import today.wivel.root.data.domain.Note;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String>, SecurityNoteRepository, NoteRepositoryCustom {
    List<Note> findByCreatedByName(@Param("author") String name);

    List<Note> findByTagsName(@Param("tag") String tagName);

    @Query("{'text': ?#{[0]} }")
    List<Note> findByQueryWithExpression(@Param("text") String param0);
}
package today.wivel.root.data;

import org.springframework.data.mongodb.repository.Query;
import today.wivel.root.data.domain.Note;

import java.util.List;

interface SecurityNoteRepository {
    @Query("{$and: [{'createdBy.id': ?#{#security.principal.id}}]}")
    List<Note> findAllForAuthor();
}
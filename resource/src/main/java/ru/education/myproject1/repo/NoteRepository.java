package ru.education.myproject1.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import ru.education.myproject1.model.Note;

public interface NoteRepository extends R2dbcRepository<Note, Long> {
    Flux<Note> findAllByUserId(Long id);
}

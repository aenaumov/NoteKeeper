package ru.education.myproject1.service;

import reactor.core.publisher.Flux;
import ru.education.myproject1.model.Note;

public interface NoteService
{
    Flux<Note> getNotes (Long id, Long user_id, String role);

}

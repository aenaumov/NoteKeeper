package ru.education.myproject1.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.education.myproject1.model.Note;
import ru.education.myproject1.repo.NoteRepository;
import ru.education.myproject1.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public Flux<Note> getNotes(Long id, Long user_id, String role) {

//        TODO проверка id and role

        return noteRepository.findAllByUserId(id);
    }
}

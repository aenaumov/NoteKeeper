package ru.education.myproject1.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.myproject1.model.Note;
import ru.education.myproject1.repo.NoteRepository;
import ru.education.myproject1.service.NoteService;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public List<Note> getNotes(Long id, Long user_id, String role) {

//        TODO проверка id and role

        return noteRepository.findAllByUserId(id);
    }
}

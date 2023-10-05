package ru.education.myproject1.service;

import ru.education.myproject1.model.Note;

import java.util.List;

public interface NoteService
{
    List<Note> getNotes (Long id, Long user_id, String role);

}

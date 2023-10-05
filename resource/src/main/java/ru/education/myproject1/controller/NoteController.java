package ru.education.myproject1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.education.myproject1.model.Note;
import ru.education.myproject1.service.NoteService;

import java.util.List;

@Slf4j
@RestController

@RequestMapping("/notes/user")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/{id}")
    public List<Note> getUser(@PathVariable Long id,
                              @RequestParam Long user_id,
                              @RequestParam String role) {

        return noteService.getNotes(id, user_id, role);
    }
}

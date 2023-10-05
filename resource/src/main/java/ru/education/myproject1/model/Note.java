package ru.education.myproject1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "NOTES")
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
@AllArgsConstructor
public class Note {

    @Id
    @Column(name = "NOTE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "USERID")
    private Long userId;
}

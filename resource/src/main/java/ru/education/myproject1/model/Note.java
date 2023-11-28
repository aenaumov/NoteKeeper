package ru.education.myproject1.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Table("NOTES")
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
@AllArgsConstructor
public class Note {
    @Id
    @Column("NOTE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column("NOTE")
    private String note;
    @Column("USERID")
    private Long userId;
}

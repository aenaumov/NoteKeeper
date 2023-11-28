package ru.education.myproject1.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Table("USERS")
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
@AllArgsConstructor
public class User {

    @Id
    @Column("USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column("USERNAME")
    private String username;
    @Column("USER_PASSWORD")
    private String password;
    @Column("USER_ROLE")
    private String userRole;
}

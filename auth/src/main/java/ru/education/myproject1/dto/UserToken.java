package ru.education.myproject1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserToken {

    private Long id;
    private String userRole;
}

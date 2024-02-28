package ru.education.myproject1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AccessRefreshToken {

    final private String type = "Bearer";
    private String access_token;
    private String refresh_token;
}

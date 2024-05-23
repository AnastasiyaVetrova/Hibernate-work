package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalDataDTO {
    private String login;
    private String password;
    private Long person;

    public PersonalDataDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

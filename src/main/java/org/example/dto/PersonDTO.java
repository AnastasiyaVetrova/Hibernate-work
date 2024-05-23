package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private String name;
    private int age;
    private List<CommentDTO> comment = new ArrayList<>();
    private PersonalDataDTO personalData;
    private List<CommunityDTO> communities = new ArrayList<>();
}

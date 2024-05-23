package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "personal_data")
@NoArgsConstructor
public class PersonalDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    public PersonalDataEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Setter
@Getter
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @OneToMany(mappedBy = "person")
    private List<CommentEntity> comment = new ArrayList<>();
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonalDataEntity personalDataEntity;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_communities",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "community_id", referencedColumnName = "id")
    )
    private List<CommunityEntity> communities = new ArrayList<>();

}

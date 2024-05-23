package org.example.services;

import org.example.dto.CommentDTO;
import org.example.dto.CommunityDTO;
import org.example.dto.PersonDTO;
import org.example.dto.PersonalDataDTO;
import org.example.entity.PersonEntity;
import org.example.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePerson implements ServiceInterface<PersonDTO> {
    private final RepositoryInterface<PersonEntity> repository;

    @Autowired
    public ServicePerson(RepositoryInterface<PersonEntity> repository) {
        this.repository = repository;
    }

    public List<PersonDTO> getAll() throws Exception {
        List<PersonEntity> entityList = repository.findAll();
        List<PersonDTO> dtoList = new ArrayList<>();
        entityList.forEach(e -> dtoList.add(mapToDTO(e)));
        return dtoList;
    }

    public PersonDTO get(long id) throws Exception {
        PersonEntity personEntity = repository.findById(id);
        return mapToDTO(personEntity);
    }

    public void create(PersonDTO personDTO) throws Exception {
        repository.save(mapToEntity(personDTO));
    }

    public void update(Long id, PersonDTO personDTO) throws Exception {
        PersonEntity personEntity = repository.findById(id);
        if (personDTO.getName() != null) {
            personEntity.setName(personDTO.getName());
        } else if (personDTO.getAge() != 0) {
            personEntity.setAge(personDTO.getAge());
        }
        repository.update(personEntity);
    }

    public void delete(Long id) throws Exception {
        repository.delete(id);
    }

    private PersonDTO mapToDTO(PersonEntity personEntity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(personEntity.getName());
        personDTO.setAge(personEntity.getAge());
        if (personEntity.getPersonalDataEntity() != null) {
            personDTO.setPersonalData(new PersonalDataDTO(personEntity.getPersonalDataEntity().getLogin(),
                    personEntity.getPersonalDataEntity().getPassword()));
        }
        if (!personEntity.getCommunities().isEmpty()) {
            personEntity.getComment().forEach(e -> personDTO.getComment().add(new CommentDTO(e.getComment())));
        }
        if (!personEntity.getCommunities().isEmpty()) {
            personEntity.getCommunities().forEach(e -> personDTO.getCommunities().add(new CommunityDTO(e.getName(), e.getDescription())));
        }
        return personDTO;
    }

    private PersonEntity mapToEntity(PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personDTO.getName());
        personEntity.setAge(personDTO.getAge());
        return personEntity;
    }
}

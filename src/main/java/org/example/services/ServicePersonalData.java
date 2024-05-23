package org.example.services;

import org.example.dto.PersonalDataDTO;
import org.example.entity.PersonEntity;
import org.example.entity.PersonalDataEntity;
import org.example.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePersonalData implements ServiceInterface<PersonalDataDTO> {
    private final RepositoryInterface<PersonalDataEntity> repository;

    @Autowired
    public ServicePersonalData(RepositoryInterface<PersonalDataEntity> repository) {
        this.repository = repository;
    }

    @Override
    public List<PersonalDataDTO> getAll() throws Exception {
        List<PersonalDataEntity> entityList = repository.findAll();
        List<PersonalDataDTO> dtoList = new ArrayList<>();
        entityList.forEach(e -> dtoList.add(mapToDTO(e)));
        return dtoList;
    }

    @Override
    public PersonalDataDTO get(long id) throws Exception {
        PersonalDataEntity personalDataEntity = repository.findById(id);
        return mapToDTO(personalDataEntity);
    }

    @Override
    public void create(PersonalDataDTO personalDataDTO) throws Exception {
        repository.save(mapToEntity(personalDataDTO));
    }

    @Override
    public void update(Long id, PersonalDataDTO personalDataDTO) throws Exception {
        PersonalDataEntity personalDataEntity = mapToEntity(personalDataDTO);
        personalDataEntity.setId(id);
        repository.update(personalDataEntity);
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.delete(id);
    }

    private PersonalDataDTO mapToDTO(PersonalDataEntity personalDataEntity) {
        PersonalDataDTO personalDataDTO = new PersonalDataDTO();
        personalDataDTO.setLogin(personalDataEntity.getLogin());
        personalDataDTO.setPassword(personalDataEntity.getPassword());
        personalDataDTO.setPerson(personalDataEntity.getPerson().getId());
        return personalDataDTO;
    }

    private PersonalDataEntity mapToEntity(PersonalDataDTO personalDataDTO) {
        PersonalDataEntity personalDataEntity = new PersonalDataEntity();
        personalDataEntity.setLogin(personalDataDTO.getLogin());
        personalDataEntity.setPassword(personalDataDTO.getPassword());
        if (personalDataDTO.getPerson() != 0) {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setId(personalDataDTO.getPerson());
            personalDataEntity.setPerson(personEntity);
        }
        return personalDataEntity;
    }
}

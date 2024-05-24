package org.example.services;

import org.example.dto.CommunityDTO;
import org.example.entity.CommunityEntity;
import org.example.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceCommunity implements ServiceInterface<CommunityDTO> {
    private final RepositoryInterface<CommunityEntity> repository;

    @Autowired
    public ServiceCommunity(RepositoryInterface<CommunityEntity> repository) {
        this.repository = repository;
    }

    @Override
    public List<CommunityDTO> getAll() throws Exception {
        List<CommunityEntity> entityList = repository.findAll();
        List<CommunityDTO> dtoList = new ArrayList<>();
        entityList.forEach(e -> dtoList.add(mapToDTO(e)));
        return dtoList;
    }

    @Override
    public CommunityDTO get(long id) throws Exception {
        CommunityEntity communityEntity = repository.findById(id);
        return mapToDTO(communityEntity);
    }

    @Override
    public void create(CommunityDTO communityDTO) throws Exception {
        repository.save(mapToEntity(communityDTO));
    }

    @Override
    public void update(Long id, CommunityDTO communityDTO) throws Exception {
        CommunityEntity communityEntity = mapToEntity(communityDTO);
        communityEntity.setId(id);
        repository.update(communityEntity);
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.delete(id);
    }

    private CommunityDTO mapToDTO(CommunityEntity communityEntity) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setName(communityEntity.getName());
        communityDTO.setDescription(communityEntity.getDescription());
        return communityDTO;
    }

    private CommunityEntity mapToEntity(CommunityDTO communityDTO) {
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setName(communityDTO.getName());
        communityEntity.setDescription(communityDTO.getDescription());
        return communityEntity;
    }
}

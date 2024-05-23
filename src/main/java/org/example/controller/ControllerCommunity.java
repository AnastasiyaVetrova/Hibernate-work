package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CommunityDTO;
import org.example.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
@Slf4j
public class ControllerCommunity {
    private final ServiceInterface<CommunityDTO> serviceCommunity;

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getAll(){
        try {
            return ResponseEntity.ok(serviceCommunity.getAll());
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityDTO> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceCommunity.get(id));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestBody CommunityDTO communityDTO) {
        try {
            serviceCommunity.create(communityDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
    @PutMapping("/update/{id}")
    public HttpStatus update(@PathVariable Long id, @RequestBody CommunityDTO communityDTO) {
        try {
            serviceCommunity.update(id, communityDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id){
        try {
            serviceCommunity.delete(id);
        } catch (Exception e) {
            log.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}

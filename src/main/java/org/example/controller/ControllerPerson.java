package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PersonDTO;
import org.example.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
@Slf4j
public class ControllerPerson {
    private final ServiceInterface<PersonDTO> servicePerson;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll(){
        try {
            return ResponseEntity.ok(servicePerson.getAll());
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(servicePerson.get(id));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestBody PersonDTO personDTO) {
        try {
            servicePerson.create(personDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
    @PutMapping("/update/{id}")
    public HttpStatus update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        try {
            servicePerson.update(id, personDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id){
        try {
            servicePerson.delete(id);
        } catch (Exception e) {
            log.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}

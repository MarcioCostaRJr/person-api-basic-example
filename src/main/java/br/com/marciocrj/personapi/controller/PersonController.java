package br.com.marciocrj.personapi.controller;

import br.com.marciocrj.personapi.dto.MessageResponseDTO;
import br.com.marciocrj.personapi.dto.request.PersonDTO;
import br.com.marciocrj.personapi.entity.Person;
import br.com.marciocrj.personapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid final PersonDTO dto) {
        final Person savePerson = service.save(dto);
        return Objects.isNull(savePerson)
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.created(null)
                    .body(MessageResponseDTO
                            .builder()
                            .message("Created person with ID: " + savePerson.getId())
                            .build());
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        final Collection<?> listAll = service.listAll();
        return CollectionUtils.isEmpty(listAll)
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(listAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable  final Long id){
        final PersonDTO personDTO = service.findById(id);
        return Objects.isNull(personDTO)
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(personDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable final Long id,
                                        @RequestBody @Valid final PersonDTO dto) {
        final PersonDTO personDTO = service.updateById(id, dto);
        return Objects.isNull(personDTO)
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok("Update person with ID: " + id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}

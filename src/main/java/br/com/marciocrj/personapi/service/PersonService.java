package br.com.marciocrj.personapi.service;

import br.com.marciocrj.personapi.dto.request.PersonDTO;
import br.com.marciocrj.personapi.entity.Person;
import br.com.marciocrj.personapi.mapper.PersonMapper;
import br.com.marciocrj.personapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public Person save(final PersonDTO dto) {
        final Person person = mapper.toModel(dto);
        return repository.save(person);
    }

    public Collection<?> listAll() {
        final Collection<Person> all = repository.findAll();
        return all.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        final Optional<Person> personById = verifyExists(id);
        return personById.isEmpty() ? null : mapper.toDTO(personById.get());
    }

    public void delete(Long id) {
        final Optional<Person> optPerson = verifyExists(id);
        if (!optPerson.isEmpty()) {
            repository.deleteById(id);
        }
    }

    public PersonDTO updateById(Long id, PersonDTO dto) {
        final Optional<Person> optPerson = verifyExists(id);
        return optPerson.isEmpty()
                ? null
                : mapper.toDTO(repository.save(mapper.toModel(dto)));
    }

    private Optional<Person> verifyExists(final Long id) {
        return repository.findById(id);
    }
}

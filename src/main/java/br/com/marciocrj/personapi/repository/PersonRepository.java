package br.com.marciocrj.personapi.repository;

import br.com.marciocrj.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

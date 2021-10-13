package br.com.marciocrj.personapi.mapper;

import br.com.marciocrj.personapi.dto.request.PersonDTO;
import br.com.marciocrj.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(final PersonDTO dto);

    PersonDTO toDTO(final Person person);
}

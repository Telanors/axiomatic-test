package ru.telanors.rest.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.telanors.rest.DTO.PersonDTO;
import ru.telanors.rest.entity.Person;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper extends BaseMapper<Person, PersonDTO> {
    @Override
    PersonDTO toDTO(Person entity);

    @Override
    Person toEntity(PersonDTO dto);
}

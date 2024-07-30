package ru.telanors.rest.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.telanors.rest.DTO.PersonDTO;
import ru.telanors.rest.entity.Person;
import ru.telanors.rest.repository.PersonRepository;
import ru.telanors.rest.service.PersonService;
import ru.telanors.rest.util.mapper.DocumentMapper;
import ru.telanors.rest.util.mapper.PersonMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final DocumentMapper documentMapper;

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        var person = personMapper.toEntity(personDTO);
        return personMapper.toDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO findById(Long id) {
        var person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return personMapper.toDTO(person);
    }

    @Override
    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO update(Long id, PersonDTO personDTO) {
        var person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setPatronymic(personDTO.getPatronymic());
        person.setBirthDate(personDTO.getBirthDate());
        person.setGender(personDTO.getGender());
        person.setDocument(documentMapper.toEntity(personDTO.getDocument()));

        return personMapper.toDTO(personRepository.save(person));
    }

    @Override
    public void delete(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        personRepository.delete(person);
    }
}

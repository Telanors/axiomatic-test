package ru.telanors.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.telanors.rest.DTO.PersonDTO;
import ru.telanors.rest.service.PersonService;
import ru.telanors.rest.service.SoapClientService;
import ru.telanors.rest.util.xml.XmlConverter;

@Validated
@RestController
@RequestMapping("/rest/api/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final SoapClientService soapClientService;

    private final XmlConverter xmlConverter;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid PersonDTO personDTO) throws Exception {
        personService.save(personDTO);
        var personXml = xmlConverter.convertDtoToXml(personDTO, "person");
        var personXslt = soapClientService.sendRequest(personXml);
        var person = xmlConverter.convertXsltToDto(PersonDTO.class, personXslt);
        personService.save(person);

        return ResponseEntity.ok(personXslt);
    }
}
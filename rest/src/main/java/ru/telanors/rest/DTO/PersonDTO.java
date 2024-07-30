package ru.telanors.rest.DTO;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.telanors.rest.entity.enums.Gender;
import ru.telanors.rest.util.adapter.LocalDateAdapter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDTO {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String surname;

    @XmlAttribute
    private String patronymic;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute
    private LocalDate birthDate;

    @XmlAttribute
    private Gender gender;

    @XmlElement(name = "document")
    private DocumentDTO document;
}

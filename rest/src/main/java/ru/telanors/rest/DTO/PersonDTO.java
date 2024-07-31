package ru.telanors.rest.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.telanors.rest.entity.enums.Gender;
import ru.telanors.rest.util.adapter.LocalDateAdapter;

import java.time.LocalDate;

import static ru.telanors.rest.util.validation.ValidationUtils.LETTERS_ONLY_PATTERN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDTO {

    @NotNull
    @Pattern(regexp = LETTERS_ONLY_PATTERN)
    @XmlAttribute
    private String name;

    @NotNull
    @Pattern(regexp = LETTERS_ONLY_PATTERN)
    @XmlAttribute
    private String surname;

    @Pattern(regexp = LETTERS_ONLY_PATTERN)
    @XmlAttribute
    private String patronymic;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull
    @PastOrPresent
    @XmlAttribute
    private LocalDate birthDate;

    @NotNull
    @XmlAttribute
    private Gender gender;

    @Valid
    @NotNull
    @XmlElement(name = "document")
    private DocumentDTO document;
}

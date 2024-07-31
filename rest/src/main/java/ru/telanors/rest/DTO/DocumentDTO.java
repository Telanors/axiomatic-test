package ru.telanors.rest.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.telanors.rest.entity.enums.DocumentType;
import ru.telanors.rest.util.adapter.LocalDateAdapter;

import java.time.LocalDate;

import static ru.telanors.rest.util.validation.ValidationUtils.NUMBER_PATTERN;
import static ru.telanors.rest.util.validation.ValidationUtils.SERIES_PATTERN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentDTO {
    @NotNull
    @Pattern(regexp = SERIES_PATTERN)
    @XmlAttribute
    private String series;

    @NotNull
    @Pattern(regexp = NUMBER_PATTERN)
    @XmlAttribute
    private String number;

    @NotNull
    @XmlAttribute
    private DocumentType type;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull
    @PastOrPresent
    @XmlAttribute
    private LocalDate issueDate;
}

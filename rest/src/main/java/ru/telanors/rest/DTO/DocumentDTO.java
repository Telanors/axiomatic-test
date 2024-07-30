package ru.telanors.rest.DTO;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentDTO {
    @XmlAttribute
    private String series;

    @XmlAttribute
    private String number;

    @XmlAttribute
    private DocumentType type;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute
    private LocalDate issueDate;
}

package ru.telanors.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.telanors.rest.entity.enums.DocumentType;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "documents")
public class Document implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String series;
    private String number;
    private LocalDate issueDate;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @ToString.Exclude
    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    public void setPerson(Person person) {
        if (person != null) {
            this.person = person;
            person.setDocument(this);
        }
    }
}

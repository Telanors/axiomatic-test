package ru.telanors.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.telanors.rest.entity.enums.Gender;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "persons")
public class Person implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "document_id")
    private Document document;

    public void setDocument(Document document) {
        if (document != null) {
            this.document = document;
            document.setPerson(this);
        }
    }
}

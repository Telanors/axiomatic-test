package ru.telanors.rest.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.telanors.rest.entity.Person;
import ru.telanors.rest.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Person save(Person person) {
        Session session = sessionFactory.getCurrentSession();

        return session.merge(person);
    }

    @Override
    public Person update(Long id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person existingPerson = session.get(Person.class, id);
        if (existingPerson != null) {
            existingPerson.setName(person.getName());
            existingPerson.setSurname(person.getSurname());
            existingPerson.setPatronymic(person.getPatronymic());
            existingPerson.setGender(person.getGender());
            existingPerson.setBirthDate(person.getBirthDate());

            session.merge(existingPerson);
        }

        return existingPerson;
    }

    @Override
    public void delete(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(person);
    }

    @Override
    public Optional<Person> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        var person = session.get(Person.class, id);

        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Person", Person.class).list();
    }
}

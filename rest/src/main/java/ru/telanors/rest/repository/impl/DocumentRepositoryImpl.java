package ru.telanors.rest.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.telanors.rest.entity.Document;
import ru.telanors.rest.repository.DocumentRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Document save(Document document) {
        Session session = sessionFactory.getCurrentSession();

        return session.merge(document);
    }

    @Override
    public Document update(Long id, Document document) {
        Session session = sessionFactory.getCurrentSession();
        Document existingDocument = session.get(Document.class, id);
        if (existingDocument != null) {
            existingDocument.setSeries(document.getSeries());
            existingDocument.setNumber(document.getNumber());
            existingDocument.setIssueDate(document.getIssueDate());
            existingDocument.setType(document.getType());

            session.merge(existingDocument);
        }

        return existingDocument;
    }

    @Override
    public void delete(Document document) {
        Session session = sessionFactory.getCurrentSession();
        if (document != null) {
            session.remove(document);
        }
    }

    @Override
    public Optional<Document> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        var document = session.get(Document.class, id);

        return Optional.ofNullable(document);
    }

    @Override
    public List<Document> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Document", Document.class).list();
    }
}

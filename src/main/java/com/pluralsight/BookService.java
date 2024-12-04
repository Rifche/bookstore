package com.pluralsight;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
@ApplicationScoped
@Named
public class BookService {

    @Inject
    EntityManager em;

    @Inject
    IsbnGenerator generator;

    public Book find(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll () {
        TypedQuery<Book> query = em.createQuery("select b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("select COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(Book book) {
        // create book
        book.setIsbn(generator.generateNumber());
        em.persist(book);
        return book;
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        // delete book
        em.remove(em.getReference(Book.class, id));
    }

}

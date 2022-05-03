package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class BooksDAO {

    @Inject
    private EntityManager em;

    public List<Book> loadAll() { return em.createNamedQuery("Book.findAll", Book.class).getResultList(); }

    public void setEm(EntityManager em) { this.em = em; }

    public void persist(Book book) { this.em.persist(book); }

    public Book findOne(Integer bookId) { return em.find(Book.class, bookId); }
}

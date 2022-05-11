package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@ApplicationScoped
public class BookDAO {

    @Inject
    private EntityManager entityManager;

    public void persist(Book book) {
        this.entityManager.persist(book);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Book findOne(Long id){
        return entityManager.find(Book.class, id);
    }

    public Book update(Book book){
        return entityManager.merge(book);
    }
}


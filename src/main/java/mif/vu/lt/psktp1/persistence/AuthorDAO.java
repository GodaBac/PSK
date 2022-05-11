package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AuthorDAO {

    @Inject
    private EntityManager entityManager;

    public List<Author> loadAll() {
        return entityManager.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public void persist(Author author) {
        this.entityManager.persist(author);
    }

    public Author findOne(Long id) {
        return entityManager.find(Author.class, id);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

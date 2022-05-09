package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AuthorsDAO {

    @Inject
    private EntityManager em;

    public List<Author> loadAll() {return em.createNamedQuery("Author.findAll", Author.class).getResultList();}

    public void setEm(EntityManager em) {this.em = em;}

    public void persist(Author author) {this.em.persist(author);}

    public Author findOne(Integer authorId) {return em.find(Author.class, authorId); }

    public boolean checkIfExists(Author author) {
        List<Author> allAuthors;
        allAuthors = loadAll();
        return allAuthors.contains(author);
    }
}

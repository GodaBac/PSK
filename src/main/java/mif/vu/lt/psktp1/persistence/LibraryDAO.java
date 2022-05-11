package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Library;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@ApplicationScoped
public class LibraryDAO {

    @Inject
    private EntityManager entityManager;

    public List<Library> loadAll() {
        return entityManager.createNamedQuery("Library.findAll", Library.class).getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(Library library) {
        this.entityManager.persist(library);
    }

    public Library findOne(Long id) {
        return entityManager.find(Library.class, id);
    }

    public Library findByName(String name) {
        try {
            return entityManager.createNamedQuery("Library.findOne", Library.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}

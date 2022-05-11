package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Library;
import mif.vu.lt.psktp1.persistence.LibraryDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Libraries implements Serializable {
    @Inject
    private LibraryDAO libraryDAO;

    @Getter
    @Setter
    private Library libraryToCreate = new Library();

    @Getter
    private List<Library> allLibraries;

    @PostConstruct
    public void init() {
        loadLibraries();
    }

    public void loadLibraries() {
        this.allLibraries = libraryDAO.loadAll();
    }

    @Transactional
    public String createLibrary() {
        this.libraryDAO.persist(libraryToCreate);
        return "index?faces-redirect=true";
    }
}

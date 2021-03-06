package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.persistence.AuthorDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Authors implements Serializable {

    @Inject
    private AuthorDAO authorDAO;

    @Getter
    @Setter
    private Author authorToCreate = new Author();

    @Getter
    private List<Author> allAuthors;

    @PostConstruct
    public void init() {
        loadAccounts();
    }

    public void loadAccounts() {
        this.allAuthors = authorDAO.loadAll();
    }

    @Transactional
    public String createAuthor() {
        this.authorDAO.persist(authorToCreate);
        return "index?faces-redirect=true";
    }

}

package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.interceptors.LoggedInvocation;
import mif.vu.lt.psktp1.persistence.AuthorDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateAuthorDetails implements Serializable {

    private Author author;

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private EntityManager entityManager;

    @PostConstruct
    private void init() {
        System.out.println("----------UpdateAuthorDetails INIT CALLED----------");
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = authorDAO.findOne(authorId);
    }

    @Transactional
    @LoggedInvocation
    public String updateAuthorFirstName() {
        try {
            authorDAO.update(this.author);
        } catch (OptimisticLockException e) {
            System.out.println(this.author.getId());
            return "authorDetails?faces-redirect=true&authorId=" + this.author.getId() + "&error=optimistic-lock-exception";
        }
        return "authors?faces-redirect=true";

    }
}

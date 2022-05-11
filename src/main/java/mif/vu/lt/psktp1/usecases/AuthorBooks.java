package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Book;
import mif.vu.lt.psktp1.persistence.AuthorsDAO;
import mif.vu.lt.psktp1.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class AuthorBooks implements Serializable {

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter @Setter
    private Book bookToAssign = new Book();


    @Transactional
    public String assignAuthorForBook(String authorId) {
        Book assign = booksDAO.findByBookName(this.bookToAssign.getBookName());

        if (assign != null) {
            return "index?faces-redirect=true";
        }
        else {
            return "index?faces-redirect=true";
        }
    }

}

package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Author;
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
    private Author authorToAdd = new Author();

    @Getter @Setter
    private Book book;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer bookId = Integer.parseInt(requestParameters.get("bookId"));
        this.book = booksDAO.findOne(bookId);
    }

    @Transactional
    public void addAuthor() {
        if (authorsDAO.checkIfExists(authorToAdd)) {
            this.book.setAuthorId(authorToAdd.getId());
        }
        else {
            authorToAdd.setBookList(this.authorToAdd.getBookList());
            authorsDAO.persist(authorToAdd);
        }
    }

}

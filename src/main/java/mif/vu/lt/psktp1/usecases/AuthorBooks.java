package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.alternatives.Message;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.entities.Book;
import mif.vu.lt.psktp1.persistence.AuthorDAO;
import mif.vu.lt.psktp1.persistence.BookDAO;
import mif.vu.lt.psktp1.qualifiers.BookTypeProcessor;
import mif.vu.lt.psktp1.qualifiers.Hardcover;
import mif.vu.lt.psktp1.qualifiers.HardcoverBookType;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class AuthorBooks implements Serializable {

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private BookDAO bookDAO;

    @Inject
    private Message message;

    @Inject @Hardcover
    BookTypeProcessor bookTypeProcessor;

    @Inject @Any
    private HardcoverBookType hardcoverBookType;

    @Getter
    @Setter
    private Author author;

    @Getter
    @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = authorDAO.findOne(authorId);
    }

    @Transactional
    public String createBook() {
        bookToCreate.setAuthor(this.author);
        bookDAO.persist(bookToCreate);
        System.out.println(message.WriteMessage());
        System.out.println(bookTypeProcessor.BookType());
        System.out.println(hardcoverBookType.BookType());
        return "books?faces-redirect=true&authorId=" + this.author.getId();
    }

}

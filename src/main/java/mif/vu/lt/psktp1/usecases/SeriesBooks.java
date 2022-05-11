package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.entities.Book;
import mif.vu.lt.psktp1.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class SeriesBooks implements Serializable{

    @Inject
    private BooksDAO booksDAO;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @Getter @Setter
    private List<Book> allBooks;

    @Getter @Setter
    private List<Author> allAuthors;

    @PostConstruct
    public void init(){
        loadBooks();
    }

    public void loadBooks() { this.allBooks = booksDAO.loadAll(); }

    @Transactional
    public String createBook(){
        this.booksDAO.persist(bookToCreate);
        return "index?faces-redirect=true";
    }
}

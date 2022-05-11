package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.mybatis.dao.AuthorMapper;
import mif.vu.lt.psktp1.mybatis.dao.BookMapper;
import mif.vu.lt.psktp1.mybatis.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class BooksAndAuthorsMyBatis implements Serializable {

    @Inject
    private BookMapper bookMapper;

    @Getter
    private List<Book> allBooks;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    private void init() { loadAllBooks(); }

    @Transactional
    public String createBook() {
        bookMapper.insert(bookToCreate);
        return "/myBatis/booksAndAuthors?faces-redirect=true";
    }

    private void loadAllBooks() { allBooks = bookMapper.selectAll(); }
}

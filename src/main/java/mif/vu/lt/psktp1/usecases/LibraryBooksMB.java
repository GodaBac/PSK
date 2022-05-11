package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.mybatis.dao.BookMapper;
import mif.vu.lt.psktp1.mybatis.dao.BooksLibraryMapper;
import mif.vu.lt.psktp1.mybatis.dao.LibraryMapper;
import mif.vu.lt.psktp1.mybatis.model.Book;
import mif.vu.lt.psktp1.mybatis.model.BooksLibrary;
import mif.vu.lt.psktp1.mybatis.model.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class LibraryBooksMB implements Serializable {

    @Inject
    private BookMapper bookMapper;

    @Inject
    private LibraryMapper libraryMapper;

    @Inject
    private BooksLibraryMapper booksLibraryMapper;

    @Getter
    @Setter
    private Library library;

    @Getter
    @Setter
    private Book bookToAdd = new Book();

    @Getter
    private List<Book> allExistingBooks;

    @Transactional
    public String addBookForLibrary(Long bookId) {
        if (booksLibraryMapper.getResultCountByBookAndLibraryId(bookId, this.library.getId()) == 0) {
            BooksLibrary booksLibrary = new BooksLibrary();
            booksLibrary.setBookId(bookId);
            booksLibrary.setLibraryId(this.library.getId());
            booksLibraryMapper.insert(booksLibrary);
        }
        return "/myBatis/librariesAndBooks?faces-redirect=true";
    }

    @Transactional
    public String addNewBookForLibrary() {
        if (bookMapper.getResultCountByBookTitle(bookToAdd.getTitle()) == 0) {
            bookMapper.insert(bookToAdd);
        }
        Book addedBook = bookMapper.findByName(bookToAdd.getTitle());
        BooksLibrary booksLibrary = new BooksLibrary();
        booksLibrary.setBookId(addedBook.getId());
        booksLibrary.setLibraryId(this.library.getId());
        booksLibraryMapper.insert(booksLibrary);
        return "/myBatis/librariesAndBooks?faces-redirect=true";
    }

    @PostConstruct
    private void init() {
        Map<String, String > requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        Long libraryId = Long.parseLong(requestParams.get("libraryId"));
        this.library = libraryMapper.selectByPrimaryKey(libraryId);
        this.allExistingBooks = this.bookMapper.selectAll();
    }
}

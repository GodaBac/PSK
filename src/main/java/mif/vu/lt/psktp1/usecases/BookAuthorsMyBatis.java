package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.mybatis.dao.AuthorMapper;
import mif.vu.lt.psktp1.mybatis.dao.AuthorsBooksMapper;
import mif.vu.lt.psktp1.mybatis.dao.BookMapper;
import mif.vu.lt.psktp1.mybatis.model.Author;
import mif.vu.lt.psktp1.mybatis.model.AuthorsBooks;
import mif.vu.lt.psktp1.mybatis.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class BookAuthorsMyBatis implements Serializable {

    @Inject
    private BookMapper bookMapper;

    @Inject
    private AuthorMapper authorMapper;

    @Inject
    private AuthorsBooksMapper authorsBooksMapper;

    @Getter @Setter
    private Book book;

    @Getter @Setter
    private Author authorToAdd = new Author();

    @Getter
    private List<Author> allExistingAuthors;

    @Transactional
    public String addAuthorForBook (Integer authorId) {
        if(authorsBooksMapper.getResultCountByAuthorAndBookId(authorId, this.book.getId()) == 0 ) {
            AuthorsBooks authorsBooks = new AuthorsBooks();
            authorsBooks.setAuthorListId(authorId);
            authorsBooks.setBookListId(this.book.getId());
            authorsBooksMapper.insert(authorsBooks);
        }
        return "/myBatis/booksAndAuthors?faces-redirect=true";
    }

    @Transactional
    public String addNewAuthorForBook () {
        if (authorMapper.getResultCountByAuthorName(authorToAdd.getAuthorName()) == 0) {
            authorMapper.insert(authorToAdd);
        }
        Author addedAuthor = authorMapper.findByAuthorName(authorToAdd.getAuthorName());
        AuthorsBooks authorsBooks = new AuthorsBooks();
        authorsBooks.setAuthorListId(addedAuthor.getId());
        authorsBooks.setBookListId(this.book.getId());
        authorsBooksMapper.insert(authorsBooks);
        return "/myBatis/booksAndAuthors?faces-redirect=true";
    }

    @PostConstruct
    private void init() {
        Map<String, String > requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        Integer bookId = Integer.parseInt(requestParams.get("bookId"));
        this.book = bookMapper.selectByPrimaryKey(bookId);
        this.allExistingAuthors = this.authorMapper.selectAll();
    }
}

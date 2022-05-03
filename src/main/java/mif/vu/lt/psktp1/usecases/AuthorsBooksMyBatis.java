package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import mif.vu.lt.psktp1.mybatis.dao.AuthorMapper;
import mif.vu.lt.psktp1.mybatis.dao.BookMapper;
import mif.vu.lt.psktp1.mybatis.model.Author;
import mif.vu.lt.psktp1.mybatis.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AuthorsBooksMyBatis {

    @Inject
    AuthorMapper authorMapper;

    @Inject
    BookMapper bookMapper;


    @Getter
    private List<Book> bookList;

    @Getter
    private List<Author> authorList;

    @PostConstruct
    public void init(){this.loadAllBookAuthors();}

    private void loadAllAuthorBooks(){
        this.bookList = authorMapper.selectBooksForAuthor(1);
    }

    private void loadAllBookAuthors(){this.authorList = bookMapper.selectAuthorsForBook(1);}

}

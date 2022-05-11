package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.mybatis.dao.AuthorMapper;
import mif.vu.lt.psktp1.mybatis.dao.BookMapper;
import mif.vu.lt.psktp1.mybatis.model.Author;
import mif.vu.lt.psktp1.mybatis.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
public class AuthorsBooksMyBatis {

    @Inject
    AuthorMapper authorMapper;

    @Getter
    @Setter
    private Author author;

    @Getter @Setter
    private Author authorToCreate = new Author();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
        this.author = authorMapper.selectByPrimaryKey(authorId);
//        this.loadAllBookAuthors();
    }
//
//    private void loadAllAuthorBooks(){
//        this.bookList = authorMapper.selectBooksForAuthor(1);
//    }
//
//    private void loadAllBookAuthors(){this.authorList = bookMapper.selectAuthorsForBook(1);}

}

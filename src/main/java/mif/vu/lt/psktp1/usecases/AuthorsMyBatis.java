package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Series;
import mif.vu.lt.psktp1.entities.Book;
import mif.vu.lt.psktp1.mybatis.dao.AuthorMapper;
import mif.vu.lt.psktp1.mybatis.model.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AuthorsMyBatis {
    @Inject
    AuthorMapper authorMapper;

    @Getter
    private List<Author> authorsList;

    @Getter @Setter
    private Author authorsToCreate = new Author();

    @PostConstruct
    public void init(){this.loadAllAuthors();}

    private void loadAllAuthors(){
        this.authorsList = authorMapper.selectAll();
    }



    @Transactional
    public String createAuthor(){
        authorMapper.insert(authorsToCreate);
        return "/myBatis/authors?faces-redirect=true";
    }
}

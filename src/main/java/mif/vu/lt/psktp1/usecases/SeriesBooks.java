package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Series;
import mif.vu.lt.psktp1.entities.Book;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.persistence.AuthorsDAO;
import mif.vu.lt.psktp1.persistence.SeriesDAO;
import mif.vu.lt.psktp1.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class SeriesBooks implements Serializable{

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private SeriesDAO seriesDAO;

    @Getter @Setter
    private Series series;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @Getter @Setter
    private List<Author> authorList;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer seriesId = Integer.parseInt(requestParameters.get("seriesId"));
        this.series = seriesDAO.findOne(seriesId);
    }

    @Transactional
    public void createBook(){
        bookToCreate.setSeries(this.series);
        bookToCreate.setAuthorId(this.series.getAuthor().getAuthorId());
        booksDAO.persist(bookToCreate);
    }
}

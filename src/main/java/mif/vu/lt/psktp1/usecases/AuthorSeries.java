package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Series;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.persistence.SeriesDAO;
import mif.vu.lt.psktp1.persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class AuthorSeries implements Serializable{

    @Inject
    private SeriesDAO seriesDAO;

    @Inject
    private AuthorsDAO authorsDAO;

    @Getter @Setter
    private Author author;

    @Getter @Setter
    private Series seriesToCreate = new Series();

    @Getter
    private List<Series> allSeries;

    @PostConstruct
    public void init(){
        loadBooks();
    }

    public void loadBooks() { this.allSeries = seriesDAO.loadAll(); }

    @Transactional
    public String createSeries(){
        this.seriesDAO.persist(seriesToCreate);
        return "index?faces-redirect=true";
    }
}

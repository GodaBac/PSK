package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Series;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.persistence.SeriesDAO;
import mif.vu.lt.psktp1.persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

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

    @PostConstruct
    public void init(){
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
    }

    @Transactional
    public void createSeries(){
        seriesToCreate.setAuthor(this.author);
        seriesToCreate.setSeriesAuthor(this.author.getAuthorName());
        seriesDAO.persist(seriesToCreate);
    }
}

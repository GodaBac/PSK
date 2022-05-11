package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.entities.Series;
import mif.vu.lt.psktp1.persistence.AuthorsDAO;
import mif.vu.lt.psktp1.persistence.SeriesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class Authors implements Serializable {

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private SeriesDAO seriesDAO;

    @Getter @Setter
    private Series series;

    @Getter @Setter
    private Author authorToCreate = new Author();


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer seriesId = Integer.parseInt(requestParameters.get("seriesId"));
        this.series = seriesDAO.findOne(seriesId);
    }

    @Transactional
    public String createAuthor() {
        authorToCreate.setSeries(this.series);
        authorsDAO.persist(authorToCreate);
        return "authors?faces-redirect=true&seriesId=" + this.series.getId();
    }

}

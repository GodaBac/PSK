package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.mybatis.dao.AuthorMapper;
import mif.vu.lt.psktp1.mybatis.dao.SeriesMapper;
import mif.vu.lt.psktp1.mybatis.model.Author;
import mif.vu.lt.psktp1.mybatis.model.Series;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class AuthorSeriesMyBatis {

    @Inject
    AuthorMapper authorMapper;

    @Inject
    SeriesMapper seriesMapper;

    @Getter @Setter
    private Author author;

    @Getter
    private List<Series> seriesList;

    @Getter @Setter
    private Series seriesToCreate = new Series();

    @PostConstruct
    public void init(){
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));

        this.author = authorMapper.selectByPrimaryKey(authorId);

    }

    public void loadAllSeries(){this.seriesList = seriesMapper.selectAll();}

    @Transactional
    public void createSeries(){
        seriesToCreate.setSeriesAuthor(this.author.getAuthorName());
    }

}

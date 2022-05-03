package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Series;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SeriesDAO {

    @Inject
    private EntityManager em;

    public void persist(Series series) {this.em.persist(series);}

    public Series findOne(Integer seriesId) {return em.find(Series.class, seriesId);}

    public List<Series> findAll() {return em.createNamedQuery("Series.findAll",Series.class).getResultList();} //

    public Series update(Series series) {return  em.merge(series); }
}

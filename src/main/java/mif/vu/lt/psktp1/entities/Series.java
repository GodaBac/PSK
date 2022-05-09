package mif.vu.lt.psktp1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Series.findAll", query = "select s from Series as s")
})

@Table (name = "SERIES")
@Getter @Setter
public class Series implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 50)
    @Column(name = "SERIES_NAME", nullable = false, unique = true)
    private String seriesName;

    @Column(name = "SERIES_AUTHOR")
    private String seriesAuthor;

    @Column(name = "BOOKS_AMOUNT")
    private Integer booksAmount;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @OneToMany(mappedBy = "series")
    private List<Book> bookList = new ArrayList<>();

    public Series() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return id.equals(series.id) && seriesName.equals(series.seriesName) && seriesAuthor.equals(series.seriesAuthor);

    }

    @Override
    public int hashCode() {return Objects.hash(id, seriesName, seriesAuthor); }
}

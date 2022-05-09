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
        @NamedQuery(name = "Author.findAll", query = "select a from Author as a ")
})

@Table(name = "AUTHOR")
@Getter @Setter
public class Author implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 50)
    @Column(name = "AUTHOR_NAME", nullable = false, unique = true)
    private String authorName;

    @OneToMany(mappedBy = "author")
    private List<Series> seriesList = new ArrayList<>();

    @ManyToMany(mappedBy = "authorList")
    private List<Book> bookList = new ArrayList<>();

    public Author(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id) && authorName.equals(author.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName);
    }
}

package mif.vu.lt.psktp1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = "Library.findAll", query = "SELECT l FROM Library AS l"),
        @NamedQuery(name = "Library.findOne", query = " SELECT l FROM Library  AS l WHERE l.name = :name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LIBRARY")
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column
    @ManyToMany
    @JoinTable(name = "BOOKS_LIBRARY",
            joinColumns = @JoinColumn(name = "LIBRARY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    List<Book> books = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Library library = (Library) o;
        return id.equals(library.id) && name.equals(library.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

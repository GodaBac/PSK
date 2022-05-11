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
        @NamedQuery(name = "Book.findAll", query = "select b from Book as b"),
        @NamedQuery(name = "Book.findOne", query = " select b from Book as b where b.bookName = :bookName")
})

@Table(name = "BOOK")
@Getter @Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "AUTHOR")
    private Integer authorId;

    @Size(max = 50)
    @Column(name = "BOOK_NAME", nullable = false, unique = true)
    private String bookName;

    @Column
    @ManyToMany
    @JoinTable(name = "AUTHORS_BOOKS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    List<Author> authors = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "SERIES_ID")
//    private Series series;

    public Book() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookName, book.bookName);
    }

    @Override
    public int hashCode() { return Objects.hash(id, authorId, bookName); }
}

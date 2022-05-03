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
        @NamedQuery(name = "Book.findAll", query = "select b from Book as b")
})

@Table(name = "BOOK")
@Getter @Setter
public class Book implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(name = "AUTHOR")
    private Integer authorId;

    @Size(max = 50)
    @Column(name = "BOOK_NAME", nullable = false, unique = true)
    private String bookName;

    @ManyToMany
    @JoinTable(name = "AUTHORS_BOOKS")
    @JoinColumn(name = "AUTHOR_ID")
    private List<Author> authorList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "SERIES_ID")
    private Series series;

    public Book() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) && Objects.equals(authorId, book.authorId) && Objects.equals(bookName, book.bookName) && series.equals(book.series);
    }

    @Override
    public int hashCode() { return Objects.hash(bookId, authorId, bookName, series); }
}

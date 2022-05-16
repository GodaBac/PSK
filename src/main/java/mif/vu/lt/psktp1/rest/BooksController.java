package mif.vu.lt.psktp1.rest;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.entities.Book;
import mif.vu.lt.psktp1.persistence.BookDAO;
import mif.vu.lt.psktp1.rest.contracts.BookDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@ApplicationScoped
@Path("/books")
public class BooksController {

    @Inject
    @Getter @Setter
    private BookDAO bookDAO;

    @Getter @Setter
    private Author author;

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/books/1
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Book book = bookDAO.findOne(id);

        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        BookDto bookDto = new BookDto();
        bookDto.setBookName(book.getTitle());
        bookDto.setAuthorFirstName(book.getAuthor().getFirstName());
        bookDto.setAuthorLastName(book.getAuthor().getLastName());

        return Response.ok(bookDto).build();
    }

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/books/1
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Long bookId, BookDto bookDto) {
        try {
            Book existingBook = bookDAO.findOne(bookId);
            if (existingBook == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingBook.setTitle(bookDto.getBookName());
            existingBook.getAuthor().setFirstName(bookDto.getAuthorFirstName());
            existingBook.getAuthor().setLastName(bookDto.getAuthorLastName());
            bookDAO.update(existingBook);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

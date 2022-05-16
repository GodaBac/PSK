package mif.vu.lt.psktp1.rest;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Author;
import mif.vu.lt.psktp1.persistence.AuthorDAO;
import mif.vu.lt.psktp1.rest.contracts.AuthorDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@ApplicationScoped
@Path("/authors")
public class AuthorsController {

    @Inject
    @Getter @Setter
    private AuthorDAO authorDAO;

    @Inject
    private EntityManager entityManager;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {

        Author author = authorDAO.findOne(id);

        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());

        return Response.ok(authorDto).build();
    }

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/authors
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AuthorDto authorDto) {

        try {
            if (authorDto == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            Author authorToCreate = new Author();
            authorToCreate.setFirstName(authorDto.getFirstName());
            authorToCreate.setLastName(authorDto.getLastName());
            authorDAO.persist(authorToCreate);

            URI location = UriBuilder.fromResource(AuthorsController.class)
                    .path("/{id}")
                    .resolveTemplate("id", authorToCreate.getId())
                    .build();

            return Response.created(location).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

package com.pluralsight;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService bookService;

    @Inject
    UriInfo uriInfo;

    @GET
    @Operation(summary = "Returns all the books from the database")
    @APIResponse(responseCode = "200",
            description = "A list of books",
            content =@Content(mediaType = APPLICATION_JSON,schema=@Schema(type = SchemaType.ARRAY, implementation = Book.class)))
    @APIResponse(responseCode = "204", description = "No books")
    public Response getBooks(){
        List<Book> books = bookService.findAll();

        if (books.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(books).build();
    }

    @GET
    @Path("{id}")
    @APIResponse(responseCode = "200",
            description = "A book",
            content =@Content(mediaType = APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "No book for this id")
    @Operation(summary = "Returns all a book for a given id if it exists")
    public Response getBook(@PathParam("id") @Min(1) Long id){
        Book book = bookService.find(id);

        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    @Operation
    public Response countBooks(){
        Long nbOfBooks = bookService.countAll();
        if (nbOfBooks == 0) {
            return Response.noContent().build();
        }
        return Response.ok(nbOfBooks).build();
    }

    @POST
    @Operation(summary = "Creates a valid book")
    public Response createBook(Book book) throws URISyntaxException {
        book = bookService.create(book);
        URI createdURI =uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();

        return Response.created(createdURI).entity(book).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Deletes a book for a given id if it exists")
    public Response deleteBook(@PathParam("id") @Min(1 ) Long id){
        bookService.delete(id);
        return  Response.noContent().build();
    }
}

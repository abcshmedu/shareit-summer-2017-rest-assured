package edu.hm.shareit.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;
import edu.hm.shareit.service.MediaService;
import edu.hm.shareit.service.MediaServiceResult;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @author Andrea Limmer, limmer@hm.edu
 * @since 18/04/2017
 */
@Path("/media")
public class MediaResource {

    /**
     * MediaService to work with.
     */
    private final MediaService mediaService;

    /**
     * constructor.
     * @param mediaService MediaService.
     */
    @Inject
    public MediaResource(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * Add a book.
     *
     * @param book Book to add.
     * @param jwt  The User's jwt token.
     * @return Response.
     */
    @POST
    @Path("/books/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book, @PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        MediaServiceResult result = mediaService.addBook(book);
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }

    /**
     * Get a book.
     *
     * @param isbn ISBN of the book.
     * @param jwt  The User's jwt token.
     * @return Response with the book if available.
     */
    @GET
    @Path("/books/{isbn}/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn, @PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Medium book = mediaService.getBook(isbn);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = book != null ? mapper.writeValueAsString(book) : MediaServiceResult.ISBN_NOT_FOUND.getStatus();
        } catch (JsonProcessingException e) {
            json = MediaServiceResult.ERROR.getStatus();
        }
        return Response.status(Response.Status.OK).entity(json).build();
    }

    /**
     * Get all books.
     *
     * @param jwt The User's jwt token.
     * @return Response with all books.
     */
    @GET
    @Path("/books/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(@PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return getMedia(mediaService.getBooks());
    }

    /**
     * Update a book.
     *
     * @param isbn ISBN of the book.
     * @param book Book to update.
     * @param jwt  The User's jwt token.
     * @return Response.
     */
    @PUT
    @Path("/books/{isbn}/{jwt}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book book, @PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        MediaServiceResult result;
        if (isbn.equals(book.getIsbn())) {
            result = mediaService.updateBook(book);
        } else {
            result = MediaServiceResult.ISBN_MISMATCH;
        }
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }

    /**
     * Add a disc.
     *
     * @param disc Disc.
     * @param jwt  The User's jwt token.
     * @return Response.
     */
    @POST
    @Path("/discs/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDisc(Disc disc, @PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        MediaServiceResult result = mediaService.addDisc(disc);
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }

    /**
     * Get a disc.
     *
     * @param barcode Barcode to identify the disc.
     * @param jwt     The User's jwt token.
     * @return Response with the disc if available.
     */
    @GET
    @Path("/discs/{barcode}/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode, @PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Medium disc = mediaService.getDisc(barcode);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = disc != null ? mapper.writeValueAsString(disc) : MediaServiceResult.BARCODE_NOT_FOUND.getStatus();
        } catch (JsonProcessingException e) {
            json = MediaServiceResult.ERROR.getStatus();
        }
        return Response.status(Response.Status.OK).entity(json).build();
    }

    /**
     * Get all discs.
     *
     * @param jwt The User's jwt token.
     * @return Response with all discs.
     */
    @GET
    @Path("/discs/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return getMedia(mediaService.getDiscs());
    }

    /**
     * Update a disc.
     *
     * @param barcode Barcode of the disc.
     * @param disc    Disc to be updated.
     * @param jwt     The User's jwt token.
     * @return Response.
     */
    @PUT
    @Path("/discs/{barcode}/{jwt}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(@PathParam("barcode") String barcode, Disc disc, @PathParam("jwt") String jwt) {
        if (!AuthValidator.checkValidity(jwt)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        MediaServiceResult result;
        if (barcode.equals(disc.getBarcode())) {
            result = mediaService.updateDisc(disc);
        } else {
            result = MediaServiceResult.BARCODE_MISMATCH;
        }
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }

    /**
     * Helper class for getBooks()/getDiscs().
     *
     * @param media Array of the Media.
     * @return Response.
     */
    private Response getMedia(Medium[] media) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(media);
        } catch (JsonProcessingException e) {
            json = MediaServiceResult.ERROR.getStatus();
        }
        return Response.status(Response.Status.OK).entity(json).build();
    }
}

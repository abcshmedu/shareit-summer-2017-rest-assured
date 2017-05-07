package edu.hm.shareit.api;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;
import edu.hm.shareit.service.MediaService;
import edu.hm.shareit.service.MediaServiceImpl;
import edu.hm.shareit.service.MediaServiceResult;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @since 18/04/2017
 */
@Path("/media")
public class MediaResource {

    /**
     * MediaService to work with.
     */
    private final MediaService mediaService = new MediaServiceImpl();

    /**
     * Add a book.
     * @param book Book to add.
     * @return Response.
     */
    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        MediaServiceResult result = mediaService.addBook(book);
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }

    /**
     * Get a book.
     * @param isbn ISBN of the book.
     * @return Response with the book if available.
     */
    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
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
     * @return Response with all books.
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        return getMedia(mediaService.getBooks());
    }

    /**
     * Update a book.
     * @param isbn ISBN of the book.
     * @param book Book to update.
     * @return Response.
     */
    @PUT
    @Path("/books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book book) {
        MediaServiceResult result;
        if (isbn.equals(book.getIsbn())) {
            result = mediaService.updateBook(book);
        }
        else {
            result = MediaServiceResult.ISBN_MISMATCH;
        }
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }

    /**
     * Add a disc.
     * @param disc Disc.
     * @return Response.
     */
    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDisc(Disc disc) {
        MediaServiceResult result = mediaService.addDisc(disc);
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }

    /**
     * Get a disc.
     * @param barcode Barcode to identify the disc.
     * @return Response with the disc if available.
     */
    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) {
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
     * @return Response with all discs.
     */
    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        return getMedia(mediaService.getDiscs());
    }

    /**
     * Update a disc.
     * @param barcode Barcode of the disc.
     * @param disc Disc to be updated.
     * @return Response.
     */
    @PUT
    @Path("/discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(@PathParam("barcode") String barcode, Disc disc) {
        MediaServiceResult result;
        if (barcode.equals(disc.getBarcode())) {
            result = mediaService.updateDisc(disc);
        }
        else {
            result = MediaServiceResult.BARCODE_MISMATCH;
        }
        return Response.status(result.getCode()).entity(result.getStatus()).build();
    }
    
    /**
     * Helper class for getBooks()/getDiscs().
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

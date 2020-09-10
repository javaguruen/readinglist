package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import no.hamre.booklist.app.log
import no.hamre.booklist.app.rest.api.*
import no.hamre.booklist.app.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import javax.validation.Valid
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.MediaType.TEXT_PLAIN
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.CREATED
import javax.ws.rs.core.Response.ok
import no.hamre.booklist.app.model.Author as ModelAuthor
import no.hamre.booklist.app.model.Book as ModelBook
import no.hamre.booklist.app.model.Medium as ModelMedium
import no.hamre.booklist.app.model.Tag as ModelTag

@Path("/v1/books")
@Produces(APPLICATION_JSON)
@Controller
class BookController @Autowired constructor(val bookService: BookService) {

  @POST
  @Path("/raw")
  @Produces(TEXT_PLAIN)
  fun addRawTextAsBody(body: String): Response {
    log.info("Received body: $body")
    val newId = bookService.addRawBookInfo(body)
    return ok("New id is $newId").build()
  }

  @POST
  @Operation(summary = "Add a book", description = "Add a new book.")
  // Please Note: Repeatable Annotations with non-SOURCE retentions are not yet supported with Kotlin so we are using `@ApiResponses`
  // instead of `@ApiResponse`, see https://youtrack.jetbrains.com/issue/KT-12794
  @ApiResponses(
      ApiResponse(content = [Content(mediaType = "application/json", schema = Schema(type = "string"))]),
      ApiResponse(responseCode = "400", description = "Invalid book"),
      ApiResponse(responseCode = "404", description = "Person not found")
  )
  @Tag(name = "books")
  fun addBook(@Valid book: Book): Response {
    println("Book $book")
    //val book = ObjectMapperFactory.create().readValue(bookS, Book::class.java)
    val modelBook = bookService.addBook(ApiToDomainMapper.mapBook(book))
    return Response
        .status(CREATED)
        .entity(DomainToApiMapper.mapBook(modelBook))
        .build()
  }

  @GET
  fun listBooks(): Response {
    val modelBooks = bookService.listBooks()
    return ok(DomainToApiMapper.mapBooks(modelBooks))
        .build()
  }
}
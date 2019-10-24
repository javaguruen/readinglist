package no.hamre.booklist.app.rest.controller

import no.hamre.booklist.app.rest.api.Author
import no.hamre.booklist.app.rest.api.Book
import no.hamre.booklist.app.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.CREATED
import javax.ws.rs.core.Response.ok
import no.hamre.booklist.app.model.Author as modelAuthor
import no.hamre.booklist.app.model.Book as modelBook

@Path("/api/v1/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Controller
class BookController @Autowired constructor(val bookService: BookService) {

  @POST
  fun addBook(@Valid book: Book): Response {
      val modelBook = bookService.addBook(no.hamre.booklist.app.model.Book(originalTitle = book.originalTitle))
    return Response
        .status(CREATED)
        .entity( Model2ApiMapper.mapBook(modelBook) ).build()
  }

  @GET
  fun listBooks(): Response {
    val modelBooks = bookService.listBooks()
    return ok( Model2ApiMapper.mapBooks(modelBooks))
        .build()
  }
}

object Model2ApiMapper {
  fun mapBooks(books: List<modelBook>): List<Book> {
      return books.map { mapBook(it) }
  }

  fun mapBook(book: modelBook): Book {
    return Book(
        id = book.id,
        authors = book.authors.map { mapAuthor(it) },
        originalTitle = book.originalTitle
    )
  }

  fun mapAuthor(author: modelAuthor): Author {
    return Author(id = author.id,
        firstName = author.firstName,
        lastName = author.lastName)
  }
}
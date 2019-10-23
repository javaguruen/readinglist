package no.hamre.booklist.app.rest.controller

import no.hamre.booklist.app.model.Author
import no.hamre.booklist.app.rest.api.Book
import no.hamre.booklist.app.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok

@Path("/api/v1/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
open class BookController @Autowired constructor(val bookService: BookService) {

  @POST
  fun addBook(@Valid book: Book): Response {
    return ok(bookService.addBook(no.hamre.booklist.app.model.Book(originalTitle = book.originalTitle))).build()
  }

  @GET
  fun listBooks(): Response {
    val modelBooks = bookService.listBooks()
    return ok( Model2ApiMapper.mapBooks(modelBooks))
        .build()
  }
}

object Model2ApiMapper {
  fun mapBooks(books: List<no.hamre.booklist.app.model.Book>): List<Book> {
      return books.map { mapBook(it) }
  }

  fun mapBook(book: no.hamre.booklist.app.model.Book): Book {
    return Book(
        authors = book.authors.map { mapAuthor(it) },
        originalTitle = book.originalTitle
    )
  }

  fun mapAuthor(author: Author): no.hamre.booklist.app.rest.api.Author {
    return no.hamre.booklist.app.rest.api.Author(author.firstName, author.lastName)
  }
}
package no.hamre.booklist.app.rest.controller

import no.hamre.booklist.app.ObjectMapperFactory
import no.hamre.booklist.app.rest.api.*
import no.hamre.booklist.app.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.CREATED
import javax.ws.rs.core.Response.ok
import no.hamre.booklist.app.model.Author as ModelAuthor
import no.hamre.booklist.app.model.Book as ModelBook
import no.hamre.booklist.app.model.Medium as ModelMedium
import no.hamre.booklist.app.model.Tag as ModelTag

@Path("/api/v1/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Controller
class BookController @Autowired constructor(val bookService: BookService) {

  @POST
  fun addBook(bookS: String): Response {
//  fun addBook(@Valid book: Book): Response {
    println("Book $bookS")
    val book = ObjectMapperFactory.create().readValue(bookS, Book::class.java)
    val modelBook = bookService.addBook(Api2ModelMapper.mapBook(book))
    return Response
        .status(CREATED)
        .entity(Model2ApiMapper.mapBook(modelBook))
        .build()
  }

  @GET
  fun listBooks(): Response {
    val modelBooks = bookService.listBooks()
    return ok(Model2ApiMapper.mapBooks(modelBooks))
        .build()
  }
}

object Api2ModelMapper {
  fun mapBook(book: Book): ModelBook {
    return ModelBook(
        id = book.id,
        originalTitle = book.originalTitle,
        norwegianTitle = book.norwegianTitle,
        authors = mapAuthors(book.authors),
        readingOrder = book.readingOrder,
        medium = mapMedium(book.medium),
        language = book.language,
        tags = mapTags(book.tags)
        )
  }

  private fun mapTags(tags: Set<Tag>): Set<ModelTag> {
    return tags.map { t -> ModelTag(t.name.name, setOf()) }.toSet()
  }

  private fun mapMedium(medium: Medium?): ModelMedium? {
    return when (medium) {
      null -> null
      Medium.PAPIR -> ModelMedium.PAPIR
      Medium.LYDBOK -> ModelMedium.LYDBOK
      Medium.EBOK -> ModelMedium.EBOK
    }
  }

  fun mapAuthors(authors: List<Author>): Set<ModelAuthor> {
    return authors.map { mapAuthor(it) }.toSet()
  }

  fun mapAuthor(author: Author): ModelAuthor {
    return ModelAuthor(id = author.id, firstName = author.firstName, lastName = author.lastName)
  }
}


object Model2ApiMapper {
  fun mapBooks(books: List<ModelBook>): List<Book> {
    return books.map { mapBook(it) }
  }

  fun mapBook(book: ModelBook): Book {
    return Book(
        id = book.id,
        authors = book.authors.map { mapAuthor(it) },
        originalTitle = book.originalTitle,
        norwegianTitle = book.norwegianTitle,
        language = book.language,
        link = null,
        medium = mapMedium(book.medium),
        tags = mapTags(book.tags)
    )
  }

  private fun mapMedium(medium: ModelMedium?): Medium? {
    return when (medium) {
      null -> null
      ModelMedium.PAPIR -> Medium.PAPIR
      ModelMedium.LYDBOK -> Medium.LYDBOK
      ModelMedium.EBOK -> Medium.EBOK
    }

  }

  private fun mapTags(tags: Set<ModelTag>): Set<Tag> {
    return tags.map { t -> Tag( TagName( name = t.name) ) }.toSet()
  }

  fun mapAuthor(author: ModelAuthor): Author {
    return Author(id = author.id,
        firstName = author.firstName,
        lastName = author.lastName)
  }
}
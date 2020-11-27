package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.tags.Tags
import no.hamre.booklist.app.rest.api.Book
import no.hamre.booklist.app.rest.api.ErrorResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid
import javax.websocket.server.PathParam

@Tags(Tag(name = "Books", description = """Services for the lifecylce of a book.
  
   * Adding a book with one or more authors,
   * Adding and removing tags `this is markdown`,
   * Updating a book,
   * Deleting a book.
   
Also possible to just store a plain text String that will be manually convertetd to a book later,
   as a note. 
"""))
@SecuritySchemes(SecurityScheme(type = SecuritySchemeType.OAUTH2, name = "bearerToken"))
//@ApiResponses
interface BookController {

  @RequestMapping(path = ["/raw"], method = [POST], consumes = [TEXT_PLAIN_VALUE], produces = [TEXT_PLAIN_VALUE])
  @Operation(
      security = [
        SecurityRequirement(name = "bearerToken", scopes = ["book.write"])
      ]
  )
  @ApiResponses(value = [
    ApiResponse(responseCode = "201", description = "Successfully persisted.",
        content = [Content(schema = Schema(implementation = Long::class))])
  ])
  fun addRawTextAsBody(@RequestBody body: String?): ResponseEntity<Long>

  @RequestMapping(method = [POST])
  @ApiResponses(value = [
    ApiResponse(responseCode = "201", description = "Add a complete book.", content = [Content(schema = Schema(implementation = Book::class))]),
    ApiResponse(responseCode = "403", description = "Bad input data.", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
  ]
  )
  @Operation(
      security = [
        SecurityRequirement(name = "bearerToken", scopes = ["book.write"])
      ]
  )
  fun addBook(@RequestBody @Valid book: Book): ResponseEntity<Book>

  @RequestMapping(method = [PUT], path = ["/{id}"])
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Book updated successfully.", content = [Content(schema = Schema(implementation = Book::class))]),
    ApiResponse(responseCode = "403", description = "Bad input data.", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
  ]
  )
  @Operation(
      security = [
        SecurityRequirement(name = "bearerToken", scopes = ["book.write"])
      ]
  )
  fun updateBook(@PathVariable("id", name="id", required = true) id: Long, @RequestBody @Valid book: Book): ResponseEntity<Book>

  @RequestMapping(method = [GET], path = ["/"], produces = [APPLICATION_JSON_VALUE])
  fun listBooks(): ResponseEntity<Array<Book>>

}
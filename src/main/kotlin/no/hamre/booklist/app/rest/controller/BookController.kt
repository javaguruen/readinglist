package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import no.hamre.booklist.app.rest.api.Book
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.TEXT_PLAIN_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import javax.validation.Valid


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
  fun addBook(@RequestBody @Valid book: Book): ResponseEntity<Book>

  @RequestMapping(method = [GET], path = ["/"], produces = [APPLICATION_JSON_VALUE])
  fun listBooks(): ResponseEntity<Array<Book>>

}
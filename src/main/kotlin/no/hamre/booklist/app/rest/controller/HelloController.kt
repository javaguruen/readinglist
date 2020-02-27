package no.hamre.booklist.app.rest.controller

import io.swagger.annotations.Api
import no.hamre.booklist.app.log
import no.hamre.booklist.app.rest.api.*
import no.hamre.booklist.app.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.MediaType.TEXT_PLAIN
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.CREATED
import javax.ws.rs.core.Response.ok
import no.hamre.booklist.app.model.Author as ModelAuthor
import no.hamre.booklist.app.model.Book as ModelBook
import no.hamre.booklist.app.model.Medium as ModelMedium
import no.hamre.booklist.app.model.Tag as ModelTag

@Path("/v1/hello")
@Controller
@Api
class HelloController (@Value("\${hello.string:Hello, default}") val greeting: String) {
//class HelloController (@Value("\${hello.string:Hello, default}") val greeting: String) {

  @GET
  fun greeting(): Response {
    return ok(greeting, MediaType.TEXT_PLAIN_TYPE)
        .build()
  }
}
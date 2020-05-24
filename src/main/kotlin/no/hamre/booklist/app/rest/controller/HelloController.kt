package no.hamre.booklist.app.rest.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok

@Path("/v1/hello")
@Controller
class HelloController (@Value("\${hello.string:Hello, default}") val greeting: String) {
//class HelloController (@Value("\${hello.string:Hello, default}") val greeting: String) {

  @GET
  fun greeting(): Response {
    return ok(greeting, MediaType.TEXT_PLAIN_TYPE)
        .build()
  }
}
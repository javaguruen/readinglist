package no.hamre.booklist.app

import io.swagger.jaxrs.config.BeanConfig
import io.swagger.jaxrs.listing.ApiListingResource
import io.swagger.jaxrs.listing.SwaggerSerializers
import no.hamre.booklist.app.rest.controller.BookController
import no.hamre.booklist.app.rest.controller.HelloController
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.server.wadl.internal.WadlResource
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.ws.rs.ApplicationPath


@Component
@ApplicationPath("/api")
class JerseyConfig(
    @Value("\${spring.jersey.application-path:/api}")
    private val apiPath: String? = null
) : ResourceConfig() {

    @PostConstruct
    fun init() {
      registerEndpoints()
      configureSwagger()
    }

    private fun registerEndpoints() {
      this.register(BookController::class.java)
      this.register(HelloController::class.java)
      this.register(WadlResource::class.java)
    }

    private fun configureSwagger() {
      this.register(ApiListingResource::class.java)
      this.register(SwaggerSerializers::class.java)
      val config = BeanConfig()
      config.title = "POC - Restful API by Spring Boot, Jersey, Swagger"
      config.version = "v1"
      config.contact = "Bright Zheng"
      config.schemes = arrayOf("http", "https")
      config.basePath = apiPath
      config.resourcePackage = "no.hamre.booklist.app.rest.controller"
      config.prettyPrint = true
      config.scan = true
    }
 }
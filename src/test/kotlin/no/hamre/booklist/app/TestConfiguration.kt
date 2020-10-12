package no.hamre.booklist.app

import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import java.io.IOException


@Configuration
class TestConfiguration {

/*
  @Bean
  @Primary
  fun testRestTemplate(applicationContext: ApplicationContext): TestRestTemplate {
    val restTemplate: RestTemplateBuilder = RestTemplateBuilder()
        .errorHandler(object : ResponseErrorHandler {
          @Throws(IOException::class)
          override fun hasError(response: ClientHttpResponse): Boolean {
            return false
          }

          @Throws(IOException::class)
          override fun handleError(response: ClientHttpResponse) {
          }
        })

    val testRestTemplate = TestRestTemplate(restTemplate, "user", "pwd",
        TestRestTemplate.HttpClientOption.ENABLE_REDIRECTS,
        TestRestTemplate.HttpClientOption.ENABLE_COOKIES)

    // let this testRestTemplate resolve paths relative to http://localhost:${local.server.port}
    val handler = LocalHostUriTemplateHandler(applicationContext.getEnvironment(), "http")
    testRestTemplate.setUriTemplateHandler(handler)
    println("Created bean testRestTemplate")
    return testRestTemplate
  }
*/
}
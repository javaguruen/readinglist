package no.hamre.booklist.app

import com.fasterxml.jackson.databind.ObjectMapper
import no.hamre.booklist.app.rest.controller.BookController
import org.glassfish.jersey.server.ResourceConfig
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@SpringBootApplication
open class AppApplication : SpringBootServletInitializer() {
}

fun main(args: Array<String>) {
    runApplication<AppApplication>(*args)
}

val log = LoggerFactory.getLogger(AppApplication::class.java)

@Configuration
open class JerseyConfig() : ResourceConfig() {
    init {
        log.info("Register Controllers")
        register(BookController::class.java)
    }
}

@Configuration
open class ObjectMapperFactoryBean {

    @Bean
    @Primary
    open fun springObjectMapperConfiguration(): ObjectMapper {
        log.info("Configure Spring's ObjectMapper")
        return ObjectMapperFactory.create()
    }
}


@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
    }

    @Bean
    open fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}

/*

@Configuration
@EnableWebSecurity
open class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic()
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
				.antMatchers("/api/v1/books").permortMapper()
        super.configure(http)
    }
}
*/

/*
@Configuration
class WebSecurityConfiguration() : WebSecurityConfigurerAdapter() {

	override
	open fun configure(http: HttpSecurity) {
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
	}
}
*/

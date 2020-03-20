package no.hamre.booklist.app

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@SpringBootApplication
@EnableWebSecurity
class AppApplication : SpringBootServletInitializer() {
}
    fun main(args: Array<String>) {
        runApplication<AppApplication>(*args)
    }

    val log = LoggerFactory.getLogger(AppApplication::class.java)

//@Configuration
/*
open class JerseyConfig() : ResourceConfig() {
    init {
        log.info("Register Controllers")
        register(BookController::class.java)
    }
}
*/

@Configuration
class ObjectMapperFactoryBean {

    @Bean
    @Primary
    fun springObjectMapperConfiguration(): ObjectMapper {
        log.info("Configure Spring's ObjectMapper")
        return ObjectMapperFactory.create()
    }
}
/*
@EnableWebSecurity
@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        //http.cors().and().csrf().disable()
        http
            .authorizeRequests()
            .antMatchers("/").access("hasRole('READER')")
            .antMatchers("/").permitAll()

            .and()

            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error=true")
    }

    data class MyUserDetails(val uname: String, val pwd: String) : UserDetails {
        override fun isEnabled(): Boolean = true
        override fun isAccountNonExpired() = true
        override fun isAccountNonLocked() = true
        override fun isCredentialsNonExpired() = true
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
            TODO("Not yet implemented")
        }

        override fun getUsername(): String {
            return uname
        }

        override fun getPassword(): String {
            return pwd
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(UserDetailsService() { un ->
                MyUserDetails(un, "pwd2")
            })
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/2x*", configuration)
        return source
    }

}
*/

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

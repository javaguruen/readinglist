package no.hamre.booklist.app

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.servers.Server
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@SpringBootApplication
@OpenAPIDefinition(
    info = Info(title = "Open API 3.0 example app.", version = "1.0.0"),
    servers = [
      Server(url = "http://localhost:8080/", description = "base url", )
    ],
    security = [
      SecurityRequirement(name = "basicAuth"),
      SecurityRequirement(name = "bearerToken")
    ]
)

class AppApplication() {
}

fun main(args: Array<String>) {
  runApplication<AppApplication>(*args)
}

@Configuration
class ObjectMapperFactoryBean {

  @Bean
  @Primary
  fun springObjectMapperConfiguration(): ObjectMapper {
    print("Configure Spring's ObjectMapper")
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
class WebSecurityConfiguration() : WebSecurityConfigurerAdapter() {

	override
	open fun configure(http: HttpSecurity) {
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
	}
}
*/

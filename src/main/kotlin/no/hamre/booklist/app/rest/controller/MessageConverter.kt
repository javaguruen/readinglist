package no.hamre.booklist.app.rest.controller

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.util.StreamUtils
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.IOException
import java.nio.charset.StandardCharsets


@Configuration
class MessageConverter : WebMvcConfigurer {

  override
  fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
    println("configureMessageConverters.size(): " + converters.size)
    converters.add(Long2String(name = "first", supportedClass = java.lang.Long::class.java))
  }
}
@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
class Long2String(val name: String, val supportedClass: Class<java.lang.Long>) : AbstractHttpMessageConverter<java.lang.Long>(TEXT_PLAIN) {

  override fun readInternal(p0: Class<out java.lang.Long>, p1: HttpInputMessage): java.lang.Long {
    throw UnsupportedOperationException("This converter can only write objects, not read")
  }


  override fun supports(clazz: Class<*>): Boolean {
    return supportedClass.isAssignableFrom(clazz)
  }

  @Throws(HttpMessageNotWritableException::class, IOException::class)
  override fun writeInternal(o: java.lang.Long, outputMessage: HttpOutputMessage) {
    StreamUtils.copy(o.toString(),
        StandardCharsets.UTF_8, outputMessage.body)
  }
}
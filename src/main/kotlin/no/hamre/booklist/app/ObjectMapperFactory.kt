package no.hamre.booklist.app

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

object ObjectMapperFactory {
    fun create() = configure(ObjectMapper())

    fun configure(objectMapper: ObjectMapper): ObjectMapper {
        objectMapper.registerModule(KotlinModule())
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        //objectMapper.disable(WRITE)
        return objectMapper
    }
}
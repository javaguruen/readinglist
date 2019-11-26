package no.hamre.booklist.app.rest.api

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class Book(
    val id: Long? = null,
    val authors: List<Author>,
    @ApiModelProperty(required = true)
    val originalTitle: String,
    val norwegianTitle: String? = null,
    val language: String? = null,
    val tags: Set<Tag> = emptySet(),
    val readingOrder: Int = 0,
    val medium: Medium? = null,
    val link: String? = null
)

data class Author(val id: Long? = null, val firstName: String, val lastName: String)
data class Tag(val name: TagName, val tagged: LocalDateTime = LocalDateTime.now())
data class TagName(val name: String, val deleted: LocalDateTime? = null)
enum class Medium { PAPIR, EBOK, LYDBOK }
data class User(
    val id: Long?,
    val email: String
)
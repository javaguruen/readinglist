package no.hamre.booklist.app.rest.api

import java.time.LocalDateTime

data class Book(
//        val author: Author,
        val originalTitle: String,
        val norwegianTitle: String? = null
/*
        val language: String? = null,
        val tags: List<Tag> = emptyList(),
        val readingOrder: Int = 0,
        val medium: Medium? = null
*/
)

data class Author(val firstName: String, val lastName: String)
data class Tag(val name: TagName, val tagged: LocalDateTime = LocalDateTime.now() )
data class TagName(val name: String, val deleted: LocalDateTime? = null)
enum class Medium{PAPIR, EBOK, LYDBOK}
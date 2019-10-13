package no.hamre.booklist.app.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val originalTitle: String,
        val norwegianTitle: String? = null
/*
        //val authors: List<Author>,
        val language: String? = null,
        //val tags: List<Tag> = emptyList(),
        val readingOrder: Int = 0,
        val medium: Medium? = null
*/
)

data class Author(val firstName: String, val lastName: String)
data class Tag(val name: TagName, val tagged: LocalDateTime = LocalDateTime.now() )
data class TagName(val name: String, val deleted: LocalDateTime? = null)
enum class Medium{PAPIR, EBOK, LYDBOK}



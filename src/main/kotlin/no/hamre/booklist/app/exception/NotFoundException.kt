package no.hamre.booklist.app.exception

data class NotFoundException(val msg: String): RuntimeException(msg)
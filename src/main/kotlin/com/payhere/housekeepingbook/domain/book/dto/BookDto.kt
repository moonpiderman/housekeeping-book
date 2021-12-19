package com.payhere.housekeepingbook.domain.book.dto

import com.payhere.housekeepingbook.domain.book.model.Book
import com.payhere.housekeepingbook.domain.bookLog.dto.BookLogDto
import com.payhere.housekeepingbook.domain.bookLog.repository.BookLogRepository
import javax.validation.constraints.NotBlank

class BookDto {
    data class BookResponse(
        val id: Long,
        val title: String,
        val memo: String,
        val balance: Float
    ) {
        constructor(book: Book) : this(
            id = book.id,
            title = book.title,
            memo = book.memo,
            balance = book.balance,
        )
    }

    data class BookTotalResponse(
        val id: Long,
        val title: String,
        val memo: String,
        val balance: Float,
        val logs: List<BookLogDto.LogResponse>,
    ) {
        constructor(book: Book, bookLogRepository: BookLogRepository) : this(
            id = book.id,
            title = book.title,
            memo = book.memo,
            balance = book.balance,
            logs = book.logs.map {
                it ->
                BookLogDto.LogResponse(bookLogRepository.findByBook(it.id))
            }
        )
    }

    data class CreateRequest(
        @field:NotBlank
        val title: String,

        @field:NotBlank
        val memo: String = "",
    )
}

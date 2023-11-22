package br.com.leomaia.controller

import br.com.leomaia.data.vo.v1.BookVO
import br.com.leomaia.services.BookService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/book/v1")
@Tag(name = "Books", description = "Endpoints to manage books")
class BookController {
    @Autowired
    private lateinit var bookService: BookService

    @GetMapping
    fun getAllBooks(): List<BookVO>{
        return bookService.findAll()
    }

    @GetMapping("{id}")
    fun getBookById(@PathVariable(value = "id") id: Long): BookVO{
        return bookService.findById(id)
    }

    @PostMapping
    fun createBook(@RequestBody book: BookVO): BookVO{
        return bookService.create(book)
    }

    @PutMapping
    fun updateBook(@RequestBody book: BookVO): BookVO{
        return bookService.update(book)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable("id") id: Long): ResponseEntity<*>{
        bookService.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}
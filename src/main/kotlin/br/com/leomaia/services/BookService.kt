package br.com.leomaia.services

import br.com.leomaia.data.vo.v1.BookVO
import br.com.leomaia.exceptions.ResourceNotFoundException
import br.com.leomaia.mapper.DozerMapper
import br.com.leomaia.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import java.awt.print.Book
import java.util.logging.Logger

class BookService {
    @Autowired
    private lateinit var repository: BookRepository

    private val logger = Logger.getLogger(BookService::class.java.name)

    fun findAll(): List<BookVO> {
        logger.info("Finding all books")

        val books = repository.findAll()

        return DozerMapper.parseListObjects(books, BookVO::class.java)
    }

    fun findById(id: Long): BookVO{
        logger.info("Finding a book")

        val book = repository.findById(id)

        return DozerMapper.parseObject(book, BookVO::class.java)
    }

    fun create(bookVO: BookVO): BookVO{
        logger.info("Adding a book")

        val bookEntity = DozerMapper.parseObject(bookVO, br.com.leomaia.model.Book::class.java)

        repository.save(bookEntity)

        return bookVO
    }

    fun update(bookVO: BookVO): BookVO{
        logger.info("Updating the book with id: ${bookVO.id}")

        val bookEntity = repository.findById(bookVO.id)
            .orElseThrow{ResourceNotFoundException("Book not found")}

        bookEntity.id = bookVO.id
        bookEntity.author = bookVO.author
        bookEntity.price = bookVO.price
        bookEntity.title = bookVO.title
        bookEntity.launchDate = bookVO.launchDate

        repository.save(bookEntity)

        return bookVO
    }

    fun delete(id:Long){
        logger.info("Deleting book with id: $id")

        val bookEntity = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("Book Not Found")}

        repository.delete(bookEntity)
    }
}
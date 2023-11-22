package br.com.leomaia.mapper.custom

import br.com.leomaia.data.vo.v1.BookVO
import br.com.leomaia.model.Book
import org.springframework.stereotype.Service


@Service
class BookMapper {
    fun mapEntityToVO(bookEntity: Book): BookVO{
        var bookVO = BookVO()

        bookVO.id = bookEntity.id
        bookVO.author = bookEntity.author
        bookVO.launchDate = bookEntity.launchDate
        bookVO.price = bookEntity.price
        bookVO.title = bookEntity.title

        return bookVO
    }

    fun mapVotoEntity(bookVo: BookVO): Book{
        val bookEntity = Book()

        bookEntity.id = bookVo.id
        bookEntity.author = bookVo.author
        bookEntity.launchDate = bookVo.launchDate
        bookEntity.price = bookVo.price
        bookEntity.title = bookVo.title

        return bookEntity
    }
}
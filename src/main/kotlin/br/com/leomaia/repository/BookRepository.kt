package br.com.leomaia.repository

import br.com.leomaia.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Long?> {
}
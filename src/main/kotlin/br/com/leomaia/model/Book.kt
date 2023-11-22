package br.com.leomaia.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "books")
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(length = 150, nullable = false)
    var author: String = ""

    @Column(name = "launch_date",nullable = false)
    var launchDate: Date = Date()

    @Column(nullable = false)
    var price: Double = 0.0

    @Column(nullable = false, length = 150)
    var title: String = ""
}
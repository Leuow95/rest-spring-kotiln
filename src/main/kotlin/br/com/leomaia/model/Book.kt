package br.com.leomaia.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "books")
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(length = 150, nullable = false)
    val autor: String = ""

    @Column(name = "launch_date",nullable = false)
    val launchDate: Date = Date()

    @Column(nullable = false)
    val price: Double = 0.0

    @Column(nullable = false, length = 150)
    val title: String = ""
}
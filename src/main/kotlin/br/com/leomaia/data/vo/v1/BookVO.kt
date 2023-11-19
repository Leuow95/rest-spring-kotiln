package br.com.leomaia.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.hateoas.RepresentationModel
import java.util.*

@JsonPropertyOrder("id", "title", "author", "price", "launch_date" )
data class BookVO (

    val id: Long = 0,

    val author: String = "",

    @field:JsonProperty("launch_date")
    val launchDate: Date = Date(),

    val price: Double = 0.0,

    val title: String = ""

) : RepresentationModel<BookVO>()
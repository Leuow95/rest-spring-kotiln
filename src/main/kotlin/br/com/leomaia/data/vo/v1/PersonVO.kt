package br.com.leomaia.data.vo.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.hateoas.RepresentationModel


data class PersonVO(

    var id: Long = 0,

    var firstName: String = "",

    var lastName: String = "",

    var address: String = "",

    var gender: String = ""
): RepresentationModel<PersonVO>()

//@JsonPropertyOrder("id", "address", "first_name", "last_name", "gender")
//data class PersonVO(
//
//    var id: Long = 0,
//
//    @field:JsonProperty("first_name")
//    var firstName: String = "",
//
//    @field:JsonProperty("last_name")
//    var lastName: String = "",
//
//    var address: String = "",
//
//    @field:JsonIgnore
//    var gender: String = ""
//)

package br.com.leomaia.controller


import br.com.leomaia.data.vo.v1.PersonVO
import br.com.leomaia.data.vo.v2.PersonVO as PersonVOV2
import br.com.leomaia.services.PersonService
import br.com.leomaia.util.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints to manage people")
class PersonController {
    @Autowired
    private lateinit var personService: PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Finds all people", description = "Return all people",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = PersonVO::class)))]
            ),
            ApiResponse(
                description = "No content", responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Server Error", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ])
    fun getAllPerson(): List<PersonVO>{
        return personService.findAll()
    }

    @GetMapping(value= ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Finds a person", description = "Return a person",
        tags = ["People"],
        responses = [
            ApiResponse(
            description = "Success",
            responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = PersonVO::class))]
            ),
            ApiResponse(
                description = "No content", responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Server Error", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
    ])
    fun getPersonById(@PathVariable(value = "id") id: Long
    ): PersonVO {
    return personService.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Creates a person", description = "Creates a person",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = PersonVO::class)))]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Server Error", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ])
    fun createPerson(@RequestBody person: PersonVO): PersonVO {
        return personService.create(person)
    }
    @PostMapping(value=["/v2"],consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun createPersonV2(@RequestBody person: PersonVOV2): PersonVOV2 {
        return personService.createV2(person)
    }
    @PutMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Updates a Person", description = "Updates a Person",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = PersonVO::class)))]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Server Error", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ])
    fun updatePerson(@RequestBody person: PersonVO): PersonVO {
        return personService.update(person)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Deletes a Person", description = "Deletes a Person",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Bad Request", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Server Error", responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ])
    fun deletePerson(@PathVariable("id") id: Long): ResponseEntity<*> {
        personService.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
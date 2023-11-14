package br.com.leomaia.controller


import br.com.leomaia.model.Person
import br.com.leomaia.services.PersonService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {
    @Autowired
    private lateinit var personService: PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllPerson(): List<Person>{
        return personService.findAll()
    }

    @GetMapping(value= ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPersonById(@PathVariable(value = "id") id: Long,

    ): Person {
    return personService.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: Person):Person{
        return personService.create(person)
    }
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: Person):Person {
        return personService.update(person)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePerson(@PathVariable("id") id: Long): ResponseEntity<*> {
        personService.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
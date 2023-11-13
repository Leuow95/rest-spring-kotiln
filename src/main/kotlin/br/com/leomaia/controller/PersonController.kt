package br.com.leomaia.controller


import br.com.leomaia.model.Person
import br.com.leomaia.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {
    @Autowired
    private lateinit var personService: PersonService

    @RequestMapping(method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllPerson(): List<Person>{
        return personService.findAll()
    }

    @RequestMapping(value= ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPersonById(@PathVariable(value = "id") id: Long,

    ): Person {
    return personService.findById(id)
    }

    @RequestMapping( method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: Person):Person{
        return personService.create(person)
    }
    @RequestMapping( method = [RequestMethod.PUT],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: Person):Person {
        return personService.update(person)
    }

    @RequestMapping( method = [RequestMethod.DELETE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePerson(@PathVariable("id") id: Long) {
        personService.delete(id)
    }
}
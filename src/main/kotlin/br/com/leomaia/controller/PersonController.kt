package br.com.leomaia.controller


import br.com.leomaia.data.vo.v1.PersonVO
import br.com.leomaia.data.vo.v2.PersonVO as PersonVOV2
import br.com.leomaia.services.PersonService
import br.com.leomaia.util.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/person/v1")
class PersonController {
    @Autowired
    private lateinit var personService: PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun getAllPerson(): List<PersonVO>{
        return personService.findAll()
    }

    @GetMapping(value= ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun getPersonById(@PathVariable(value = "id") id: Long,

    ): PersonVO {
    return personService.findById(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
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
    fun updatePerson(@RequestBody person: PersonVO): PersonVO {
        return personService.update(person)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun deletePerson(@PathVariable("id") id: Long): ResponseEntity<*> {
        personService.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
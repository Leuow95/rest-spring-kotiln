package br.com.leomaia.services

import br.com.leomaia.controller.PersonController
import br.com.leomaia.data.vo.v1.PersonVO
import br.com.leomaia.data.vo.v2.PersonVO as PersonVOV2
import br.com.leomaia.exceptions.handler.ResourceNotFoundException
import br.com.leomaia.mapper.DozerMapper
import br.com.leomaia.mapper.custom.PersonMapper
import br.com.leomaia.model.Person
import br.com.leomaia.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO>{
        logger.info("Finding all people")

        val persons = repository.findAll()
        val vos = DozerMapper.parseListObjects(persons, PersonVO::class.java)
        for(person in vos){
            val withSelfRel = linkTo(PersonController::class.java).slash(person.id).withSelfRel()
            person.add(withSelfRel)
        }
        return vos
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")

        val person = repository.findById(id).get()
//            .orElseThrow{ResourceNotFoundException("No records found by this id")}
        val personVO: PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.id).withSelfRel()
        personVO.add(withSelfRel)

        return personVO
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("Creating one person with name ${person.firstName}")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)

        val person = repository.save(entity)
        val personVO: PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)
        val withSelfRef = linkTo(PersonController::class.java).slash(person.id).withSelfRel()
        personVO.add(withSelfRef)
        return  personVO
    }
    fun createV2(person: PersonVOV2): PersonVOV2 {
        logger.info("Creating one person with name ${person.firstName}")
        val entity: Person = mapper.mapVoToEntity(person)

        return mapper.mapEntityToVO(repository.save(entity))
    }
    fun update(person: PersonVO): PersonVO {
        logger.info("Updating person with ID: ${person.id}")
        val entity = repository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found by this id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val personVO: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRelf = linkTo(PersonController::class.java).slash(person.id).withSelfRel()
        personVO.add(withSelfRelf)
        return personVO
    }
    fun delete(id: Long){
        logger.info("Deleting person with ID: $id")

        val person = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found with this id!")}

        repository.delete(person)
    }
}
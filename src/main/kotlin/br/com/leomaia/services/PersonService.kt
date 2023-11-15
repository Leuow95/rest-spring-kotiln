package br.com.leomaia.services

import br.com.leomaia.data.vo.v1.PersonVO
import br.com.leomaia.exceptions.handler.ResourceNotFoundException
import br.com.leomaia.mapper.DozerMapper
import br.com.leomaia.model.Person
import br.com.leomaia.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO>{
        logger.info("Finding all people")

        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")

        var person = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found by this id")}
        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("Creating one person with name ${person.firstName}")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)

        val person = repository.save(entity)
        return DozerMapper.parseObject(person, PersonVO::class.java)
    }
    fun update(person: PersonVO): PersonVO {
        logger.info("Updating person with ID: ${person.id}")
        val entity = repository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found by this id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }
    fun delete(id: Long){
        logger.info("Deleting person with ID: $id")

        val person = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found with this id!")}

        repository.delete(person)
    }


}
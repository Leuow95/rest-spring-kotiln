package br.com.leomaia.services

import br.com.leomaia.exceptions.handler.ResourceNotFoundException
import br.com.leomaia.model.Person
import br.com.leomaia.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<Person>{
        logger.info("Finding all people")

        return repository.findAll()
    }

    fun findById(id: Long): Person{
        logger.info("Finding one person!")

        return repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found by this id")}
    }

    fun create(person: Person): Person{
        logger.info("Creating one person with name ${person.firstName}")
        return repository.save(person)
    }
    fun update(person: Person): Person {
        logger.info("Updating person with ID: ${person.id}")
        val newPerson = repository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found by this id")}

        newPerson.firstName = person.firstName
        newPerson.lastName = person.lastName
        newPerson.address = person.address
        newPerson.gender = person.gender

        return repository.save(person)

    }
    fun delete(id: Long){
        logger.info("Deleting person with ID: $id")

        val person = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found with this id!")}

        repository.delete(person)
    }


}
package br.com.leomaia.mockito.services

import br.com.leomaia.repository.PersonRepository
import br.com.leomaia.restspringkotiln.unittests.mapper.mocks.PersonMock
import br.com.leomaia.services.PersonService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.Mockito.`when`
import java.util.Optional

@ExtendWith(MockitoExtension::class)
internal class PersonServiceTest {

    private lateinit var personMock: PersonMock

    @InjectMocks
    private lateinit var service: PersonService

    @Mock
    private lateinit var repository: PersonRepository

    @BeforeEach
    fun setUp() {
        personMock = PersonMock()
        MockitoAnnotations.openMocks(this)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun findAll() {
    }

    @Test
    fun findById() {
        val person = personMock.mockEntity(1)
        person.id = 1L
        `when`(repository.findById(1)).thenReturn(Optional.of(person))

        val result = service.findById(1)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/person/v1/1"))
        assertEquals("First Name Test 1", result.firstName)
        assertEquals("Last Name Test 1", result.lastName)
        assertEquals("Address Test 1", result.address)
        assertEquals("Female", result.gender)

    }

    @Test
    fun create() {
        val entity = personMock.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1

        val vo = personMock.mockVO(1)

        `when`(repository.save(entity)).thenReturn(persisted)

        val result = service.create(vo)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/person/v1/1"))
        assertEquals("First Name Test 1", result.firstName)
        assertEquals("Last Name Test 1", result.lastName)
        assertEquals("Address Test 1", result.address)
        assertEquals("Female", result.gender)
    }

    @Test
    fun update() {
        val entity = personMock.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = personMock.mockVO(1)
        val result = service.update(vo)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/person/v1/1"))
        assertEquals("First Name Test 1", result.firstName)
        assertEquals("Last Name Test 1", result.lastName)
        assertEquals("Address Test 1", result.address)
        assertEquals("Female", result.gender)

    }

    @Test
    fun delete() {
        val entity = personMock.mockEntity(1)

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))

        service.delete(1)


    }
}
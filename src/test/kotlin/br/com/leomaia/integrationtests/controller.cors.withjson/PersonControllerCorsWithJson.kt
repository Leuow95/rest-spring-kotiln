package br.com.leomaia.integrationtests.controller.cors.withjson

import br.com.leomaia.integrationtests.TestConfigs
import br.com.leomaia.integrationtests.testcontainers.AbstractIntegrationTests
import br.com.leomaia.integrationtests.vo.PersonVO
import ch.qos.logback.core.spi.LifeCycle
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter

import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerCorsWithJson(): AbstractIntegrationTests() {

	private lateinit var specification: RequestSpecification
	private lateinit var objectMapper: ObjectMapper
	private lateinit var personVO: PersonVO

	@BeforeAll
	fun setUpTests(){
		objectMapper = ObjectMapper()
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		personVO = PersonVO()
	}

	@Test
	@Order(1)
	fun testCreate() {
		mockPerson()

		specification = RequestSpecBuilder()
			.addHeader(
				TestConfigs.HEADER_PARAM_ORIGIN,
				TestConfigs.ORIGIN_LEO
			)
			.setBasePath("/api/person/v1")
			.setPort(TestConfigs.SERVER_PORT)
			.addFilter(RequestLoggingFilter(LogDetail.ALL))
			.addFilter(ResponseLoggingFilter(LogDetail.ALL))
			.build()


		val content = given()
			.spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.body(personVO)
			.`when`()
			.post()
			.then()
				.statusCode(200)
			.extract()
			.body()
			.asString()

		val createdPerson = objectMapper.readValue(
			content,
			PersonVO::class.java
		)

		Assertions.assertNotNull(createdPerson.id)
		Assertions.assertNotNull(createdPerson.firstName)
		Assertions.assertNotNull(createdPerson.lastName)
		Assertions.assertNotNull(createdPerson.address)
		Assertions.assertNotNull(createdPerson.gender)
		Assertions.assertTrue(createdPerson.id > 0)
		Assertions.assertEquals(createdPerson.firstName, "Nelson")
		Assertions.assertEquals(createdPerson.lastName, "Piquet")
		Assertions.assertEquals(createdPerson.address, "Brasilia, DF, Brasil")
		Assertions.assertEquals(createdPerson.gender, "Male")
	}

	@Test
	@Order(1)
	fun testCreateWithWrongOrigin() {
		mockPerson()

		specification = RequestSpecBuilder()
			.addHeader(
				TestConfigs.HEADER_PARAM_ORIGIN,
				TestConfigs.ORIGIN_SEMERU
			)
			.setBasePath("/api/person/v1")
			.setPort(TestConfigs.SERVER_PORT)
			.addFilter(RequestLoggingFilter(LogDetail.ALL))
			.addFilter(ResponseLoggingFilter(LogDetail.ALL))
			.build()


		val content = given()
			.spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.body(personVO)
			.`when`()
			.post()
			.then()
			.statusCode(403)
			.extract()
			.body()
			.asString()


		Assertions.assertEquals(content, "Invalid CORS request")
	}

	private fun mockPerson() {
		personVO.firstName = "Nelson"
		personVO.lastName = "Piquet"
		personVO.address = "Brasilia, DF, Brasil"
		personVO.gender = "Male"
	}

}
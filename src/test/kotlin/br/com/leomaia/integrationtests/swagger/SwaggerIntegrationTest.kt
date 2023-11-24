package br.com.leomaia.integrationtests.swagger

import br.com.leomaia.integrationtests.TestConfigs
import br.com.leomaia.integrationtests.testcontainers.AbstractIntegrationTests
import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest(): AbstractIntegrationTests() {

	@Test
	fun shouldDisplaySwaggerUiPage() {
		val content = RestAssured.given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfigs.SERVER_PORT)
			.`when`()
			.get()
			.then()
				.statusCode(200)
			.extract()
			.body()
			.asString()

		Assertions.assertTrue(content.contains("Swagger UI"))
	}

}
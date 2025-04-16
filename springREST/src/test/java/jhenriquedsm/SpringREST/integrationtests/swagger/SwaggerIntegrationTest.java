package jhenriquedsm.SpringREST.integrationtests.swagger;

import static io.restassured.RestAssured.given;

import jhenriquedsm.SpringREST.config.TestConfigs;
import jhenriquedsm.SpringREST.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@DisplayName("Should Display Swagger UI Page")
	@Test
	void testShouldDisplaySwaggerUiPage() {
		var content = given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();

		Assertions.assertTrue(content.contains("Swagger UI"));
	}
}
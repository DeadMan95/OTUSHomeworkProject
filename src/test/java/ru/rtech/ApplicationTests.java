package ru.rtech;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
class ApplicationTests extends Configuration {

    String createBody = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";
    String updateBody = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"zion resident\"\n" +
            "}";

    @BeforeAll
    public static void setUpMockServer() {
        getConfiguration();
    }

    @AfterAll
    public static void tearDownMockServer() {
        stopMock();
    }

    @Test
    void getUserTest() {
        Response response = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .when()
                .get("/users/2")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
        response.getBody().prettyPrint();
        Assertions.assertEquals("Janet", response.jsonPath().getString("data.first_name"));
        Assertions.assertEquals("Weaver", response.jsonPath().getString("data.last_name"));
    }

    @Test
    void createUserTest() {
        Response response = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(createBody)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(201, response.statusCode());
        response.getBody().prettyPrint();
        Assertions.assertEquals("morpheus", response.jsonPath().getString("name"));
        Assertions.assertEquals("leader", response.jsonPath().getString("job"));
    }

    @Test
    void updateUserTest() {
        Response response = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .put("/users/2")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
        response.getBody().prettyPrint();
        Assertions.assertEquals("morpheus", response.jsonPath().getString("name"));
        Assertions.assertEquals("zion resident", response.jsonPath().getString("job"));
    }
}



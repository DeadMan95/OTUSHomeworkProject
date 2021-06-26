package services;

import dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PetAPI {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String PET = "/pet";
    private RequestSpecification spec;

    public PetAPI() {
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response createPet(Pet pet) {
        return
                given(spec)
                        .body(pet)
                        .when()
                        .log().all()
                        .post(PET);
    }

    public Response getPetById(Long id) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return
                given(spec)
                        .when()
                        .log().all()
                        .get(PET + "/" + id);
    }

    public Response updatePetFull(Pet pet) {
        return
                given(spec)
                        .body(pet)
                        .when()
                        .log().all()
                        .put(PET);
    }

    public Response updatePetParams(Long id, String newName, String newStatus) {
        return
                given(spec).contentType("application/x-www-form-urlencoded")
                        .formParam("name", newName)
                        .formParam("status", newStatus)
                        .when()
                        .log().all()
                        .post(PET + "/" + id);
    }

    public void checkResponseCode(Response response, Long maxTimeOut) {
        response.then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(maxTimeOut));
    }

}
package citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Instant;


public class TestFirst extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient httpClient;

    private TestContext context;

    private String user = "2";

    @Test(description = "Получение информации о пользователе")
    @CitrusTest
    public void getUserTest() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .send()
                .get("users/" + user)
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "   \"data\":{\n" +
                        "      \"id\":2,\n" +
                        "      \"email\":\"janet.weaver@reqres.in\",\n" +
                        "      \"first_name\":\"Janet\",\n" +
                        "      \"last_name\":\"Weaver\",\n" +
                        "      \"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"\n" +
                        "   },\n" +
                        "   \"support\":{\n" +
                        "      \"url\":\"https://reqres.in/#support-heading\",\n" +
                        "      \"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                        "   }\n" +
                        "}")
        );
    }

    @Test(description = "Получение токена")
    @CitrusTest
    public void getToken() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .send()
                .post("login/")
                .payload("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"token\": \"QpwL5tke4Pnpja7X4\"\n" +
                        "}")
        );
    }

    @Test(description = "Получение токена")
    @CitrusTest
    public void updateUser() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .send()
                .put("users/" + user)
                .payload("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
        );


        Instant instant = new Timestamp(System.currentTimeMillis()).toInstant();

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\",\n" +
                        "    \"updatedAt\": \"" + instant + "\"\n" +
                        "}")
        );


    }

    @Test(description = "Удаление пользователя")
    @CitrusTest
    public void deleteUser() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .send()
                .delete("users/" + user)
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .statusCode(204)
        );
    }

    @Test(description = "Получение токена")
    @CitrusTest
    public void accessDenied() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .send()
                .post("login/")
                .payload("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(httpClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"error\": \"Missing password\"\n" +
                        "}")
                .statusCode(400)
        );
    }

}

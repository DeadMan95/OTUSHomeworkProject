package ru.rtech;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public static WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options()
            .port(5050));
    public static String baseUrl;

    public static void getConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (properties.get("mock").equals("true")) {
            baseUrl = "http://localhost:5050/api";
            wireMockServer.start();
            WireMock.configureFor("localhost", 5050);
            WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users/2"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withBody("{\n" +
                                    "    \"data\": {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"email\": \"janet.weaver@reqres.in\",\n" +
                                    "        \"first_name\": \"Janet\",\n" +
                                    "        \"last_name\": \"Weaver\",\n" +
                                    "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                                    "    },\n" +
                                    "    \"support\": {\n" +
                                    "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                                    "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                                    "    }\n" +
                                    "}")));

            WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/users"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(201)
                            .withBody("{\n" +
                                    "    \"name\": \"morpheus\",\n" +
                                    "    \"job\": \"leader\",\n" +
                                    "    \"id\": \"760\",\n" +
                                    "    \"createdAt\": \"2021-07-18T14:08:22.862Z\"\n" +
                                    "}")));

            WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/api/users/2"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(200)
                            .withBody("{\n" +
                                    "    \"name\": \"morpheus\",\n" +
                                    "    \"job\": \"zion resident\",\n" +
                                    "    \"updatedAt\": \"2021-07-18T14:09:38.270Z\"\n" +
                                    "}")));

        } else {
            baseUrl = "https://reqres.in/api";
        }
    }

    public static void stopMock() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (properties.get("mock").equals("true")) {
            wireMockServer.stop();
        }
    }

}

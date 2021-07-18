package ru.rtech;

import ru.rtech.pojo.CreateUserRequest;
import ru.rtech.pojo.CreateUserResponse;
import ru.rtech.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;

import java.io.IOException;

@SpringBootTest
class ApiHelpersRetrofitTests {

    APIInterface service = APIClientHelper.getClient().create(APIInterface.class);

    @Test
    @DisplayName("GET - GET USER BY ID")
    void getUserTest() {
        Response<User> response;

        try {
            response = service.getUser().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(response.isSuccessful(), "Response FAILED!!!");

        User user = response.body();
        System.out.println(user);
        Assertions.assertNotNull(user, "User object is null!!!");
        Assertions.assertEquals(2, user.getData().getId());
        Assertions.assertEquals("janet.weaver@reqres.in", user.getData().getEmail());

    }

    @Test
    @DisplayName("POST - CREATE USER")
    void userCreationTest() {
        String name = "Danila";
        String job = "BestQA";

        Response<CreateUserResponse> responseCreateUser;
        CreateUserRequest requestBody = getRequestBody(name, job);
        System.out.println("Request:\n" + requestBody);

        try {
            responseCreateUser = service.createUser(requestBody).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(responseCreateUser.isSuccessful(), "Response FAILED!!!");

        CreateUserResponse userResponse = responseCreateUser.body();
        System.out.println("Response:\n" + userResponse);
        Assertions.assertEquals(201, responseCreateUser.code());
        Assertions.assertEquals(name, responseCreateUser.body().getName(), "Wrong name!!!");
        Assertions.assertEquals(job, responseCreateUser.body().getJob(), "Wrong job!!!");
    }

    public CreateUserRequest getRequestBody(String name, String job) {
        CreateUserRequest requestNewUserData = new CreateUserRequest();
        requestNewUserData.setName(name);
        requestNewUserData.setJob(job);

        return requestNewUserData;
    }

    @Test
    @DisplayName("PUT - UPDATE USER")
    void userUpdateTest() {
        String name = "Danila";
        String job = "BestQA";

        Response<CreateUserResponse> responseCreateUser;
        CreateUserRequest requestBody = getRequestBody(name, job);
        System.out.println("Request:\n" + requestBody);

        try {
            responseCreateUser = service.updateUser(requestBody).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(responseCreateUser.isSuccessful(), "Response FAILED!!!");

        CreateUserResponse userResponse = responseCreateUser.body();
        System.out.println("Response:\n" + userResponse);
        Assertions.assertEquals(200, responseCreateUser.code());
        Assertions.assertEquals(name, responseCreateUser.body().getName(), "Wrong name!!!");
        Assertions.assertEquals(job, responseCreateUser.body().getJob(), "Wrong job!!!");
    }

    @Test
    @DisplayName("DELETE - DELETE USER")
    void userDeleteTest() {
        Response<CreateUserResponse> responseCreateUser;

        try {
            responseCreateUser = service.deleteUser().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(responseCreateUser.isSuccessful(), "Response FAILED!!!");

        CreateUserResponse userResponse = responseCreateUser.body();
        System.out.println("Response:\n" + userResponse);
        Assertions.assertEquals(204, responseCreateUser.code());
    }

}

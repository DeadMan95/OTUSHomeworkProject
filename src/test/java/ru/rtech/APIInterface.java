package ru.rtech;

import retrofit2.http.*;
import ru.rtech.pojo.CreateUserRequest;
import ru.rtech.pojo.CreateUserResponse;
import ru.rtech.pojo.User;
import retrofit2.Call;

public interface APIInterface {
    @GET("2")
    Call<User> getUser();

    @POST("users")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest body);

    @PUT("2")
    Call<CreateUserResponse> updateUser(@Body CreateUserRequest body);

    @DELETE("2")
    Call<CreateUserResponse> deleteUser();

}

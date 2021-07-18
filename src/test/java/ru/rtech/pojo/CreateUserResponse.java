package ru.rtech.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponse {

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("id")
    private String id;

    @JsonProperty("job")
    private String job;

    @JsonProperty("name")
    private String name;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"" + job + "\",\n" +
                "    \"id\": \"" + id + "\",\n" +
                "    \"createdAt\": \"" + createdAt + "\"\n" +
                "    \"updatedAt\": \"" + updatedAt + "\"\n" +
                "}";
    }
}

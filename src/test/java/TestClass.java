import dto.Category;
import dto.Pet;
import dto.Tag;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.PetAPI;

import java.util.Arrays;
import java.util.List;

import static logic.CategoryCreator.createCategory;
import static logic.PetCreator.createPetObject;
import static logic.TagCreator.createTag;

public class TestClass {

    private final Long id = 175L;
    private final Long otherId = 1L;
    private final String petName = "Snowball_I";
    private final String newPetName = "Snowball_II";
    private final List<String> photoUrls = Arrays.asList("https://test.ru/1", "https://test.ru/2", "https://test.ru/3");
    private final String petStatus = "available";
    private final String newPetStatus = "not_available";
    private final String categoryName = "Cats";
    private final Category category = createCategory(id, categoryName);
    private final String tagName = "new";
    private final List<Tag> tags = Arrays.asList(createTag(id, tagName));

    private final Long maxTimeOut = 5000L;

    Pet pet;
    Response response;
    PetAPI petAPI = new PetAPI();

    @Test(description = "create pet")
    public void createPetTest() {
        pet = createPetObject(id, petName, petStatus, photoUrls, tags, category);
        response = petAPI.createPet(pet);
        petAPI.checkResponseCode(response, maxTimeOut);
        response = petAPI.getPetById(id);
        petAPI.checkResponseCode(response, maxTimeOut);
        pet = response.getBody().as(Pet.class);
        Assert.assertEquals(pet.getName(), petName, "Имена не совпадают!");
    }

    @Test(description = "find pet")
    public void getPetTest() {
        response = petAPI.getPetById(otherId);
        petAPI.checkResponseCode(response, maxTimeOut);
        pet = response.getBody().as(Pet.class);
        Assert.assertNotNull(pet.getName(), "Имя = null!");
    }

    @Test(description = "update pet")
    public void updatePetFull() {
        pet = createPetObject(id, newPetName, petStatus, photoUrls, tags, category);
        response = petAPI.updatePetFull(pet);
        petAPI.checkResponseCode(response, maxTimeOut);
        response = petAPI.getPetById(id);
        petAPI.checkResponseCode(response, maxTimeOut);
        pet = response.getBody().as(Pet.class);
        Assert.assertEquals(pet.getName(), newPetName, "Имена не совпадают!");
    }

    @Test(description = "update pet params")
    public void updatePetParams() {
        response = petAPI.updatePetParams(id, newPetName, newPetStatus);
        petAPI.checkResponseCode(response, maxTimeOut);
        response = petAPI.getPetById(id);
        petAPI.checkResponseCode(response, maxTimeOut);
        pet = response.getBody().as(Pet.class);
        Assert.assertEquals(pet.getName(), newPetName, "Имена не совпадают!");
        Assert.assertEquals(pet.getStatus(), newPetStatus, "Статусы не совпадают!");
    }

}
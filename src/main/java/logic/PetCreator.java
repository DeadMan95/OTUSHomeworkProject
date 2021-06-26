package logic;

import dto.Category;
import dto.Pet;
import dto.Tag;

import java.util.List;

public class PetCreator {

    public static Pet createPetObject(Long id, String name, String status, List<String> photoUrls, List<Tag> tags, Category category) {
        return Pet.builder()
                .id(id)
                .name(name)
                .category(category)
                .status(status)
                .photoUrls(photoUrls)
                .tags(tags)
                .build();
    }

}

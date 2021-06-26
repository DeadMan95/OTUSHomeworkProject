package logic;

import dto.Category;

public class CategoryCreator {

    public static Category createCategory(Long categoryId, String categoryName) {
        return new Category(categoryId, categoryName);
    }

}

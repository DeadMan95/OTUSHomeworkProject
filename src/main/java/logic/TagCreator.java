package logic;

import dto.Tag;

public class TagCreator {

    public static Tag createTag(Long tagId, String tagName) {
        return new Tag(tagId, tagName);
    }

}

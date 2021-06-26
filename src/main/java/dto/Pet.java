package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class Pet {
    private Category category;
    private Long id;
    private String name;
    private List<String> photoUrls;
    private String status;
    private List<Tag> tags;
}

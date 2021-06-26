package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@Data
@Builder
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;
}

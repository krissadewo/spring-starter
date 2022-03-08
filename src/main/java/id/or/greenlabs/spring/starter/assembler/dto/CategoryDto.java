package id.or.greenlabs.spring.starter.assembler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author krissadewo
 * @date 1/21/22 1:05 PM
 */
@Data
@NoArgsConstructor
public class CategoryDto {

    private String id;

    private String name;

    public CategoryDto(String id) {
        this.id = id;
    }
}

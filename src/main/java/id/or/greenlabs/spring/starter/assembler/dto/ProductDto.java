package id.or.greenlabs.spring.starter.assembler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author krissadewo
 * @date 1/31/22 1:01 PM
 */
@Data
@NoArgsConstructor
public class ProductDto implements Serializable {

    private String id;

    private String code;

    private String name;

    private CategoryDto category;

    private boolean delete;

    public ProductDto(CategoryDto category) {
        this.category = category;
    }

    public ProductDto(String id) {
        this.id = id;
    }

    public ProductDto(String id, CategoryDto category) {
        this.id = id;
        this.category = category;
    }
}

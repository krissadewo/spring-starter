package id.or.greenlabs.spring.starter.document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 * @author krissadewo
 * @date 2/23/22 3:46 PM
 */
@Data
@Document("product")
@NoArgsConstructor
public class Product implements DocumentAware {

    @Id
    private String id;

    private String code;

    private String name;

    @Field(targetType = FieldType.OBJECT_ID)
    private String categoryId;

    @Setter(AccessLevel.NONE)
    private Category category;

    private boolean delete;

}

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
 * @date 2/23/22 3:45 PM
 */
@Data
@Document("order")
@NoArgsConstructor
public class Order implements DocumentAware {

    @Id
    private String id;

    @Field(targetType = FieldType.OBJECT_ID)
    private String productId;

    @Setter(AccessLevel.NONE)
    private Product product;

    private String code;

    private int qty;

    private Type type;

    private long createdTime = System.currentTimeMillis();

    public enum Type {
        SELL, BUY
    }

}

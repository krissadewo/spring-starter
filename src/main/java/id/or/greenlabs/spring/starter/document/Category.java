package id.or.greenlabs.spring.starter.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author krissadewo
 * @date 2/23/22 3:45 PM
 */
@Data
@Document("category")
@NoArgsConstructor
public class Category implements DocumentAware {

    @Id
    private String id;

    private String name;

    private boolean delete;

    public Category(String id) {
        this.id = id;
    }
}

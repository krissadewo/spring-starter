package id.or.greenlabs.spring.starter.repository.converter;

import id.or.greenlabs.spring.starter.document.Category;
import org.bson.BsonDocument;
import org.springframework.core.convert.converter.Converter;

/**
 * @author krissadewo
 * @date 1/29/21 2:31 PM
 */
public class CategoryConverter implements Converter<BsonDocument, Category> {

    @Override
    public Category convert(BsonDocument source) {
        Category category = new Category();
        category.setId(source.get("_id").asString().getValue());
        category.setName(source.get("name").asString().getValue());

        return category;
    }
}

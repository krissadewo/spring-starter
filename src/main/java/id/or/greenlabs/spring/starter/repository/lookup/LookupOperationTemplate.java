package id.or.greenlabs.spring.starter.repository.lookup;

import org.springframework.data.mongodb.core.aggregation.LookupOperation;

/**
 * @author krissadewo
 * @date 1/30/21 11:23 AM
 */
public class LookupOperationTemplate {

    public static LookupOperation lookupCategory() {
        return LookupOperation.newLookup()
            .from("category")
            .localField("categoryId")
            .foreignField("_id")
            .as("category");
    }

    public static LookupOperation lookupProduct() {
        return LookupOperation.newLookup()
            .from("product")
            .localField("productId")
            .foreignField("_id")
            .as("product");
    }

}

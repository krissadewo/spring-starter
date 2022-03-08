package id.or.greenlabs.spring.starter.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author krissadewo
 * @date 2/23/22 3:46 PM
 */
@Data
@Document("stock")
@NoArgsConstructor
public class Stock implements DocumentAware {

    @Id
    private String id;

    private String productId;

    private Product product;

    private String orderId;

    private Order order;

    private int qty;

    private long createdTime = System.currentTimeMillis();
}


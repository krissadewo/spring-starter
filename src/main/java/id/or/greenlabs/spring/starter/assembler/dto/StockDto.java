package id.or.greenlabs.spring.starter.assembler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author krissadewo
 * @date 2/4/22 1:14 PM
 */
@Data
@NoArgsConstructor
public class StockDto {

    private String id;

    private OrderDto order;

    private ProductDto product;

    private int qty;

    private long createdTime;
}

package id.or.greenlabs.spring.starter.assembler.dto;

import id.or.greenlabs.spring.starter.document.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author krissadewo
 * @date 2/3/22 5:33 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private String id;

    private ProductDto product;

    private String code;

    private int qty;

    private Order.Type type;

    private long createdTime;

    public enum Type {
        SELL, BUY
    }

    public OrderDto(String id) {
        this.id = id;
    }


}

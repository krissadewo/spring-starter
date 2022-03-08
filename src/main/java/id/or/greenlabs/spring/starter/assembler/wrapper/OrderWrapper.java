package id.or.greenlabs.spring.starter.assembler.wrapper;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.assembler.generic.Assembler;
import id.or.greenlabs.spring.starter.document.Order;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author krissadewo
 * @date 2/3/22 5:34 PM
 */
public class OrderWrapper implements Assembler<OrderDto, Order> {

    @Override
    public OrderDto toDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setQty(entity.getQty());
        dto.setType(entity.getType());
        dto.setCreatedTime(entity.getCreatedTime());

        if (entity.getProduct() != null) {
            dto.setProduct(new ProductWrapper().toDto(entity.getProduct()));
        }

        return dto;
    }

    @Override
    public Collection<OrderDto> toDto(Collection<Order> documents) {
        Collection<OrderDto> dtos = new ArrayList<>();

        documents.forEach(object -> {
            dtos.add(toDto(object));
        });

        return dtos;
    }

    @Override
    public Order toParam(OrderDto dto) {
        Order param = new Order();
        param.setCode(dto.getCode());

        return param;
    }

    @Override
    public Order toDocument(OrderDto dto) {
        Order document = new Order();
        document.setCode(dto.getCode());
        document.setType(dto.getType());
        document.setQty(dto.getQty());
        document.setProductId(dto.getProduct().getId());

        return document;
    }

    @Override
    public Collection<Order> toDocument(Collection<OrderDto> dto) {
        Collection<Order> documents = new ArrayList<>();

        dto.forEach(object -> {
            documents.add(toDocument(object));
        });

        return documents;
    }
}

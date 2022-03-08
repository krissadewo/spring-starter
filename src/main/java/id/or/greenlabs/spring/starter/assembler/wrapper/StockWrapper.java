package id.or.greenlabs.spring.starter.assembler.wrapper;

import id.or.greenlabs.spring.starter.assembler.dto.StockDto;
import id.or.greenlabs.spring.starter.assembler.generic.Assembler;
import id.or.greenlabs.spring.starter.document.Stock;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author krissadewo
 * @date 2/4/22 1:16 PM
 */
public class StockWrapper implements Assembler<StockDto, Stock> {

    @Override
    public StockDto toDto(Stock entity) {
        StockDto dto = new StockDto();
        dto.setId(entity.getId());
        dto.setQty(entity.getQty());
        dto.setCreatedTime(entity.getCreatedTime());

        if (entity.getProduct() != null) {
            dto.setProduct(new ProductWrapper().toDto(entity.getProduct()));
        }

        if (entity.getOrder() != null) {
            dto.setOrder(new OrderWrapper().toDto(entity.getOrder()));
        }

        return null;
    }

    @Override
    public Collection<StockDto> toDto(Collection<Stock> documents) {
        Collection<StockDto> dtos = new ArrayList<>();

        documents.forEach(object -> {
            dtos.add(toDto(object));
        });

        return dtos;
    }

    @Override
    public Stock toParam(StockDto dto) {
        return null;
    }

    @Override
    public Stock toDocument(StockDto dto) {
        Stock document = new Stock();
        document.setOrderId(dto.getOrder().getId());
        document.setQty(dto.getQty());

        if (dto.getOrder().getProduct() != null) {
            document.setProductId(dto.getOrder().getProduct().getId());
        }

        return document;
    }

    @Override
    public Collection<Stock> toDocument(Collection<StockDto> dto) {
        Collection<Stock> documents = new ArrayList<>();

        dto.forEach(object -> {
            documents.add(toDocument(object));
        });

        return documents;
    }
}

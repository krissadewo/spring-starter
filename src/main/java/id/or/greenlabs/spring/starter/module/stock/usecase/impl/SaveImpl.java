package id.or.greenlabs.spring.starter.module.stock.usecase.impl;

import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.assembler.dto.StockDto;
import id.or.greenlabs.spring.starter.common.UseCase;
import id.or.greenlabs.spring.starter.document.Order;
import id.or.greenlabs.spring.starter.module.stock.port.StockAdapter;
import id.or.greenlabs.spring.starter.module.stock.usecase.Save;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krissadewo
 * @date 3/8/22 7:12 AM
 */
@UseCase("stockSave")
@RequiredArgsConstructor
public class SaveImpl implements Save {

    private final StockAdapter adapter;

    @Override
    public Mono<List<StockDto>> execute(List<OrderDto> dtos) {
        List<StockDto> stocks = new ArrayList<>();

        dtos.forEach(dto -> {
            StockDto stock = new StockDto();
            stock.setOrder(dto);
            stock.setProduct(dto.getProduct());

            if (dto.getType() == Order.Type.SELL) {
                stock.setQty(dto.getQty() * -1);
            } else {
                stock.setQty(dto.getQty());
            }

            stocks.add(stock);
        });

        return adapter.save(stocks);
    }
}

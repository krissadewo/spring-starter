package id.or.greenlabs.spring.starter.common;

import id.or.greenlabs.spring.starter.assembler.dto.CategoryDto;
import id.or.greenlabs.spring.starter.assembler.dto.OrderDto;
import id.or.greenlabs.spring.starter.assembler.dto.ProductDto;
import id.or.greenlabs.spring.starter.assembler.dto.StockDto;
import id.or.greenlabs.spring.starter.document.Category;
import id.or.greenlabs.spring.starter.document.Order;
import id.or.greenlabs.spring.starter.document.Product;
import id.or.greenlabs.spring.starter.document.Stock;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author krissadewo
 * @date 2/24/22 2:09 PM
 */
public class DummyData {

    public Category category() {
        Category category = new Category();
        category.setName("SUV");

        return category;
    }

    public CategoryDto categoryDto() {
        CategoryDto dto = new CategoryDto();
        dto.setName("SUV");

        return dto;
    }

    public Product product(String categoryId) {
        Product product = new Product();
        product.setName("HRV");
        product.setCode("M001");
        product.setCategoryId(categoryId);

        return product;
    }

    public ProductDto productDto(String categoryId) {
        ProductDto dto = new ProductDto();
        dto.setName("HRV");
        dto.setCode("M001");
        dto.setCategory(new CategoryDto(categoryId));

        return dto;
    }

    public ProductDto productDto(CategoryDto category) {
        ProductDto dto = new ProductDto();
        dto.setName("HRV");
        dto.setCode("M001");
        dto.setCategory(category);

        return dto;
    }

    public List<Order> orders(Product product) {
        Order order = new Order();
        order.setProductId(product.getId());
        order.setQty(8);
        order.setType(Order.Type.SELL);

        return Collections.singletonList(order);
    }

    public List<OrderDto> orderDtos() {
        List<OrderDto> orders = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            OrderDto dto = new OrderDto();
            dto.setCode(UUID.randomUUID().toString());
            dto.setProduct(new ProductDto(new ObjectId().toHexString(), new CategoryDto(new ObjectId().toHexString())));
            dto.setQty(i + 2);
            dto.setType(Order.Type.SELL);

            orders.add(dto);
        }

        return orders;
    }

    public List<Stock> stocks(List<Order> orders) {
        List<Stock> stocks = new ArrayList<>();

        orders.forEach(order -> {
            Stock stock = new Stock();
            stock.setOrderId(order.getId());
            stock.setProductId(order.getProductId());
            stock.setQty(order.getQty());

            stocks.add(stock);
        });

        return stocks;
    }

    public List<StockDto> stockDtos(List<OrderDto> orders) {
        List<StockDto> stocks = new ArrayList<>();

        orders.forEach(order -> {
            StockDto stock = new StockDto();
            stock.setOrder(new OrderDto(new ObjectId().toHexString()));
            stock.setProduct(new ProductDto(order.getProduct().getId()));
            stock.setQty(order.getQty());

            stocks.add(stock);
        });

        return stocks;
    }
}

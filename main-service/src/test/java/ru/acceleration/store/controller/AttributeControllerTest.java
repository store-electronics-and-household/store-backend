package ru.acceleration.store.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.acceleration.store.dto.AttributeProductRequest;
import ru.acceleration.store.dto.AttributeResponse;
import ru.acceleration.store.dto.ProductAttributesDto;
import ru.acceleration.store.mapper.AttributeProductMapper;
import ru.acceleration.store.model.ProductAttributes;
import ru.acceleration.store.service.AttributeService;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql({"/schema.sql"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttributeTest {

    private final AttributeService attributeService;

    @Test
    void getAttributeCategory() {
    }

    @Test
    void getAttributeProduct() {
        AttributeResponse attributeResponse= attributeService.getAttributeProduct(new AttributeProductRequest(1L));
        List<ProductAttributes> attributes =attributeResponse.getAttributes();
        List<ProductAttributesDto> productAttributesDtos = attributes.stream()
                .map(AttributeProductMapper::toproductAttributesDto)
                .toList();
        System.out.println("qwer123     "+productAttributesDtos);
    }
}
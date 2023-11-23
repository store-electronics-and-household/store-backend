package ru.acceleration.store;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.acceleration.store.dto.*;
import ru.acceleration.store.service.AttributeService;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql({"/schema-test.sql"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttributeTest {

    private final AttributeService attributeService;

    @Test
    void getAttributeCategory() {
        AttributeCategoryResponse attributeCategoryResponse = attributeService.getAttributeCategory(new AttributeCategoryRequest(1L));
        List<CategoryAttributesDto> attributes = attributeCategoryResponse.getAttributes();
        Assertions.assertAll(
                () -> Assertions.assertEquals(attributes.get(0).getAttributeName(), "годВыпуска"),
                () -> Assertions.assertEquals(attributes.get(1).getListvalue().size(), 2)
        );

    }

    @Test
    void getAttributeProduct() {
        AttributeProductResponse attributeProductResponse = attributeService.getAttributeProduct(new AttributeProductRequest(1L));
        List<ProductAttributesDto> attributes = attributeProductResponse.getAttributes();
        Assertions.assertAll(
                () -> Assertions.assertEquals(attributes.get(0).getValue(), "555Ач"),
                () -> Assertions.assertEquals(attributes.get(1).getAttributeName(), "вес")
        );
    }
}
package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.acceleration.store.dto.attribute.*;
import ru.acceleration.store.service.attribute.AttributeService;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql({"/data-test.sql"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttributeServiceTest {

    private final AttributeService attributeService;

    @Test
    void getAttributeCategory() {
        AttributeCategoryResponse attributeCategoryResponse = attributeService.getAttributeCategory(new AttributeCategoryRequest(1L));
        List<CategoryAttributesDto> attributes = attributeCategoryResponse.getAttributes();
        Assertions.assertAll(
                () -> Assertions.assertEquals(attributes.get(0).getAttributeName(), "emkocti"),
                () -> Assertions.assertEquals(attributes.get(1).getListvalue().size(), 1)
        );

    }

    @Test
    void getAttributeProduct() {
        AttributeProductResponse attributeProductResponse = attributeService.getAttributeProduct(new AttributeProductRequest(1L));
        List<ProductAttributesDto> attributes = attributeProductResponse.getAttributes();
        Assertions.assertAll(
                () -> Assertions.assertEquals(attributes.get(0).getValue(), "555A"),
                () -> Assertions.assertEquals(attributes.get(1).getAttributeName(), "ves")
        );
    }
}

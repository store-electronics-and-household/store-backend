package ru.acceleration.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.acceleration.store.dto.*;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql({"/schema-test.sql"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AttributeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void getAttributeCategory() throws Exception {
        AttributeCategoryRequest attributeCategoryRequest = new AttributeCategoryRequest(1L);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/attribute/category")
                        .content(mapper.writeValueAsString(attributeCategoryRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        AttributeCategoryResponse dto = objectMapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
             //   result.getResponse().getContentAsString(),
                AttributeCategoryResponse.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(dto.getAttributes().get(0).getAttributeName(), "555Ач"),
                () -> Assertions.assertEquals(dto.getAttributes().get(1).getListvalue().size(), "вес")
        );
    }

    @Test
    void getAttributeProduct() throws Exception {
        AttributeProductRequest attributeProductRequest = new AttributeProductRequest(1L);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/attribute/product")
                        .content(mapper.writeValueAsString(attributeProductRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        AttributeProductResponse dto = objectMapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                //result.getResponse().getContentAsString(),
                AttributeProductResponse.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(dto.getAttributes().get(0).getValue(), "555Ач"),
                () -> Assertions.assertEquals(dto.getAttributes().get(1).getAttributeName(), "вес")
        );
    }
}
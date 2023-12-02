package ru.acceleration.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.acceleration.store.dto.attribute.AttributeCategoryRequest;
import ru.acceleration.store.dto.attribute.AttributeCategoryResponse;
import ru.acceleration.store.dto.attribute.AttributeProductRequest;
import ru.acceleration.store.dto.attribute.AttributeProductResponse;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql({"/data-test.sql"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttributeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void getAttributeCategory() {
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
                () -> Assertions.assertEquals(dto.getAttributes().get(0).getAttributeName(), "emkocti"),
                () -> Assertions.assertEquals(dto.getAttributes().get(1).getListvalue().size(), 1)
        );
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
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
                () -> Assertions.assertEquals(dto.getAttributes().get(0).getValue(), "555A"),
                () -> Assertions.assertEquals(dto.getAttributes().get(1).getAttributeName(), "ves")
        );
    }
}

package ru.acceleration.store.controller.attributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.acceleration.store.dto.attribute.AttributeDtoResponse;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.enums.AttributeType;
import ru.acceleration.store.service.attribute.CategoryAttributesService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryAttributeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryAttributesService categoryAttributesService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getCategoryAttributeById_givenValidId_thenExpectOkWithBody() {
        CategoryAttributeDtoResponse categoryAttributeDtoResponse = CategoryAttributeDtoResponse.builder()
                        .id(17L)
                        .priority(10L)
                        .attributeType(AttributeType.FOURTH_TYPE)
                        .categoryOutcomeDto(CategoryOutcomeDto.builder().id(9L).name("CategoryName").build())
                        .attributeDtoResponse(AttributeDtoResponse.builder().id(13L).name("AttributeName").build())
                        .build();
        when(categoryAttributesService.getCategoryAttributeById(17L))
                .thenReturn(categoryAttributeDtoResponse);

        String response = mockMvc.perform(get("/api/v1/admin/category-attributes/17"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(categoryAttributesService, atLeast(1)).getCategoryAttributeById(17L);
        assertEquals(response, objectMapper.writeValueAsString(categoryAttributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getCategoryAttributeById_givenInvalidId_thenExpectNotFound() {
        when(categoryAttributesService.getCategoryAttributeById(17L))
                .thenThrow(DataNotFoundException.class);

        mockMvc.perform(get("/api/v1/admin/category-attributes/17"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(categoryAttributesService, atLeast(1)).getCategoryAttributeById(17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getCategoryAttributeByCategoryId_givenValidId_thenExpectOkWithBody() {
        List<CategoryAttributeDtoResponse> categoryAttributeDtoResponseList = List.of(CategoryAttributeDtoResponse.builder()
                .id(17L)
                .priority(10L)
                .attributeType(AttributeType.FOURTH_TYPE)
                .categoryOutcomeDto(CategoryOutcomeDto.builder().id(9L).name("CategoryName").build())
                .attributeDtoResponse(AttributeDtoResponse.builder().id(13L).name("AttributeName").build())
                .build());
        when(categoryAttributesService.getCategoryAttributesByCategoryId(9L))
                .thenReturn(categoryAttributeDtoResponseList);

        String response = mockMvc.perform(get("/api/v1/admin/category-attributes?categoryId=9"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(categoryAttributesService, atLeast(1)).getCategoryAttributesByCategoryId(9L);
        assertEquals(response, objectMapper.writeValueAsString(categoryAttributeDtoResponseList));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getCategoryAttributeByCategoryId_givenInvalidId_thenExpectNotFound() {
        when(categoryAttributesService.getCategoryAttributesByCategoryId(9L))
                .thenThrow(DataNotFoundException.class);

        mockMvc.perform(get("/api/v1/admin/category-attributes?categoryId=9"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(categoryAttributesService, atLeast(1)).getCategoryAttributesByCategoryId(9L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void createCategoryAttribute_givenValidIds_thenExpectCreatedWithBody() {
        CategoryAttributeDtoResponse categoryAttributeDtoResponse = CategoryAttributeDtoResponse.builder()
                .id(17L)
                .priority(10L)
                .attributeType(AttributeType.FOURTH_TYPE)
                .categoryOutcomeDto(CategoryOutcomeDto.builder().id(9L).name("CategoryName").build())
                .attributeDtoResponse(AttributeDtoResponse.builder().id(13L).name("AttributeName").build())
                .build();
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE)
                .priority(10L)
                .build();
        when(categoryAttributesService.createCategoryAttributes(9L, 13L, categoryAttributeDtoRequest))
                .thenReturn(categoryAttributeDtoResponse);

        String response = mockMvc.perform(post("/api/v1/admin/category-attributes/9?attributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryAttributeDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(categoryAttributesService, atLeast(1))
                .createCategoryAttributes(9L, 13L, categoryAttributeDtoRequest);
        assertEquals(response, objectMapper.writeValueAsString(categoryAttributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void createCategoryAttribute_givenInvalidIds_thenExpectNotFound() {
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE)
                .priority(10L)
                .build();
        when(categoryAttributesService.createCategoryAttributes(9L, 13L, categoryAttributeDtoRequest))
                .thenThrow(DataNotFoundException.class);

        mockMvc.perform(post("/api/v1/admin/category-attributes/9?attributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryAttributeDtoRequest)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(categoryAttributesService, atLeast(1))
                .createCategoryAttributes(9L, 13L, categoryAttributeDtoRequest);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void updateCategoryAttribute_givenValidIds_thenExpectOkWithBody() {
        CategoryAttributeDtoResponse categoryAttributeDtoResponse = CategoryAttributeDtoResponse.builder()
                .id(17L)
                .priority(10L)
                .attributeType(AttributeType.FOURTH_TYPE)
                .categoryOutcomeDto(CategoryOutcomeDto.builder().id(9L).name("CategoryName").build())
                .attributeDtoResponse(AttributeDtoResponse.builder().id(13L).name("AttributeName").build())
                .build();
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE)
                .priority(10L)
                .build();
        when(categoryAttributesService.updateCategoryAttribute(17L, 9L, 13L, categoryAttributeDtoRequest))
                .thenReturn(categoryAttributeDtoResponse);

        String response = mockMvc.perform(put("/api/v1/admin/category-attributes/17?categoryId=9&attributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryAttributeDtoRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(categoryAttributesService, atLeast(1))
                .updateCategoryAttribute(17L, 9L, 13L, categoryAttributeDtoRequest);
        assertEquals(response, objectMapper.writeValueAsString(categoryAttributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void updateCategoryAttribute_givenInvalidIds_thenExpectNotFound() {
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE)
                .priority(10L)
                .build();
        when(categoryAttributesService.updateCategoryAttribute(17L, 9L, 13L, categoryAttributeDtoRequest))
                .thenThrow(DataNotFoundException.class);

        mockMvc.perform(put("/api/v1/admin/category-attributes/17?categoryId=9&attributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryAttributeDtoRequest)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(categoryAttributesService, atLeast(1))
                .updateCategoryAttribute(17L, 9L, 13L, categoryAttributeDtoRequest);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void deleteCategoryAttribute_givenValidIds_thenExpect204() {
        doNothing().when(categoryAttributesService).deleteCategoryAttributeById(17L);

        mockMvc.perform(delete("/api/v1/admin/category-attributes/17"))
                .andExpect(status().is(204))
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(categoryAttributesService, atLeast(1)).deleteCategoryAttributeById(17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void deleteCategoryAttribute_givenInvalidId_thenExpectNotFound() {
        doThrow(DataNotFoundException.class).when(categoryAttributesService).deleteCategoryAttributeById(17L);

        mockMvc.perform(delete("/api/v1/admin/category-attributes/17"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(categoryAttributesService, atLeast(1)).deleteCategoryAttributeById(17L);
    }
}

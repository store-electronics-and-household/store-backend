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
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.enums.AttributeType;
import ru.acceleration.store.service.attribute.ModelAttributesService;

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
public class AdminModelAttributesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ModelAttributesService modelAttributesService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getModelAttributeById_givenValidId_thenExpectOkWithBody() {
        ModelAttributeDtoResponse modelAttributeDtoResponse = ModelAttributeDtoResponse.builder()
                .id(17L)
                .modelShortDto(ModelShortDto.builder().id(19L).name("modelName").price(100L).description("Desc")
                        .build())
                .categoryAttributeDtoResponse(CategoryAttributeDtoResponse.builder().id(13L).priority(8L)
                        .attributeType(AttributeType.SECOND_TYPE).build())
                .value("value")
                .build();
        when(modelAttributesService.getModelAttributesById(17L))
                .thenReturn(modelAttributeDtoResponse);

        String response = mockMvc.perform(get("/admin/model-attributes/17"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(modelAttributesService, atLeast(1)).getModelAttributesById(17L);
        assertEquals(response, objectMapper.writeValueAsString(modelAttributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getModelAttributeById_givenInvalidId_thenExpectNotFound() {
        when(modelAttributesService.getModelAttributesById(17L)).thenThrow(DataNotFoundException.class);

        mockMvc.perform(get("/admin/model-attributes/17"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(modelAttributesService, atLeast(1)).getModelAttributesById(17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getModelAttributeByModelId_givenValidId_thenExpectOkWithBody() {
        List<ModelAttributeDtoResponse> modelAttributeDtoResponseList = List.of(ModelAttributeDtoResponse.builder()
                .id(17L)
                .modelShortDto(ModelShortDto.builder().id(19L).name("modelName").price(100L).description("Desc")
                        .build())
                .categoryAttributeDtoResponse(CategoryAttributeDtoResponse.builder().id(13L).priority(8L)
                        .attributeType(AttributeType.SECOND_TYPE).build())
                .value("value")
                .build());
        when(modelAttributesService.getModelAttributesByModelId(19L)).thenReturn(modelAttributeDtoResponseList);

        String response = mockMvc.perform(get("/admin/model-attributes/model/19"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(modelAttributesService, atLeast(1)).getModelAttributesByModelId(19L);
        assertEquals(response, objectMapper.writeValueAsString(modelAttributeDtoResponseList));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getModelAttributeByModelId_givenInvalidId_thenExpectNotFound() {
        when(modelAttributesService.getModelAttributesByModelId(19L)).thenThrow(DataNotFoundException.class);

        mockMvc.perform(get("/admin/model-attributes/model/19"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(modelAttributesService, atLeast(1)).getModelAttributesByModelId(19L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void createModelAttribute_givenValidId_thenExpectCreatedWithBody() {
        ModelAttributeDtoResponse modelAttributeDtoResponse = ModelAttributeDtoResponse.builder()
                .id(17L)
                .modelShortDto(ModelShortDto.builder().id(19L).name("modelName").price(100L)
                        .description("Desc").build())
                .categoryAttributeDtoResponse(CategoryAttributeDtoResponse.builder().id(13L).priority(8L)
                        .attributeType(AttributeType.SECOND_TYPE).build())
                .value("value")
                .build();
        ModelAttributeDtoRequest modelAttributeDtoRequest = ModelAttributeDtoRequest.builder().value("Value").build();
        when(modelAttributesService.createModelAttribute(19L, 13L, modelAttributeDtoRequest))
                .thenReturn(modelAttributeDtoResponse);

        String response = mockMvc.perform(post("/admin/model-attributes/model/19?categoryAttributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelAttributeDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(modelAttributesService, atLeast(1))
                .createModelAttribute(19L, 13L, modelAttributeDtoRequest);
        assertEquals(response, objectMapper.writeValueAsString(modelAttributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void createModelAttribute_givenInvalidId_thenExpectNotFound() {
        ModelAttributeDtoRequest modelAttributeDtoRequest = ModelAttributeDtoRequest.builder().value("Value").build();
        when(modelAttributesService.createModelAttribute(19L, 13L, modelAttributeDtoRequest))
                .thenThrow(DataNotFoundException.class);

        mockMvc.perform(post("/admin/model-attributes/model/19?categoryAttributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelAttributeDtoRequest)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(modelAttributesService, atLeast(1))
                .createModelAttribute(19L, 13L, modelAttributeDtoRequest);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void updateModelAttribute_givenValidId_thenExpectCreatedWithBody() {
        ModelAttributeDtoResponse modelAttributeDtoResponse = ModelAttributeDtoResponse.builder()
                .id(17L)
                .modelShortDto(ModelShortDto.builder().id(19L).name("modelName").price(100L).description("Desc")
                        .build())
                .categoryAttributeDtoResponse(CategoryAttributeDtoResponse.builder().id(13L).priority(8L)
                        .attributeType(AttributeType.SECOND_TYPE).build())
                .value("value")
                .build();
        ModelAttributeDtoRequest modelAttributeDtoRequest = ModelAttributeDtoRequest.builder().value("Value").build();
        when(modelAttributesService
                .updateModelAttribute(17L,19L, 13L, modelAttributeDtoRequest))
                .thenReturn(modelAttributeDtoResponse);

        String response = mockMvc.perform(put("/admin/model-attributes/17?modelId=19&categoryAttributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelAttributeDtoRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(modelAttributesService, atLeast(1))
                .updateModelAttribute(17L, 19L, 13L, modelAttributeDtoRequest);
        assertEquals(response, objectMapper.writeValueAsString(modelAttributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void updateModelAttribute_givenInvalidId_thenExpectNotFound() {
        ModelAttributeDtoRequest modelAttributeDtoRequest = ModelAttributeDtoRequest.builder().value("Value").build();
        when(modelAttributesService
                .updateModelAttribute(17L,19L, 13L, modelAttributeDtoRequest))
                .thenThrow(DataNotFoundException.class);

        mockMvc.perform(put("/admin/model-attributes/17?modelId=19&categoryAttributeId=13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelAttributeDtoRequest)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(modelAttributesService, atLeast(1))
                .updateModelAttribute(17L, 19L, 13L, modelAttributeDtoRequest);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void deleteModelAttributeById_givenValidId_thenExpectDeleted() {
        doNothing().when(modelAttributesService).deleteModelAttribute(17L);

        mockMvc.perform(delete("/admin/model-attributes/17"))
                .andExpect(status().is(204))
                .andReturn()
                .getResponse();

        verify(modelAttributesService, atLeast(1)).deleteModelAttribute(17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void deleteModelAttributeById_givenInvalidId_thenExpectNotFound() {
        doThrow(DataNotFoundException.class).when(modelAttributesService).deleteModelAttribute(17L);

        mockMvc.perform(delete("/admin/model-attributes/17"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(modelAttributesService, atLeast(1)).deleteModelAttribute(17L);
    }
}

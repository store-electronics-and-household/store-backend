package ru.acceleration.store.controller;

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
import ru.acceleration.store.dto.attribute.AttributeDtoRequest;
import ru.acceleration.store.dto.attribute.AttributeDtoResponse;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.service.attribute.AttributeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributesControllerTests {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    AttributeService attributeService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getAttributeById_givenValidId_thenExpectOkWithBody() {
        AttributeDtoResponse attributeDtoResponse = AttributeDtoResponse.builder().id(17L).name("Attribute17").build();
        when(attributeService.getAttributeById(17L)).thenReturn(attributeDtoResponse);

        String response = mockMvc.perform(get("/attributes/17"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(attributeService, atLeast(1)).getAttributeById(17L);
        assertEquals(response, objectMapper.writeValueAsString(attributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void getAttributeById_givenInvalidId_thenExpectOkWithBody() {
        when(attributeService.getAttributeById(17L)).thenThrow(DataNotFoundException.class);

        mockMvc.perform(get("/attributes/17"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(attributeService, atLeast(1)).getAttributeById(17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void postAttribute_givenValidRequest_thenExpect201WithBody() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("NewAttribute").build();
        AttributeDtoResponse attributeDtoResponse = AttributeDtoResponse.builder().id(17L).name("NewAttribute").build();
        when(attributeService.createAttribute(attributeDtoRequest)).thenReturn(attributeDtoResponse);

        String response = mockMvc.perform(post("/attributes")
                        .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(attributeService, atLeast(1)).createAttribute(attributeDtoRequest);
        assertEquals(response, objectMapper.writeValueAsString(attributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void postAttribute_givenAttributeNameNULL_thenExpectBadRequest() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name(null).build();

        mockMvc.perform(post("/attributes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void postAttribute_givenAttributeNameEMPTY_thenExpectBadRequest() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("").build();

        mockMvc.perform(post("/attributes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void postAttribute_givenAttributeNameOver100chars_thenExpectBadRequest() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder()
                .name(String.format("%101c", '1')).build();

        mockMvc.perform(post("/attributes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void patchAttribute_givenValidRequest_thenExpectOkWithBody() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("NewAttribute").build();
        AttributeDtoResponse attributeDtoResponse = AttributeDtoResponse.builder().id(17L).name("NewAttribute").build();
        when(attributeService.patchAttribute(attributeDtoRequest, 17L)).thenReturn(attributeDtoResponse);

        String response = mockMvc.perform(patch("/attributes/17")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(attributeService, atLeast(1)).patchAttribute(attributeDtoRequest, 17L);
        assertEquals(response, objectMapper.writeValueAsString(attributeDtoResponse));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void patchAttribute_givenInvalidId_thenExpectNotFound() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("NewAttribute").build();
        when(attributeService.patchAttribute(attributeDtoRequest, 17L)).thenThrow(DataNotFoundException.class);

        mockMvc.perform(patch("/attributes/17")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(attributeService, atLeast(1)).patchAttribute(attributeDtoRequest, 17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void patchAttribute_givenAttributeNameNULL_thenExpectBadRequest() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name(null).build();

        mockMvc.perform(patch("/attributes/17")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void patchAttribute_givenAttributeNameBlank_thenExpectBadRequest() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("").build();

        mockMvc.perform(patch("/attributes/17")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void patchAttribute_givenAttributeNameOver100Chars_thenExpectBadRequest() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder()
                .name(String.format("%101c", '1')).build();

        mockMvc.perform(patch("/attributes/17")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attributeDtoRequest)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void deleteAttribute_givenValidId_thenExpect204() {
        doNothing().when(attributeService).deleteAttribute(17L);

        mockMvc.perform(delete("/attributes/17"))
                .andExpect(status().is(204))
                .andReturn()
                .getResponse();

        verify(attributeService, atLeast(1)).deleteAttribute(17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void deleteAttribute_givenInvalidId_thenExpect204() {
        doThrow(DataNotFoundException.class).when(attributeService).deleteAttribute(17L);

        mockMvc.perform(delete("/attributes/17"))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        verify(attributeService, atLeast(1)).deleteAttribute(17L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void findAttributes_givenValidData_thenExpectOk() {
        List<AttributeDtoResponse> attributeDtoResponseList =
                List.of(AttributeDtoResponse.builder().id(17L).name("name").build());
        when(attributeService.findAttributes("na", 0 ,10)).thenReturn(attributeDtoResponseList);

        String response = mockMvc.perform(get("/attributes?text=na"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(attributeService, atLeast(1)).findAttributes("na", 0, 10);
        assertEquals(response, objectMapper.writeValueAsString(attributeDtoResponseList));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin@mail.ru", authorities = "ROLE_ADMIN")
    public void findAttributes_givenInvalidFromParam_thenExpectBadRequest() {
        mockMvc.perform(get("/attributes?text=na&from=-10&size=-1"))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();

    }

}

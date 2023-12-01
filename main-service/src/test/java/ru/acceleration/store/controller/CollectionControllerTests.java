package ru.acceleration.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.acceleration.store.dto.collection.CollectionDto;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class CollectionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    CollectionDto collectionCreateDto = new CollectionDto(null, "Компьютеры со скидкой до -30%", "https://test.ru/link");
    CollectionDto collectionCreateDtoWithEmptyName = new CollectionDto(null, "", "https://test.ru/link");
    CollectionDto collectionCreateDtoWithEmptyLink = new CollectionDto(null, "Компьютеры со скидкой до -30%", null);
    CollectionDto collectionEditDto = new CollectionDto(null, "Телефоны со скидкой до -50%", "https://test.ru/link");

    @Test
    void shouldAddNewCollection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/collections")
                        .content(mapper.writeValueAsString(collectionCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Компьютеры со скидкой до -30%"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageLink").value("https://test.ru/link"));
    }

    @Test
    void postCollectionShouldReturnBadRequestWhenNameIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/collections")
                        .content(mapper.writeValueAsString(collectionCreateDtoWithEmptyName))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postCollectionShouldReturnBadRequestWhenImageLinkIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/collections")
                        .content(mapper.writeValueAsString(collectionCreateDtoWithEmptyLink))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldEditCollection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/collections")
                .content(mapper.writeValueAsString(collectionCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/collections/1")
                        .content(mapper.writeValueAsString(collectionEditDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Телефоны со скидкой до -50%"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageLink").value("https://test.ru/link"));
    }

    @Test
    void shouldDeleteCollection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/collections")
                .content(mapper.writeValueAsString(collectionCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/collections/1"))
                .andExpect(status().isNoContent());
    }
}

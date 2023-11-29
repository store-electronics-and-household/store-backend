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

    CollectionDto promotionCreateDto = new CollectionDto(null, "Компьютеры со скидкой до -30%", null);
    CollectionDto promotionCreateDtoWithEmptyName = new CollectionDto(null, "", null);
    CollectionDto promotionEditDto = new CollectionDto(null, "Телефоны со скидкой до -50%", null);

    @Test
    void postPromotionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/promotions")
                        .content(mapper.writeValueAsString(promotionCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Компьютеры со скидкой до -30%"));
    }

    @Test
    void postPromotionWithEmptyNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/promotions")
                        .content(mapper.writeValueAsString(promotionCreateDtoWithEmptyName))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void editPromotionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/promotions")
                .content(mapper.writeValueAsString(promotionCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/promotions/2")
                        .content(mapper.writeValueAsString(promotionEditDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Телефоны со скидкой до -50%"));
    }

    @Test
    void deletePromotionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/promotions")
                .content(mapper.writeValueAsString(promotionCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/promotions/1"))
                .andExpect(status().isNoContent());
    }
}

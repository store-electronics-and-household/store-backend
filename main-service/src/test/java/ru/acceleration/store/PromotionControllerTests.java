package ru.acceleration.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.acceleration.store.dto.PromotionDto;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class PromotionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    PromotionDto promotionCreateDto = new PromotionDto(null, "Компьютеры со скидкой до -30%", null);
    PromotionDto promotionCreateDtoWithEmptyName = new PromotionDto(null, "", null);
    PromotionDto promotionEditDto = new PromotionDto(null, "Телефоны со скидкой до -50%", null);

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
    void getPromotionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/promotions")
                .content(mapper.writeValueAsString(promotionCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/promotions/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Компьютеры со скидкой до -30%"));
    }

    @Test
    void editPromotionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/promotions")
                .content(mapper.writeValueAsString(promotionCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/promotions/1")
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
                        .delete("/promotions/2"))
                .andExpect(status().isNoContent());
    }
}

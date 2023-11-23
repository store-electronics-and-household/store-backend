package ru.acceleration.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.acceleration.store.dto.NewProductDto;
import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.PromotionDto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class SaleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    NewSaleDto saleCreateDto = new NewSaleDto("Такая вот скидка", "200", null);
    NewSaleDto saleCreateDtoWithPromotion = new NewSaleDto("Такая вот скидка в подборке", "200", 1L);
    NewProductDto productCreateDto = new NewProductDto("XY73GS33", "Apple iPhone 13 Pro Max 256GB");
    PromotionDto promotionCreateDto = new PromotionDto(null, "Компьютеры со скидкой до -30%", null);


    @Test
    void postSaleWithNullProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/product/2/sale")
                        .content(mapper.writeValueAsString(saleCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void postProductTestWithoutPromotion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(mapper.writeValueAsString(productCreateDto))
                        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/product/1/sale")
                        .content(mapper.writeValueAsString(saleCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Такая вот скидка"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.promotionId").doesNotExist());
    }

    @Test
    void postProductTestWithPromotion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .content(mapper.writeValueAsString(productCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/promotions")
                .content(mapper.writeValueAsString(promotionCreateDto))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/product/1/sale")
                        .content(mapper.writeValueAsString(saleCreateDtoWithPromotion))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Такая вот скидка в подборке"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.promotionId").value(1));
    }
}

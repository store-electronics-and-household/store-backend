package ru.acceleration.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.dto.sale.NewSaleDto;
import ru.acceleration.store.dto.sale.UpdateSaleDto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class SaleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    NewSaleDto newSaleDto = new NewSaleDto(1L, 30);
    NewSaleDto newSaleDtoWithNullModel = new NewSaleDto(null, 30);
    NewSaleDto newSaleDtoWithModelWhichDoesntExist = new NewSaleDto(3L, 30);
    NewSaleDto newSaleDtoWithNullPercent = new NewSaleDto(1L, null);
    UpdateSaleDto updateSaleDto = new UpdateSaleDto(10);
    NewModelDto modelCreateDto = new NewModelDto("XY73GS33", "Apple iPhone 13 Pro Max 256GB", 100L);


//    @Test
//    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
//    void postSaleShouldReturnBadRequestWithNullModel() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/sale")
//                        .content(mapper.writeValueAsString(newSaleDtoWithNullModel))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void postSaleShouldReturnBadRequestWithNullPercent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/sale")
                        .content(mapper.writeValueAsString(newSaleDtoWithNullPercent))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
//    void postSaleShouldReturnNotFoundWhenModelDoesntExist() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/sale")
//                        .content(mapper.writeValueAsString(newSaleDtoWithModelWhichDoesntExist))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void postSaleShouldAddOnlyOneSaleForOneModel() throws Exception {
/*        mockMvc.perform(MockMvcRequestBuilders
                        .post("/models/1/model")
                        .content(mapper.writeValueAsString(modelCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/sale")
                        .content(mapper.writeValueAsString(newSaleDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelId").value(newSaleDto.getModelId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.percent").value(newSaleDto.getPercent()));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/sale")
                        .content(mapper.writeValueAsString(newSaleDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());*/
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void shouldEditSale() throws Exception {
/*        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/sale/1")
                        .content(mapper.writeValueAsString(updateSaleDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.percent").value(10));*/
    }
}

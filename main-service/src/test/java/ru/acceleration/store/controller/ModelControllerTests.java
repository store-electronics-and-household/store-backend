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
import ru.acceleration.store.dto.model.NewModelDto;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class ModelControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    NewModelDto productCreateDto = new NewModelDto("XY73GS33", "Apple iPhone 13 Pro Max 256GB");
    NewModelDto productCreateDtoWithEmptyName = new NewModelDto("XY73GS33", "");
    NewModelDto productCreateDtoWithEmptyVendorCode = new NewModelDto("", "Apple iPhone 13 Pro Max 256GB");

    @Test
    void postProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(mapper.writeValueAsString(productCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendorCode").value("XY73GS33"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Apple iPhone 13 Pro Max 256GB"));
    }

    @Test
    void postProductWithEmptyNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(mapper.writeValueAsString(productCreateDtoWithEmptyName))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postProductWithEmptyVendorCodeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(mapper.writeValueAsString(productCreateDtoWithEmptyVendorCode))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}


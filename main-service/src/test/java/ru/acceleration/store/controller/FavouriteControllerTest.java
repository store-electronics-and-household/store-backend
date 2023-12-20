package ru.acceleration.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.dto.favourite.FavouriteResponseDto;
import ru.acceleration.store.dto.model.ModelShortDto;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql({"/schema-test.sql", "/data-for-basket-tests.sql","/data-for-favorite-tests.sql"})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class FavouriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    @SneakyThrows
    void addFavoriteModel() {
        Long modelId = 1L;
        Long modelId2 = 2L;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/favourite/add")
                        .param("modelId",modelId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/favourite/add")
                        .param("modelId",modelId2.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/favourite/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        FavouriteResponseDto dto = mapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                FavouriteResponseDto.class);
        Iterator<ModelShortDto> itr = dto.getModelShortDtos().iterator();
        if (itr.hasNext()) {
            ModelShortDto modelShortDto = itr.next();
            Assertions.assertAll(
                    () -> Assertions.assertEquals(dto.getModelShortDtos().size(), 2),
                    () -> Assertions.assertEquals(modelShortDto.getDescription(), "ModelTwoDesc")
            );
        } else {
            new RuntimeException();
        }
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    @SneakyThrows
    void deleteFavoriteModel() {
        Long modelId = 1L;
        Long modelId2 = 2L;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/favourite/add")
                        .param("modelId",modelId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/favourite/add")
                        .param("modelId",modelId2.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/favourite/delete")
                        .param("modelId",modelId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/favourite/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        FavouriteResponseDto dto = mapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                FavouriteResponseDto.class);
        Iterator<ModelShortDto> itr = dto.getModelShortDtos().iterator();
        if (itr.hasNext()) {
            ModelShortDto modelShortDto = itr.next();
            Assertions.assertAll(
                    () -> Assertions.assertEquals(dto.getModelShortDtos().size(), 1),
                    () -> Assertions.assertEquals(modelShortDto.getDescription(), "ModelTwoDesc")
            );
        } else {
            new RuntimeException();
        }
    }
}
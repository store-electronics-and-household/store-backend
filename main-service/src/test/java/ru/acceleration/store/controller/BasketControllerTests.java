package ru.acceleration.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.dto.product.ProductFullDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.service.basket.BasketService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class BasketControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    BasketService basketServiceMock;

    private BasketResponseDto basketResponseDto1;
    private BasketResponseDto basketResponseDto2;
    private BasketResponseDto basketResponseDto3;

    @BeforeEach
    void beforeEach() {
        ProductFullDto firstProductCreateDto = new ProductFullDto(1L, "XY73GS33", "Apple iPhone 13 Pro Max 256GB", "1000");
        ProductFullDto secondProductCreateDto = new ProductFullDto(2L, "YY735S3HG", "Apple iPhone 18 Pro Min 128GB", "2000");
        ProductFullDto thirdProductCreateDto = new ProductFullDto(3L, "YT738BB93NHG", "Apple iPhone 4000 Pro Ultra 1TB", "13900");
        List<ProductFullDto> firstUserProductList = new ArrayList<>();
        firstUserProductList.add(firstProductCreateDto);
        firstUserProductList.add(thirdProductCreateDto);
        List<ProductFullDto> secondUserProductList = new ArrayList<>();
        secondUserProductList.add(firstProductCreateDto);
        secondUserProductList.add(secondProductCreateDto);
        secondUserProductList.add(thirdProductCreateDto);
        List<ProductFullDto> thirdUserProductList = new ArrayList<>();
        thirdUserProductList.add(firstProductCreateDto);
        thirdUserProductList.add(thirdProductCreateDto);
        thirdUserProductList.remove(firstProductCreateDto);
        basketResponseDto1 = new BasketResponseDto(1L, 1L, firstUserProductList);
        basketResponseDto2 = new BasketResponseDto(2L, 2L, secondUserProductList);
        basketResponseDto3 = new BasketResponseDto(1L, 1L, thirdUserProductList);
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void addProductsToBaskets() throws Exception {
        Mockito.when(basketServiceMock.addProductToBasket(1L, 1L))
                .thenReturn(basketResponseDto1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/basket/add/1?userId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].name").value("Apple iPhone 13 Pro Max 256GB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].vendorCode").value("XY73GS33"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].id").value(1));
        Mockito.when(basketServiceMock.addProductToBasket(3L, 1L))
                .thenReturn(basketResponseDto1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/basket/add/3?userId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].name").value("Apple iPhone 4000 Pro Ultra 1TB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].vendorCode").value("YT738BB93NHG"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()").value(2));
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void addProductsToSecondUserBasket() throws Exception {
        Mockito.when(basketServiceMock.addProductToBasket(1L, 2L))
                .thenReturn(basketResponseDto2);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/basket/add/1?userId=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].name").value("Apple iPhone 13 Pro Max 256GB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].vendorCode").value("XY73GS33"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].id").value(1));
        Mockito.when(basketServiceMock.addProductToBasket(2L, 2L))
                .thenReturn(basketResponseDto2);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/basket/add/2?userId=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].name").value("Apple iPhone 18 Pro Min 128GB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].vendorCode").value("YY735S3HG"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].id").value(2));
        Mockito.when(basketServiceMock.addProductToBasket(3L, 2L))
                .thenReturn(basketResponseDto2);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/basket/add/3?userId=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[2].name").value("Apple iPhone 4000 Pro Ultra 1TB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[2].vendorCode").value("YT738BB93NHG"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()").value(3));
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void deleteProductFromUserBasket() throws Exception {
        Mockito.when(basketServiceMock.removeProductFromBasket(1L, 1L))
                .thenReturn(basketResponseDto3);
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/basket/remove/1?basketId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()").value(1));
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void getUserBasket() throws Exception {
        Mockito.when(basketServiceMock.getBasket(2L))
                .thenReturn(basketResponseDto2);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/basket/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[2].vendorCode").value("YT738BB93NHG"));

    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void addProductWithUserNotFound() throws Exception {
        Mockito.when(basketServiceMock.addProductToBasket(18L, 1L))
                .thenThrow(DataNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/basket/add/18?userId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "email@bk.ru", authorities = "ROLE_USER")
    void addProductWithProductNotFound() throws Exception {
        Mockito.when(basketServiceMock.addProductToBasket(1L, 18L))
                .thenThrow(DataNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/basket/add/1?userId=18")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

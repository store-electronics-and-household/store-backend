//package ru.acceleration.store.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import ru.acceleration.store.dto.sale.NewSaleDto;
//import ru.acceleration.store.dto.sale.SaleDto;
//import ru.acceleration.store.exceptions.DataNotFoundException;
//import ru.acceleration.store.service.sale.SaleService;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//@RequiredArgsConstructor
//public class SaleControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @MockBean
//    private SaleService saleServiceMock;
//
//    private NewSaleDto newSaleDto;
//    private NewSaleDto newSaleDtoWithNullProduct;
//    private SaleDto saleDto;
//    private NewSaleDto newSaleDtoWithPromotionDoesntExist;
//    private NewSaleDto newSaleDtoWithoutPromotion;
//    private SaleDto saleDtoWithoutPromotion;
//
//    @BeforeEach
//    void beforeEach() {
//        newSaleDto = new NewSaleDto("Такая вот скидка в подборке", "200", 1L);
//        newSaleDtoWithNullProduct = new NewSaleDto("Такая вот скидка", "200", 1L);
//        saleDto = new SaleDto(1L, "Такая вот скидка в подборке", "200", 1L, 1L);
//        newSaleDtoWithPromotionDoesntExist = new NewSaleDto("Такая вот скидка", "200", 1000L);
//        newSaleDtoWithoutPromotion = new NewSaleDto("Такая вот скидка", "200", null);
//        saleDtoWithoutPromotion = new SaleDto(1L, "Такая вот скидка", "200", 2L, null);
//    }
//
//    @Test
//    void postSaleWithPromotion() throws Exception {
//        Mockito.when(saleServiceMock.addSale(1L, this.newSaleDto)).thenReturn(saleDto);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/product/1/sale")
//                        .content(mapper.writeValueAsString(saleDto))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Такая вот скидка в подборке"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("200"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.promotionId").value(1));
//    }
//
//    @Test
//    void postSaleWithNullProduct() throws Exception {
//        Mockito.when(saleServiceMock.addSale(4L, newSaleDtoWithNullProduct)).thenThrow(DataNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/product/4/sale")
//                        .content(mapper.writeValueAsString(newSaleDtoWithNullProduct))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void postSaleWithoutPromotion() throws Exception {
//        Mockito.when(saleServiceMock.addSale(2L, newSaleDtoWithoutPromotion)).thenReturn(saleDtoWithoutPromotion);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/product/2/sale")
//                        .content(mapper.writeValueAsString(saleDtoWithoutPromotion))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Такая вот скидка"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("200"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(2))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.promotionId").doesNotExist());
//    }
//
//    @Test
//    void postSaleWithPromotionWhichDoesntExist() throws Exception {
//        Mockito.when(saleServiceMock.addSale(2L, newSaleDtoWithPromotionDoesntExist)).thenThrow(DataNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/product/2/sale")
//                        .content(mapper.writeValueAsString(newSaleDtoWithPromotionDoesntExist))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//}

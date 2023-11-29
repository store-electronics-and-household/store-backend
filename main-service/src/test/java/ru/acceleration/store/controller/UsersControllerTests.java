package ru.acceleration.store.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.acceleration.store.dto.user.UserRequestDto;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class UsersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private UserRequestDto userRequestDto;
    private UserRequestDto userRequestDtoWithEmptyName;
    private UserRequestDto userRequestDtoWithEmptyPassword;
    private UserRequestDto userRequestDtoWithEmptyEmail;
    private UserRequestDto userRequestDtoWithIncorrectEmail;

    @BeforeEach()
    void beforeEach() {
        userRequestDto = new UserRequestDto("user", "user123@mail.com","user");
        userRequestDtoWithEmptyName = new UserRequestDto("", "user123@mail.com","user");
        userRequestDtoWithEmptyPassword = new UserRequestDto("user", "user123@mail.com","");
        userRequestDtoWithEmptyEmail = new UserRequestDto("user", "","user");
        userRequestDtoWithIncorrectEmail = new UserRequestDto("user", "user123mail.com","user");
    }

    @Test
    void postUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("user"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("user123@mail.com"))
                .andExpect(status().isCreated());
    }

    @Test
    void postUserWithEmptyNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userRequestDtoWithEmptyName))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postUserWithEmptyPasswordTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userRequestDtoWithEmptyPassword))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postUserWithEmptyEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userRequestDtoWithEmptyEmail))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postUserWithIncorrectEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userRequestDtoWithIncorrectEmail))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

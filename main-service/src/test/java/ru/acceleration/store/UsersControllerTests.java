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
import ru.acceleration.store.dto.user.UserCreateDto;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class UsersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    UserCreateDto userCreateDto = new UserCreateDto("user", "user123@mail.com","user");
    UserCreateDto userCreateDtoWithEmptyName = new UserCreateDto("", "user123@mail.com","user");
    UserCreateDto userCreateDtoWithEmptyPassword = new UserCreateDto("user", "user123@mail.com","");
    UserCreateDto userCreateDtoWithEmptyEmail = new UserCreateDto("user", "","user");
    UserCreateDto userCreateDtoWithIncorrectEmail = new UserCreateDto("user", "user123mail.com","user");


    @Test
    void postUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("user"));
    }

    @Test
    void postUserWithEmptyNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userCreateDtoWithEmptyName))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postUserWithEmptyPasswordTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userCreateDtoWithEmptyPassword))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postUserWithEmptyEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userCreateDtoWithEmptyEmail))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postUserWithIncorrectEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(mapper.writeValueAsString(userCreateDtoWithIncorrectEmail))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

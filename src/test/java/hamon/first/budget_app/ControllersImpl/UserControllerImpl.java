package hamon.first.budget_app.ControllersImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hamon.first.budget_app.controllers.UserController;
import hamon.first.budget_app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(UserController.class)
public class UserControllerImpl {

    @MockBean
    private UserService workerService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }


    @Test
    void getAllWallets() throws Exception {
        mockMvc.perform(get("/users/wallets"))
                .andExpect(status().isCreated());
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/users/create"))
                .andExpect(status().isAccepted());
    }

    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/users/registration"))
                .andExpect(status().isOk());
    }



}

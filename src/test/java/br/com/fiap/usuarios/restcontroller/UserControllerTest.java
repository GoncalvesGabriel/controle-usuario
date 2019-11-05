package br.com.fiap.usuarios.restcontroller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import br.com.fiap.usuarios.entity.Account;
import br.com.fiap.usuarios.entity.User;
import br.com.fiap.usuarios.entity.vo.UserVo;
import br.com.fiap.usuarios.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean()
    private UserService service;

    @Test
    public void createUser() throws Exception {
        User savedResult = User.builder()
            .id(2L)
            .build();
        Mockito.when(this.service.createUser(any())).thenReturn(savedResult);

        String exampleUserJson = "{"
                + "  \"balance\": 5120093.02,\n"
                + "  \"cpfCnpj\": \"12345678911\",\n"
                + "  \"email\": \"gabriel.goncalves@silva.com.br\",\n"
                + "  \"name\": \"Gabes custodiador\"\n"
                + "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/user/create")
            .accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
            .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("2", response.getContentAsString());
    }

    @Test
    public void addAccountToUser() throws Exception {
        Account savedAccount = Account.builder()
            .id(2L)
            .build();
        Mockito.when(this.service.addAccountToUser(any())).thenReturn(savedAccount);

        String exampleAccountJson = "{"
            + "  \"agencyDigit\": \"1\",\n"
            + "  \"agencyNumber\": \"1234\",\n"
            + "  \"digit\": \"2\",\n"
            + "  \"number\": \"12345\",\n"
            + "  \"numberBank\": \"33\",\n"
            + "  \"userId\": 1\n"
            + "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/user/addAccount")
            .accept(MediaType.APPLICATION_JSON).content(exampleAccountJson)
            .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("2", response.getContentAsString());
    }

    @Test
    public void findByCpfCnpj() throws Exception {
        UserVo expected = UserVo.builder()
            .cpfCnpj("12345678911")
            .name("Gabes arduineiro")
            .balance(9999999d)
            .build();

        Mockito.when(this.service.findUserbyCpfCnpj("12345678911")).thenReturn(expected);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
            "/user/byCpfCnpj").accept(
            MediaType.APPLICATION_JSON).param("cpfCnpj", "12345678911");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        UserVo actual = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
                                                     UserVo.class);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected.getCpfCnpj(), actual.getCpfCnpj());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getBalance(), actual.getBalance());
    }
}
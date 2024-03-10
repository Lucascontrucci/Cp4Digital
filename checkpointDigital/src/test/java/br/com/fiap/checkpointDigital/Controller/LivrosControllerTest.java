package br.com.fiap.checkpointDigital.Controller;

import br.com.fiap.checkpointDigital.Model.Livros;
import br.com.fiap.checkpointDigital.Service.LivroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivrosController.class)
public class LivrosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;


    @Test
    public void testOrderByYear() throws Exception {
        List<Livros> livros = new ArrayList<>();
        given(livroService.orderByYear()).willReturn(livros);
        mockMvc.perform(get("/livros/orderByYear"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFindByTitle() throws Exception {
        List<Livros> livros = new ArrayList<>();
        given(livroService.findByTitle("Game of Thrones")).willReturn(livros);
        mockMvc.perform(get("/livros/findByTitle").param("titulo", "Game of Thrones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}

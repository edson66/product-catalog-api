package api.catalog.api.controllers;

import api.catalog.api.domain.dadosCategoria.Categoria;
import api.catalog.api.domain.dadosCategoria.CategoriaRepository;
import api.catalog.api.domain.dadosProduto.Produto;
import api.catalog.api.domain.dadosProduto.ProdutoRepository;
import api.catalog.api.domain.dadosUsuario.UsuarioRepository;
import api.catalog.api.security.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProdutoController.class)
@WithMockUser
class ProdutoControllerTest {

    @MockitoBean
    private ProdutoRepository produtoRepository;

    @MockitoBean
    private CategoriaRepository categoriaRepository;

    @MockitoBean
    private TokenService service;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MockMvc mvc;


    @Test
    @DisplayName("Deve retornar 200 OK e o produto ao buscar por id existente")
    void buscarPorId() throws Exception {

        var categoria = new Categoria(1L,"Eletrônico");
        var produto = new Produto(1L,"Computador","Bom estado",new BigDecimal(1000),2,categoria);

        when(produtoRepository.getReferenceById(1L)).thenReturn(produto);


        var request = get("/produtos/1");
        var response = mvc.perform(request);

        response.andExpect(status().isOk());

        response.andExpect(jsonPath("$.id").value(1L));
        response.andExpect(jsonPath("$.nome").value("Computador"));
        response.andExpect(jsonPath("$.categoria").value("Eletrônico"));
    }

}
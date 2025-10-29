package api.catalog.api.domain.dadosUsuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    public void inicializar(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("deve retornar senha codificada")
    void codificarSenha() {
        var senha = "senhateste";
        var senhaEsperada = "senhaEsperada";

        when(encoder.encode(senha)).thenReturn(senhaEsperada);

        var resultado = service.codificarSenha(senha);

        assertEquals(senhaEsperada,resultado);

        verify(encoder,times(1)).encode(senha);
    }
}
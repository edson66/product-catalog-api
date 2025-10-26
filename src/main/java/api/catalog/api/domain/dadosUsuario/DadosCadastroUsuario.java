package api.catalog.api.domain.dadosUsuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}

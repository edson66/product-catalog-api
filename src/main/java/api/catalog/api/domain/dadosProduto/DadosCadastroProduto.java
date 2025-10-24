package api.catalog.api.domain.dadosProduto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DadosCadastroProduto(
        @NotBlank
        String nome,
        String descricao,
        @NotNull
        @Positive
        BigDecimal valor,
        @NotNull
        @Positive
        Integer estoque,
        @NotBlank
        String categoria
) {
}

package api.catalog.api.domain.dadosProduto;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DadosAtualizacaoProdutos(
        String nome,
        String descricao,
        @Positive
        BigDecimal valor,
        @Positive
        Integer estoque,
        String categoria
) {
}

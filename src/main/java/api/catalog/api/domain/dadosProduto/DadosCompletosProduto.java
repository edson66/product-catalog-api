package api.catalog.api.domain.dadosProduto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCompletosProduto(
        @NotNull
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        Integer estoque,
        String categoria
) {
    public DadosCompletosProduto(Produto produto) {
        this(produto.getId(),produto.getNome(),produto.getDescricao(),produto.getValor(),
                produto.getEstoque(), produto.getCategoria().getNome());

    }
}

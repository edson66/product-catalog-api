package api.catalog.api.domain.dadosProduto;


import api.catalog.api.domain.dadosCategoria.Categoria;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "produtos")
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Integer estoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto(@Valid DadosCadastroProduto dados,Categoria categoria) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.estoque = dados.estoque();
        this.categoria = categoria;
    }

    public void atualizarInformacoes(DadosAtualizacaoProdutos dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if (dados.valor() != null){
            this.valor = dados.valor();
        }
        if (dados.estoque() != null){
            this.estoque = dados.estoque();
        }

    }

    public void atualizarInformacoes(DadosAtualizacaoProdutos dados,Categoria categoria) {
        this.atualizarInformacoes(dados);
        this.categoria = categoria;
    }
}

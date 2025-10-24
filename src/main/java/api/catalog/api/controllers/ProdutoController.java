package api.catalog.api.controllers;

import api.catalog.api.domain.dadosCategoria.Categoria;
import api.catalog.api.domain.dadosCategoria.CategoriaRepository;
import api.catalog.api.domain.dadosProduto.DadosCadastroProduto;

import api.catalog.api.domain.dadosProduto.DadosCompletosProduto;
import api.catalog.api.domain.dadosProduto.Produto;
import api.catalog.api.domain.dadosProduto.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity postarProduto(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder builder){
        var categoria = categoriaRepository.findByNome(dados.categoria());
        if (categoria == null){
            var novaCategoria = new Categoria(dados.categoria());
            categoria = categoriaRepository.save(novaCategoria);
        }
        var produto = new Produto(dados,categoria);
        produtoRepository.save(produto);

        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        var dto = new DadosCompletosProduto(produto);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosCompletosProduto>> read(@PageableDefault(sort = {"id"},size = 20) Pageable pageable){
        var listaDeProdutos = produtoRepository.findAll(pageable).map(DadosCompletosProduto::new);

        return ResponseEntity.ok(listaDeProdutos);
    }
}

package api.catalog.api.controllers;

import api.catalog.api.domain.dadosCategoria.Categoria;
import api.catalog.api.domain.dadosCategoria.CategoriaRepository;
import api.catalog.api.domain.dadosProduto.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var produto = produtoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosCompletosProduto(produto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarDados(@PathVariable Long id, @RequestBody DadosAtualizacaoProdutos dados){
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
        if (dados.categoria() != null){
            var categoria = categoriaRepository.findByNome(dados.categoria());
            if (categoria == null){
                categoria = categoriaRepository.save(new Categoria(dados.categoria()));
            }
            produto.atualizarInformacoes(dados,categoria);
        }else{
            produto.atualizarInformacoes(dados);
        }

        return ResponseEntity.ok().body(new DadosCompletosProduto(produto));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarProduto (@PathVariable Long id){
        var produto = produtoRepository.getReferenceById(id);

        produtoRepository.delete(produto);

        return ResponseEntity.noContent().build();
    }
}

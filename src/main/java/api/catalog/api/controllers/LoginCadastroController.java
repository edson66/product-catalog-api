package api.catalog.api.controllers;

import api.catalog.api.domain.dadosUsuario.DadosCadastroUsuario;
import api.catalog.api.domain.dadosUsuario.Usuario;
import api.catalog.api.domain.dadosUsuario.UsuarioRepository;
import api.catalog.api.domain.dadosUsuario.UsuarioService;
import api.catalog.api.security.TokenDto;
import api.catalog.api.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class LoginCadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados){
        String senhaCodificada = usuarioService.codificarSenha(dados.senha());
        var usuario = new Usuario(dados.login(),senhaCodificada);
        repository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity fazerLogin(@RequestBody @Valid DadosCadastroUsuario dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var usuarioAutenticado = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok().body(new TokenDto(tokenJWT));
    }
}

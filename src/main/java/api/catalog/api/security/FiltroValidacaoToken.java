package api.catalog.api.security;

import api.catalog.api.domain.dadosUsuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroValidacaoToken extends OncePerRequestFilter {

    @Autowired
    private TokenService service;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = pegarToken(request);

        if (tokenJWT != null){
            var login = service.validarToken(tokenJWT);
            var usuario = repository.findByLogin(login);

            var atenticacao = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(atenticacao);
        }


        filterChain.doFilter(request,response);
    }

    private String pegarToken(HttpServletRequest request) {
        var token =  request.getHeader("Authorization");

        if (token != null){
            return token.replace("Bearer ","");
        }

        return null;
    }
}

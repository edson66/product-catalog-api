package api.catalog.api.security;

import api.catalog.api.domain.dadosUsuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${senha.secreta}")
    private String secreta;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreta);
            return JWT.create()
                    .withIssuer("apiCatalogo")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT " + exception);
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreta);
            return JWT.require(algorithm)
                    .withIssuer("apiCatalogo")
                    .build()
                    .verify(token)
                    .getSubject()
                    ;


        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT Inválido " + exception);
        }
    }
}

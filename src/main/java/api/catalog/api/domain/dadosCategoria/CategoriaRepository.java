package api.catalog.api.domain.dadosCategoria;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Categoria findByNome(@NotBlank String categoria);
}

package api.catalog.api.domain.dadosCategoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;



import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve retornar a Categoria correta ao buscar por nome")
    void findByNomeCenario1() {

        var categoria = new Categoria("Eletrônicos");
        em.persistAndFlush(categoria);

        var categoriaBuscada = repository.findByNome("Eletrônicos");

        assertEquals("Eletrônicos",categoriaBuscada.getNome());
        assertEquals(categoria.getId(),categoriaBuscada.getId());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar por nome que não existe")
    void findByNomeCenario2() {
        var categoria = repository.findByNome("Leitura");

        assertNull(categoria);
    }
}
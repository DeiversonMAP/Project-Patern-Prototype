package org.example.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PerfilImpressaoTest {

    private PerfilImpressao petg;

    @BeforeEach
    void montarPerfil() {
        petg = new PerfilImpressao("PETG resistente", 240, 80, 50, 200);
        petg.adicionarObservacao("Secar o filamento antes");
    }

    @Test
    void deveDescreverOPerfil() {
        assertEquals("PETG resistente: bico 240C, mesa 80C, 50 mm/s, camada 200 um", petg.descricao());
    }

    @Test
    void cloneDeveTerOsMesmosValoresEOutraInstancia() {
        PerfilImpressao copia = petg.clonar();

        assertNotSame(petg, copia);
        assertEquals(petg.descricao(), copia.descricao());
        assertEquals(Arrays.asList("Secar o filamento antes"), copia.getObservacoes());
    }

    @Test
    void alterarOCloneNaoDeveAlterarOOriginal() {
        PerfilImpressao copia = petg.clonar();
        copia.setNome("PETG lento");
        copia.setVelocidadeMmPorSegundo(30);
        copia.setTemperaturaBico(245);

        assertEquals("PETG resistente", petg.getNome());
        assertEquals(50, petg.getVelocidadeMmPorSegundo());
        assertEquals(240, petg.getTemperaturaBico());
    }

    @Test
    void copiaDeveSerProfundaNaListaDeObservacoes() {
        PerfilImpressao copia = petg.clonar();
        copia.adicionarObservacao("Usar brim");

        assertEquals(2, copia.getObservacoes().size());
        assertEquals(1, petg.getObservacoes().size());
    }

    @Test
    void listaDeObservacoesNaoDeveSerModificavelPorFora() {
        assertThrows(UnsupportedOperationException.class, () -> petg.getObservacoes().clear());
    }
}

package org.example.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistroPerfisTest {

    private RegistroPerfis registro;

    @BeforeEach
    void montarRegistro() {
        registro = new RegistroPerfis();
        registro.registrar("pla-rapido", new PerfilImpressao("PLA rapido", 210, 60, 100, 280));
        registro.registrar("petg", new PerfilImpressao("PETG resistente", 240, 80, 50, 200));
    }

    @Test
    void deveContarPerfisRegistrados() {
        assertEquals(2, registro.quantidade());
    }

    @Test
    void deveCriarPerfilAPartirDoModelo() {
        PerfilImpressao perfil = registro.criar("pla-rapido");

        assertEquals("PLA rapido: bico 210C, mesa 60C, 100 mm/s, camada 280 um", perfil.descricao());
    }

    @Test
    void cadaPedidoDeveReceberUmCloneProprio() {
        PerfilImpressao primeiro = registro.criar("petg");
        PerfilImpressao segundo = registro.criar("petg");

        assertNotSame(primeiro, segundo);
    }

    @Test
    void alterarUmCloneNaoDeveAfetarOsProximos() {
        PerfilImpressao primeiro = registro.criar("petg");
        primeiro.setVelocidadeMmPorSegundo(20);
        primeiro.adicionarObservacao("Peca fragil");

        PerfilImpressao segundo = registro.criar("petg");

        assertEquals(50, segundo.getVelocidadeMmPorSegundo());
        assertEquals(0, segundo.getObservacoes().size());
    }

    @Test
    void alterarOObjetoRegistradoNaoDeveAfetarOModelo() {
        PerfilImpressao original = new PerfilImpressao("ABS", 250, 100, 60, 200);
        registro.registrar("abs", original);

        original.setTemperaturaBico(999);

        assertEquals(250, registro.criar("abs").getTemperaturaBico());
    }

    @Test
    void deveLancarExcecaoParaChaveDesconhecida() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> registro.criar("resina")
        );
        assertEquals("Perfil nao registrado: resina", excecao.getMessage());
    }
}

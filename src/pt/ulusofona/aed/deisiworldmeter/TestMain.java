package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestMain {

    @Test
    public void testConversaoParaStringPaisesComIdMenorQue700() {

        Paises pais = new Paises(620, "pt", "prt", "Portugal");
        String expected = "Portugal | 620 | PT | PRT";
        assertEquals(expected, pais.toString());
    }


    @Test
    public void testConversaoParaStringPaisesComIdMaiorQue700() {

        Paises pais = new Paises(724, "es", "esp", "Espanha");
        pais.setNumOcorrenciasPopulacao(8);
        String expected = "Espanha | 724 | ES | ESP | 8";
        assertEquals(expected, pais.toString());

    }

    @Test
    public void testConversaoParaStringCidades() {

        Cidades cidade = new Cidades("pt", "barreiro", "19", 51279, 38.663137,-9.072395);
        String expected = "barreiro | PT | 19 | 51279 | (38.663137,-9.072395)";
        assertEquals(expected, cidade.toString());

    }

    @Test
    public void testLeituraDeArquivosSemErros() {
        // Mock do ambiente
        File pastaTeste = new File("test-files");

        // Teste
        assertTrue(Main.parseFiles(pastaTeste));
        ArrayList<String> objetosPaises = Main.getObjects(TipoEntidade.PAIS);
        ArrayList<String> objetosCidades = Main.getObjects(TipoEntidade.CIDADE);

        // Verificação
        assertEquals("[Brasil | 76 | BR | BRA, Portugal | 620 | PT | PRT, Angola | 24 | AO | AGO, Cabo-Verde | 132 | CV | CPV, Moçambique | 508 | MZ | MOZ, Espanha | 724 | ES | ESP | 8]", objetosPaises.toString());
        assertEquals("[alges | PT | 14 | 19326 | (38.702452,-9.22936), barreiro | PT | 19 | 51279 | (38.663137,-9.072395), belas | PT | 14 | 21018 | (38.777212,-9.260782)]", objetosCidades.toString());
    }

    @Test
    public void testLeituraDeArquivosComErros() {
        // Mock do ambiente
        File pastaTesteComErros = new File("test-files");

        // Teste
        assertTrue(Main.parseFiles(pastaTesteComErros));
        ArrayList<String> objetosErro = Main.getObjects(TipoEntidade.INPUT_INVALIDO);

        // Verificação
        assertEquals("[paises.csv | 6 | 1 | 7, cidades.csv | 3 | 1 | 2, populacao.csv | 22 | 1 | 13]", objetosErro.toString());
    }
}
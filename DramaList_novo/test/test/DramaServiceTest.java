package test;

import model.Drama;
import org.junit.Test;
import service.DramaService;

public class DramaServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void deveRejeitarDramaComNumeroDeEpisodiosInvalido() throws Exception {

        Drama drama = new Drama();
        drama.setTitulo("Drama de Teste");
        drama.setNumeroEpisodios(0);

        DramaService service = new DramaService(null);

        service.cadastrar(drama);
    }
}
import com.manoelcampos.retornoboleto.LeituraRetornoBancoBradesco;
import com.manoelcampos.retornoboleto.LeituraRetornoBancoBrasil;
import com.manoelcampos.retornoboleto.ProcessardorBoletos;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Classe para ver o funcionamento da leitura de boletos.
 *
 * @author Manoel Campos da Silva Filho
 */
public class Principal {
    public static void main(String[] args) throws URISyntaxException {
        URI caminhoArquivo = Principal.class.getResource("banco-brasil-1.csv").toURI();
        final var processador = new ProcessardorBoletos(LeituraRetornoBancoBrasil::processarLinhaArquivo);

        System.out.println("Lendo arquivo " + caminhoArquivo + "\n");

        processador.processar(caminhoArquivo);
        URI caminhoArquivo2 = Principal.class.getResource("bradesco-1.csv").toURI();
        processador.setProcessarLinhaArquivo(LeituraRetornoBancoBradesco::processarLinhaArquivo);
        processador.processar(caminhoArquivo2);
    }
}

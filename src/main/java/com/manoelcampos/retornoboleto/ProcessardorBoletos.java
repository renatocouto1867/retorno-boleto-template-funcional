package com.manoelcampos.retornoboleto;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public  class ProcessardorBoletos {
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    Function <String [],Boleto> processarLinhaArquivo;

    public ProcessardorBoletos(Function<String[], Boleto> processarLinhaArquivo) {
        this.processarLinhaArquivo = processarLinhaArquivo;
    }

    public final void processar(URI caminhoArquivo) {
        System.out.println("Boletos");
        System.out.println("----------------------------------------------------------------------------------");

        final var listaBoletos = new ArrayList<Boleto>();

        try {
            var listaLinhas = Files.readAllLines(Paths.get(caminhoArquivo));
            for (String linha : listaLinhas) {
                final String[] vetor = linha.split(";");
                final var boleto = processarLinhaArquivo.apply(vetor);

                listaBoletos.add(boleto);
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        for (Boleto boleto : listaBoletos) {
            System.out.println(boleto);
        }
    }

    public void setProcessarLinhaArquivo(Function<String[], Boleto> processarLinhaArquivo) {
        this.processarLinhaArquivo = processarLinhaArquivo;
    }
    //
//    protected abstract Boleto procesarLinhaArquivo(String[] vetor);


}

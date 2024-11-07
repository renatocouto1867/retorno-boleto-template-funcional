package com.manoelcampos.retornoboleto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.manoelcampos.retornoboleto.ProcessardorBoletos.FORMATO_DATA;
import static com.manoelcampos.retornoboleto.ProcessardorBoletos.FORMATO_DATA_HORA;

public class LeituraRetornoBancoBradesco {

    public static Boleto processarLinhaArquivo(String[] vetor) {
        final var boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);
        boleto.setAgencia(vetor[2]);
        boleto.setContaBancaria(vetor[3]);
        boleto.setDataVencimento(LocalDate.parse(vetor[4],FORMATO_DATA));
        boleto.setDataPagamento(LocalDateTime.parse(vetor[5],FORMATO_DATA_HORA));
        boleto.setCpfCliente(vetor[6].replace(".","").replace("-",""));
        boleto.setValor(Double.parseDouble(vetor[7]));
        boleto.setJuros(Double.parseDouble(vetor[8]));
        boleto.setMulta(Double.parseDouble(vetor[9]));
        return boleto;
    }
}


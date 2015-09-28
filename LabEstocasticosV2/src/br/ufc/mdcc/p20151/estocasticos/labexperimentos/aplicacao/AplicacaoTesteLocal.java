package br.ufc.mdcc.p20151.estocasticos.labexperimentos.aplicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Experimento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto.ProcessadorDeComandos;

public class AplicacaoTesteLocal {

	public static void main(String[] args) throws IOException {
		
		Experimento experimento = new Experimento();
		
		String texto = "";
		ProcessadorDeComandos entrada = new ProcessadorDeComandos(texto, experimento);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (texto != null) {
			texto = in.readLine();
			entrada.setTexto(texto);
			entrada.executarComando();
		}

	}

}
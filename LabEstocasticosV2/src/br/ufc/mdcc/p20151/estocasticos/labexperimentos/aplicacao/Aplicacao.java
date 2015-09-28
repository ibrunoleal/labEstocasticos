package br.ufc.mdcc.p20151.estocasticos.labexperimentos.aplicacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Experimento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto.ProcessadorDeComandos;

public class Aplicacao {

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			//System.out.println("o endereco do arquivo eh: " + args[0]);
			//System.out.println("o argumento de pergunta eh: " + args[1]);
			Experimento experimento = new Experimento();
			ProcessadorDeComandos processadorDeComandos;
			
			FileReader fileReader = new FileReader(args[0]);
			BufferedReader bufferDeLeitura = new BufferedReader(fileReader);
			String linhaDeComando = bufferDeLeitura.readLine();
			while (linhaDeComando != null) {
				System.out.println(">>:" + linhaDeComando);
				processadorDeComandos = new ProcessadorDeComandos(linhaDeComando, experimento);
				processadorDeComandos.executarComando();
				linhaDeComando = bufferDeLeitura.readLine();
			}
			bufferDeLeitura.close();
			
			processadorDeComandos = new ProcessadorDeComandos(args[1], experimento);
			processadorDeComandos.executarComando();
		} else {
			System.out.println("Voce nao digitou argumentos. Por favor forneca uma entrada valida.");
		}

	}
}
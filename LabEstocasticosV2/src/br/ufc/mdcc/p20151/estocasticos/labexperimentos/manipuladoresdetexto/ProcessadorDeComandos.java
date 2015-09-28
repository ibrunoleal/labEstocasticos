package br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.ConsolidadorDeInformacoes;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Experimento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.Informacao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Poliedro;

public class ProcessadorDeComandos {
	
	private String texto;
	
	private Experimento experimento;

	public ProcessadorDeComandos(String texto, Experimento experimento) {
		this.texto = texto;
		this.experimento = experimento;
	}
	
	/**
	 * Verifica a qual tipo de instancia do experimento o atributo 
	 * texto(entada do usuario) corresponde e retorna o seu objeto
	 * criado a partir da definicao textual
	 * @return um objeto do experimento. Pode ser um poliedro, 
	 * uma fase, uma condicao ou um comando de informacao
	 */
	public void executarComando() {
		if (isTextoDeSair()) {
			sair();
		}
		
		if (! (texto.length() <= 1) ) {
			if (isTextoDePoliedro()) {
				Poliedro poliedro = new TextoToPoliedro(texto).getPoliedro();
				experimento.adicionarPoliedro(poliedro);
			} else {
				if (isTextoDeDefinicaoDeFase()) {
					Fase fase = new TextoToFase(texto, experimento).getFase();
					experimento.adicionarFase(fase);
				} else {
					if (isTextoDeInformacao()) {
						Informacao informacao = new TextoToInformacao(texto, experimento).getInformacao();
						ConsolidadorDeInformacoes consolidador = new ConsolidadorDeInformacoes(experimento, informacao);
						
						consolidador.exibirEstatisticas();
					} else {
						new TextoToExecucao(texto, experimento).definirExecucao();
					}
				}
			}
		} else {
			//leu linha em branco
			//nao fazer nada
		}
		
		
	}
	
	/**
	 * Verifica se o texto de entrada é a definicao de um poliedro.
	 * obs.: verifica apenas se a palavra reservada "obj" é a inicial
	 * do texto.  
	 * @return true se o atributo texto é a definicao de um poliedro
	 */
	public boolean isTextoDePoliedro() {
		String[] partes = texto.split(" ");
		if (partes[0].equalsIgnoreCase("obj")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se o texto de entrada é a definicao de uma quetao.
	 * obs.: verifica apenas se o texto é iniciado com a palvra info
	 * @return true se o atributo texto é a definição de uma questão e false c.c.
	 */
	public boolean isTextoDeInformacao() {
		String[] partes = texto.split(" ");
		if(partes[0].equalsIgnoreCase("?")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se o texto de entrada é a definicao de uma fase.
	 * obs.: verifica apenas se a palavra "fase" é o inicio do texto.
	 * @return true se o atributo texto é a definicao de uma fase e false c.c.
	 */
	public boolean isTextoDeDefinicaoDeFase() {
		String[] partes = texto.split(" ");
		if (partes[0].equalsIgnoreCase("fase")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se o texto de entrada é a definicao de uma execucao.
	 * obs.: verifica apenas se não é nenhum dos outros tipos.
	 * @return true se o atributo texto é a definicao de uma execucao e false c.c.
	 */
	public boolean isTextoDeExecucao() {
		if (!isTextoDePoliedro() && !isTextoDeInformacao() && !isTextoDeDefinicaoDeFase()) {
			return true;
		} else {
			return false;
		}
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public boolean isTextoDeSair() {
		if (texto.equalsIgnoreCase("sair")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void sair() {
		System.exit(0);
	}

	
}

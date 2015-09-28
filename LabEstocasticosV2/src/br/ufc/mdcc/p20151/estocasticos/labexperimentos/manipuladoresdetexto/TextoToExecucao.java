package br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto;

import java.util.ArrayList;
import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Experimento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.Condicao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.CondicaoDeDiferenca;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.CondicaoDeIgualdade;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.CondicaoDePadrao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.CondicaoDeRepeticao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.CondicaoDeSoma;

public class TextoToExecucao {
	
	private String texto;
	
	private Experimento experimento;

	public TextoToExecucao(String texto, Experimento experimento) {
		this.texto = texto;
		this.experimento = experimento;
	}
	
	
	/**
	 * A partir do atributo texto de definicao da execucao, configura as fases
	 * definidas no experimento.
	 *
	 */
	public void definirExecucao() {
		if (temValor() && isValorDeElemento()) {
			definirExecucaoDeElemento();
		} else {
			definirExecucaoDeValor();
		}

	}
	
	public void definirExecucaoDeValor() {
		String textoDaCondicao = getTextoCondicao();
		String primeiroCaractere = String.valueOf(textoDaCondicao.charAt(0));
		int valorDeReferencia;
		Condicao condicao = null;
		
		if (primeiroCaractere.equalsIgnoreCase("=")) {
			valorDeReferencia = getValorDeReferenciaDaCondicao();
			condicao = new CondicaoDeIgualdade(
					experimento.getFase(getTextoFaseInicio()),
					valorDeReferencia);
		} else {
			if (primeiroCaractere.equalsIgnoreCase("!")) {
				valorDeReferencia = getValorDeReferenciaDaCondicao();
				condicao = new CondicaoDeDiferenca(
						experimento.getFase(getTextoFaseInicio()),
						valorDeReferencia);
			} else {
				if (primeiroCaractere.equalsIgnoreCase("#")) {
					valorDeReferencia = getValorDeReferenciaDaCondicao();
					condicao = new CondicaoDeRepeticao(
							experimento.getFase(getTextoFaseInicio()),
							valorDeReferencia);
				} else {
					if (primeiroCaractere.equalsIgnoreCase("+")) {
						valorDeReferencia = getValorDeReferenciaDaCondicao();
						condicao = new CondicaoDeSoma(
								experimento.getFase(getTextoFaseInicio()),
								valorDeReferencia);
					} else {
						if (primeiroCaractere.equalsIgnoreCase("[")) {
							List<Integer> padrao = getPadraoDeReferenciaDaCondicao();
							condicao = new CondicaoDePadrao(
									experimento.getFase(getTextoFaseInicio()),
									padrao);
						}
						/*
						 * implementar demais tipos de condicoes aqui
						 */
					}
					
				}
			}
		}
		
		if (isFaseDeTransicao()) {
			experimento.getFase(getTextoFaseInicio()).adicionarCondicaoDeProximaFase(condicao);
			experimento.getFase(getTextoFaseInicio()).setProximaFase(experimento.getFase(getTextoFaseTransicao()));
		} else {
			experimento.getFase(getTextoFaseInicio()).adicionarCondicaoDeParada(condicao);
		}
	}
	
	public void definirExecucaoDeElemento() {
		String textoDaCondicao = getTextoCondicao();
		String primeiroCaractere = String.valueOf(textoDaCondicao.charAt(0));
		Elemento elementoDeReferencia;
		Condicao condicao = null;
		
		if (primeiroCaractere.equalsIgnoreCase("=")) {
			elementoDeReferencia = getElementoDeReferencia();
			condicao = new CondicaoDeIgualdade(
					experimento.getFase(getTextoFaseInicio()),
					elementoDeReferencia);
			
		} else {
			if (primeiroCaractere.equalsIgnoreCase("!")) {
				elementoDeReferencia = getElementoDeReferencia();
				condicao = new CondicaoDeDiferenca(
						experimento.getFase(getTextoFaseInicio()),
						elementoDeReferencia);
			} else {
				if (primeiroCaractere.equalsIgnoreCase("+")) {
					elementoDeReferencia = getElementoDeReferencia();
					condicao = new CondicaoDeSoma(
							experimento.getFase(getTextoFaseInicio()),
							elementoDeReferencia);
				}
			}
			/*
			 * implementar demais operadores de condicao
			 */
		}
		
		if (isFaseDeTransicao()) {
			experimento.getFase(getTextoFaseInicio()).adicionarCondicaoDeProximaFase(condicao);
			experimento.getFase(getTextoFaseInicio()).setProximaFase(experimento.getFase(getTextoFaseTransicao()));
		} else {
			experimento.getFase(getTextoFaseInicio()).adicionarCondicaoDeParada(condicao);
		}
	}
	
	private List<Integer> getPadraoDeReferenciaDaCondicao() {
		String texto = getTextoCondicao();
		String textoSemParenteses = texto.substring(1, (texto.length() - 1));
		String[] valores = textoSemParenteses.split(",");
		List<Integer> listaDeValores = new ArrayList<Integer>();
		for (int i = 0; i < valores.length; i++) {
			listaDeValores.add(Integer.parseInt(valores[i]));
		}
		return listaDeValores;
	}


	private int getValorDeReferenciaDaCondicao() {
		String valor = getTextoCondicao().substring(1);
		if (valor.length() == 0) {
			return Condicao.CONDICAO_SEM_VALOR_DE_REFERENCIA;
		} else {
			return Integer.valueOf(valor);
		}
	}
	
	private Elemento getElementoDeReferencia() {
		Elemento elemento = new Elemento();
		
		String textoSemOperador = getTextoCondicao().substring(1);
		String textoSemParenteses = textoSemOperador.substring(1, (textoSemOperador.length() - 1));
		String[] valores = textoSemParenteses.split(",");
		for (int i = 0; i < valores.length; i++) {
			elemento.adicionar(Integer.parseInt(valores[i]));
		}
		
		return elemento;
	}
	
	public boolean isValorDeElemento() {
		if (temValor()) {
			String segundoCaractere = String.valueOf(getTextoCondicao().charAt(1));
			if (segundoCaractere.equalsIgnoreCase("(")){
				return true;
			}
		}
		return false;
	}
	
	public boolean temValor() {
		if (getTextoCondicao().length() > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private String getTextoFaseInicio() {
		String partes[] = texto.split(" ");
		String textoFaseInicio = partes[0];
		return textoFaseInicio;
	}
	
	private String getTextoCondicao() {
		String partes[] = texto.split(" ");
		String textoCondicao = partes[1];
		return textoCondicao;
	}
	
	/**
	 * Recupera o nome da fase seguinte da execução.
	 * @return o nome da fase seguinte da 
	 * execução quando houver. Se não houver retorna null.
	 */
	private String getTextoFaseTransicao() {
		String partes[] = texto.split(" ");
		String textoFaseTransicao = partes[2];
		return textoFaseTransicao;
	}
	
	public boolean isFaseDeTransicao() {
		String partes[] = texto.split(" ");
		if (partes.length > 2) {
			return true;
		} else {
			return false;
		}
	}

}

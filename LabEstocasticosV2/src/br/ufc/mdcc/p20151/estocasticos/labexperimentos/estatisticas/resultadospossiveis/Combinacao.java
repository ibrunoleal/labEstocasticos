package br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis;

import java.util.ArrayList;
import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class Combinacao {
	
	/**
	 * Fase a partir da qual se quer obter todas as combinacoes
	 * de resultados possiveis em sua execucao.
	 */
	private Fase fase;
	
	/**
	 * Lista que representa todas as combinacoes possiveis de resultados
	 * em uma execucao da fase
	 */
	private List<Elemento> listaCombinada;
	
	public Combinacao(Fase fase) {
		this.fase = fase;
		this.listaCombinada = new ArrayList<Elemento>();
		gerarCombinacao();
	}
	
	/**
	 * atribui valores ao atributo listaCombinada. Esses valores sao todas
	 * as combinacoes possiveis de resultaos em um lancamento de sua fase
	 */
	public void gerarCombinacao() {
		int contPoliedros = fase.getPoliedros().size();
		int proximo = 1;
		
		/*
		 * Define os elementos iniciais (a primeira combinacao) antes de ser combinada
		 * com outra - referente a um unico poliedro.
		 */
		List<Integer> inicio = fase.getPoliedros().get(0).getValoresDasFaces();
		for (Integer resultado : inicio) {
			Elemento elemento = new Elemento();
			elemento.adicionar(resultado);
			
			/*
			 * A lista combinada agora passa a ser essa combinacao inicial
			 */
			listaCombinada.add(elemento);
		}
		
		/*
		 * verifica se ainda ha poliedros para realizar a combinacao
		 */
		while (proximo < contPoliedros) {
			List<Integer> seguinte = fase.getPoliedros().get(proximo).getValoresDasFaces();
			
			/*
			 * Forma uma lista combinada simples com os elementos do poliedro seguinte(caso exista).
			 */
			List<Elemento> listaProxima = new ArrayList<Elemento>();
			for (Integer resultado : seguinte) {
				Elemento elemento = new Elemento();
				elemento.adicionar(resultado);
				listaProxima.add(elemento);
			}
			
			/*
			 * Realizamos agora a combinacao entre a lista proxima e a atual e botamos o
			 * resultado na novaCombinacao
			 */
			List<Elemento> novaCombinacao = new ArrayList<Elemento>();
			for (Elemento elemento : listaProxima) {
				List<Elemento> elementosAux = elemento.combinar(listaCombinada);
				novaCombinacao.addAll(elementosAux);
				//System.out.println("nova combinacao do loop:\n" + novaCombinacao);
			}
			
			/*
			 *A nova lista combinada agora eh dada pela novaCombinacao
			 */
			listaCombinada = novaCombinacao;
			
			/*
			 * Incremento para verificar se ha um proximo poliedro para realizar
			 * a combinacao de resultados
			 */
			proximo++;
		}
		
	}
	
	/**
	 * Recupera uma lista de elementos, onde cada elemento é uma de todas
	 * as possíveis combinacoes de resultados da fase.
	 * @return lista de elementos com todas as combinacoes possiveis
	 */
	public List<Elemento> getListaCombinada() {
		return listaCombinada;
	}

	public int getTamanhoDaCombinacao() {
		return listaCombinada.size();
	}

	@Override
	public String toString() {
		String texto = "[";
		
		for (int i = 0; i < listaCombinada.size(); i++) {
			if (i != (listaCombinada.size() - 1) ) {
				texto += listaCombinada.get(i) + ";";
			} else {
				texto += listaCombinada.get(i);
			}
		}
		texto += "]";
		
		return texto;
	}
	
	

}

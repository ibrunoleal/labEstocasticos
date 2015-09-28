package br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis;

import java.util.ArrayList;
import java.util.List;

public class Elemento {

	/**
	 * Um possivel resultado de execucao de uma fase
	 */
	private List<Integer> elemento;
	
	public Elemento() {
		elemento = new ArrayList<Integer>();
	}
	
	/**
	 * Adiciona um valor ao possivel resultado.
	 * @param valor valor a ser adicionado ao resultado.
	 */
	public void adicionar(int valor) {
		elemento.add(valor);
	}
	
	/**
	 * Recebe uma Combinacao e retorna uma nova combinacao feita
	 * a partir da recebida combinada este elemento.
	 * @param outrosElementos a combinacao dos elementos já 
	 * combinados anteriormente e que serao combinados com este elemento.
	 * @return a combinacao entre este elemento e a outra combinacao já realizada.
	 */
	public List<Elemento> combinar(List<Elemento> outrosElementos) {
		/*
		 * nova lista de elementos a ser retornada
		 */
		List<Elemento> listaDeElementos = new ArrayList<Elemento>();

		for (Elemento elemento : outrosElementos) {
			Elemento e = new Elemento();
			
			/*
			 * para cada valor em elemento repetir esses valores
			 * no novo elemento e
			 */
			for (Integer valor : elemento.getElemento()) {
				e.adicionar(valor);
			}
			
			/*
			 * acrescentar cada valor deste elemento
			 */
			for (Integer valor2 : this.elemento) {
				e.adicionar(valor2);
			}
			listaDeElementos.add(e);
		}

		return listaDeElementos;
	}

	public List<Integer> getElemento() {
		return elemento;
	}

	public void setElemento(List<Integer> elemento) {
		this.elemento = elemento;
	}
	
	public boolean isIgual(Elemento outroElemento) {
		if (elemento.size() == outroElemento.getElemento().size()) {
			for (int i = 0; i < elemento.size(); i++) {
				if (elemento.get(i) != outroElemento.getElemento().get(i)) {
					return false;
				}
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public int getSomaDoElemento() {
		int soma = 0;
		for (Integer valor : elemento) {
			soma += valor;
		}
		return soma;
	}
	
	public String toString() {
		String texto = "(";
		
		for (int i = 0; i < elemento.size() ; i++) {
			if (i != (elemento.size()-1) ) {
				texto += elemento.get(i) + ",";
			} else {
				texto += elemento.get(i);
			}
		}
		texto += ")";
		
		return texto;
	}

}

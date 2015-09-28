package br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;

public class ElementoDeResultado {

	private Elemento elemento;
	
	private int contador;

	public ElementoDeResultado(Elemento elemento) {
		this.elemento = elemento;
		this.contador = 0;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public int getContador() {
		return contador;
	}
	
	public void incrementarContador() {
		this.contador++;
	}
	
	public void limpar() {
		this.contador = 0;
	}

}

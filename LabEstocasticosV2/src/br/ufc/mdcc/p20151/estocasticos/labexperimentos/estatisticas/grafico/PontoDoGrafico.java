package br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.grafico;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;

public class PontoDoGrafico {

	/**
	 * possivel resultado do experimento
	 */
	private Elemento elemento;
	
	/**
	 * quantas vezes o elemento ocorre em media no experimento definido
	 */
	private double ocorrenciaMedia;

	
	public PontoDoGrafico(Elemento elemento, double ocorrenciaMedia) {
		this.elemento = elemento;
		this.ocorrenciaMedia = ocorrenciaMedia;
	}
	
	
	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public double getOcorrenciaMedia() {
		return ocorrenciaMedia;
	}


	public void setOcorrenciaMedia(double ocorrenciaMedia) {
		this.ocorrenciaMedia = ocorrenciaMedia;
	}


	public String toString() {
		return "Ponto:(" + elemento + "," + ocorrenciaMedia + ")";
	}

}

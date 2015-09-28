package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public abstract class Condicao {
	
	public static final int CONDICAO_SEM_VALOR_DE_REFERENCIA = -1;
	
	private Fase fase;
	
	private int valorDeReferencia;
	
	private Elemento elementoDeReferencia;

	public Condicao(Fase fase) {
		this.fase = fase;
		this.valorDeReferencia = CONDICAO_SEM_VALOR_DE_REFERENCIA;
		this.elementoDeReferencia = null;
	}
	
	public Condicao(Fase fase, int valorDeReferencia) {
		this.fase = fase;
		this.valorDeReferencia = valorDeReferencia;
		this.elementoDeReferencia = null;
	}
	
	public Condicao(Fase fase, Elemento elementoDeReferencia) {
		this.fase = fase;
		this.valorDeReferencia = CONDICAO_SEM_VALOR_DE_REFERENCIA;
		this.elementoDeReferencia = elementoDeReferencia;
	}

	public abstract boolean isCondicaoDeParada();

	public boolean isCondicaoComElemento() {
		if (getElementoDeReferencia() != null) {
			return true;
		}
		return false;
	}

	public Fase getFase() {
		return fase;
	}

	

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public int getValorDeReferencia() {
		return valorDeReferencia;
	}

	public void setValorDeReferencia(int valorDeReferencia) {
		this.valorDeReferencia = valorDeReferencia;
	}

	public Elemento getElementoDeReferencia() {
		return elementoDeReferencia;
	}
	
	

}

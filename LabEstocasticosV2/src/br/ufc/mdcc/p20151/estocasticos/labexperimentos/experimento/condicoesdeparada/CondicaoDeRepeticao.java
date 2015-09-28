package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class CondicaoDeRepeticao extends Condicao {
	
	public static final int CONDICAO_DE_REPETICAO_INDETERMINADA = 1000;

	private int valorAux;
	
//	public CondicaoRepeticao(Fase fase) {
//		super(fase);
//		// TODO Auto-generated constructor stub
//	}

	public CondicaoDeRepeticao(Fase fase, int valorDeReferencia) {
		super(fase, valorDeReferencia);
		valorAux = 0;
		if (getValorDeReferencia() == CONDICAO_SEM_VALOR_DE_REFERENCIA) {
			setValorDeReferencia(CONDICAO_DE_REPETICAO_INDETERMINADA);
		}
	}

	@Override
	public boolean isCondicaoDeParada() {		
		valorAux++;
		if (valorAux == getValorDeReferencia()) {
			valorAux = 0;
			return true;
		} else {
			return false;
		}
	}

}

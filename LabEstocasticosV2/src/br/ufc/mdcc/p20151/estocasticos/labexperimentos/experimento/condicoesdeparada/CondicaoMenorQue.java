package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class CondicaoMenorQue extends Condicao {

	public CondicaoMenorQue(Fase fase) {
		super(fase);
		// TODO Auto-generated constructor stub
	}

	public CondicaoMenorQue(Fase fase, int valorDeReferencia) {
		super(fase, valorDeReferencia);
		// TODO Auto-generated constructor stub
	}

	public CondicaoMenorQue(Fase fase, Elemento elementoDeReferencia) {
		super(fase, elementoDeReferencia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCondicaoDeParada() {
		// TODO Auto-generated method stub
		return false;
	}

}

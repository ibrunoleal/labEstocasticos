package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes;

import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Etapa;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.CondicaoDeDiferenca;

public class InformacaoDeDiferenca extends Informacao {	
	
	public InformacaoDeDiferenca(Fase fase, int valor) {
		super(fase, valor);
	}

	

	public InformacaoDeDiferenca(Fase fase, Elemento elemento) {
		super(fase, elemento);
		// TODO Auto-generated constructor stub
	}



	@Override
	public boolean isEtapaValida(Etapa etapa) {
		if (isInformacaoComElemento()) {
			if (isDistintoDoElemento(etapa)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getValor() == CondicaoDeDiferenca.CONDICAO_SEM_VALOR_DE_REFERENCIA) {
				List<Integer> lista = etapa.getResultadoDaEtapa()
						.getResultados();
				if (todosDistintosUnsDosOutros(lista)) {
					return true;
				}

			} else {
				int cont = 0;
				for (Integer res : etapa.getResultadoDaEtapa().getResultados()) {
					if (res == getValor()) {
						cont++;
					}
				}

				if (cont != etapa.getResultadoDaEtapa().getResultados().size()) {
					return true;
				}

			}
			return false;
		}
	}

	private boolean todosDistintosUnsDosOutros(List<Integer> numeros) {
		for (int i = 0; i < numeros.size(); i++) {
			for (int j = 0; j < numeros.size(); j++) {
				if (j != i) {
					if (numeros.get(i) == numeros.get(j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private boolean isDistintoDoElemento(Etapa etapa){
		List<Integer> lista = etapa.getResultadoDaEtapa().getResultados();
		Elemento elemento = new Elemento();
		for (Integer res : lista) {
			elemento.adicionar(res);
		}
		if (!elemento.isIgual(getElemento())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementoDeInteresse(Elemento elemento) {
		if (isInformacaoComElemento()) {
			if (!getElemento().isIgual(elemento)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getValor() == CondicaoDeDiferenca.CONDICAO_SEM_VALOR_DE_REFERENCIA) {
				List<Integer> lista = elemento.getElemento();
				if (todosDistintosUnsDosOutros(lista)) {
					return true;
				}

			} else {
				int cont = 0;
				for (Integer res : elemento.getElemento()) {
					if (res == getValor()) {
						cont++;
					}
				}

				if (cont != elemento.getElemento().size()) {
					return true;
				}

			}
			return false;
		}
	}
	
	

}

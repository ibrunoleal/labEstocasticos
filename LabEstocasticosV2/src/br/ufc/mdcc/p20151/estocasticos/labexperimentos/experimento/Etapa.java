package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento;


import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Face;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Poliedro;

public class Etapa {
	
	private List<Poliedro> poliedros;
	
	private ResultadoDeEtapa resultadoDaEtapa;
	
	public Etapa() {
		this.resultadoDaEtapa = new ResultadoDeEtapa();
	}
	
	public Etapa(List<Poliedro> poliedros) {
		this.poliedros = poliedros;
		this.resultadoDaEtapa = new ResultadoDeEtapa();
	}
	
	public void executar() {
		for (Poliedro poliedro : poliedros) {
			Face face = poliedro.lancar();
			resultadoDaEtapa.adicionarResultado(face);
		}
	}

	public Elemento getElementoDaEtapa() {
		Elemento elemento = new Elemento();
		for (Integer res : resultadoDaEtapa.getResultados()) {
			elemento.adicionar(res);
		}
		return elemento;
	}
	
	public void adicionarPoliedro(Poliedro poliedro) {
		this.poliedros.add(poliedro);
	}
	
	/**
	 * Apaga os resultados obtidos na etapa.
	 * obs.: nao apaga os poliedros definidos para a etapa
	 */
	public void limpar() {
		this.resultadoDaEtapa.limpar();
	}
	
	public ResultadoDeEtapa getResultadoDaEtapa() {
		return resultadoDaEtapa;
	}

	
	public String toString() {
		String res = "";
//		for (Poliedro poliedro : poliedros) {
//			res += poliedro;
//			res += "\n";
//		}
		res += resultadoDaEtapa;
		return res;
	}

}

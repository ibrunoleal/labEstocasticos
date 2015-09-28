package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento;

import java.util.ArrayList;
import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Face;

public class ResultadoDeEtapa {
	
	/**
	 * Face(s) obtida(s) no lancamento do(s) dado(s) da etapa.
	 * obs.: corresponde a um unico lancamento
	 */
	private List<Face> resultadosFace;

	public ResultadoDeEtapa() {
		resultadosFace = new ArrayList<Face>();
	}

	public List<Face> getResultadosFace() {
		return resultadosFace;
	}

	public void setResultadosFace(List<Face> resultadosFace) {
		this.resultadosFace = resultadosFace;
	}
	
	public void adicionarResultado(Face face) {
		resultadosFace.add(face);
	}
	
	public int getSize() {
		return resultadosFace.size();
	}
	
	public void limpar() {
		this.resultadosFace = new ArrayList<Face>();
	}
	
	/**
	 * Constroi uma lista dos valores das faces obtidas na execucao da etapa.
	 * @return lista de valores numericos com os resultados obtidos
	 * na execucao da etapa.
	 * obs.: corresponde a um unico lancamento
	 */
	public List<Integer> getResultados() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (Face face : resultadosFace) {
			int res = face.getValor();
			lista.add(res);
		}
		return lista;
	}
	
	public String toString() {
		String res = "(";
		for (int i = 0; i < getResultados().size() - 1; i++) {
			res += getResultados().get(i) + ",";
		}
		
		res += getResultados().get(getResultados().size() - 1) + ")";

		return res;
	}

}

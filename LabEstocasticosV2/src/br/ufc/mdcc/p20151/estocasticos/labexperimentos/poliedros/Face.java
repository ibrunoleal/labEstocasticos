package br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros;

public class Face {
	
	/**
	 * Define a probabilidade desta face ocorrer em relação ao objeto(moeda, dado, poliedro etc.) 
	 * como um todo
	 */
	private double probabilidade;
	
	/**
	 * Define o valor associado à face. Necessário quando o experimento quiser obter dados em função 
	 * de somatórios ou médias dos resultados.
	 */
	private int valor;


	/**
	 * Construtor para a criação de faces para objetos de lançamento onde não estamos
	 * interessados em associar a nomenclatura da face, apenas o seu valor 
	 * de ocorrência (e.g. 6 para a sexta face de um dado)
	 * @param probabilidade probablidade associada à face (e.g. 0.17 =~ 1/6)
	 * @param valor valor de ocorrência para a face(e.g. 6)
	 */
	public Face(double probabilidade, int valor) {
		this.probabilidade = probabilidade;
		this.valor = valor;
	}


	public double getProbabilidade() {
		return probabilidade;
	}

	/**
	 * Define a probabilidade desta face ocorrer no lancamento
	 * do poliedro a qual irá compor.
	 * obs.: deve ser um numero maior ou igual a 0 e menor ou igual a 1.
	 * @param probabilidade probabilidade da face ser obtida no lancamento
	 * do poliedro que ira compor
	 */
	public void setProbabilidade(double probabilidade) {
		if (probabilidade >= 0 && probabilidade <=1) {
			this.probabilidade = probabilidade;
		}
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	

	/**
	 * Retorna um texto da face no mesmo formato de entrada usado para
	 * definir a face
	 */
	public String toString() {
		return "(" + probabilidade + "," + valor + ")"; 
	}

}

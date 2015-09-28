package br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros;

import java.util.ArrayList;
import java.util.List;

public class Poliedro {
	
	/**
	 * Determina a nomenclatura do poliedro (e.g. "moeda1", "M1", "dado1", "D1")
	 */
	private String nome;
	
	/**
	 * Determina a quantidade de resultados distintos de
	 * faces que podem ser resultado do lancamento
	 */
	private int numeroDeFaces;
	
	/**
	 * Representa o conjunto de faces do poliedro 
	 * (objeto de lançamento - moeda, dado etc.)
	 */
	private Face[] faces;
	
	
	
	public Poliedro(String nome, int numeroDeFaces) {
		this.nome = nome; 
		this.numeroDeFaces = numeroDeFaces;
		this.faces = new Face[this.numeroDeFaces];
	}
	
	
	/**
	 * realiza o sorteio das faces levando em conta a probabilidade
	 * de cada uma
	 * @return a posicao da face sorteada no vetor faces
	 */
	private int sortearFace() {
		double tokenMin = 0;
		double tokenMax = 0;
		double result = Math.random();
		
		for (int i = 0; i < faces.length; i++) {
			tokenMax = tokenMin + faces[i].getProbabilidade();
			if (result >= tokenMin && result < tokenMax ) {
				return i;
			}
			tokenMin = tokenMax;
		}
		
		//nunca deve chegar aqui
		return 0;
	}
	
	/**
	 * Simula a execução(lancamento) do poliedro e
	 * pegar o seu resultado
	 * @return a face sorteada (resultado do lancamento)
	 */
	public Face lancar() {
		int faceSorteada = sortearFace();
		Face face = faces[faceSorteada];
		return face;
	}
	
	/**
	 * Adiciona uma face ao poliedro (metodo necessario para a definicao)
	 * de um novo poliedro
	 * @param face face de resultado a ser adicionada
	 * @param posicao posicao relativa que ira ocupar na definicao
	 * do poliedro
	 */
	public void adicionarFace(Face face, int posicao) {
		this.faces[posicao] = face;
	}
	
	/**
	 * Verifica se as probabilidades digitadas na definicao do poliedro
	 * são válidas - se a soma das probabilidades de todas as faces que compoem
	 * o poliedro é igual a 1.
	 * @return true se sao probabilidades validas e false c.c.
	 */
	public boolean isFacesValidas() {
		double soma = 0;
		for (int i = 0; i < faces.length; i++) {
			soma += faces[i].getProbabilidade();
		}
		
		if (soma >= 0 && soma <1.001) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroDeFaces() {
		return numeroDeFaces;
	}

	public void setNumeroDeFaces(int numeroDeFaces) {
		this.numeroDeFaces = numeroDeFaces;
	}

	public Face[] getFaces() {
		return faces;
	}

	public void setFaces(Face[] faces) {
		this.faces = faces;
	}

	public List<Integer> getValoresDasFaces() {
		List<Integer> valores = new ArrayList<Integer>();
		for (int i = 0; i < faces.length; i++) {
			valores.add(faces[i].getValor());
		}
		return valores;
	}

	private String facesToString() {
		String result = "";
		for (int i = 0; i < (faces.length - 1); i++) {
			result += faces[i] + ",";
		}
		result += faces[faces.length-1];
		return result;
	}
	
	/**
	 * Retorna um texto no mesmo formato do texto entrado
	 * pelo usuario para a definicao do poliedro
	 */
	public String toString() {
		return nome + "={" + facesToString() + "}";
	}
	
}

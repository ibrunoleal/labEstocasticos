package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento;

import java.util.ArrayList;
import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Poliedro;

public class Experimento {
	
	private List<Poliedro> poliedros;
	
	private List<Fase> fases;
	
	public Experimento() {
		poliedros = new ArrayList<Poliedro>();
		fases = new ArrayList<Fase>();
	}
	
	/**
	 * Executa a primeira fase adicionada ao experimento
	 * (supondo que esta eh a fase onde o experimento se inicia)
	 */
	public void executar() {
		fases.get(0).executar();
	}
	
	public void limpar() {
		for (Fase fase : fases) {
			fase.limpar();
		}
	}
	
	/**
	 * recupera o numero de passos - soma das quantidades 
	 * de execucoes de cada uma das fases.
	 * @return um valor inteiro do numero de passos executados
	 * no experimento.
	 */
	public int getNumeroDePassos() {
		int contador = 0;
		for (Fase fase: fases) {
			contador += fase.getNumeroDeExecucoes();
		}
		return contador;
	}
	
	/**
	 * Adiciona um poliedro ao experimento
	 * @param poliedro poliedro a ser adicionado (dado, moeda etc.)
	 */
	public void adicionarPoliedro(Poliedro poliedro) {
		poliedros.add(poliedro);
	}
	
	/**
	 * Adiciona uma fase ao experimento
	 * @param fase fase a ser adicionada ao experimento
	 */
	public void adicionarFase(Fase fase) {
		fases.add(fase);
	}
	
	/**
	 * Recupera um poliedro do experimento pelo seu nome.
	 * @param nome nome do poliedro a ser recuperado.
	 * @return um poliedro com o mesmo nome dado. Se nao houver poliedro
	 * com o nome dado será retornado null.
	 */
	public Poliedro getPoliedro(String nome) {
		for (Poliedro poliedro : poliedros) {
			if (poliedro.getNome().equalsIgnoreCase(nome)){
				return poliedro;
			}
		}
		return null;
	}
	
	/**
	 * Recupera uma fase do experimento pelo seu nome.
	 * @param nome nome do poliedro a ser recuperado.
	 * @return um poliedro com o mesmo nome dado. Se nao houver poliedro
	 * com o nome dado será retornado null.
	 */
	public Fase getFase(String nome) {
		for (Fase fase : fases) {
			if (fase.getNome().equalsIgnoreCase(nome)) {
				return fase;
			}
		}
		return null;
	}
	
	
	
	public List<Poliedro> getPoliedros() {
		return poliedros;
	}

	public List<Fase> getFases() {
		return fases;
	}

	public String toString() {
		String texto = "Resultados do experimento\n";
		for (Fase fase : fases) {
			texto += fase + "\n--------------------\n";
		}
		return texto;
	}
	
	
}

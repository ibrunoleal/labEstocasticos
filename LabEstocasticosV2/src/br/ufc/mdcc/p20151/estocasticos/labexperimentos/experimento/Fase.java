package br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento;

import java.util.ArrayList;
import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.ElementoDeResultado;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Combinacao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.condicoesdeparada.Condicao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Poliedro;

public class Fase {
	
	private String nome;
	
	private List<Etapa> etapas;
	
	private Fase proximaFase;
	
	private List<Poliedro> poliedros;
	
	private Condicao condicaoDeParada;
	
	private Condicao condicaoDeProximaFase;
	
	/**
	 * Lista com todos os resultados possíveis na execução da fase
	 */
	private List<ElementoDeResultado> elementosDeResultado;

	public Fase(String nome) {
		this.nome = nome;
		this.etapas = new ArrayList<Etapa>();
		this.poliedros = new ArrayList<Poliedro>();
		this.proximaFase = null;
		//this.elementosDeResultado = formarElementosDeResultado();
	}

	public void executar() {
		
		executarUmPasso();
		
		if (proximaFase == null) {
			while(!condicaoDeParada.isCondicaoDeParada()){
				executarUmPasso();
			}
			/* termino de execucao de fase sem proxima */
		} else {
			if (condicaoDeParada == null) {
				while(!condicaoDeProximaFase.isCondicaoDeParada()){
					executarUmPasso();
				}
				 /* termino de execucao de fase com proxima mas sem parada */
				proximaFase.executar();
			} else {
				while(!condicaoDeProximaFase.isCondicaoDeParada() && !condicaoDeParada.isCondicaoDeParada()) {
					executarUmPasso();
				}
				/* termino de execucao de fase com proxima e com termino */
				if (condicaoDeProximaFase.isCondicaoDeParada()) {
					proximaFase.executar();
				} /* else significa que foi atingida condicao de parada(fim do experimento) */
				
			}
		}
	}
	
	
	public void executarUmPasso() {
		Etapa etp = new Etapa(poliedros);
		etp.executar();
		adicionarEtapa(etp);

		verificarResultado(etp);
	}
	
	/**
	 * Compara qual o resultado obtido com todos os resultados possiveis e vai
	 * incrementando quantas vezes cada resultado foi obtido na execucao da
	 * fase.
	 * 
	 * @param etapa etapa obtida na execucao de um passo da fase
	 */
	public void verificarResultado(Etapa etapa){
		Elemento elemento = etapa.getElementoDaEtapa();
		for (int i = 0; i < elementosDeResultado.size(); i++) {
			if (elementosDeResultado.get(i).getElemento().isIgual(elemento)) {
				elementosDeResultado.get(i).incrementarContador();
			}
		}
	}
	
	public List<ElementoDeResultado> formarElementosDeResultado() {
		List<ElementoDeResultado> listaDeResultados = new ArrayList<ElementoDeResultado>();
		for (Elemento elemento : getTodosElementosPossiveis()) {
			ElementoDeResultado elementoDeResultado = new ElementoDeResultado(elemento);
			listaDeResultados.add(elementoDeResultado);
		}
		return listaDeResultados;
	}
	
	/**
	 * Forma uma lista com todas as combinacoes de resultados possíveis
	 * na execucao da fase.
	 * @return uma lista com todos os resultados possiveis
	 */
	private List<Elemento> getTodosElementosPossiveis() {
		Combinacao combinacao = new Combinacao(this);
		return combinacao.getListaCombinada();
	}

	public List<Elemento> faseToResultadoDeExperimento() {
		List<Elemento> listaDeElementos = new ArrayList<Elemento>();
		for (Etapa etapa : etapas) {
			Elemento elemento = etapa.getElementoDaEtapa();
			listaDeElementos.add(elemento);
		}
		return listaDeElementos;
	}
	
	public void adicionarEtapa(Etapa etapa) {
		this.etapas.add(etapa);
	}
	
	public void adicionarPoliedro(Poliedro poliedro) {
		this.poliedros.add(poliedro);
		/*
		 *Toda vez que um poliedro eh adicionado as possibilidades de resultados
		 *mudam e devem ser atualizadas 
		 */
		this.elementosDeResultado = formarElementosDeResultado();
	}
	
	public void adicionarCondicaoDeParada(Condicao condicaoDeParada) {
		this.condicaoDeParada = condicaoDeParada;
		condicaoDeParada.setFase(this);
	}
	
	public void adicionarCondicaoDeProximaFase(Condicao condicaoDeProximaFase) {
		this.condicaoDeProximaFase = condicaoDeProximaFase;
		condicaoDeProximaFase.setFase(this);
	}
	
	public void limpar() {
		etapas = new ArrayList<Etapa>();
		this.elementosDeResultado = formarElementosDeResultado();
	}
	


	/**
	 * Contabiliza o numero de execucoes desta fase
	 * @return o numero de execucoes da fase
	 */
	public int getNumeroDeExecucoes() {
		return etapas.size();
	}
	
	public List<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}
	

	public Condicao getCondicaoDeParada() {
		return condicaoDeParada;
	}

	public void setCondicaoDeParada(Condicao condicaoDeParada) {
		this.condicaoDeParada = condicaoDeParada;
	}

	public String getNome() {
		return nome;
	}

	public Fase getProximaFase() {
		return proximaFase;
	}

	public void setProximaFase(Fase proximaFase) {
		this.proximaFase = proximaFase;
	}

	
	
	public List<Poliedro> getPoliedros() {
		return poliedros;
	}
	
	

	public List<ElementoDeResultado> getElementosDeResultado() {
		return elementosDeResultado;
	}

	/**
	 * Pega a etapa na ultima posicao da lista
	 * @return a ultima etapa executada
	 */
	public Etapa getUltimaEtapaExecutada() {
		return etapas.get(etapas.size() - 1);
	}
	
	public String dadosNomeToString() {
		String texto = "";
		if (poliedros.size() > 0) {
			for (int i = 0; i < (poliedros.size() - 1); i++) {
				texto += poliedros.get(i).getNome() + ",";
			}
			texto += poliedros.get(poliedros.size() - 1).getNome();
		}
		return texto;
	}

	public String toString() {
		String res = nome + "=" + dadosNomeToString() + "\n";
		for (Etapa etapa : etapas) {
			res += etapa + "\n";
		}
		return res;
	}
	
}

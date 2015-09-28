package br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto;

import java.util.ArrayList;
import java.util.List;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.resultadospossiveis.Elemento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Experimento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.Informacao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoAll;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDeDiferenca;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDeIgualdade;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDePadrao;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.informacoes.InformacaoDeSoma;

public class TextoToInformacao {
	
	public static final int VALOR_DA_INFORMACAO_NAO_DEFINIDO = -1;
	public static final String INFORMACAO_NAO_DEFINIDA = "all";
	public static final String OPERADOR_NAO_DEFINIDO = "nao";
	
	private String texto;
	
	private Experimento experimento;

	public TextoToInformacao(String texto, Experimento experimento) {
		this.texto = texto;
		this.experimento = experimento;
	}
	
	public Informacao getInformacao() {
		
		if (temValor() && isValorDeElemento()) {
			return getInformacaoComElemento();
		} else {
			return getInformacaoComValor();
		}

	}
	
	public Informacao getInformacaoComElemento() {
		Elemento elemento = getElementoDaCombinacao();
		
		if (getOperadorDaInformacao().equalsIgnoreCase("=")) {
			InformacaoDeIgualdade informacao = new InformacaoDeIgualdade(experimento.getFase(getTextoDaFase()), elemento);
			if (isResultadoExpandido()) {
				informacao.setInformacaoComResultadoExpandido(true);
			}
			return informacao;
		} else {
			if (getOperadorDaInformacao().equalsIgnoreCase("!")) {				
				InformacaoDeDiferenca informacao = new InformacaoDeDiferenca(experimento.getFase(getTextoDaFase()), elemento);
				if (isResultadoExpandido()) {
					informacao.setInformacaoComResultadoExpandido(true);
				}
				return informacao;
			}
		}
		
		return null;
	}
	
	public Informacao getInformacaoComValor() {
		
		if (getOperadorDaInformacao().equalsIgnoreCase("+")) {
			InformacaoDeSoma informacao = new InformacaoDeSoma(experimento.getFase(getTextoDaFase()), getValorDaInformacao());
			if (isResultadoExpandido()) {
				informacao.setInformacaoComResultadoExpandido(true);
			}
			return informacao;
		} else {
			if (getOperadorDaInformacao().equalsIgnoreCase("=")) {
				InformacaoDeIgualdade informacao = new InformacaoDeIgualdade(experimento.getFase(getTextoDaFase()), getValorDaInformacao());
				if (isResultadoExpandido()) {
					informacao.setInformacaoComResultadoExpandido(true);
				}
				return informacao;
			} else {
				if (getOperadorDaInformacao().equalsIgnoreCase("!")) {
					InformacaoDeDiferenca informacao = new InformacaoDeDiferenca(experimento.getFase(getTextoDaFase()), getValorDaInformacao());
					if (isResultadoExpandido()) {
						informacao.setInformacaoComResultadoExpandido(true);
					}
					return informacao;
				} else {
					if (getOperadorDaInformacao().equalsIgnoreCase("[")) {
						InformacaoDePadrao informacao = new InformacaoDePadrao(experimento.getFase(getTextoDaFase()), getPadraoDaInformacao());
						if (isResultadoExpandido()) {
							informacao.setInformacaoComResultadoExpandido(true);
						}
						return informacao;
					} else {
						if (getOperadorDaInformacao().equalsIgnoreCase(OPERADOR_NAO_DEFINIDO)) {
							InformacaoAll informacao = new InformacaoAll(experimento.getFase(getTextoDaFase()), getValorDaInformacao());
							if (isResultadoExpandido()) {
								informacao.setInformacaoComResultadoExpandido(true);
							}
							return informacao;
						}
					}
					/*
					 * complementar apos implementar demais tipos de classes de informacao
					 */
				}
			}
			
		}
		
		return null;
	}
	
	public boolean isValorDeElemento() {
		if (temValor()) {
			String segundoCaractere = String.valueOf(getTextoInformacao().charAt(1));
			if (segundoCaractere.equalsIgnoreCase("(")){
				return true;
			}
		}
		return false;
	}
	
	public boolean temValor() {
		if (getTextoInformacao().length() > 1 && !getTextoInformacao().equalsIgnoreCase(INFORMACAO_NAO_DEFINIDA)) {
			return true;
		} else {
			return false;
		}
	}
	
	private Elemento getElementoDaCombinacao() {
		Elemento elemento = new Elemento();
		
		String textoSemOperador = getTextoInformacao().substring(1);
		String textoSemParenteses = textoSemOperador.substring(1, (textoSemOperador.length() - 1));
		String[] valores = textoSemParenteses.split(",");
		for (int i = 0; i < valores.length; i++) {
			elemento.adicionar(Integer.parseInt(valores[i]));
		}
		
		return elemento;
	}

	private List<Integer> getPadraoDaInformacao() {
		String texto = getTextoInformacao();
		String textoSemParenteses = texto.substring(1, (texto.length() - 1));
		String[] valores = textoSemParenteses.split(",");
		List<Integer> listaDeValores = new ArrayList<Integer>();
		for (int i = 0; i < valores.length; i++) {
			listaDeValores.add(Integer.parseInt(valores[i]));
		}
		return listaDeValores;
	}

	private String getOperadorDaInformacao() {
		String informacao = getTextoInformacao();
		if (informacao.equalsIgnoreCase(INFORMACAO_NAO_DEFINIDA)) {
			return OPERADOR_NAO_DEFINIDO;
		}
		String operador = String.valueOf(informacao.charAt(0));
		return operador;
	}
	
	private int getValorDaInformacao() {
		String informacao = getTextoInformacao();
		if (informacao.equalsIgnoreCase(INFORMACAO_NAO_DEFINIDA)) {
			return VALOR_DA_INFORMACAO_NAO_DEFINIDO;
		}
		String valorTexto = getTextoInformacao().substring(1);
		
		if (valorTexto.length() >= 1) {
			return Integer.parseInt(valorTexto);
		} else {
			return VALOR_DA_INFORMACAO_NAO_DEFINIDO;
		}
	}
	
	private String getTextoDaFase() {
		String[] partes = texto.split(" ");
		String nomeFase = partes[1];
		return nomeFase;
	}
	
	private String getTextoInformacao() {
		String[] partes = texto.split(" ");
		if (partes.length == 3 && !isResultadoExpandido()) {
			String textoInformacao = partes[2];
			return textoInformacao;
		} else {
			if (partes.length == 4 && isResultadoExpandido()) {
				String textoInformacao = partes[2];
				return textoInformacao;
			}
		}
		return INFORMACAO_NAO_DEFINIDA;
	}
	
	private boolean isResultadoExpandido() {
		String[] partes = texto.split(" ");
		int ultimaParte = (partes.length - 1);
		if (partes[ultimaParte].equalsIgnoreCase("*")) {
			return true;
		} else {
			return false;
		}
	}
}

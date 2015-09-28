package br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Experimento;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.experimento.Fase;

public class TextoToFase {
	
	private String texto;
	
	private Experimento experimento;

	public TextoToFase(String texto, Experimento experimento) {
		this.texto = texto;
		this.experimento = experimento;
	}

	public Fase getFase() {
		Fase fase = new Fase(getNomeDaFase());
		
		String[] nomesDosPoliedros = getTextoDosPoliedros().split(",");
		for (int i = 0; i < nomesDosPoliedros.length; i++) {
			fase.adicionarPoliedro(experimento.getPoliedro(nomesDosPoliedros[i]));
		}
		
		return fase;
	}
	
	/**
	 * A partir do texto de definicao da fase, recupera apenas
	 * a parte referente ao nome dos poliedros que estao
	 * associados à fase.
	 * @return o texto com o nome dos poliedros associados à fase
	 */
	private String getTextoDosPoliedros() {
		String[] partes = texto.split(" ");
		String textoDeDefinicao = partes[1];
		String[] partes2 = textoDeDefinicao.split("=");
		String textoPoliedros = partes2[1];
		return textoPoliedros;
	}
	
	private String getNomeDaFase() {
		String[] partes = texto.split(" ");
		String textoDeDefinicao = partes[1];
		String[] partes2 = textoDeDefinicao.split("=");
		String nome = partes2[0];
		return nome;
	}


	

	
}

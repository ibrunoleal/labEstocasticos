package br.ufc.mdcc.p20151.estocasticos.labexperimentos.manipuladoresdetexto;

import java.util.ArrayList;
import java.util.regex.Pattern;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Face;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Poliedro;


public class TextoToPoliedro {
	
	/**
	 * Texto de definicao do poliedro
	 */
	private String texto;
	
	/**
	 * Construtor para iniciar o texto a partir do qual será feito o poliedro
	 * @param texto String de definicao do poliedro
	 */
	public TextoToPoliedro(String texto) {
		this.texto = texto;
	}
	
	
	
	/**
	 * Forma e retorna um poliedro a partir do atributo texto - que possui a
	 * definicao para a criacao do poliedro
	 * 
	 * @return um poliedro criado a partir do atributo texto
	 */
	public Poliedro getPoliedro() {
		String nome = pegarNome();
		ArrayList<Face> faces = formarFaces();
		Poliedro poliedro = new Poliedro(nome, faces.size());

		int i = 0;
		for (Face face : faces) {
			poliedro.getFaces()[i] = face;
			i++;
		}

		if (poliedro.isFacesValidas()) {
			return poliedro;
		} else {
			System.out.println("erro: probabilidades invalidas"
					+ " - soma menor que zero ou maior que um.");
			return null;
		}
	}

	/**
	 * Do atributo texto (de definicao do poliedro) pega o nome da variável
	 * do poliedro
	 * @return nome do poliedro.
	 * Ex: Ex: do texto "obj X={(1/2,0),(1/2,1)}" o retorno e "X"
	 */
	public String pegarNome() {
		String[] partes = texto.split(" ");
		String parte1 = partes[1];
		String nome = parte1.split("=")[0];
		return nome;
	}
	
	/**
	 * Do atributo texto (de definicao do poliedro) pega a parte do texto 
	 * referente às faces do poliedro
	 * @return da definicao do poliedro pegamos a parte do texto
	 * referente as faces.
	 * Ex: do texto "obj X={(1/2,0),(1/2,1)}" o retorno e "(1/2,0),(1/2,1)"
	 */
	public String pegarTextoFaces() {
		String[] partes1 = texto.split(Pattern.quote("{"));
		String parte1 = partes1[1];
		String[] partes2 = parte1.split(Pattern.quote("}"));
		String textoFaces = partes2[0];
		return textoFaces;
	}
	
	/**
	 * A partir do texto de definicao do poliedro forma suas faces
	 * @return uma lista com as faces do poliedro
	 */
	public ArrayList<Face> formarFaces() {
		ArrayList<Face> faces = new ArrayList<Face>();
		
		String textoDasFaces = pegarTextoFaces();
		String[] texto = textoDasFaces.split("");
		String textoFace = "";
		
		/* A cada texto contido entre parenteses formamos uma face */
		for (int i = 0; i < texto.length; i++) {
			if (texto[i].equalsIgnoreCase("(")) {
				textoFace = "";
			}
			if (texto[i].equalsIgnoreCase(")")) {
				Face face = textoToFace(textoFace);
				faces.add(face);
			}
			textoFace += texto[i];
		}
		
		return faces;
	}
	
	/**
	 * Converte um texto da forma "(nome,p,valor)" em um objeto do tipo face
	 * @param textoDaFace texto a ser convertido para face
	 * @return a Face criada a partir do texto
	 */
	public Face textoToFace(String textoDaFace) {
		
		/* Remove os parenteses do texto */
		String[] partes = textoDaFace.split(Pattern.quote("("));
		String parte1 = partes[1];
		String[] partes2 = parte1.split(Pattern.quote(")"));
		String face = partes2[0];
		
		/* Com os parenteses removidos do texto separamos os parametros pelas virgulas */
		String partesFace[] = face.split(",");
		String probabilidadeFace = partesFace[0];
		String valorFace = partesFace[1];
		
		/* Como a probabilidade pode vir no formado fracionario ou real temos que
		tratar os dois casos */
		double probabilidadeRes;
		/* Tratando o caso fracionario e convertendo pra double*/
		if (probabilidadeFace.contains("/")) {
			String[] numeros = probabilidadeFace.split(Pattern.quote("/"));
			double numerador = Double.parseDouble(numeros[0]);
			int denominador = Integer.parseInt(numeros[1]);
			probabilidadeRes = numerador/denominador;
		/* Tratando o caso padrao e convertendo pra double */
		} else {
			probabilidadeRes = Double.parseDouble(probabilidadeFace);
		}
		
		Face f = new Face(probabilidadeRes, Integer.parseInt(valorFace));
		return f;
		
	}
	
	
	
}

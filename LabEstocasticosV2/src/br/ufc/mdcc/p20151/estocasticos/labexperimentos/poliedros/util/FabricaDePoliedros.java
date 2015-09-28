package br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.util;

import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Face;
import br.ufc.mdcc.p20151.estocasticos.labexperimentos.poliedros.Poliedro;

public class FabricaDePoliedros {

	public FabricaDePoliedros() {
		// TODO Auto-generated constructor stub
	}
	
	public static Poliedro getNovaMoedaComum(String nome) {
		Poliedro poliedro = new Poliedro(nome, 2);
		Face faceCara = new Face(1.0/2, 0);
		Face faceCoroa = new Face(1.0/2, 1);
		poliedro.getFaces()[0] = faceCara;
		poliedro.getFaces()[1] = faceCoroa;
		return poliedro;
	}
	
	public static Poliedro getDadoComum(String nome) {
		Poliedro poliedro = new Poliedro(nome, 6);
		Face faceUm = new Face(1.0/6, 1);
		Face faceDois = new Face(1.0/6, 2);
		Face faceTres = new Face(1.0/6, 3);
		Face faceQuatro = new Face(1.0/6, 4);
		Face faceCinco = new Face(1.0/6, 5);
		Face faceSeis = new Face(1.0/6, 6);
		poliedro.getFaces()[0] = faceUm;
		poliedro.getFaces()[1] = faceDois;
		poliedro.getFaces()[2] = faceTres;
		poliedro.getFaces()[3] = faceQuatro;
		poliedro.getFaces()[4] = faceCinco;
		poliedro.getFaces()[5] = faceSeis;
		return poliedro;
	}

}

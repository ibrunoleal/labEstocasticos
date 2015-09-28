package br.ufc.mdcc.p20151.estocasticos.labexperimentos.estatisticas.grafico;
import java.io.IOException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafico extends ApplicationFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultCategoryDataset dataset;
	
	private JFreeChart barChart;
	
	public Grafico(String tituloDaAplicacao, String tituloDoGrafico) {
		super(tituloDaAplicacao);
		dataset = new DefaultCategoryDataset();

		String legendaX = "Resultado";
		String tipoDeValorX = "Probabilidade";
		barChart = ChartFactory.createBarChart(tituloDoGrafico, legendaX, tipoDeValorX, dataset);
		
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		setContentPane(chartPanel);
	}

	public void adicionarPonto(String resultadoCombinado, double probabilidade) {
		dataset.addValue(probabilidade, "probabilidade", resultadoCombinado);
	}
	
//	private DefaultCategoryDataset createDataset() {
//		return dataset;
//	}

	/**
	 * Gera e exporta uma imagem JPG do grafico. Alem disso escreve o link na tela para a figura
	 * ser acessada remotamente
	 */
	public void exportarGraficoComoFigura() {
		double numeroaletorio = Math.random() * 1000000;
		String nomeAux = "" + numeroaletorio;
		String nomeAleatorio = "grafico" + nomeAux.substring(0,5) + ".jpg";

		try {
			ChartUtilities.saveChartAsJPEG(new java.io.File(nomeAleatorio), barChart, 700, 600);
			String caminho;

			caminho = "http://turing.lia.ufc.br/labest/estocasticos/mcctrabestocasticos/" + nomeAleatorio; 
			
			System.out.println("Para ver o grafico acesse " + caminho);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Exibe o grafico na tela em uma janela de aplicacao
	 */
	public void exibirGrafico() {
		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}
}
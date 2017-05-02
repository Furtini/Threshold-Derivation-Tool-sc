package tdt.methods.ferreira;

import java.util.ArrayList;

/* 
 * Universidade Federal De Minas Gerais
 * Departamento da Ciência da Computação
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */

public class AnalyseFerreira {

	private static double thresholdOne;
	private static double thresholdTwo;
	
	private static String savePathFerreira;
	private static ArrayList<String> filesPathFerreira = new ArrayList<String>();
	private static ArrayList<String> selectedFilesFerreira = new ArrayList<String>();
	private static ArrayList<String> metricsListFerreira = new ArrayList<String>();

	public double getThresholdOne() {
		return thresholdOne;
	}

	public void setThresholdOne(double thresholdOne) {
		AnalyseFerreira.thresholdOne = thresholdOne;
	}

	public double getThresholdTwo() {
		return thresholdTwo;
	}

	public void setThresholdTwo(double thresholdTwo) {
		AnalyseFerreira.thresholdTwo = thresholdTwo;
	}

	public String getSavePathFerreira() {
		return savePathFerreira;
	}

	public void setSavePathFerreira(String savePathFerreira) {
		AnalyseFerreira.savePathFerreira = savePathFerreira;
	}

	public ArrayList<String> getFilesPathFerreira() {
		return filesPathFerreira;
	}

	public void setFilesPathFerreira(ArrayList<String> filesPathFerreira) {
		AnalyseFerreira.filesPathFerreira = filesPathFerreira;
	}

	public ArrayList<String> getSelectedFilesFerreira() {
		return selectedFilesFerreira;
	}

	public void setSelectedFilesFerreira(ArrayList<String> selectedFilesFerreira) {
		AnalyseFerreira.selectedFilesFerreira = selectedFilesFerreira;
	}

	public ArrayList<String> getMetricsListFerreira() {
		return metricsListFerreira;
	}

	public void setMetricsListFerreira(ArrayList<String> metricsListFerreira) {
		AnalyseFerreira.metricsListFerreira = metricsListFerreira;
	}

}

package tdt.methods.vale;

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

public class AnalyseVale {

	private static String savePathVale;
	private static ArrayList<String> filesPathVale = new ArrayList<String>();
	private static ArrayList<String> selectedFilesVale = new ArrayList<String>();
	private static ArrayList<String> metricsListVale = new ArrayList<String>();

	public String getSavePathVale() {
		return savePathVale;
	}

	public void setSavePathVale(String savePath) {
		AnalyseVale.savePathVale = savePath;
	}

	public ArrayList<String> getSelectedFilesVale() {
		return selectedFilesVale;
	}

	public void setSelectedFilesVale(ArrayList<String> selectedFiles) {
		AnalyseVale.selectedFilesVale = selectedFiles;
	}

	public ArrayList<String> getFilesPathVale() {
		return filesPathVale;
	}

	public void setFilesPathVale(ArrayList<String> filesPath) {
		AnalyseVale.filesPathVale = filesPath;
	}

	public ArrayList<String> getMetricsListVale() {
		return metricsListVale;
	}

	public void setMetricsListVale(ArrayList<String> metricsList) {
		AnalyseVale.metricsListVale = metricsList;
	}

}

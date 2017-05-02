package tdt.methods.alves;

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

public class AnalyseAlves {

	private static String savePathAlves;
	private static ArrayList<String> filesPathAlves = new ArrayList<String>();
	private static ArrayList<String> selectedFilesAlves = new ArrayList<String>();
	private static ArrayList<String> metricsListAlves = new ArrayList<String>();

	public String getSavePathAlves() {
		return savePathAlves;
	}

	public void setSavePathAlves(String savePath) {
		AnalyseAlves.savePathAlves = savePath;
	}

	public ArrayList<String> getSelectedFilesAlves() {
		return selectedFilesAlves;
	}

	public void setSelectedFilesAlves(ArrayList<String> selectedFiles) {
		AnalyseAlves.selectedFilesAlves = selectedFiles;
	}

	public ArrayList<String> getFilesPathAlves() {
		return filesPathAlves;
	}

	public void setFilesPathAlves(ArrayList<String> filesPath) {
		AnalyseAlves.filesPathAlves = filesPath;
	}

	public ArrayList<String> getMetricsListAlves() {
		return metricsListAlves;
	}

	public void setMetricsListAlves(ArrayList<String> metricsList) {
		AnalyseAlves.metricsListAlves = metricsList;
	}

}

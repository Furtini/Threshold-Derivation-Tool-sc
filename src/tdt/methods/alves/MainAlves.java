package tdt.methods.alves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.csvreader.CsvWriter;
import com.opencsv.CSVReader;

/* 
 * Universidade Federal De Minas Gerais
 * Departamento da Ciência da Computação
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */

public class MainAlves {

	// A list of every selected metric, each one containing a list of
	// metric values.
	static List<List<Integer>> metricsListA = new ArrayList<List<Integer>>();

	// A list of every selected metric, each one containing a list of AlvesData
	// objects.
	// An AlvesData object is a pair (entity value, loc/totalLoc)
	// Each list represents a stage of the method.
	static List<List<AlvesData>> dataList = new ArrayList<List<AlvesData>>();
	static List<List<AlvesData>> intermediateList = new ArrayList<List<AlvesData>>();
	static List<List<AlvesData>> finalList = new ArrayList<List<AlvesData>>();

	static List<Integer> finalResultA = new ArrayList<Integer>();

	// Create Loc/ Total Loc lists for every system.
	static List<Double> locPercentageList = new ArrayList<Double>();

	public static void run() throws IOException {

		AnalyseAlves fta = new AnalyseAlves();

		// Array to save the metrics column on the file.
		int[] mLocations = new int[fta.getMetricsListAlves().size()];

		// Find the columns of the selected metrics in the file.
		try {
			String strFile = fta.getFilesPathAlves().get(0);
			CSVReader reader = new CSVReader(new FileReader(strFile));
			String[] nextLine;

			nextLine = reader.readNext();

			String[] line = nextLine[0].split(";");

			for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {

				for (int j = 0; j < line.length; j++) {
					if (line[j].equals(fta.getMetricsListAlves().get(i))) {
						mLocations[i] = j;
					}
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO: File not found.!");
			e.printStackTrace();
		}

		for (int k = 0; k < fta.getFilesPathAlves().size(); k++) {

			// Getting the weight ratio value
			weightCalculation(fta.getFilesPathAlves().get(k));

			// Creating lists of AlvesData objects.
			for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
				List<AlvesData> alvesData = new ArrayList<AlvesData>();
				List<AlvesData> intermedData = new ArrayList<AlvesData>();
				List<AlvesData> finalData = new ArrayList<AlvesData>();
				dataList.add(alvesData);
				intermediateList.add(intermedData);
				finalList.add(finalData);

			}

			// Getting entities values for the selected metrics.
			// And adding to the list of metrics.
			for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
				metricsListA.add(entityReader(fta.getFilesPathAlves().get(k), mLocations[i]));
			}

			for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
				for (int j = 0; j < metricsListA.get(i).size(); j++) {
					AlvesData data = new AlvesData(metricsListA.get(i).get(j), locPercentageList.get(j));
					dataList.get(i).add(data);
				}
			}
			// Ordering in ascending order based of the metric value.
			for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
				Collections.sort(dataList.get(i));
			}

			// Removing duplicates.
			for (int i = 0; i < dataList.size(); i++) {
				removeDuplicates(dataList.get(i), intermediateList.get(i));
			}

			dataList.removeAll(dataList);
			metricsListA.removeAll(metricsListA);
			locPercentageList.removeAll(locPercentageList);

		} // End for through files.

		// Dividing intermediate list percentages values by the total of
		// systems.
		for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
			for (int j = 0; j < intermediateList.get(i).size(); j++) {
				double pondering = intermediateList.get(i).get(j).getPercentageAlves()
						/ fta.getSelectedFilesAlves().size();
				intermediateList.get(i).get(j).setPercentageAlves(pondering);
			}
		}
		// Ordering in ascending order based of the metric value.
		for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
			Collections.sort(intermediateList.get(i));
		}

		// Removing duplicates.
		for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
			removeDuplicates(intermediateList.get(i), finalList.get(i));
		}

		// Print teste
		for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {
			// System.out.println(fta.getMetricsListAlves().get(i) + "=" +
			// finalList.get(i));
		}

	}

	// Calculate the weight ratio based on the method's formula
	// (loc / total loc) * 100
	// Then return a list with the percentage values.
	// The method parameter is the path for the file to analyze.
	private static void weightCalculation(String path) throws IOException {

		int totalLoc = 0;

		int j = 0; // Number of the loc metric column.

		// Find the column of loc metric.
		try {
			String strFile = path;
			CSVReader reader = new CSVReader(new FileReader(strFile));
			String[] nextLine;

			nextLine = reader.readNext();

			String[] line = nextLine[0].split(";");

			// Read First Line to find loc column, break loop when found.
			for (j = 0; j < line.length; j++) {

				if (line[j].equalsIgnoreCase("loc") || line[j].equalsIgnoreCase("numberOfLinesOfCode")
						|| line[j].equalsIgnoreCase("linesofcode")) {
					break;
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// When found, loop through entities and get the sum of all values.
		File csvFile = new File(path);

		String linesOfFile = new String();
		Scanner rd = new Scanner(csvFile);

		rd.nextLine();

		// Loop through file lines.
		// First one to get the total loc.
		while (rd.hasNext()) {
			linesOfFile = rd.nextLine();
			String[] valueBetweenComma = linesOfFile.split(";");

			totalLoc += Integer.parseInt(valueBetweenComma[j]);
		}
		rd.close();
		// Second one to calculate the loc/ total loc.
		rd = new Scanner(csvFile);
		rd.nextLine();
		while (rd.hasNext()) {
			linesOfFile = rd.nextLine();
			String[] valueBetweenComma = linesOfFile.split(";");

			locPercentageList.add((Double.parseDouble(valueBetweenComma[j]) / (double) totalLoc) * (double) 100);
		}

		rd.close();

	}

	// Method for retrieve the entities values on the file.
	// Parameters: Path of the file, the metric column value as a integer.
	private static List<Integer> entityReader(String path, int metricPos) {

		List<Integer> entityList = new ArrayList<Integer>();

		File csvFile = new File(path);

		try {
			String linesOfFile = new String();
			Scanner rd = new Scanner(csvFile);

			rd.nextLine();

			// Loop while file has lines.
			while (rd.hasNext()) {
				linesOfFile = rd.nextLine();
				String[] valueBetweenComma = linesOfFile.split(";");

				entityList.add(Integer.parseInt(valueBetweenComma[metricPos]));
			}

			rd.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO: File not found.!");

		}

		return entityList;

	}

	// Remove duplicates entities and add the respective weight percentage
	public static void removeDuplicates(List<AlvesData> list, List<AlvesData> listIntermed) {

		int i;
		int aux = list.get(0).getMetricAlves();
		double sum = list.get(0).getPercentageAlves();

		for (i = 1; i < list.size(); i++) {

			if (aux == list.get(i).getMetricAlves()) {

				sum += list.get(i).getPercentageAlves();

			} else {
				AlvesData data = new AlvesData(list.get(i - 1).getMetricAlves(), sum);

				listIntermed.add(data);

				aux = list.get(i).getMetricAlves();
				sum = list.get(i).getPercentageAlves();
			}
		}
		// Adding final sum.
		AlvesData data = new AlvesData(list.get(i - 1).getMetricAlves(), sum);
		listIntermed.add(data);
	}

	// Save final list on csv file
	public static void saveAlves() {

		AnalyseAlves fta = new AnalyseAlves();

		final String[] percentage = { "0-70", "70-80", "80-90", ">90" };
		final String[] labels = { "Low", "Moderate", "High", "Very-High" };

		int aux = 0;

		// Creating method's directory
		new File(fta.getSavePathAlves() + File.separator + "Alves's Method Output").mkdir();
		String path = fta.getSavePathAlves() + File.separator + "Alves's Method Output";

		// Delete files if they already exists.
		File file = new File(path);
		String[] files;
		if (file.isDirectory()) {
			files = file.list();
			for (int i = 0; i < files.length; i++) {
				File myFile = new File(file, files[i]);
				myFile.delete();
			}
		}
		// End deleting files.

		// Creating and saving the csv files.
		for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {

			// Create output file path.
			String outputFile = path + File.separator + fta.getMetricsListAlves().get(i) + ".csv";
			String outputFinalResult = path + File.separator + "Final-Result" + ".csv";

			// Check if file already exists.
			boolean alreadyExist = new File(outputFile).exists();
			boolean alreadyExist2 = new File(outputFinalResult).exists();

			try {
				CsvWriter metricOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
				CsvWriter finalResultOutput = new CsvWriter(new FileWriter(outputFinalResult, true), ';');

				// If file don't exists, write the header line
				if (!alreadyExist) {

					metricOutput.write(fta.getMetricsListAlves().get(i));
					metricOutput.write("Percentage");
					metricOutput.endRecord();

					// Write the metric values on the lists
					for (int j = 0; j < finalList.get(i).size(); j++) {
						metricOutput.write(Integer.toString(finalList.get(i).get(j).getMetricAlves()));
						metricOutput.write(Double.toString(finalList.get(i).get(j).getPercentageAlves()));
						metricOutput.endRecord();
					}
				}
				// If files already exists.
				else {

					// Write the metric values on the lists
					for (int j = 0; j < finalList.get(i).size(); j++) {
						metricOutput.write(Integer.toString(finalList.get(i).get(j).getMetricAlves()));
						metricOutput.write(Double.toString(finalList.get(i).get(j).getPercentageAlves()));
						metricOutput.endRecord();
					}
				}

				// Saving Final Result
				if (!alreadyExist2) {
					finalResultOutput.write("Metric");
					finalResultOutput.write("Lable");
					finalResultOutput.write("Percentage(%)");
					finalResultOutput.write("Value");
					finalResultOutput.endRecord();

					for (int j = 0; j < labels.length; j++) {
						finalResultOutput.write(fta.getMetricsListAlves().get(i));
						finalResultOutput.write(labels[j]);
						finalResultOutput.write(percentage[j]);
						finalResultOutput.write(Integer.toString(finalResultA.get(aux)));
						finalResultOutput.endRecord();
						aux++;
					}

					// If files already exists.
				} else {
					for (int j = 0; j < labels.length; j++) {
						finalResultOutput.write(fta.getMetricsListAlves().get(i));
						finalResultOutput.write(labels[j]);
						finalResultOutput.write(percentage[j]);
						finalResultOutput.write(Integer.toString(finalResultA.get(aux)));
						finalResultOutput.endRecord();
						aux++;
					}
				}

				metricOutput.close();
				finalResultOutput.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// Calculate final results.
	public static void calculateResults() {
		// For each metric, go through finals lists doing a sum of the
		// percentages.
		// When the percentage equals a border value, save the entity value and
		// break the loop.
		// Export the final entities values list to a class.
		// The border are the lower value from the categories below.
		// Low 0 - 70%
		// Moderate 70 - 80%
		// High 80 - 90%
		// Very High >90%
		AnalyseAlves fta = new AnalyseAlves();
		AlvesFinalResult afr = new AlvesFinalResult();

		// Loop through metrics files
		for (int i = 0; i < fta.getMetricsListAlves().size(); i++) {

			Double sum = finalList.get(i).get(0).getPercentageAlves();

			// Low
			finalResultA.add(finalList.get(i).get(0).getMetricAlves());

			// Moderate
			for (int j = 1; j < finalList.get(i).size(); j++) {

				if (finalList.get(i).get(0).getPercentageAlves() >= 70.0) {
					finalResultA.add(finalList.get(j).get(0).getMetricAlves());
					break;
				} else {
					sum += finalList.get(i).get(j).getPercentageAlves();
					if (sum >= 70.0) {
						finalResultA.add(finalList.get(i).get(j).getMetricAlves());
						break;
					}
				}
			}
			sum = finalList.get(i).get(0).getPercentageAlves();
			// High
			for (int j = 1; j < finalList.get(i).size(); j++) {

				if (finalList.get(i).get(0).getPercentageAlves() >= 80.0) {
					finalResultA.add(finalList.get(j).get(0).getMetricAlves());
					break;
				} else {
					sum += finalList.get(i).get(j).getPercentageAlves();
					if (sum >= 80.0) {
						finalResultA.add(finalList.get(i).get(j).getMetricAlves());
						break;
					}
				}
			}
			sum = finalList.get(i).get(0).getPercentageAlves();
			// Very High
			for (int j = 1; j < finalList.get(i).size(); j++) {

				if (finalList.get(i).get(0).getPercentageAlves() >= 90.0) {
					finalResultA.add(finalList.get(j).get(0).getMetricAlves());
					break;
				} else {
					sum += finalList.get(i).get(j).getPercentageAlves();
					if (sum >= 90.0) {
						finalResultA.add(finalList.get(i).get(j).getMetricAlves());
						break;
					}
				}
			}
			sum = finalList.get(i).get(0).getPercentageAlves();
		} // End for through metrics.
		afr.setResultEntitiesAlves(finalResultA);
	}
}

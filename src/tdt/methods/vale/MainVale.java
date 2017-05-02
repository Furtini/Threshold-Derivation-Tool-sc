package tdt.methods.vale;

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
 * Departamento da Ci�ncia da Computa��o
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */

public class MainVale {

	// Create the final lists, to store the data after pondering.
	static List<List<Integer>> finalmetricsListV = new ArrayList<List<Integer>>();
	static List<List<Double>> finalPercentageListV = new ArrayList<List<Double>>();

	static List<Integer> finalResultV = new ArrayList<Integer>();

	public static void run() throws IOException {

		AnalyseVale fta = new AnalyseVale();

		// Array to save the metrics column on the file.
		int[] mLocations = new int[fta.getMetricsListVale().size()];

		// Create Lists for every selected metric.
		// And a list for the respective weight calculation.
		List<List<Integer>> metricsListV = new ArrayList<List<Integer>>();
		List<Double> percentageList = new ArrayList<Double>();
		for (int i = 0; i < fta.getMetricsListVale().size(); i++) {
			List<Integer> metric = new ArrayList<>();
			metricsListV.add(metric);
			// Final
			List<Integer> finalMetric = new ArrayList<>();
			List<Double> finalPercentage = new ArrayList<>();
			finalmetricsListV.add(finalMetric);
			finalPercentageListV.add(finalPercentage);
		}
		// Find the columns of the selected metrics in the file.
		try {
			String strFile = fta.getFilesPathVale().get(0);
			CSVReader reader = new CSVReader(new FileReader(strFile));
			String[] nextLine;

			nextLine = reader.readNext();

			String[] line = nextLine[0].split(";");

			for (int i = 0; i < fta.getMetricsListVale().size(); i++) {

				for (int j = 0; j < line.length; j++) {
					if (line[j].equals(fta.getMetricsListVale().get(i))) {
						mLocations[i] = j;
					}
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO: File not found.!");
			e.printStackTrace();
		}
		// Loop through all files to set entities values.
		for (int k = 0; k < fta.getFilesPathVale().size(); k++) {
			// Getting values.
			File csvFile = new File(fta.getFilesPathVale().get(k));

			try {
				String linesOfFile = new String();
				Scanner rd = new Scanner(csvFile);

				rd.nextLine();

				// Loop while the file has lines.
				while (rd.hasNext()) {
					linesOfFile = rd.nextLine();
					String[] valueBetweenComma = linesOfFile.split(";");

					// Loop through all metrics
					for (int i = 0; i < fta.getMetricsListVale().size(); i++) {
						metricsListV.get(i).add(Integer.parseInt(valueBetweenComma[mLocations[i]]));
					}

				}

				rd.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "ERRO: File not found.!");
			}
		} // end of loop through files.

		// Adding the percentage value of the formula for each entity
		// Formula ( 1 / totalEntities * 100)
		long totalEntities = metricsListV.get(0).size();
		double percentage = (1.0 / (double) totalEntities) * 100.0;
		// Adding the percentage value to the percentage list
		for (int i = 0; i < totalEntities; i++) {
			percentageList.add(percentage);
		}

		// Sorting Lists
		// Remove Duplicates.
		for (int i = 0; i < mLocations.length; i++) {

			Collections.sort(metricsListV.get(i));

			removeDuplicates(metricsListV.get(i), finalmetricsListV.get(i), percentageList,
					finalPercentageListV.get(i));
		}

	} // end run() method

	// remove duplicates method
	private static void removeDuplicates(List<Integer> metric, List<Integer> finalMetric, List<Double> percen,
			List<Double> finalPercen) {

		int i;
		int m = metric.get(0);

		double sum = percen.get(0);

		for (i = 1; i < percen.size(); i++) {

			if (m == metric.get(i)) {

				sum += percen.get(i);

			} else {
				finalMetric.add(metric.get(i - 1));
				finalPercen.add(sum);

				m = metric.get(i);
				sum = percen.get(i);
			}

		}
		// Adding final sum.
		finalMetric.add(metric.get(i - 1));
		finalPercen.add(sum);

	}

	// Save final list on csv file
	public static void saveVale() {

		AnalyseVale fta = new AnalyseVale();

		final String[] percentage = { "0-3", "3-15", "15-90", "90-95", ">95" };
		final String[] labels = { "Very-Low", "Low", "Moderate", "High", "Very-High" };

		int aux = 0;

		// Creating method's directory
		new File(fta.getSavePathVale() + File.separator + "Vale's Method Output").mkdir();
		String path = fta.getSavePathVale() + File.separator + "Vale's Method Output";
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
		for (int i = 0; i < fta.getMetricsListVale().size(); i++) {

			// Create output file path.
			String outputFile = path + File.separator + fta.getMetricsListVale().get(i) + ".csv";
			String outputFinalResult = path + File.separator + "Final-Result" + ".csv";

			// Check if file already exists.
			boolean alreadyExist = new File(outputFile).exists();
			boolean alreadyExist2 = new File(outputFinalResult).exists();

			try {
				CsvWriter metricOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
				CsvWriter finalResultOutput = new CsvWriter(new FileWriter(outputFinalResult, true), ';');

				// If file don't exists, write the header line
				if (!alreadyExist) {

					metricOutput.write(fta.getMetricsListVale().get(i));
					metricOutput.write("Percentage");
					metricOutput.endRecord();

					// Write the metric values on the lists
					for (int j = 0; j < finalmetricsListV.get(i).size(); j++) {
						metricOutput.write(Integer.toString(finalmetricsListV.get(i).get(j)));
						metricOutput.write(Double.toString(finalPercentageListV.get(i).get(j)));
						metricOutput.endRecord();
					}
				}
				// If files already exists.
				else {

					// Write the metric values on the lists
					for (int j = 0; j < finalmetricsListV.get(i).size(); j++) {
						metricOutput.write(Integer.toString(finalmetricsListV.get(i).get(j)));
						metricOutput.write(Double.toString(finalPercentageListV.get(i).get(j)));
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
						finalResultOutput.write(fta.getMetricsListVale().get(i));
						finalResultOutput.write(labels[j]);
						finalResultOutput.write(percentage[j]);
						finalResultOutput.write(Integer.toString(finalResultV.get(aux)));
						finalResultOutput.endRecord();
						aux++;
					}

					// If files already exists.
				} else {
					for (int j = 0; j < labels.length; j++) {
						finalResultOutput.write(fta.getMetricsListVale().get(i));
						finalResultOutput.write(labels[j]);
						finalResultOutput.write(percentage[j]);
						finalResultOutput.write(Integer.toString(finalResultV.get(aux)));
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

	// Calculate the results.
	public static void calculateResult() {
		// For each metric, go through finals lists doing a sum of the
		// percentages.
		// When the percentage equals a border value, save the entity value and
		// break the loop.
		// Export the final entities values list to a class.
		// The border are the lower value from the categories below.
		// Very Low 0-3%
		// Low 3-15%
		// Moderate 15-90%
		// High 90-95%
		// Very High >95%
		AnalyseVale fta = new AnalyseVale();
		ValeFinalResult vfr = new ValeFinalResult();

		// Loop through metrics files.
		for (int i = 0; i < fta.getMetricsListVale().size(); i++) {

			Double sum = finalPercentageListV.get(i).get(0);

			// Very Low
			finalResultV.add(finalmetricsListV.get(i).get(0));

			// Low
			for (int j = 1; j < finalPercentageListV.get(i).size(); j++) {

				if (finalPercentageListV.get(i).get(0) >= 3.0) {
					finalResultV.add(finalmetricsListV.get(i).get(0));
					break;
				} else {
					sum += finalPercentageListV.get(i).get(j);
					if (sum >= 3.0) {
						finalResultV.add(finalmetricsListV.get(i).get(j));
						break;
					}
				}

			}
			sum = finalPercentageListV.get(i).get(0);
			// Moderate
			for (int j = 1; j < finalPercentageListV.get(i).size(); j++) {

				if (finalPercentageListV.get(i).get(0) >= 15.0) {
					finalResultV.add(finalmetricsListV.get(i).get(0));
					break;
				} else {
					sum += finalPercentageListV.get(i).get(j);
					if (sum >= 15.0) {
						finalResultV.add(finalmetricsListV.get(i).get(j));
						break;
					}
				}
			}
			sum = finalPercentageListV.get(i).get(0);
			// High
			for (int j = 1; j < finalPercentageListV.get(i).size(); j++) {

				if (finalPercentageListV.get(i).get(0) >= 90.0) {
					finalResultV.add(finalmetricsListV.get(i).get(0));
					break;
				} else {
					sum += finalPercentageListV.get(i).get(j);
					if (sum >= 90.0) {
						finalResultV.add(finalmetricsListV.get(i).get(j));
						break;
					}
				}
			}
			sum = finalPercentageListV.get(i).get(0);
			// Very High
			for (int j = 1; j < finalPercentageListV.get(i).size(); j++) {

				if (finalPercentageListV.get(i).get(0) >= 95.0) {
					finalResultV.add(finalmetricsListV.get(i).get(0));
					break;
				} else {
					sum += finalPercentageListV.get(i).get(j);
					if (sum >= 95.0) {
						finalResultV.add(finalmetricsListV.get(i).get(j));
						break;
					}
				}
			}
			sum = finalPercentageListV.get(i).get(0);

		} // End external for.
		vfr.setResultEntitiesVale(finalResultV);
	}

	public void clearVale() {

		finalmetricsListV.clear();
		finalPercentageListV.clear();
		finalResultV.clear();
	}
}
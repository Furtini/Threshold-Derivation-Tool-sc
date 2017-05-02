package tdt.methods.ferreira;

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

public class MainFerreira {

	// Create the final lists, to store the data after pondering.
	static List<List<Integer>> finalMetricsListF = new ArrayList<List<Integer>>();
	static List<List<Double>> finalPercentageListF = new ArrayList<List<Double>>();

	static List<Integer> finalResultF = new ArrayList<Integer>();

	public static void run() throws IOException {

		AnalyseFerreira fta = new AnalyseFerreira();

		// Array to save the metrics column on the file.
		int[] mLocations = new int[fta.getMetricsListFerreira().size()];

		// Create Lists for every selected metric.
		// And a list for the respective weight calculation.
		List<List<Integer>> metricsListV = new ArrayList<List<Integer>>();
		List<Double> percentageList = new ArrayList<Double>();
		for (int i = 0; i < fta.getMetricsListFerreira().size(); i++) {
			List<Integer> metric = new ArrayList<>();
			metricsListV.add(metric);
			// Final
			List<Integer> finalMetric = new ArrayList<>();
			List<Double> finalPercentage = new ArrayList<>();
			finalMetricsListF.add(finalMetric);
			finalPercentageListF.add(finalPercentage);
		}
		// Find the columns of the selected metrics in the file.
		try {
			String strFile = fta.getFilesPathFerreira().get(0);
			CSVReader reader = new CSVReader(new FileReader(strFile));
			String[] nextLine;

			nextLine = reader.readNext();

			String[] line = nextLine[0].split(";");

			for (int i = 0; i < fta.getMetricsListFerreira().size(); i++) {

				for (int j = 0; j < line.length; j++) {
					if (line[j].equals(fta.getMetricsListFerreira().get(i))) {
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
		for (int k = 0; k < fta.getFilesPathFerreira().size(); k++) {
			// Getting values.
			File csvFile = new File(fta.getFilesPathFerreira().get(k));

			try {
				String linesOfFile = new String();
				Scanner rd = new Scanner(csvFile);

				rd.nextLine();

				// Loop while the file has lines.
				while (rd.hasNext()) {
					linesOfFile = rd.nextLine();
					String[] valueBetweenComma = linesOfFile.split(";");

					// Loop through all metrics
					for (int i = 0; i < fta.getMetricsListFerreira().size(); i++) {
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

			removeDuplicates(metricsListV.get(i), finalMetricsListF.get(i), percentageList,
					finalPercentageListF.get(i));
		}
	}

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

	public static void calculateResult() {
		// For each metric, go through finals lists doing a sum of the
		// percentages.
		// When the percentage equals a border value, save the entity value and
		// break the loop.
		// Export the final entities values list to a class.
		// The border are the lower value from the categories below.
		// For the default limits
		// Good 70%
		// Regular 80%
		// Bad 90%

		AnalyseFerreira fta = new AnalyseFerreira();
		FerreiraFinalResult ffr = new FerreiraFinalResult();

		// Loop through metrics files.
		for (int i = 0; i < fta.getMetricsListFerreira().size(); i++) {

			Double sum = finalPercentageListF.get(i).get(0);

			// Good (0 - Threshold 1)
			for (int j = 1; j < finalPercentageListF.get(i).size(); j++) {

				if (finalPercentageListF.get(i).get(0) >= fta.getThresholdOne()) {
					finalResultF.add(finalMetricsListF.get(j).get(0));
					break;
				} else {
					sum += finalPercentageListF.get(i).get(j);
					if (sum >= fta.getThresholdOne()) {
						finalResultF.add(finalMetricsListF.get(i).get(j));
						break;
					}
				}
			}
			sum = finalPercentageListF.get(i).get(0);
			// Regular (Threshold 1 - threshold 2)
			for (int j = 1; j < finalPercentageListF.get(i).size(); j++) {

				if (finalPercentageListF.get(i).get(0) >= fta.getThresholdTwo()) {
					finalResultF.add(finalMetricsListF.get(j).get(0));
					break;
				} else {
					sum += finalPercentageListF.get(i).get(j);
					if (sum >= fta.getThresholdTwo()) {
						finalResultF.add(finalMetricsListF.get(i).get(j));
						break;
					}
				}
			}
			sum = finalPercentageListF.get(i).get(0);
			// Bad (Threshold 2 - 100)
			for (int j = 1; j < finalPercentageListF.get(i).size(); j++) {

				if (finalPercentageListF.get(i).get(0) >= fta.getThresholdTwo()) {
					finalResultF.add(finalMetricsListF.get(j).get(0));
					break;
				} else {
					sum += finalPercentageListF.get(i).get(j);
					if (sum >= fta.getThresholdTwo()) {
						finalResultF.add(finalMetricsListF.get(i).get(j));
						break;
					}
				}
			}
			sum = finalPercentageListF.get(i).get(0);
		} // End of external for.
		ffr.setResultEntitiesFerreira(finalResultF);

	}

	// Save final list on csv file
	public static void saveVale() {

		AnalyseFerreira fta = new AnalyseFerreira();

		final String[] percentage = { "<" + String.valueOf(fta.getThresholdOne()),
				String.valueOf(fta.getThresholdOne()) + "-" + String.valueOf(fta.getThresholdTwo()),
				String.valueOf(fta.getThresholdTwo()) + ">" };
		final String[] labels = { "Good", "Regular", "Bad" };

		int aux = 0;

		// Creating method's directory
		new File(fta.getSavePathFerreira() + File.separator + "Ferreira's Method Output").mkdir();
		String path = fta.getSavePathFerreira() + File.separator + "Ferreira's Method Output";
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
		for (int i = 0; i < fta.getMetricsListFerreira().size(); i++) {

			// Create output file path.
			String outputFile = path + File.separator + fta.getMetricsListFerreira().get(i) + ".csv";
			String outputFinalResult = path + File.separator + "Final-Result" + ".csv";

			// Check if file already exists.
			boolean alreadyExist = new File(outputFile).exists();
			boolean alreadyExist2 = new File(outputFinalResult).exists();

			try {
				CsvWriter metricOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
				CsvWriter finalResultOutput = new CsvWriter(new FileWriter(outputFinalResult, true), ';');

				// If file don't exists, write the header line
				if (!alreadyExist) {

					metricOutput.write(fta.getMetricsListFerreira().get(i));
					metricOutput.write("Percentage");
					metricOutput.endRecord();

					// Write the metric values on the lists
					for (int j = 0; j < finalMetricsListF.get(i).size(); j++) {
						metricOutput.write(Integer.toString(finalMetricsListF.get(i).get(j)));
						metricOutput.write(Double.toString(finalPercentageListF.get(i).get(j)));
						metricOutput.endRecord();
					}
				}
				// If files already exists.
				else {

					// Write the metric values on the lists
					for (int j = 0; j < finalMetricsListF.get(i).size(); j++) {
						metricOutput.write(Integer.toString(finalMetricsListF.get(i).get(j)));
						metricOutput.write(Double.toString(finalPercentageListF.get(i).get(j)));
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

					finalResultOutput.write(fta.getMetricsListFerreira().get(i));
					finalResultOutput.write(labels[0]);
					finalResultOutput.write(percentage[0]);
					finalResultOutput.write("<" + Integer.toString(finalResultF.get(aux)));
					finalResultOutput.endRecord();
					aux++;

					finalResultOutput.write(fta.getMetricsListFerreira().get(i));
					finalResultOutput.write(labels[1]);
					finalResultOutput.write(percentage[1]);
					finalResultOutput.write(Integer.toString(finalResultF.get(aux - 1)) + "-"
							+ Integer.toString(finalResultF.get(aux)));
					finalResultOutput.endRecord();
					aux++;

					finalResultOutput.write(fta.getMetricsListFerreira().get(i));
					finalResultOutput.write(labels[2]);
					finalResultOutput.write(percentage[2]);
					finalResultOutput.write(">" + Integer.toString(finalResultF.get(aux)));
					finalResultOutput.endRecord();
					aux++;

					// If files already exists.
				} else {

					finalResultOutput.write(fta.getMetricsListFerreira().get(i));
					finalResultOutput.write(labels[0]);
					finalResultOutput.write(percentage[0]);
					finalResultOutput.write("<" + Integer.toString(finalResultF.get(aux)));
					finalResultOutput.endRecord();
					aux++;

					finalResultOutput.write(fta.getMetricsListFerreira().get(i));
					finalResultOutput.write(labels[1]);
					finalResultOutput.write(percentage[1]);
					finalResultOutput.write(Integer.toString(finalResultF.get(aux - 1)) + "-"
							+ Integer.toString(finalResultF.get(aux)));
					finalResultOutput.endRecord();
					aux++;

					finalResultOutput.write(fta.getMetricsListFerreira().get(i));
					finalResultOutput.write(labels[2]);
					finalResultOutput.write(percentage[2]);
					finalResultOutput.write(">" + Integer.toString(finalResultF.get(aux)));
					finalResultOutput.endRecord();
					aux++;
				}
				metricOutput.close();
				finalResultOutput.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

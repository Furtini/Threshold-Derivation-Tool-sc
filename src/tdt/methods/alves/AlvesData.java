package tdt.methods.alves;

/* 
 * Universidade Federal De Minas Gerais
 * Departamento da Ciência da Computação
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */

// This class represents the type of file that the method requires.
// That means, a metric followed by the loc/total_Loc percentage.
public class AlvesData implements Comparable<AlvesData> {

	private int metricAlves;
	private double percentageAlves;

	// Method for ascending order of a list of AlvesData objects.
	public int compareTo(AlvesData compareAlvesData) {

		int compareValue = ((AlvesData) compareAlvesData).getMetricAlves();

		// Ascending order
		return this.metricAlves - compareValue;
	}

	@Override
	public String toString() {
		return "[" + getMetricAlves() + ", " + getPercentageAlves() + "]";
	}

	// Constructor
	public AlvesData(int metric, double percentage) {
		super();
		this.metricAlves = metric;
		this.percentageAlves = percentage;
	}

	// Getters and Setters
	public int getMetricAlves() {
		return metricAlves;
	}

	public void setMetricAlves(int metric) {
		this.metricAlves = metric;
	}

	public double getPercentageAlves() {
		return percentageAlves;
	}

	public void setPercentageAlves(double percentage) {
		this.percentageAlves = percentage;
	}

}

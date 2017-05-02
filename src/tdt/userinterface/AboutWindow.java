package tdt.userinterface;

import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

/* 
 * Universidade Federal De Minas Gerais
 * Departamento da Ciência da Computação
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */
public class AboutWindow extends JFrame {

	private static final long serialVersionUID = 4461901270578101282L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public AboutWindow() {
		setTitle("About");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
				


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		JTextPane txtpnTeste = new JTextPane();
		txtpnTeste.setBackground(SystemColor.menu);
		txtpnTeste.setText("TDTool (Threshold Derivation Tool)\n\n"
				+ "TDTool is tool to support threshold derivation for software metrics. The supporting tool provides 4 different derivation methods, proposed by Alves et al. (2010), Ferreira et al. (2012), Oliveira et al. (2014), and Vale and Figeuredo (2015). \n\n"
				+ " - First, a user selects the thershold derivation method to be executed. \n"
				+ " - Second, the user selects a set of CSV files with software metrics extracted from benchmarks of systems. \n"
				+ " - Third, the user selects metrics from the CSV files for threshold derivation. \n"
				+ " - Fourth, TDTool derives thresholds that are reported via user interface (as a table) and may be exported as CSV files. \n\n"

				+ "Note: the supporting tool for Oliveira's method, RTTool (see: https://github.com/ASERG-UFMG/RTTool), was completely integrated with TDTool. We did not change the implementation of the proposed method (input, processing, and output). \n\n"

				+ "Website of TDTool: http://labsoft.dcc.ufmg.br/doku.php?id=about:tdtool \n\n"
				+ "References:\n"
				+ "Alves et al. (2010) 'Deriving Metric Threshold from Benchmark Data' \n"
				+ "Ferreira et al. (2012) 'Identifying Thresholds for Object-Oriented Software Metrics' \n"
				+ "Oliveira et al. (2014) 'Extracting Relative Thresholds for Source Code Metrics' \n"
				+ "Vale and Figueiredo (2015) 'A Method to Derive Metric Thresholds for Software Product Lines'");
		txtpnTeste.setCaretPosition(0);
		scrollPane.setViewportView(txtpnTeste);
		contentPane.setLayout(gl_contentPane);
	}
}
package tdt.userinterface.ferreirawindow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import tdt.methods.ferreira.AnalyseFerreira;
import tdt.methods.ferreira.FerreiraFinalResult;
import tdt.methods.ferreira.MainFerreira;

/* 
 * Universidade Federal De Minas Gerais
 * Departamento da Ciência da Computação
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */
public class FerreiraWindow extends JFrame {

	private static final long serialVersionUID = 1020914172402853892L;
	private JPanel contentPane;
	private JPanel panelOverview;
	private JTabbedPane tabbedPane;
	private JPanel panelOverviewButtons;
	private JButton btnCancelOverview;
	private JButton btnStartOverview;
	private JPanel panelSelectedFiles;
	private JScrollPane scrollPaneSelectFiles;
	private JButton btnSelectFiles;
	private JTextArea textAreaSaveFolder;
	private JPanel panelSelectFilesButtons;
	private JButton buttonCancelSelectFiles;
	private JButton btnNextSelectFiles;
	private JButton btnBackSelectFiles;
	private JButton btnOutputFolder;
	private JPanel panelSelectMetrics;
	private JList<String> listSelectFiles;
	private JPanel panelMetricsButtons;
	private JButton btnCancelMetric;
	private JButton btnBackMetric;
	private JButton btnNextMetric;
	private JScrollPane scrollPaneMetricsAvaliable;
	private JList<String> listMA;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JList<String> listMS;
	private JScrollPane scrollPaneMetricsSelected;
	private JPanel panelChooseLabel;
	private JPanel panelResults;
	private JPanel panelResultsButtons;
	private JButton btnClose;
	private JButton btnBackResults;
	private JButton btnSave;
	private JScrollPane scrollPaneResults;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panelLabel;
	private JPanel panelLabelButtons;
	private JButton btnCancelLabel;
	private JButton btnBackLabel;
	private JButton btnRunMetric;
	private JLabel lblRegular;
	private JTextField textFieldThresh1;
	private JTextField textFieldThresh2;
	private JLabel lblDefaultValues;
	private JLabel lblGood;
	private JLabel lblRegular_1;
	// =================
	// Create the frame.
	// =================
	private JScrollPane scrollPaneOverview;
	private JTextArea txtrOverview;
	private JTable table;
	// Default Models
	private DefaultTableModel modelTable;
	private static DefaultListModel<String> modelSelectedFiles = new DefaultListModel<String>();
	private static DefaultListModel<String> modelMetricsAvailable = new DefaultListModel<String>();
	private static DefaultListModel<String> modelMetricsSelected = new DefaultListModel<String>();

	private String filePath = "";
	private ArrayList<String> filesPath = new ArrayList<String>();
	private ArrayList<String> filesSelected = new ArrayList<String>();
	private ArrayList<String> avaliableMetrics = new ArrayList<String>();
	private ArrayList<String> metricsList = new ArrayList<String>();
	
	private FileSystemView filesys = FileSystemView.getFileSystemView();

	public FerreiraWindow() {

		initComponents();
		createEvents();
	}

	private void initComponents() {

		setTitle("Ferreira's Method");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(39, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE));

		panelOverview = new JPanel();
		panelOverview.setToolTipText("Overview");
		tabbedPane.addTab("Overview", null, panelOverview, null);

		panelOverviewButtons = new JPanel();

		scrollPaneOverview = new JScrollPane();
		GroupLayout gl_panelOverview = new GroupLayout(panelOverview);
		gl_panelOverview.setHorizontalGroup(gl_panelOverview.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelOverview.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOverview.createParallelGroup(Alignment.LEADING)
								.addComponent(panelOverviewButtons, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 590,
										Short.MAX_VALUE)
						.addComponent(scrollPaneOverview, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelOverview
				.setVerticalGroup(gl_panelOverview.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOverview.createSequentialGroup().addContainerGap()
								.addComponent(scrollPaneOverview, GroupLayout.PREFERRED_SIZE, 316,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelOverviewButtons, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
								.addContainerGap()));

		txtrOverview = new JTextArea();
		txtrOverview.setLineWrap(true);
		txtrOverview.setWrapStyleWord(true);
		txtrOverview.setBackground(SystemColor.controlHighlight);
		txtrOverview.setEditable(false);
		txtrOverview.setText("Ferreira et al. (2012) proposes this method in 'Identifying Thresholds for Object-Oriented Software Metrics'.\n\n"
				+ "The method consists of 5 steps:\n\n"
				+ "Step 1. Metrics Extraction:\n"
				+ "\tFor each system, and for each entity from the system (it may be a class, for instance), record the metric value (metric values of entity from the same system must be in the same input file).\n\n"
				+ "Step 2. Weight Ratio calculation:\n"
				+ "\tFor each entity, compute the weight percentage with respect to the total number of entities. For this purpose, divide one by the total number of entities, and them multiply by one hundred.\n\n"
				+ "Step 3. Sort in Ascending Order:\n"
				+ "\tSort the metric values in ascending order. Then, extract the maximal metric value that represents 1%, 2%, …, 100% of the weight (this is equivalent to compute a density function, in which x-axis represents the weight ratio (0 - 100%), and y-axis represents the metric scale).\n\n"
				+ "Step 4. Entity Aggregation:\n"
				+ "\tAggregate all entities per metric value, which is equivalent to compute a weighted histogram (the sum of all bins must be 100%).\n\n"
				+ "Step 5. Threshold Derivation:\n\n"
				+ "\tDerive thresholds by choosing the percentage of metric values you want to represent.\n\n"
				+ "Input: a collection of CSV file with metric values, one file per system from a benchmark, or a single CSV file with all entities from the entire benchmark. The tool also receives two thresholds informed by the user (we defined the thresholds 70% and 90% as default).\n\n"
				+ "Output: a table with three labels: good, regular, and bad. The user informs the values that define these labels. This tool also provides a CSV output file with the derived thresholds.\n\n"
				+ "This tool also provides a CSV file for each metric selected by user for threshold derivation."
				);
		txtrOverview.setCaretPosition(0);
		
		scrollPaneOverview.setViewportView(txtrOverview);
		panelOverviewButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCancelOverview = new JButton("Cancel");
		btnCancelOverview
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/cancel.png")));

		panelOverviewButtons.add(btnCancelOverview);

		btnStartOverview = new JButton("Start");
		btnStartOverview
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/next.png")));

		panelOverviewButtons.add(btnStartOverview);
		panelOverview.setLayout(gl_panelOverview);

		panelSelectedFiles = new JPanel();
		tabbedPane.addTab("Select Files", null, panelSelectedFiles, null);

		scrollPaneSelectFiles = new JScrollPane();

		btnSelectFiles = new JButton("Select Files");
		btnSelectFiles.setIcon(
				new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/SelectetFiles.png")));

		textAreaSaveFolder = new JTextArea();
		textAreaSaveFolder.setEditable(false);

		panelSelectFilesButtons = new JPanel();
		panelSelectFilesButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		buttonCancelSelectFiles = new JButton("Cancel");
		buttonCancelSelectFiles
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/cancel.png")));
		panelSelectFilesButtons.add(buttonCancelSelectFiles);

		btnBackSelectFiles = new JButton("Back");
		btnBackSelectFiles
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/back.png")));
		panelSelectFilesButtons.add(btnBackSelectFiles);

		btnNextSelectFiles = new JButton("Next");
		btnNextSelectFiles
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/next.png")));
		panelSelectFilesButtons.add(btnNextSelectFiles);

		btnOutputFolder = new JButton("Output Folder");
		btnOutputFolder.setIcon(
				new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/SelectetFiles.png")));

		listSelectFiles = new JList<String>(modelSelectedFiles);
		scrollPaneSelectFiles.setViewportView(listSelectFiles);
		GroupLayout gl_panelSelectedFiles = new GroupLayout(panelSelectedFiles);
		gl_panelSelectedFiles.setHorizontalGroup(gl_panelSelectedFiles.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelectedFiles.createSequentialGroup().addGap(12)
						.addGroup(gl_panelSelectedFiles.createParallelGroup(Alignment.LEADING)
								.addComponent(panelSelectFilesButtons, GroupLayout.PREFERRED_SIZE, 589,
										GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelSelectedFiles.createSequentialGroup()
								.addGroup(gl_panelSelectedFiles.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnOutputFolder, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSelectFiles, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_panelSelectedFiles.createParallelGroup(Alignment.LEADING)
										.addComponent(textAreaSaveFolder, GroupLayout.PREFERRED_SIZE, 420,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPaneSelectFiles, GroupLayout.PREFERRED_SIZE, 420,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap()))));
		gl_panelSelectedFiles.setVerticalGroup(gl_panelSelectedFiles.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelectedFiles.createSequentialGroup().addGap(16)
						.addGroup(gl_panelSelectedFiles.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSelectFiles).addComponent(scrollPaneSelectFiles,
										GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelSelectedFiles.createParallelGroup(Alignment.LEADING)
								.addComponent(textAreaSaveFolder, GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOutputFolder))
						.addGap(13).addComponent(panelSelectFilesButtons, GroupLayout.PREFERRED_SIZE, 59,
								GroupLayout.PREFERRED_SIZE)));
		panelSelectedFiles.setLayout(gl_panelSelectedFiles);

		panelSelectMetrics = new JPanel();
		tabbedPane.addTab("Select Metrics", null, panelSelectMetrics, null);

		panelMetricsButtons = new JPanel();
		panelMetricsButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCancelMetric = new JButton("Cancel");
		btnCancelMetric
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/cancel.png")));
		panelMetricsButtons.add(btnCancelMetric);

		btnBackMetric = new JButton("Back");
		btnBackMetric.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/back.png")));
		panelMetricsButtons.add(btnBackMetric);

		btnNextMetric = new JButton("Next");
		btnNextMetric.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/next.png")));
		panelMetricsButtons.add(btnNextMetric);

		JLabel lblMetrics = new JLabel("Metrics avaliable:");
		lblMetrics.setFont(new Font("Tahoma", Font.BOLD, 13));

		scrollPaneMetricsAvaliable = new JScrollPane();

		button1 = new JButton(">");
		button1.setFont(new Font("Tahoma", Font.BOLD, 11));

		button2 = new JButton(">>");
		button2.setFont(new Font("Tahoma", Font.BOLD, 11));

		button3 = new JButton("<<");
		button3.setFont(new Font("Tahoma", Font.BOLD, 11));

		button4 = new JButton("<");
		button4.setFont(new Font("Tahoma", Font.BOLD, 11));

		scrollPaneMetricsSelected = new JScrollPane();

		JLabel lblMetricsSelected = new JLabel("Metrics selected:");
		lblMetricsSelected.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		listMS = new JList<String>(modelMetricsSelected);
		scrollPaneMetricsSelected.setViewportView(listMS);

		listMA = new JList<String>(modelMetricsAvailable);
		scrollPaneMetricsAvaliable.setViewportView(listMA);
		GroupLayout gl_panelSelectMetrics = new GroupLayout(panelSelectMetrics);
		gl_panelSelectMetrics.setHorizontalGroup(gl_panelSelectMetrics.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelectMetrics.createSequentialGroup().addGroup(gl_panelSelectMetrics
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSelectMetrics.createSequentialGroup().addGap(12).addComponent(
								panelMetricsButtons, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSelectMetrics.createSequentialGroup().addGap(86)
								.addGroup(gl_panelSelectMetrics.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelSelectMetrics.createSequentialGroup()
												.addComponent(scrollPaneMetricsAvaliable, GroupLayout.PREFERRED_SIZE,
														187, GroupLayout.PREFERRED_SIZE)
												.addGap(6)
												.addGroup(gl_panelSelectMetrics.createParallelGroup(Alignment.LEADING)
														.addComponent(button1, GroupLayout.PREFERRED_SIZE, 52,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(button2, GroupLayout.PREFERRED_SIZE, 52,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(button3, GroupLayout.PREFERRED_SIZE, 52,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(button4, GroupLayout.PREFERRED_SIZE, 52,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panelSelectMetrics.createSequentialGroup().addComponent(lblMetrics)
												.addGap(6).addComponent(lblNewLabel)))
								.addGap(6)
								.addGroup(gl_panelSelectMetrics.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelSelectMetrics.createSequentialGroup()
												.addComponent(lblMetricsSelected)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNewLabel_1))
										.addComponent(scrollPaneMetricsSelected, GroupLayout.PREFERRED_SIZE, 187,
												GroupLayout.PREFERRED_SIZE))))
						.addGap(9)));
		gl_panelSelectMetrics
				.setVerticalGroup(
						gl_panelSelectMetrics.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_panelSelectMetrics
												.createSequentialGroup().addGap(
														12)
												.addGroup(gl_panelSelectMetrics.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panelSelectMetrics
																.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblNewLabel_1)
																.addComponent(lblMetricsSelected))
										.addComponent(lblMetrics).addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelSelectMetrics.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneMetricsAvaliable, GroupLayout.PREFERRED_SIZE, 277,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelSelectMetrics.createSequentialGroup().addGap(72).addComponent(button1)
										.addGap(8).addComponent(button2).addGap(18).addComponent(button3).addGap(6)
										.addComponent(button4))
								.addComponent(scrollPaneMetricsSelected, GroupLayout.PREFERRED_SIZE, 277,
										GroupLayout.PREFERRED_SIZE)).addGap(18).addComponent(panelMetricsButtons,
												GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)));
		panelSelectMetrics.setLayout(gl_panelSelectMetrics);

		panelChooseLabel = new JPanel();
		panelChooseLabel.setBorder(null);
		tabbedPane.addTab("Choose Label", null, panelChooseLabel, null);

		panelLabel = new JPanel();
		panelLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelLabel.setBackground(SystemColor.inactiveCaption);

		panelLabelButtons = new JPanel();
		panelLabelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCancelLabel = new JButton("Cancel");

		btnCancelLabel
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/cancel.png")));
		panelLabelButtons.add(btnCancelLabel);

		btnBackLabel = new JButton("Back");

		btnBackLabel.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/back.png")));
		panelLabelButtons.add(btnBackLabel);

		btnRunMetric = new JButton("Run");

		btnRunMetric.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/run.png")));
		panelLabelButtons.add(btnRunMetric);

		JLabel lblPleaseChooseThe = new JLabel("Please choose the derivation limits:");
		lblPleaseChooseThe.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panelChooseLabel = new GroupLayout(panelChooseLabel);
		gl_panelChooseLabel.setHorizontalGroup(gl_panelChooseLabel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChooseLabel.createSequentialGroup()
						.addGroup(gl_panelChooseLabel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelChooseLabel.createSequentialGroup().addContainerGap().addComponent(
										panelLabelButtons, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelChooseLabel.createSequentialGroup().addGap(159)
								.addComponent(lblPleaseChooseThe))
						.addGroup(gl_panelChooseLabel.createSequentialGroup().addGap(208).addComponent(panelLabel,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(11, Short.MAX_VALUE)));
		gl_panelChooseLabel
				.setVerticalGroup(
						gl_panelChooseLabel.createParallelGroup(Alignment.TRAILING).addGroup(
								gl_panelChooseLabel.createSequentialGroup().addContainerGap(37, Short.MAX_VALUE)
										.addComponent(lblPleaseChooseThe).addGap(18)
										.addComponent(panelLabel, GroupLayout.PREFERRED_SIZE, 229,
												GroupLayout.PREFERRED_SIZE)
										.addGap(29).addComponent(panelLabelButtons, GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		JLabel lblNewLabel_2 = new JLabel("Threshold 1:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblRegular = new JLabel("Threshold 2:");
		lblRegular.setFont(new Font("Tahoma", Font.PLAIN, 13));

		textFieldThresh1 = new JTextField();
		textFieldThresh1.setColumns(10);

		textFieldThresh2 = new JTextField();
		textFieldThresh2.setColumns(10);

		lblDefaultValues = new JLabel("Default Values:");
		lblDefaultValues.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblGood = new JLabel("Threshold 1: 70%");

		lblRegular_1 = new JLabel("Threshold 2: 90%");
		GroupLayout gl_panelLabel = new GroupLayout(panelLabel);
		gl_panelLabel.setHorizontalGroup(gl_panelLabel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLabel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelLabel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelLabel.createSequentialGroup().addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldThresh1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(6))
						.addGroup(Alignment.LEADING,
								gl_panelLabel.createSequentialGroup().addComponent(lblRegular)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldThresh2,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				.addGroup(Alignment.LEADING,
						gl_panelLabel.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelLabel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelLabel.createSequentialGroup().addGap(10)
												.addGroup(gl_panelLabel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblRegular_1).addComponent(lblGood)))
								.addComponent(lblDefaultValues)).addContainerGap(10, Short.MAX_VALUE)));
		gl_panelLabel.setVerticalGroup(gl_panelLabel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLabel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelLabel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2)
								.addComponent(textFieldThresh1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panelLabel.createParallelGroup(Alignment.BASELINE).addComponent(lblRegular).addComponent(
						textFieldThresh2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)).addGap(41).addComponent(lblDefaultValues)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblGood)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblRegular_1)
				.addContainerGap(63, Short.MAX_VALUE)));
		panelLabel.setLayout(gl_panelLabel);
		panelChooseLabel.setLayout(gl_panelChooseLabel);

		panelResults = new JPanel();
		tabbedPane.addTab("Results", null, panelResults, null);

		panelResultsButtons = new JPanel();
		panelResultsButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnClose = new JButton("Close");
		btnClose.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/cancel.png")));
		panelResultsButtons.add(btnClose);

		btnBackResults = new JButton("Back");
		btnBackResults
				.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/back.png")));
		panelResultsButtons.add(btnBackResults);

		btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(FerreiraWindow.class.getResource("/tdt/userinterface/resources/save.png")));
		panelResultsButtons.add(btnSave);

		scrollPaneResults = new JScrollPane();
		GroupLayout gl_panelResults = new GroupLayout(panelResults);
		gl_panelResults.setHorizontalGroup(gl_panelResults.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResults.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelResults.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPaneResults, Alignment.LEADING).addComponent(panelResultsButtons,
										Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
						.addContainerGap(12, Short.MAX_VALUE)));
		gl_panelResults
				.setVerticalGroup(
						gl_panelResults.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelResults.createSequentialGroup().addContainerGap()
										.addComponent(scrollPaneResults, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelResultsButtons,
												GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		table = new JTable();
		scrollPaneResults.setViewportView(table);
		panelResults.setLayout(gl_panelResults);
		contentPane.setLayout(gl_contentPane);

	}

	private void createEvents() {
		btnCancelOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrOverview.setCaretPosition(0);
				tabbedPane.setSelectedIndex(0);
				dispose();
			}
		});

		btnStartOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});

		buttonCancelSelectFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
				txtrOverview.setCaretPosition(0);
				modelSelectedFiles.clear();
				modelMetricsAvailable.clear();
				modelMetricsSelected.clear();
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
				close();
			}
		});

		btnBackSelectFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				txtrOverview.setCaretPosition(0);
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
			}
		});

		btnNextSelectFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (listSelectFiles.getModel().getSize() == 0) {
					JOptionPane.showMessageDialog(null, "No file selected." + "\n" + "Plese select a file.");
				} else {

					try {
						if (textAreaSaveFolder.getText().isEmpty()){
							textAreaSaveFolder.setText(filesys.getHomeDirectory().getPath());
						}
						modelMetricsAvailable.clear();
						modelMetricsSelected.clear();

						findMetrics();

						tabbedPane.setSelectedIndex(2);

					} catch (IOException e1) {
						e1.printStackTrace();
					}

					lblNewLabel.setText(modelMetricsAvailable.size() + "");
					lblNewLabel_1.setText(modelMetricsSelected.size() + "");

				}

			}
		});

		btnSelectFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFiles();

			}
		});

		btnOutputFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser();
				jfc.setMultiSelectionEnabled(false);
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retorno = jfc.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					textAreaSaveFolder.setText(jfc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(null, "Inválid Folder");
				}
			}
		});

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!listMA.isSelectionEmpty()) {
					int index = listMA.getSelectedIndex();

					modelMetricsSelected.addElement(listMA.getSelectedValue());
					modelMetricsAvailable.removeElementAt(index);

					lblNewLabel.setText(modelMetricsAvailable.size() + "");
					lblNewLabel_1.setText(modelMetricsSelected.size() + "");
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!avaliableMetrics.isEmpty()) {
					for (int i = 0; i < modelMetricsAvailable.size(); i++) {
						modelMetricsSelected.addElement(modelMetricsAvailable.get(i));
					}
					modelMetricsAvailable.removeAllElements();

					lblNewLabel.setText(modelMetricsAvailable.size() + "");
					lblNewLabel_1.setText(modelMetricsSelected.size() + "");
				}
			}
		});

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!avaliableMetrics.isEmpty()) {
					for (int i = 0; i < modelMetricsSelected.size(); i++) {
						modelMetricsAvailable.addElement(modelMetricsSelected.get(i));
					}
					modelMetricsSelected.removeAllElements();

					lblNewLabel.setText(modelMetricsAvailable.size() + "");
					lblNewLabel_1.setText(modelMetricsSelected.size() + "");
				}
			}
		});

		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listMS.isSelectionEmpty()) {
					int index = listMS.getSelectedIndex();

					modelMetricsAvailable.addElement(listMS.getSelectedValue());
					modelMetricsSelected.removeElementAt(index);

					lblNewLabel.setText(modelMetricsAvailable.size() + "");
					lblNewLabel_1.setText(modelMetricsSelected.size() + "");
				}
			}
		});

		btnCancelMetric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				txtrOverview.setCaretPosition(0);
				modelSelectedFiles.clear();
				modelMetricsAvailable.clear();
				modelMetricsSelected.clear();
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
				close();
			}
		});

		btnBackMetric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				modelMetricsAvailable.clear();
				modelMetricsSelected.clear();
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
			}
		});

		btnNextMetric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tabbedPane.setSelectedIndex(3);
			}
		});

		btnCancelLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
				txtrOverview.setCaretPosition(0);
				modelSelectedFiles.clear();
				modelMetricsAvailable.clear();
				modelMetricsSelected.clear();
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
				close();
			}
		});

		btnBackLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});

		btnRunMetric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				metricsList.removeAll(metricsList);

				for (int i = 0; i < modelMetricsSelected.size(); i++) {
					metricsList.add(modelMetricsSelected.getElementAt(i));
				}

				if (metricsList.size() != 0) {

					AnalyseFerreira analyse = new AnalyseFerreira();

					// Setting files and metrics to analyze.
					if (textFieldThresh1.getText().equals("")) {
						analyse.setThresholdOne(70.0);
					} else {
						analyse.setThresholdOne(Double.parseDouble(textFieldThresh1.getText()));
					}

					if (textFieldThresh2.getText().equals("")) {
						analyse.setThresholdTwo(80.0);
					} else {
						analyse.setThresholdTwo(Float.parseFloat(textFieldThresh2.getText()));
					}

					analyse.setFilesPathFerreira(filesPath);
					analyse.setSelectedFilesFerreira(filesSelected);
					analyse.setMetricsListFerreira(metricsList);
					try {

						runMethod();

					} catch (IOException e1) {
						e1.printStackTrace();
					}

					tabbedPane.setSelectedIndex(4);
					calculateResults();
					showResults();
				} else
					JOptionPane.showMessageDialog(null, "ERROR: No Metric Selected." + "\n" + "Plese select a metric.");

			}
		});

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				txtrOverview.setCaretPosition(0);
				modelSelectedFiles.clear();
				modelMetricsAvailable.clear();
				modelMetricsSelected.clear();
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
				close();
			}
		});

		btnBackResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
				// modelTable.setRowCount(0);
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFiles();
			}
		});
	}

	private void selectFiles() {

		JFileChooser jfc = new JFileChooser();
		jfc.setMultiSelectionEnabled(true);
		jfc.setFileFilter(new javax.swing.filechooser.FileFilter() {

			// Filter, convert to lowercase letters before comparing
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".csv") || f.isDirectory();
			}

			// Text shown to user
			public String getDescription() {
				return "Files (.csv)";
			}
		});

		int retorno = jfc.showOpenDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			// Limpa lista ao re-clicar para adicionar files
			filesSelected.clear();
			for (int i = 0; i < files.length; i++) {
				filesPath.add(files[i].getPath());
				filesSelected.add(files[i].getName());
			}
			filePath = files[0].getParent();

			modelSelectedFiles.clear();
			for (int i = 0; i < filesSelected.size(); i++) {
				modelSelectedFiles.addElement(filesSelected.get(i));
			}

		} else {
			JOptionPane.showMessageDialog(null, "Invalid File");
		}

	}

	// Find metrics on the selected files.
	private void findMetrics() throws IOException {

		File file = new File(filePath + "/" + filesSelected.get(0));
		{
			try {
				String linesOfFile = new String();
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);

				linesOfFile = br.readLine();

				String[] valueBetweenComma = linesOfFile.split(";");

				for (int i = 0; i < valueBetweenComma.length; i++) {
					avaliableMetrics.add(valueBetweenComma[i]);
					modelMetricsAvailable.addElement(avaliableMetrics.get(i));
				}

				reader.close();

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "ERRO: Files not found!");
			}
		}
	} // end of findMetrics

	private void runMethod() throws IOException {

		MainFerreira.run();
	}

	private void calculateResults() {

		MainFerreira.calculateResult();
	}

	private void showResults() {

		AnalyseFerreira fta = new AnalyseFerreira();
		FerreiraFinalResult ffr = new FerreiraFinalResult();
		int aux = 0;

		final String[] percentage = { "0-" + String.valueOf(fta.getThresholdOne()),
				String.valueOf(fta.getThresholdOne()) + "-" + String.valueOf(fta.getThresholdTwo()),
				String.valueOf(fta.getThresholdTwo()) + "-100" };
		final String[] labels = { "Good", "Regular", "Bad" };

		// Header
		modelTable = new DefaultTableModel(new String[] { "Metric", "Lable", "Percentage (%)", "Value" }, 0);
		table.setModel(modelTable);

		for (int i = 0; i < metricsList.size(); i++) {

			modelTable.addRow(new Object[] { metricsList.get(i), labels[0], percentage[0],
					"<" + ffr.getResultEntitiesFerreira().get(aux) });
			aux++;
			modelTable.addRow(new Object[] { metricsList.get(i), labels[1], percentage[1],
					ffr.getResultEntitiesFerreira().get(aux - 1) + " - " + ffr.getResultEntitiesFerreira().get(aux) });
			aux++;
			modelTable.addRow(new Object[] { metricsList.get(i), labels[2], percentage[2],
					">" + ffr.getResultEntitiesFerreira().get(aux) });
			aux++;

		}

		scrollPaneResults.setViewportView(table);
	}

	private void saveFiles() {

		// Setting save files path.
		AnalyseFerreira analyse = new AnalyseFerreira();
		analyse.setSavePathFerreira(textAreaSaveFolder.getText());

		MainFerreira.saveVale();

	}

	private void close() {

		WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}

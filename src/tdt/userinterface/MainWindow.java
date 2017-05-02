package tdt.userinterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.scitools.metrics.view.newview.First;

import tdt.userinterface.alveswindow.AlvesWindow;
import tdt.userinterface.ferreirawindow.FerreiraWindow;
import tdt.userinterface.valewindow.ValeWindow;

/* 
 * Universidade Federal De Minas Gerais
 * Departamento da Ci�ncia da Computa��o
 * 
 * Created by:
 * Lucas Furtini Veado
 * furtini@dcc.ufmg.br
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = -3698613873368637608L;
	private JDesktopPane desktopPane;
	private JButton btnExecuteAlves;
	private JButton buttonExecuteFerreira;
	private JButton buttonExecuteOliveira;
	private JButton buttonExecuteVale;
	private AboutWindow about;
	
	// Methods classes.
	private AlvesWindow alvesWindow;
	private FerreiraWindow ferreiraWindow;
	private First first;
	private ValeWindow valeWindow;
	private JMenuItem mntmAbout;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JLabel lblPleaseSelectA;
	private JPanel panel_4;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		// Setting for open only one window for method.
		// Menu buttons
		about = null;
		
		// Methods buttons
		alvesWindow = null;
		ferreiraWindow = null;
		first = null;
		valeWindow = null;
		
		initComponents();
		createEvents();
	}
	
	private void initComponents(){
		
		setTitle("Threshold Derivation Tool - v 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 526);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon(MainWindow.class.getResource("/tdt/userinterface/resources/help.png")));
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblPleaseSelectA = new JLabel("Please select a method:");
		lblPleaseSelectA.setFont(new Font("Monospaced", Font.PLAIN, 16));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaption);
		
		panel_4 = new JPanel();
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblWelcomeToThe = new JLabel("Threshold Derivation Tool");
		panel_4.add(lblWelcomeToThe);
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Monospaced", Font.BOLD, 20));
		
		JPanel alvesMethodPanel = new JPanel();
		
		JPanel ferreiraMethodPanel = new JPanel();
		
		JPanel oliveiraMethodPanel = new JPanel();
		
		JPanel valeMethodPanel = new JPanel();
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(alvesMethodPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ferreiraMethodPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(oliveiraMethodPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(valeMethodPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(valeMethodPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(oliveiraMethodPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(ferreiraMethodPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(alvesMethodPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JPanel panelValeSummary = new JPanel();
		panelValeSummary.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Summary", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblMetrics = new JLabel("1 - Metrics Extraction");
		lblMetrics.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblWeight_2 = new JLabel("2 - Weight Ratio Calculation");
		lblWeight_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblSort = new JLabel("3 - Sort Ascendenting Order");
		lblSort.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblEntety = new JLabel("4 - Entity Aggregation");
		lblEntety.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblThreshold_1 = new JLabel("5 - Threshold Derivation");
		lblThreshold_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		GroupLayout gl_panelValeSummary = new GroupLayout(panelValeSummary);
		gl_panelValeSummary.setHorizontalGroup(
			gl_panelValeSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelValeSummary.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelValeSummary.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMetrics)
						.addComponent(lblWeight_2)
						.addComponent(lblSort)
						.addComponent(lblEntety)
						.addComponent(lblThreshold_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelValeSummary.setVerticalGroup(
			gl_panelValeSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelValeSummary.createSequentialGroup()
					.addGap(28)
					.addComponent(lblMetrics)
					.addGap(18)
					.addComponent(lblWeight_2)
					.addGap(18)
					.addComponent(lblSort)
					.addGap(18)
					.addComponent(lblEntety)
					.addGap(18)
					.addComponent(lblThreshold_1)
					.addContainerGap(157, Short.MAX_VALUE))
		);
		panelValeSummary.setLayout(gl_panelValeSummary);
		
		JPanel panelValeButtons = new JPanel();
		
		buttonExecuteVale = new JButton("Execute");
		buttonExecuteVale.setIcon(new ImageIcon(MainWindow.class.getResource("/tdt/userinterface/resources/Execute.png")));
		buttonExecuteVale.setFont(new Font("Monospaced", Font.PLAIN, 14));
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_valeMethodPanel = new GroupLayout(valeMethodPanel);
		gl_valeMethodPanel.setHorizontalGroup(
			gl_valeMethodPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_valeMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_valeMethodPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelValeSummary, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addComponent(panelValeButtons, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_valeMethodPanel.setVerticalGroup(
			gl_valeMethodPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_valeMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelValeSummary, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelValeButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(127, Short.MAX_VALUE))
		);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblVale = new JLabel("Vale");
		panel_3.add(lblVale);
		lblVale.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		lblVale.setFont(new Font("Monospaced", Font.PLAIN, 18));
		panelValeButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelValeButtons.add(buttonExecuteVale);
		valeMethodPanel.setLayout(gl_valeMethodPanel);
		
		JPanel panelOliveiraSummary = new JPanel();
		panelOliveiraSummary.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Summary", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblMeasurement_2 = new JLabel("1 - Metrics Extraction");
		lblMeasurement_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblGrouping_1 = new JLabel("2 - Grouping");
		lblGrouping_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblCompilance = new JLabel("3 - Compilance Rate Formula");
		lblCompilance.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblPenalitys = new JLabel("4 - Penalitys (Min and Tail)");
		lblPenalitys.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblTreshold = new JLabel("5 - Threshold Derivation");
		lblTreshold.setFont(new Font("Monospaced", Font.PLAIN, 11));
		GroupLayout gl_panelOliveiraSummary = new GroupLayout(panelOliveiraSummary);
		gl_panelOliveiraSummary.setHorizontalGroup(
			gl_panelOliveiraSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelOliveiraSummary.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelOliveiraSummary.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMeasurement_2)
						.addComponent(lblGrouping_1)
						.addComponent(lblCompilance)
						.addComponent(lblPenalitys)
						.addComponent(lblTreshold))
					.addContainerGap())
		);
		gl_panelOliveiraSummary.setVerticalGroup(
			gl_panelOliveiraSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOliveiraSummary.createSequentialGroup()
					.addGap(26)
					.addComponent(lblMeasurement_2)
					.addGap(18)
					.addComponent(lblGrouping_1)
					.addGap(18)
					.addComponent(lblCompilance)
					.addGap(18)
					.addComponent(lblPenalitys)
					.addGap(18)
					.addComponent(lblTreshold)
					.addContainerGap(159, Short.MAX_VALUE))
		);
		panelOliveiraSummary.setLayout(gl_panelOliveiraSummary);
		
		JPanel panelOliveiraButtons = new JPanel();
		
		buttonExecuteOliveira = new JButton("Execute");
		buttonExecuteOliveira.setIcon(new ImageIcon(MainWindow.class.getResource("/tdt/userinterface/resources/Execute.png")));
		buttonExecuteOliveira.setFont(new Font("Monospaced", Font.PLAIN, 14));
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_oliveiraMethodPanel = new GroupLayout(oliveiraMethodPanel);
		gl_oliveiraMethodPanel.setHorizontalGroup(
			gl_oliveiraMethodPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_oliveiraMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_oliveiraMethodPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelOliveiraSummary, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addComponent(panelOliveiraButtons, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_oliveiraMethodPanel.setVerticalGroup(
			gl_oliveiraMethodPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_oliveiraMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOliveiraSummary, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOliveiraButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblOliveira = new JLabel("Oliveira");
		panel_2.add(lblOliveira);
		lblOliveira.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		lblOliveira.setFont(new Font("Monospaced", Font.PLAIN, 18));
		panelOliveiraButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelOliveiraButtons.add(buttonExecuteOliveira);
		oliveiraMethodPanel.setLayout(gl_oliveiraMethodPanel);
		
		JPanel panelFerreiraSummary = new JPanel();
		panelFerreiraSummary.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Summary", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panelFerreiraButtons = new JPanel();
		
		buttonExecuteFerreira = new JButton("Execute");
		buttonExecuteFerreira.setIcon(new ImageIcon(MainWindow.class.getResource("/tdt/userinterface/resources/Execute.png")));
		buttonExecuteFerreira.setFont(new Font("Monospaced", Font.PLAIN, 14));
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_ferreiraMethodPanel = new GroupLayout(ferreiraMethodPanel);
		gl_ferreiraMethodPanel.setHorizontalGroup(
			gl_ferreiraMethodPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ferreiraMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ferreiraMethodPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelFerreiraSummary, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addComponent(panelFerreiraButtons, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_ferreiraMethodPanel.setVerticalGroup(
			gl_ferreiraMethodPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ferreiraMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelFerreiraSummary, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelFerreiraButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(133))
		);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblFerreira = new JLabel("Ferreira");
		panel_1.add(lblFerreira);
		lblFerreira.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		lblFerreira.setFont(new Font("Monospaced", Font.PLAIN, 18));
		panelFerreiraButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelFerreiraButtons.add(buttonExecuteFerreira);
		
		JLabel lblMeasurement_1 = new JLabel("1 - Metrics Extraction");
		lblMeasurement_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblGrouping = new JLabel("2 - Weight Ratio Calculation");
		lblGrouping.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblBoxplot = new JLabel("3 - Sort in Ascending Order");
		lblBoxplot.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblThreshold = new JLabel("4 - Entity Aggregation");
		lblThreshold.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblNewLabel = new JLabel("5 - Threshold Derivation");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		GroupLayout gl_panelFerreiraSummary = new GroupLayout(panelFerreiraSummary);
		gl_panelFerreiraSummary.setHorizontalGroup(
			gl_panelFerreiraSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFerreiraSummary.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFerreiraSummary.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGrouping)
						.addComponent(lblBoxplot)
						.addComponent(lblThreshold)
						.addComponent(lblMeasurement_1)
						.addComponent(lblNewLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelFerreiraSummary.setVerticalGroup(
			gl_panelFerreiraSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFerreiraSummary.createSequentialGroup()
					.addGap(25)
					.addComponent(lblMeasurement_1)
					.addGap(18)
					.addComponent(lblGrouping)
					.addGap(18)
					.addComponent(lblBoxplot)
					.addGap(18)
					.addComponent(lblThreshold)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		panelFerreiraSummary.setLayout(gl_panelFerreiraSummary);
		ferreiraMethodPanel.setLayout(gl_ferreiraMethodPanel);
		
		JPanel panelAlvesSummary = new JPanel();
		panelAlvesSummary.setBorder(new TitledBorder(null, "Summary", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelAlvesButtons = new JPanel();
		
		JPanel panel = new JPanel();
		GroupLayout gl_alvesMethodPanel = new GroupLayout(alvesMethodPanel);
		gl_alvesMethodPanel.setHorizontalGroup(
			gl_alvesMethodPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_alvesMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_alvesMethodPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelAlvesSummary, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 216, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addComponent(panelAlvesButtons, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_alvesMethodPanel.setVerticalGroup(
			gl_alvesMethodPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_alvesMethodPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelAlvesSummary, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelAlvesButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAlves = new JLabel("Alves");
		panel.add(lblAlves);
		lblAlves.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblAlves.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		panelAlvesButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnExecuteAlves = new JButton("Execute");
		btnExecuteAlves.setFont(new Font("Monospaced", Font.PLAIN, 14));
		btnExecuteAlves.setIcon(new ImageIcon(MainWindow.class.getResource("/tdt/userinterface/resources/Execute.png")));
		panelAlvesButtons.add(btnExecuteAlves);
		
		JLabel lblMeasurement = new JLabel("1 - Metrics Extraction");
		lblMeasurement.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblWeight = new JLabel("2 - Weight Ratio Calculation");
		lblWeight.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblEntity = new JLabel("3 - Entity Aggregation");
		lblEntity.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblSystem = new JLabel("4 - System Aggregation");
		lblSystem.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblWeight_1 = new JLabel("5 - Weight Ratio Calculation");
		lblWeight_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JLabel lblThresholds = new JLabel("6 - Threshold Derivation");
		lblThresholds.setFont(new Font("Monospaced", Font.PLAIN, 11));
		GroupLayout gl_panelAlvesSummary = new GroupLayout(panelAlvesSummary);
		gl_panelAlvesSummary.setHorizontalGroup(
			gl_panelAlvesSummary.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAlvesSummary.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelAlvesSummary.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEntity)
						.addComponent(lblThresholds)
						.addComponent(lblSystem)
						.addComponent(lblWeight)
						.addComponent(lblMeasurement)
						.addComponent(lblWeight_1, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panelAlvesSummary.setVerticalGroup(
			gl_panelAlvesSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlvesSummary.createSequentialGroup()
					.addGap(22)
					.addComponent(lblMeasurement)
					.addGap(18)
					.addComponent(lblWeight)
					.addGap(18)
					.addComponent(lblEntity)
					.addGap(18)
					.addComponent(lblSystem)
					.addGap(18)
					.addComponent(lblWeight_1)
					.addGap(18)
					.addComponent(lblThresholds)
					.addContainerGap(129, Short.MAX_VALUE))
		);
		panelAlvesSummary.setLayout(gl_panelAlvesSummary);
		alvesMethodPanel.setLayout(gl_alvesMethodPanel);
		desktopPane.setLayout(gl_desktopPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblPleaseSelectA)
					.addGap(14)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE))
				.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblPleaseSelectA))
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void createEvents(){
		
		// Method buttons
		btnExecuteAlves.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (alvesWindow == null){
					alvesWindow = new AlvesWindow();
					alvesWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
				pack();
				alvesWindow.setVisible(true);
			}
		});
		
		buttonExecuteFerreira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ferreiraWindow == null){
					ferreiraWindow = new FerreiraWindow();
					ferreiraWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
				pack();
				ferreiraWindow.setVisible(true);
			}
		});
		
		buttonExecuteOliveira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (first == null){
					first = new First();
					first.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
				pack();
				first.setVisible(true);
			}
		});
		
		buttonExecuteVale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (valeWindow == null) {
					valeWindow = new ValeWindow();
					valeWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
				pack();
				valeWindow.setVisible(true);
			}
		});
		// Method buttons end
		
		// Menu Buttons
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(about == null){
					about = new AboutWindow();
					about.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
				pack();
				about.setVisible(true);
			}
		});
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
}

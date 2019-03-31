package co.gov.antioquia;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.io.File;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class GUI implements ActionListener {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox_Municipality;
	private JTextField textField;
	private JTextField textField_6;
	private DBConnector dbConnector;
	private String status;
	private File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//ImageIcon antioqCadastralIcon = new ImageIcon("LogoCatastro.ico");
		//Image antioqCadastralIcon = Toolkit.getDefaultToolkit().getImage("LogoCatastro.ico");
		//frame.setIconImage(antioqCadastralIcon.getImage());
		//frame.setIconImage(antioqCadastralIcon);
		frame.setBounds(100, 100, 661, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		/*
		 * btnProbarConexin.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { DBConnector dbConnector = new
		 * DBConnector(); System.out.printf("Status: " +
		 * "%s",dbConnector.testConnection()); JLabel lblNewLabel_6 = new
		 * JLabel("Hola"); lblNewLabel_6.setBounds(49, 215, 320, 14);
		 * panel.add(lblNewLabel_6);
		 * 
		 * } });
		 */

		/*
		 * String municipalityName[] = { "ABEJORRAL", "ABRIAQUI", "ALEJANDRIA", "AMAGA",
		 * "AMALFI", "ANDES", "ANGELOPOLIS", "ANGOSTURA", "ANORI", "ANZA", "APARTADO",
		 * "ARBOLETES", "ARGELIA", "ARMENIA", "BARBOSA", "BELLO", "BELMIRA", "BETANIA",
		 * "BETULIA", "BRICEÑO", "BURITICA", "CACERES", "CAICEDO", "CALDAS",
		 * "CAMPAMENTO", "CAÑASGORDAS", "CARACOLI", "CARAMANTA", "CAREPA", "CAROLINA",
		 * "CAUCASIA", "CHIGORODO", "CISNEROS", "CIUDAD BOLIVAR", "COCORNA",
		 * "CONCEPCION", "CONCORDIA", "COPACABANA", "DABEIBA", "DON MATIAS", "EBEJICO",
		 * "EL BAGRE", "EL CARMEN DE VIBORAL", "EL PEÑOL", "EL RETIRO", "EL SANTUARIO",
		 * "ENTRERRIOS", "ENVIGADO", "FREDONIA", "FRONTINO", "GIRALDO", "GIRARDOTA",
		 * "GOMEZ PLATA", "GRANADA", "GUADALUPE", "GUARNE", "GUATAPE", "HELICONIA",
		 * "HISPANIA", "ITAGUI", "ITUANGO", "JARDIN", "JERICO", "LA CEJA",
		 * "LA ESTRELLA", "LA PINTADA", "LA UNION", "LIBORINA", "MACEO", "MARINILLA",
		 * "MONTEBELLO", "MURINDO", "MUTATA", "NARIÑO", "NECHI", "NECOCLI", "OLAYA",
		 * "PEQUE", "PUEBLORRICO", "PUERTO BERRIO", "PUERTO NARE", "PUERTO TRIUNFO",
		 * "REMEDIOS", "RIONEGRO", "SABANALARGA", "SABANETA", "SALGAR",
		 * "SAN ANDRES DE CUERQUIA", "SAN CARLOS", "SAN FRANCISCO", "SAN JERONIMO",
		 * "SAN JOSE DE LA MONTANA", "SAN JUAN DE URABA", "SAN LUIS",
		 * "SAN PEDRO DE LOS MILAGROS", "SAN PEDRO DE URABA", "SAN RAFAEL", "SAN ROQUE",
		 * "SAN VICENTE", "SANTA BARBARA", "SANTA FE DE ANTIOQUIA",
		 * "SANTA ROSA DE OSOS", "SANTO DOMINGO", "SEGOVIA", "SONSON", "SOPETRAN",
		 * "TAMESIS", "TARAZA", "TARSO", "TITIRIBI", "TOLEDO", "TURBO", "URAMITA",
		 * "URRAO", "VALDIVIA", "VALPARAISO", "VEGACHI", "VENECIA", "VIGIA DEL FUERTE",
		 * "YALI", "YARUMAL", "YOLOMBO", "YONDO", "ZARAGOZA"};
		 */

		int[] municipalityCode = new int[] { 2, 4, 21, 30, 31, 34, 36, 38, 40, 44, 45, 51, 55, 59, 79, 88, 86, 91, 93,
				107, 113, 120, 125, 129, 134, 138, 142, 145, 147, 150, 154, 172, 190, 101, 197, 206, 209, 212, 234, 237,
				240, 250, 148, 541, 607, 697, 264, 266, 282, 284, 306, 308, 310, 313, 315, 318, 321, 347, 353, 360, 361,
				364, 368, 376, 380, 390, 400, 411, 425, 440, 467, 475, 480, 483, 495, 490, 501, 543, 576, 579, 585, 591,
				604, 615, 628, 631, 642, 647, 649, 652, 656, 658, 659, 660, 664, 665, 667, 670, 674, 679, 42, 686, 690,
				736, 756, 761, 789, 790, 792, 809, 819, 837, 842, 847, 854, 856, 858, 861, 873, 885, 887, 890, 893,
				895 };

		int[] sectorCode = new int[] { 1, 2 };

		JPanel panel = new JPanel();
		tabbedPane.addTab("Conexi\u00F3n", null, panel, null);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(22, 104, 98, 14);
		panel.add(lblUsuario);

		textField_1 = new JTextField();
		textField_1.setBounds(138, 102, 385, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(22, 136, 98, 14);
		panel.add(lblContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(138, 134, 385, 20);
		panel.add(passwordField);

		JLabel lblNombreDeTabla = new JLabel("Nombre de BD");
		lblNombreDeTabla.setBounds(22, 72, 132, 14);
		panel.add(lblNombreDeTabla);

		textField_2 = new JTextField();
		textField_2.setBounds(138, 70, 385, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 169, 618, 11);
		panel.add(separator);

		JLabel lblNewLabel = new JLabel("Estado:");
		lblNewLabel.setBounds(10, 215, 67, 14);
		panel.add(lblNewLabel);

		JLabel lblNombreHost = new JLabel("Nombre host");
		lblNombreHost.setBounds(22, 18, 117, 15);
		panel.add(lblNombreHost);

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(22, 45, 70, 15);
		panel.add(lblPuerto);

		textField = new JTextField();
		textField.setBounds(138, 41, 385, 19);
		panel.add(textField);
		textField.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(138, 16, 385, 19);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(74, 214, 505, 15);
		panel.add(lblNewLabel_6);

		JButton btnProbarConexin = new JButton("Probar conexi\u00F3n");
		btnProbarConexin.setBounds(5, 181, 158, 23);
		panel.add(btnProbarConexin);
		btnProbarConexin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				// How to make this work ?
				// Like this:
				status = "";
				lblNewLabel_6.setText(status);
				int flagFields = 0;
				if (textField_6.getText().isEmpty()) {
					flagFields = 1;
				}
				if (textField.getText().isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 2;
					}
				}
				if (textField_2.getText().isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 3;
					}
				}
				if (textField_1.getText().isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 4;
					}
				}
				if (String.copyValueOf(passwordField.getPassword()).isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 5;
					}
				}

				switch (flagFields) {
				case 1:
					JOptionPane.showMessageDialog(frame,
							"Ingrese el nombre o dirección IP del host, ej.: 'localhost' o '127.0.0.1'",
							"Campo Nombre host vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 2:
					JOptionPane.showMessageDialog(frame, "Ingrese el puerto de escucha, ej.: '5432'",
							"Campo Puerto vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 3:
					JOptionPane.showMessageDialog(frame, "Ingrese el nombre de la BD, ej.: 'ladm_col'",
							"Campo Nombre BD vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 4:
					JOptionPane.showMessageDialog(frame, "Ingrese el usuario de la BD, ej.: 'postgres'",
							"Campo Usuario vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 5:
					JOptionPane.showMessageDialog(frame, "Ingrese la contraseña", "Campo Contraseña vacío",
							JOptionPane.WARNING_MESSAGE);
					break;
				case 6:
					JOptionPane.showMessageDialog(frame, "Complete los campos vacíos", "Campos vacíos",
							JOptionPane.WARNING_MESSAGE);
					break;
				default:
					status = new DBConnector().testConnection(textField_6.getText(),
							Integer.parseInt(textField.getText()), textField_2.getText(), textField_1.getText(),
							String.copyValueOf(passwordField.getPassword()));
					//System.out.printf("Status: " + "%s%n", status);
					lblNewLabel_6.setText(status);
				}

			}
		});

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Reporte de Actualizaci\u00F3n Catastral", null, panel_1, null);
		tabbedPane.setEnabledAt(1, false);
		panel_1.setLayout(null);

		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setEnabled(false);
		btnDesconectar.setBounds(176, 181, 132, 23);
		panel.add(btnDesconectar);
		btnDesconectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				// How to make this work ?
				// Like this:
				// System.out.println("Hola");
				status = dbConnector.disconnect(dbConnector.conn);
				//System.out.println(status);
				lblNewLabel_6.setText(status);
				btnDesconectar.setEnabled(false);
				tabbedPane.setEnabledAt(1, false);
				// btnConectar.setEnabled(true);
				// new DBConnector().testConnection("localhost", 5432, "ladm_col", "postgres",
				// "C4tastr0");
			}

		});// addActionListener(e --> new DBConnector().testConnection("localhost", 5432,
			// "ladm_col", "postgres", "C4tastr0"));

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(320, 181, 124, 23);
		panel.add(btnConectar);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(576, 339, 3, 3);
		panel.add(scrollPane_2);
		btnConectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				dbConnector = new DBConnector();
				status = "";
				lblNewLabel_6.setText(status);
				// How to make this work ?
				// Like this:
				// System.out.println("Hola");
				// System.out.print(passwordField.getPassword());
				int flagFields = 0;
				if (textField_6.getText().isEmpty()) {
					flagFields = 1;
				}
				if (textField.getText().isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 2;
					}
				}
				if (textField_2.getText().isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 3;
					}
				}
				if (textField_1.getText().isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 4;
					}
				}
				if (String.copyValueOf(passwordField.getPassword()).isEmpty()) {
					if (flagFields != 0) {
						flagFields = 6;
					} else {
						flagFields = 5;
					}
				}

				switch (flagFields) {
				case 1:
					JOptionPane.showMessageDialog(frame,
							"Ingrese el nombre o dirección IP del host, ej.: 'localhost' o '127.0.0.1'",
							"Campo Nombre host vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 2:
					JOptionPane.showMessageDialog(frame, "Ingrese el puerto de escucha, ej.: '5432'",
							"Campo Puerto vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 3:
					JOptionPane.showMessageDialog(frame, "Ingrese el nombre de la BD, ej.: 'ladm_col'",
							"Campo Nombre BD vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 4:
					JOptionPane.showMessageDialog(frame, "Ingrese el usuario de la BD, ej.: 'postgres'",
							"Campo Usuario vacío", JOptionPane.WARNING_MESSAGE);
					break;
				case 5:
					JOptionPane.showMessageDialog(frame, "Ingrese la contraseña", "Campo Contraseña vacío",
							JOptionPane.WARNING_MESSAGE);
					break;
				case 6:
					JOptionPane.showMessageDialog(frame, "Complete los campos vacíos", "Campos vacíos",
							JOptionPane.WARNING_MESSAGE);
					break;
				default:
					status = dbConnector.connect(textField_6.getText(), Integer.parseInt(textField.getText()),
							textField_2.getText(), textField_1.getText(),
							String.copyValueOf(passwordField.getPassword()));
					//System.out.println(status);
					lblNewLabel_6.setText(status);

				}

				if (dbConnector.conn != null) {
					btnDesconectar.setEnabled(true);
					tabbedPane.setEnabledAt(1, true);
				}
				// new DBConnector().testConnection("localhost", 5432, "ladm_col", "postgres",
				// "C4tastr0");
			}
		});

		JButton btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.setEnabled(false);

		JButton btnSeleccionar = new JButton("Directorio...");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*
				 * JFileChooser chooser = new JFileChooser(); chooser.setCurrentDirectory(new
				 * java.io.File(".")); chooser.setDialogTitle("select folder");
				 * chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				 * chooser.setAcceptAllFileFilterUsed(false);
				 */
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Escoja un directorio para guardar el archivo:");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = jfc.getSelectedFile();
					//System.out.println(selectedFile.getAbsolutePath());
					textField_5.setText(selectedFile.getAbsolutePath());
					btnGenerarReporte.setEnabled(true);
				}
			}

		});
		btnSeleccionar.setBounds(271, 125, 126, 23);
		panel_1.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(81, 222, 316, 15);
		panel_1.add(lblNewLabel_7);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(180, 184, 320, 34);
		panel_1.add(progressBar);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "URBANO", "RURAL" }));
		comboBox_1.setBounds(197, 85, 303, 20);
		panel_1.add(comboBox_1);
		
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CadastralUpdateReportGenerator reportGenerator = new CadastralUpdateReportGenerator();
				String RESO_VIGENCIA = textField_3.getText();
				String RESO_RESOLUC = textField_4.getText();
				int RESO_MPIO_INDEX = comboBox_Municipality.getSelectedIndex();
				String RESO_MPIO = Integer.toString(municipalityCode[RESO_MPIO_INDEX]);
				int RESO_SECTOR_INDEX = comboBox_1.getSelectedIndex();
				String RESO_SECTOR = Integer.toString(sectorCode[RESO_SECTOR_INDEX]);
				String path = "";
				status = "";
				lblNewLabel_6.setText(status);
				int flagFields = 0;
				// String RESO_SECTOR = comboBox_1.getSelectedItem().toString();
				try {
					if (textField_3.getText().isEmpty()) {
						flagFields = 1;
					}
					if (textField_4.getText().isEmpty()) {
						if (flagFields == 1) {
							flagFields = 3;
						} else {
							flagFields = 2;
						}
					}

					switch (flagFields) {
					case 1:
						JOptionPane.showMessageDialog(frame,
								"Ingrese el año de vigencia de la resolución, ej.: '2019'",
								"Campo Vigencia resolución vacío", JOptionPane.WARNING_MESSAGE);
						break;
					case 2:
						JOptionPane.showMessageDialog(frame, "Ingrese el número de la resolución, ej.: '12345567'",
								"Campo Número resolución", JOptionPane.WARNING_MESSAGE);
						break;
					case 3:
						JOptionPane.showMessageDialog(frame, "Complete los campos vacíos", "Campos vacíos",
								JOptionPane.WARNING_MESSAGE);
						break;
					default:
					lblNewLabel_7.setText("Generando reporte ...");
					Border border = BorderFactory.createTitledBorder("Reporte en proceso...");
					progressBar.setBorder(border);
					progressBar.setValue(25);
					progressBar.setStringPainted(true);
					path = reportGenerator.getDirectoryPath(selectedFile, RESO_VIGENCIA, RESO_RESOLUC);
					reportGenerator.buildReport(dbConnector, path, RESO_VIGENCIA, RESO_RESOLUC, RESO_MPIO,
							RESO_SECTOR);
					border = BorderFactory.createTitledBorder("Terminado");
					progressBar.setBorder(border);
					progressBar.setValue(100);
					progressBar.setStringPainted(true);
					lblNewLabel_7.setText("Reporte generado satisfactoriamente.");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnGenerarReporte.setBounds(10, 185, 158, 23);
		panel_1.add(btnGenerarReporte);

		JLabel lblNewLabel_1 = new JLabel("Vigencia resoluci\u00F3n");
		lblNewLabel_1.setBounds(10, 7, 143, 23);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("N\u00FAmero de la resoluci\u00F3n");
		lblNewLabel_2.setBounds(10, 38, 172, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Municipio");
		lblNewLabel_3.setBounds(10, 63, 72, 14);
		panel_1.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(197, 9, 303, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(197, 36, 303, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		comboBox_Municipality = new JComboBox<String>();
		comboBox_Municipality.setToolTipText("");
		// DefaultComboBoxModel model = new DefaultComboBoxModel();
		// comboBox.addItem(new Municipality(2, "ABEJORRAL"));
		// comboBox.addItem(new Municipality(4, "ABRIAQUI"));
		comboBox_Municipality.setModel(new DefaultComboBoxModel(new String[] { "ABEJORRAL", "ABRIAQUI", "ALEJANDRIA",
				"AMAGA", "AMALFI", "ANDES", "ANGELOPOLIS", "ANGOSTURA", "ANORI", "ANZA", "APARTADO", "ARBOLETES",
				"ARGELIA", "ARMENIA", "BARBOSA", "BELLO", "BELMIRA", "BETANIA", "BETULIA", "BRICEÑO", "BURITICA",
				"CACERES", "CAICEDO", "CALDAS", "CAMPAMENTO", "CAÑASGORDAS", "CARACOLI", "CARAMANTA", "CAREPA",
				"CAROLINA", "CAUCASIA", "CHIGORODO", "CISNEROS", "CIUDAD BOLIVAR", "COCORNA", "CONCEPCION", "CONCORDIA",
				"COPACABANA", "DABEIBA", "DON MATIAS", "EBEJICO", "EL BAGRE", "EL CARMEN DE VIBORAL", "EL PEÑOL",
				"EL RETIRO", "EL SANTUARIO", "ENTRERRIOS", "ENVIGADO", "FREDONIA", "FRONTINO", "GIRALDO", "GIRARDOTA",
				"GOMEZ PLATA", "GRANADA", "GUADALUPE", "GUARNE", "GUATAPE", "HELICONIA", "HISPANIA", "ITAGUI",
				"ITUANGO", "JARDIN", "JERICO", "LA CEJA", "LA ESTRELLA", "LA PINTADA", "LA UNION", "LIBORINA", "MACEO",
				"MARINILLA", "MONTEBELLO", "MURINDO", "MUTATA", "NARIÑO", "NECHI", "NECOCLI", "OLAYA", "PEQUE",
				"PUEBLORRICO", "PUERTO BERRIO", "PUERTO NARE", "PUERTO TRIUNFO", "REMEDIOS", "RIONEGRO", "SABANALARGA",
				"SABANETA", "SALGAR", "SAN ANDRES DE CUERQUIA", "SAN CARLOS", "SAN FRANCISCO", "SAN JERONIMO",
				"SAN JOSE DE LA MONTANA", "SAN JUAN DE URABA", "SAN LUIS", "SAN PEDRO DE LOS MILAGROS",
				"SAN PEDRO DE URABA", "SAN RAFAEL", "SAN ROQUE", "SAN VICENTE", "SANTA BARBARA",
				"SANTA FE DE ANTIOQUIA", "SANTA ROSA DE OSOS", "SANTO DOMINGO", "SEGOVIA", "SONSON", "SOPETRAN",
				"TAMESIS", "TARAZA", "TARSO", "TITIRIBI", "TOLEDO", "TURBO", "URAMITA", "URRAO", "VALDIVIA",
				"VALPARAISO", "VEGACHI", "VENECIA", "VIGIA DEL FUERTE", "YALI", "YARUMAL", "YOLOMBO", "YONDO",
				"ZARAGOZA" }));
		comboBox_Municipality.setBounds(197, 60, 303, 20);
		panel_1.add(comboBox_Municipality);
		JLabel lblNewLabel_4 = new JLabel("Sector");
		lblNewLabel_4.setBounds(10, 88, 46, 14);
		panel_1.add(lblNewLabel_4);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 114, 619, -1);
		panel_1.add(separator_1);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(20, 223, 66, 14);
		panel_1.add(lblEstado);

		JLabel lblNewLabel_5 = new JLabel("Ruta de donde guardar\u00E1 el archivo");
		lblNewLabel_5.setBounds(16, 129, 255, 19);
		panel_1.add(lblNewLabel_5);

		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setBounds(10, 154, 490, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(576, 339, 3, 3);
		panel_1.add(scrollPane_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("¿Cómo usar este programa?", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("¿Cómo conectarse a la BD?");
		lblNewLabel_8.setBounds(12, 12, 201, 15);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("¿Cómo generar el reporte de Actualización Catastral?");
		lblNewLabel_9.setBounds(12, 197, 423, 15);
		panel_2.add(lblNewLabel_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(576, 234, 3, 3);
		panel_2.add(scrollPane);
		
		JTextPane txtpnUnaVezEn = new JTextPane();
		txtpnUnaVezEn.setText("Una vez en la pestaña 'Conexión':\n\n1- Llenar cada uno de los campos necesarios para conectarse a la BD arrojada en el sistema PostgreSQL.\n\n2- Hacer clic sobre el botón 'Conectar' para generar una conexión a la BD. Nota: observe que si la conexión es exitosa se habilitará la pestaña 'Reporte de Actualización Catastral'.\n\n3- (Opcional) Hacer clic sobre el botón 'Probar conexión' para probar la conexión a la BD. Nota: esta prueba no deja la conexión abierta.");
		txtpnUnaVezEn.setEditable(false);
		txtpnUnaVezEn.setBounds(22, 27, 622, 156);
		panel_2.add(txtpnUnaVezEn);
		
		JTextPane txtpnUnaVezEn_1 = new JTextPane();
		txtpnUnaVezEn_1.setText("Una vez en la pestaña 'Reporte de Actualización Catastral':\n\n1- Llenar y seleccionar en cada uno de los campos necesarios la información pertinente para generar el reporte de Actualización Catastral en un archivo '.txt'.\n\n2 - Hacer clic en el botón 'Seleccionar' para el establecer el directorio o carpeta donde se alojará el archivo.\n\n3- Hacer clic en el botón 'Generar reporte' para dar inicio al proceso de generación del reporte de Actualización Catastral. Una vez el proceso indique 100% en la barra de progreso, puede ubicar el archivo 'actualizacion_Catastral_#Vigencia_#Resolucion.txt'.");
		txtpnUnaVezEn_1.setBounds(22, 213, 622, 171);
		panel_2.add(txtpnUnaVezEn_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Acerca", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("LADM_COL Extractor");
		lblNewLabel_10.setBounds(27, 12, 155, 32);
		panel_3.add(lblNewLabel_10);
		
		JTextPane txtpnDesarrolladoParaEl = new JTextPane();
		txtpnDesarrolladoParaEl.setText("Desarrollado para el área de soporte OVC de la Dirección de Sistemas de Información y Catastro de la Gobernación de Antioquia.\n\nAutor: Juan Sebastián Lobo R.\n\nRepositorio proyecto: https://github.com/JSLobo/LADM_COL-Extractor\n\nRelease: 1.0\n\n ");
		txtpnDesarrolladoParaEl.setEditable(false);
		txtpnDesarrolladoParaEl.setBounds(37, 56, 557, 144);
		panel_3.add(txtpnDesarrolladoParaEl);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}

package co.gov.antioquia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\jlobor\\My Documents\\eclipse-workspace\\LADM_COL-Extractor\\Sources\\LogoCatastro.ico"));
		frame.setBounds(100, 100, 400, 306);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Conexi\u00F3n", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNombreDeConexin = new JLabel("Nombre de conexi\u00F3n");
		lblNombreDeConexin.setBounds(5, 8, 121, 14);
		lblNombreDeConexin.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNombreDeConexin);
		
		textField = new JTextField();
		textField.setBounds(136, 5, 225, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(5, 33, 98, 14);
		panel.add(lblUsuario);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 30, 225, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(5, 58, 98, 14);
		panel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 55, 225, 20);
		panel.add(passwordField);
		
		JLabel lblNombreDeTabla = new JLabel("Nombre de BD");
		lblNombreDeTabla.setBounds(5, 83, 98, 14);
		panel.add(lblNombreDeTabla);
		
		textField_2 = new JTextField();
		textField_2.setBounds(136, 80, 225, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnProbarConexin = new JButton("Probar conexi\u00F3n");
		btnProbarConexin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBConnector dbConnector = new DBConnector();
				System.out.printf("Status: " + "%s",dbConnector.testConnection());
				JLabel lblNewLabel_6 = new JLabel("Hola");
				lblNewLabel_6.setBounds(49, 215, 320, 14);
				panel.add(lblNewLabel_6);
				
			}
		});
		btnProbarConexin.setBounds(5, 181, 132, 23);
		panel.add(btnProbarConexin);
		
		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(146, 181, 120, 23);
		panel.add(btnDesconectar);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(266, 181, 109, 23);
		panel.add(btnConectar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 108, 364, 5);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Estado:");
		lblNewLabel.setBounds(10, 215, 46, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Reporte de Actualizaci\u00F3n Catastral", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Generar reporte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(10, 185, 137, 23);
		panel_1.add(btnNewButton);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(157, 185, 209, 23);
		panel_1.add(progressBar);
		
		JLabel lblNewLabel_1 = new JLabel("Vigencia resoluci\u00F3n");
		lblNewLabel_1.setBounds(10, 11, 143, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("N\u00FAmero de la resoluci\u00F3n");
		lblNewLabel_2.setBounds(10, 38, 143, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Municipio");
		lblNewLabel_3.setBounds(10, 63, 72, 14);
		panel_1.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(163, 8, 203, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(163, 35, 203, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		String[] municipalityName = { "ABEJORRAL", "ABRIAQUI", "ALEJANDRIA", "AMAGA", "AMALFI", "ANDES", "ANGELOPOLIS",
				"ANGOSTURA", "ANORI", "ANZA", "APARTADO", "ARBOLETES", "ARGELIA", "ARMENIA", "BARBOSA", "BELLO",
				"BELMIRA", "BETANIA", "BETULIA", "BRICEÑO", "BURITICA", "CACERES", "CAICEDO", "CALDAS", "CAMPAMENTO",
				"CAÑASGORDAS", "CARACOLI", "CARAMANTA", "CAREPA", "CAROLINA", "CAUCASIA", "CHIGORODO", "CISNEROS",
				"CIUDAD BOLIVAR", "COCORNA", "CONCEPCION", "CONCORDIA", "COPACABANA", "DABEIBA", "DON MATIAS",
				"EBEJICO", "EL BAGRE", "EL CARMEN DE VIBORAL", "EL PEÑOL", "EL RETIRO", "EL SANTUARIO", "ENTRERRIOS",
				"ENVIGADO", "FREDONIA", "FRONTINO", "GIRALDO", "GIRARDOTA", "GOMEZ PLATA", "GRANADA", "GUADALUPE",
				"GUARNE", "GUATAPE", "HELICONIA", "HISPANIA", "ITAGUI", "ITUANGO", "JARDIN", "JERICO", "LA CEJA",
				"LA ESTRELLA", "LA PINTADA", "LA UNION", "LIBORINA", "MACEO", "MARINILLA", "MONTEBELLO", "MURINDO",
				"MUTATA", "NARIÑO", "NECHI", "NECOCLI", "OLAYA", "PEQUE", "PUEBLORRICO", "PUERTO BERRIO", "PUERTO NARE",
				"PUERTO TRIUNFO", "REMEDIOS", "RIONEGRO", "SABANALARGA", "SABANETA", "SALGAR", "SAN ANDRES DE CUERQUIA",
				"SAN CARLOS", "SAN FRANCISCO", "SAN JERONIMO", "SAN JOSE DE LA MONTANA", "SAN JUAN DE URABA",
				"SAN LUIS", "SAN PEDRO DE LOS MILAGROS", "SAN PEDRO DE URABA", "SAN RAFAEL", "SAN ROQUE", "SAN VICENTE",
				"SANTA BARBARA", "SANTA FE DE ANTIOQUIA", "SANTA ROSA DE OSOS", "SANTO DOMINGO", "SEGOVIA", "SONSON",
				"SOPETRAN", "TAMESIS", "TARAZA", "TARSO", "TITIRIBI", "TOLEDO", "TURBO", "URAMITA", "URRAO", "VALDIVIA",
				"VALPARAISO", "VEGACHI", "VENECIA", "VIGIA DEL FUERTE", "YALI", "YARUMAL", "YOLOMBO", "YONDO",
				"ZARAGOZA", };
		
		Integer[] municipalityCode = { 2, 4, 21, 30, 31, 34, 36, 38, 40, 44, 45, 51, 55, 59, 79, 88, 86, 91, 93, 107,
				113, 120, 125, 129, 134, 138, 142, 145, 147, 150, 154, 172, 190, 101, 197, 206, 209, 212, 234, 237, 240,
				250, 148, 541, 607, 697, 264, 266, 282, 284, 306, 308, 310, 313, 315, 318, 321, 347, 353, 360, 361, 364,
				368, 376, 380, 390, 400, 411, 425, 440, 467, 475, 480, 483, 495, 490, 501, 543, 576, 579, 585, 591, 604,
				615, 628, 631, 642, 647, 649, 652, 656, 658, 659, 660, 664, 665, 667, 670, 674, 679, 42, 686, 690, 736,
				756, 761, 789, 790, 792, 809, 819, 837, 842, 847, 854, 856, 858, 861, 873, 885, 887, 890, 893, 895 };
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(163, 60, 203, 20);
		panel_1.add(comboBox);
		
		
		JLabel lblNewLabel_4 = new JLabel("Sector");
		lblNewLabel_4.setBounds(10, 88, 46, 14);
		panel_1.add(lblNewLabel_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(163, 85, 203, 20);
		panel_1.add(comboBox_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 113, 359, 5);
		panel_1.add(separator_1);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(16, 215, 46, 14);
		panel_1.add(lblEstado);
		
		JLabel lblNewLabel_5 = new JLabel("Ruta de donde guardar\u00E1 el archivo");
		lblNewLabel_5.setBounds(16, 129, 201, 14);
		panel_1.add(lblNewLabel_5);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(214, 125, 103, 23);
		panel_1.add(btnSeleccionar);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setBounds(10, 154, 356, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Ayuda", null, panel_2, null);
	}
}

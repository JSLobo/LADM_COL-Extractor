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

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		frame.setBounds(100, 100, 397, 246);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Conexión", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNombreDeConexin = new JLabel("Nombre de conexión");
		lblNombreDeConexin.setBounds(5, 8, 121, 14);
		lblNombreDeConexin.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNombreDeConexin);
		
		textField = new JTextField();
		textField.setBounds(136, 5, 237, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(5, 33, 98, 14);
		panel.add(lblUsuario);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 30, 237, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(5, 58, 98, 14);
		panel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 55, 237, 20);
		panel.add(passwordField);
		
		JLabel lblNombreDeTabla = new JLabel("Nombre de BD");
		lblNombreDeTabla.setBounds(5, 83, 98, 14);
		panel.add(lblNombreDeTabla);
		
		textField_2 = new JTextField();
		textField_2.setBounds(136, 80, 237, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnProbarConexin = new JButton("Probar conexión");
		btnProbarConexin.setBounds(5, 128, 133, 23);
		panel.add(btnProbarConexin);
		
		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(141, 128, 120, 23);
		panel.add(btnDesconectar);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(264, 128, 109, 23);
		panel.add(btnConectar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 111, 368, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Estado:");
		lblNewLabel.setBounds(25, 155, 46, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Reporte de Actualización Catastral", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Ayuda", null, panel_2, null);
	}
}

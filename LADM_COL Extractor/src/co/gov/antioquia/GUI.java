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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("Conexión", null, panel, null);
		
		JLabel lblNombreDeConexin = new JLabel("Nombre de conexión");
		lblNombreDeConexin.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNombreDeConexin);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		panel.add(lblUsuario);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		panel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		panel.add(passwordField);
		
		JLabel lblNombreDeTabla = new JLabel("Nombre de BD");
		panel.add(lblNombreDeTabla);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnProbarConexin = new JButton("Probar conexión");
		panel.add(btnProbarConexin);
		
		JButton btnDesconectar = new JButton("Desconectar");
		panel.add(btnDesconectar);
		
		JButton btnConectar = new JButton("Conectar");
		panel.add(btnConectar);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Reporte de Actualización Catastral", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Ayuda", null, panel_2, null);
	}

}

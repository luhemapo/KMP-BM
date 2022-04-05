package View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ViewWindow extends JFrame{
	/**
	 * Imagen de fondo de la ventana principal
	 */
	private ImageIcon windowImg;
	/**
	 * Contenedor de la imagen de la ventana principal
	 */
	private JLabel welcome;
	/**
	 * Boton para seleccionar el algoritmo KMP
	 */
	private JButton bt_KMP;
	/**
	 * Boton para seleccionar el algoritmo BM
	 */
	private JButton bt_BM;
	/**
	 * Boton para salir
	 */
	private JButton bt_exit;
	/**
	 * Variable para determinar el ancho de la ventana
	 */
	private int width;
	/**
	 * Variable para determinar la altura de la ventana
	 */
	private int height;
	/**
	 * Metodo inicializador de la ventana inicial. Define todos sus componentes y ubicaciones
	 * <br>
	 * <b>Pre:</b>
	 * <br>
	 * <b>Post:</b>
	 * <br>
	 * La ventana se encuentra lista para mostrarse
	 */
	public ViewWindow() {

		setIconImage(new ImageIcon(getClass().getResource("/Img/logo.png")).getImage());
		setTitle("KMP & BM");
		width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setLayout(null);
		setSize(600, 460);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		

		bt_KMP = new JButton("Use KMP Algorithm");
		bt_KMP.setActionCommand("KMP");
		bt_KMP.setBounds(80, 200, 200, 30);
		bt_KMP.setVisible(true);
		add(bt_KMP);

		bt_BM = new JButton("Use BM Algorithm");
		bt_BM.setActionCommand("BM");
		bt_BM.setBounds(80, 240, 200, 30);
		bt_BM.setVisible(true);
		add(bt_BM);

		bt_exit = new JButton("Exit");
		bt_exit.setActionCommand("EXIT");
		bt_exit.setBounds(80, 280, 200, 30);
		bt_exit.setVisible(true);
		add(bt_exit);

		Dimension wsize = getSize();
		windowImg = new ImageIcon(getClass().getResource("/Img/welcomeimg.jpg"));
		welcome = new JLabel(windowImg);
		welcome.setBounds(0, 0, wsize.width, wsize.height);
		add(welcome);

		setResizable(false);
		setVisible(true);
	

	}
	/**
	 * Metodo que se ejecuta para mostrar un mensaje de informacion o de error.
	 * <br>
	 * <b>Pre:</b>
	 * <br>
	 * Muestra mensaje correspondiente
	 * <br>
	 * <b>Post:</b>
	 * <br>
	 * Se le da al usuario un mensaje
	 * @param mensaje String que contiene el mensaje a mostrar
	 * @param type String el tipo de mensaje a mostrar
	 */
	public void showMessage(String message, String type) {
		if (type.equals("Info")) {
			JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
		} else if (type.equals("Error")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Getter de ImageIcon WindowImg, se usa para obtener la imagen del icono de la ventana
	 * @return Icono de la ventana
	 */
	public ImageIcon getWindowImg() {
		return windowImg;
	}
	/**
	 * setter del ImageIcon windowImg
	 */
	public void setWindowImg(ImageIcon windowImg) {
		this.windowImg = windowImg;
	}
	/**
	 * Getter de JLabel Welcome, se usa para obtener la imagen del fondo de la ventana
	 * @return Icono de la ventana
	 */
	public JLabel getWelcome() {
		return welcome;
	}
	/**
	 * setter del JLabel welcome
	 */
	public void setWelcome(JLabel welcome) {
		this.welcome = welcome;
	}
	/**
	 * Getter de JButton Bt_KMP, se usa para asignar su respectivo Action Listener
	 * @return Accion del boton
	 */
	public JButton getBt_KMP() {
		return bt_KMP;
	}
	/**
	 * setter del JButton bt_KMP
	 */
	public void setBt_KMP(JButton bt_KMP) {
		this.bt_KMP = bt_KMP;
	}
	/**
	 * Getter de JButton Bt_BM, se usa para asignar su respectivo Action Listener
	 * @return Accion del boton
	 */
	public JButton getBt_BM() {
		return bt_BM;
	}
	/**
	 * setter del JButton bt_BM
	 */
	public void setBt_BM(JButton bt_BM) {
		this.bt_BM = bt_BM;
	}
	/**
	 * Getter de JButton Bt_exit, se usa para asignar su respectivo Action Listener
	 * @return Accion del boton
	 */
	public JButton getBt_exit() {
		return bt_exit;
	}
	/**
	 * setter del JButton bt_exit
	 */
	public void setBt_exit(JButton bt_exit) {
		this.bt_exit = bt_exit;
	}	

}

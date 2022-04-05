package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
/**
 * Clase que contiene la ventana que mostrara el archivo y permitira ingresar el texto a buscar
 */
public class ViewSearch extends JFrame{
	/**
	 * Boton que ejecutara la busqueda KMP
	 */
	private JButton bt_searchKMP;
	/**
	 * Boton que ejecutara la busqueda BM
	 */
	private JButton bt_searchBM;
	/**
	 * Boton que regresa a la ventana anterior
	 */
	private JButton bt_back;
	/**
	 * JLabel para indicar al usuario que escriba
	 */
	private JLabel type;
	/**
	 * Imagen de fondo de la ventana
	 */
	private ImageIcon img;
	/**
	 * Contenedor de la imagen de fondo de la ventana
	 */
	private JLabel backgroundImg;
	/**
	 * JCheckBox para hacer la busqueda case sensitive
	 */
	private JCheckBox check;
	/**
	 * JTextArea que muestra el archivo seleccionado
	 */
	private JTextArea file;
	/**
	 * JTextField que muestra el archivo seleccionado
	 */
	private JTextField tf_text;
	/**
	 * JLabel que muestra el numero de coincidencias
	 */
	private JLabel matches;
	/**
	 * Metodo inicializador de la ventana archivo. Define todos sus componentes y ubicaciones
	 * <br>
	 * <b>Pre:</b>
	 * <br>
	 * <b>Post:</b>
	 * <br>
	 * La ventana se encuentra lista para mostrarse
	 */
	public ViewSearch() {
		
	  Font fuente = new Font("Tahoma", 0, 25);
	  Font fuentearchivo = new Font("Tahoma", 0, 18);
		setIconImage(new ImageIcon(getClass().getResource("/Img/logo.png")).getImage());
		setTitle("Search Archive");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		setBounds(0, 0, 1350, 676);
		setLocationRelativeTo(null);

		matches = new JLabel();
		matches.setBounds(980, 250, 400, 30);
		matches.setFont(fuentearchivo);
		matches.setForeground(Color.black);
		add(matches);
		
		setLayout(null);
        check=new JCheckBox("Make search case sensitive");
        check.setBounds(980, 200, 200, 30);
        add(check);


		bt_searchKMP = new JButton("Search KMP");
		bt_searchKMP.setActionCommand("SEARCHKMP");
		bt_searchKMP.setBounds(980, 280, 200, 30);
		bt_searchKMP.setVisible(false);
		add(bt_searchKMP);
		
		bt_searchBM = new JButton("Search BM");
		bt_searchBM.setActionCommand("SEARCHBM");
		bt_searchBM.setBounds(980, 280, 200, 30);
		bt_searchBM.setVisible(false);
		add(bt_searchBM);
		
		bt_back = new JButton("Back");
		bt_back.setActionCommand("BACK");
		bt_back.setBounds(980, 330, 200, 30);
		bt_back.setVisible(true);
		add(bt_back);

		img = new ImageIcon(getClass().getResource("/Img/window.jpg"));
		backgroundImg = new JLabel(img);
		backgroundImg.setBounds(0, 0, 1350, 650);
		

		type = new JLabel("What would you like to search for?:");
		type.setBounds(980, 90, 400,30);
		type.setForeground(Color.BLACK);
		add(type);
		
		file = new JTextArea();
		file.setOpaque(true);
		file.setEnabled(true);
		file.setEditable(false);
		file.setBorder(null);

		JScrollPane scroll = new JScrollPane(file);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(45, 30, 882, 580);
		scroll.setOpaque(true);
		scroll.setBorder(null);
		add(scroll);


		tf_text = new JTextField();
		tf_text.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		tf_text.setFont(fuente);
		tf_text.setBorder(null);
		tf_text.setBounds(980, 120, 300, 60);
		add(tf_text);

		setVisible(false);
		add(backgroundImg);
	}
	/**
	 * Metodo que agrega el contenido del archivo al JTextArea que le corresponde
	 * <br>
	 * <b>Pre:</b>
	 * <br>
	 * La ventana se esta mostrando
	 * <br>
	 * Se lee el archivo
	 * <br>
	 * <b>Post:</b>
	 * <br>
	 * Se muestra el contenido del archivo leido
	 * @param file String que contiene el texto del archivo seleccionado por el usuario
	 */
	public void showfile(String file){
		this.file.setText(file);
	}
	/**
	 * Metodo que resalta la palabra buscada dentro del JTextArea
	 * <br>
	 * <b>Pre:</b>
	 * <br>
	 * Se encontra la palabra buscada dentro del texto
	 * <br>
	 * <b>Post:</b>
	 * <br>
	 * El texto se resalta dentro del JTextArea
	 * @param pos0 int Posicion inicial de la palabra
	 * @param pos1 int Posicion final de la palabra
	 * @param highlighter Highlighter Objeto del resaltador
	 */
	public void showPattern(int pos0, int pos1, Highlighter highlighter){
		try {
			Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
			highlighter.addHighlight(pos0, pos1, painter);

		} catch (BadLocationException e) {
			//e.printStackTrace();
		}
	}
	/**
	 * Metodo que se ejecuta cuando el patron no se encuentra.
	 * <br>
	 * <b>Pre:</b>
	 * <br>
	 * Se busca un patron dentro del texto y no se encontra
	 * <br>
	 * <b>Post:</b>
	 * <br>
	 * Se le informa al usuario que el patron que busca no se encontra dentro del texto
	 */
	public void noPattern(){
		Highlighter highlighter = file.getHighlighter();
		highlighter.removeAllHighlights();
		JOptionPane.showMessageDialog(this, "No se encontro el patron buscado","No encontrado", JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que muestra la cantidad de veces que el algoritmo encontra el patron en el texto
	 * <br>
	 * <b>Pre:</b>
	 * <br>
	 * Se busca el patron en el texto
	 * <br>
	 * <b>Post:</b>
	 * Se muestra la cantidad de veces que se encontro el patron en el texto
	 * @param times int Cantidad de veces que se encontra el patron en el texto
	 */
	public void showMatches(int times){
		matches.setText("Matches found: " + times );
	}
	/**
	 * Getter del JCheckBox, se usa para agregarle su respectivo ActionListener
	 * @return Estado del checkbox
	 */
	public JCheckBox getCheck() {
		return check;
	}
	/**
	 * setter del JCheckBox check
	 */
	public void setCheck(JCheckBox check) {
		this.check = check;
	}
	/**
	 * Getter del JLabel Matches, se usa para obtener el numero de coinsidencias
	 * @return Estado del checkbox
	 */
	public JLabel getMatches() {
		return matches;
	}
	/**
	 * setter del JLabel matches
	 */
	public void setMatches(JLabel matches) {
		this.matches = matches;
	}
	/**
	 * Getter de JButton Bt_searchKMP, se usa para asignar su Action Listener
	 * @return Accion del JButton
	 */
	public JButton getBt_searchKMP() {
		return bt_searchKMP;
	}
	/**
	 * setter del JLabel matches
	 */
	public void setBt_searchKMP(JButton bt_searchKMP) {
		this.bt_searchKMP = bt_searchKMP;
	}
	/**
	 * Getter de JButton Bt_back, se usa para asignar su Action Listener
	 * @return Accion del JButton
	 */
	public JButton getBt_back() {
		return bt_back;
	}
	/**
	 * setter del JButton bt_back
	 */
	public void setBt_back(JButton bt_back) {
		this.bt_back = bt_back;
	}
	/**
	 * setter del JLabel type
	 */
	public void setType(JLabel type) {
		this.type = type;
	}
	/**
	 * Getter de JButton Bt_searchBM, se usa para asignar su Action Listener
	 * @return Accion del JButton
	 */
	public JButton getBt_searchBM() {
		return bt_searchBM;
	}
	/**
	 * setter del JButton bt_searchBM
	 */
	public void setBt_searchBM(JButton bt_searchBM) {
		this.bt_searchBM = bt_searchBM;
	}
	/**
	 * Getter de JLabel BackgroundImg, se usa para obtener la imagen de fodo
	 * @return Imagen de fondo
	 */
	public JLabel getBackgroundImg() {
		return backgroundImg;
	}
	/**
	 * setter del JLabel backgroundImg
	 */
	public void setBackgroundImg(JLabel backgroundImg) {
		this.backgroundImg = backgroundImg;
	}
	/**
	 * Getter de JTextArea File, se usa para obtener el archivo seleccionado
	 * @return Archivo seleccionado
	 */
	public JTextArea getFile() {
		return file;
	}
	/**
	 * setter del JTextArea file
	 */
	public void setFile(JTextArea file) {
		this.file = file;
	}
	/**
	 * Getter de JTextArea File, se usa para obtener la palabra a buscar
	 * @return Texto a buscar
	 */
	public JTextField getTf_text() {
		return tf_text;
	}
	/**
	 * setter del JTextArea file
	 */
	public void setTf_text(JTextField tf_text) {
		this.tf_text = tf_text;
	}
	/**
	 * Getter del resaltador del JTextArea
	 * @return El objeto del resaltador
	 */
	public Highlighter getHighlighter(){
		return file.getHighlighter();
	}
	
	

}

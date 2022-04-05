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

public class ViewSearch extends JFrame{

	private JButton bt_searchKMP;
	private JButton bt_searchBM;
	private JButton bt_back;
	private JLabel type;
	private ImageIcon img;
	private JLabel backgroundImg;
	private JCheckBox check;
	private JTextArea file;
	private JTextField tf_text;
	private JLabel tipoArchivo;
	private JLabel matches;

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
        //check.setVisible(true);
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

	public void showfile(String file){
		this.file.setText(file);
	}

	public void showPattern(int pos0, int pos1, Highlighter highlighter){
		try {
			Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
			highlighter.addHighlight(pos0, pos1, painter);

		} catch (BadLocationException e) {
			//e.printStackTrace();
		}
	}

	public void noPattern(){
		Highlighter highlighter = file.getHighlighter();
		highlighter.removeAllHighlights();
		JOptionPane.showMessageDialog(this, "No se encontro el patron buscado","No encontrado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showMatches(int times){
		matches.setText("Matches found: " + times );
	}
	
	public JCheckBox getCheck() {
		return check;
	}

	public void setCheck(JCheckBox check) {
		this.check = check;
	}

	public JLabel getMatches() {
		return matches;
	}

	public void setMatches(JLabel matches) {
		this.matches = matches;
	}

	public JButton getBt_searchKMP() {
		return bt_searchKMP;
	}

	public void setBt_searchKMP(JButton bt_searchKMP) {
		this.bt_searchKMP = bt_searchKMP;
	}

	public JButton getBt_back() {
		return bt_back;
	}

	public void setBt_back(JButton bt_back) {
		this.bt_back = bt_back;
	}

	public void setType(JLabel type) {
		this.type = type;
	}
	

	public JButton getBt_searchBM() {
		return bt_searchBM;
	}

	public void setBt_searchBM(JButton bt_searchBM) {
		this.bt_searchBM = bt_searchBM;
	}

	public JLabel getBackgroundImg() {
		return backgroundImg;
	}

	public void setBackgroundImg(JLabel backgroundImg) {
		this.backgroundImg = backgroundImg;
	}

	public JTextArea getFile() {
		return file;
	}

	public void setFile(JTextArea file) {
		this.file = file;
	}

	public JTextField getTf_text() {
		return tf_text;
	}

	public void setTf_text(JTextField tf_text) {
		this.tf_text = tf_text;
	}

	public Highlighter getHighlighter(){
		return file.getHighlighter();
	}
	
	

}

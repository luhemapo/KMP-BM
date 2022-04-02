package View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ViewWindow extends JFrame{


	private ImageIcon windowImg;
	private JLabel welcome;
	private JButton bt_KMP;
	private JButton bt_BM;
	private JButton bt_exit;
	private int width;
	private int height;

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
	public void showMessage(String message, String type) {
		if (type.equals("Info")) {
			JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
		} else if (type.equals("Error")) {
			JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ImageIcon getWindowImg() {
		return windowImg;
	}

	public void setWindowImg(ImageIcon windowImg) {
		this.windowImg = windowImg;
	}


	public JLabel getWelcome() {
		return welcome;
	}

	public void setWelcome(JLabel welcome) {
		this.welcome = welcome;
	}

	public JButton getBt_KMP() {
		return bt_KMP;
	}

	public void setBt_KMP(JButton bt_KMP) {
		this.bt_KMP = bt_KMP;
	}

	public JButton getBt_BM() {
		return bt_BM;
	}
	public void setBt_BM(JButton bt_BM) {
		this.bt_BM = bt_BM;
	}
	public JButton getBt_exit() {
		return bt_exit;
	}

	public void setBt_exit(JButton bt_exit) {
		this.bt_exit = bt_exit;
	}	

}

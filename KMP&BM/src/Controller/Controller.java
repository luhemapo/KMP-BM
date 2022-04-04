package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Highlighter;

import Model.Algorithm;
import View.ViewSearch;
import View.ViewWindow;

public class Controller implements ActionListener {
	public ViewWindow viewW;
	public ViewSearch viewS;
	public Algorithm algorithm;
	public String texto = "";
	public Controller() {
		viewW = new ViewWindow();
		viewS = new ViewSearch();

		viewW.getBt_KMP().addActionListener(this);
		viewW.getBt_BM().addActionListener(this);
		viewW.getBt_exit().addActionListener(this);
		viewS.getBt_back().addActionListener(this);
		viewS.getBt_searchKMP().addActionListener(this);
		viewS.getBt_searchBM().addActionListener(this);
	}

	public String readTxt(File txtfile) {
		String txt = "";

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(txtfile));
			String nextLine;
			while ((nextLine = br.readLine()) != null) {
				txt += nextLine + "\n";
			}
			br.close();
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
		texto = txt;
		return txt;
	}

	private void chooseFile() {

		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (.txt)", "txt");
		jfc.setFileFilter(filter);
		int returnVal = jfc.showOpenDialog(viewS);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			viewW.setVisible(false);
			viewS.setVisible(true);
			String file = readTxt(jfc.getSelectedFile());
			viewS.showfile(file);
			algorithm = new Algorithm(file);

		} else {
			viewW.showMessage("No file selected", "Error");

		}

	}

	public void runSearchKMP() {
		String searchFor = viewS.getTf_text().getText();
		ArrayList<Integer> matchesFound = algorithm.KMPAlgorithm(texto,searchFor);
	
		int start;
		int end;
		Highlighter highlighter = viewS.getHighlighter();
		highlighter.removeAllHighlights();
		for (int i = 0; i < matchesFound.size();i++) {
			start = matchesFound.get(i);
			end = matchesFound.get(i) + searchFor.length();
			if (start != -1) {
				viewS.showPattern(start, end, highlighter);
			}
		}
		viewS.showMatches(matchesFound.size());
		if (matchesFound.isEmpty()) {
			viewS.noPattern();
		}
	}
	
	public void runSearchBM() {
		String searchFor = viewS.getTf_text().getText();
		ArrayList<Integer> matchesFound = algorithm.bmReadLines(searchFor);
		int start;
		int starC=0;;
		int endC=0;;
		int end;
		int count=0;
		Highlighter highlighter = viewS.getHighlighter();
		highlighter.removeAllHighlights();
		for (int posicion : matchesFound) {
			start = posicion;
			end = start + searchFor.length();
			if(start!=starC && end!=endC) {
				count++;
			}
			if (start != -1) {
				viewS.showPattern(start, end, highlighter);
				
			}
			starC=start;
			endC=end;
		}
		
		viewS.showMatches(count);
		if (matchesFound.isEmpty() || count==0) {
			viewS.noPattern();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("KMP")) {
			chooseFile();
			viewS.getBt_searchKMP().setVisible(true);
			viewS.getBt_searchBM().setVisible(false);

		} else if (e.getActionCommand().equals("BM")) {
			chooseFile();
			viewS.getBt_searchKMP().setVisible(false);
			viewS.getBt_searchBM().setVisible(true);
		} else if (e.getActionCommand().equals("EXIT")) {
			viewW.showMessage("See you soon!", "Info");
			System.exit(1);

		} else if (e.getActionCommand().equals("BACK")) {
			viewS.setVisible(false);
			viewW.setVisible(true);
			viewS.getBt_searchKMP().setVisible(false);
			viewS.getBt_searchBM().setVisible(false);
		} else if (e.getActionCommand().equals("SEARCHKMP")) {
			algorithm.ans.clear();
			runSearchKMP();

		} else if (e.getActionCommand().equals("SEARCHBM")) {
			runSearchBM();
		}
		
	}
}

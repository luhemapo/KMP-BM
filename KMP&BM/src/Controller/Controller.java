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
	ViewWindow viewW;
	ViewSearch viewS;
	Algorithm algorithm;

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
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
		String searchFor = viewS.getText().getText();
		ArrayList<Integer> matchesFound = algorithm.readLines(searchFor);
		int start;
		int end;
		Highlighter highlighter = viewS.getHighlighter();
		highlighter.removeAllHighlights();
		for (int posicion : matchesFound) {
			start = posicion;
			end = start + searchFor.length();
			if (start != -1) {
				viewS.showPattern(start, end, highlighter);
			}
		}
		viewS.showMatches(matchesFound.size());
		if (matchesFound.isEmpty()) {
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
			runSearchKMP();
		} else if (e.getActionCommand().equals("SEARCHBM")) {
			// CORRER ALGORITMO BOYER MOORE
		}
		
	}
}

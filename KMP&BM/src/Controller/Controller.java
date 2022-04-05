package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Highlighter;

import Model.Algorithm;
import View.ViewSearch;
import View.ViewWindow;
/**
 * Clase que maneja el ciclo de ejecucion de la aplicacion
 */
public class Controller implements ActionListener {
	/**
	 * Objeto de la ventana window
	 */
	public ViewWindow viewW;
	/**
	 * Objeto de la ventana Search
	 */
	public ViewSearch viewS;
	/**
	 * Objeto de la clase Algorithm que contiene los algoritmo a ejecutar
	 */
	public Algorithm algorithm;
	/**
	 * Variable que almacena el texto del archivo seleccionado
	 */
	public String texto = "";
	/**
	 * Metodo que crea un objeto de la clase controlador <br>
	 * <b>Pre:</b> <br>
	 * Las librerias estan correctamente incluidas en el proyecto <br>
	 * <b>Post:</b> <br>
	 * Se inicializaron las ventanas, clases, botones, checkbox y se muestra window.
	 */
	public Controller() {
		viewW = new ViewWindow();
		viewS = new ViewSearch();
		algorithm = new Algorithm();

		viewW.getBt_KMP().addActionListener(this);
		viewW.getBt_BM().addActionListener(this);
		viewW.getBt_exit().addActionListener(this);
		viewS.getBt_back().addActionListener(this);
		viewS.getBt_searchKMP().addActionListener(this);
		viewS.getBt_searchBM().addActionListener(this);
		viewS.getCheck().addActionListener(this);
	}
	/**
	 * Metodo que lee el archivo txt deseado <br>
	 * <b>Pre:</b> <br>
	 * El archivo existe <br>
	 * <b>Post:</b> <br>
	 * El archivo fue correctamente interpretado y su texto esta guardado en una
	 * variable de tipo String
	 * @param txtfile El archivo que selecciono el usuario a traves del JFileChooser
	 * @return El texto que contiene el archivo
	 */
	public String readTxt(File txtfile) {
		String txt ="";
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
		texto=txt;
		return txt;
	}
	/**
	 * Metodo que se encarga de recibir por parte del usuario la seleccion del
	 * archivo. Se muestra un error al usuario si el archivo es invalido <br>
	 * <b>Pre:</b> <br>
	 * El usuario oprime el boton de seleccionar archivo <br>
	 * <b>Post:</b> <br>
	 * Se desplega un JFileChooser que recibe archivos de tipo txt<br>
	 * Se ejecuta el metodo readTxt y guarda el archivo en file de tipo String <br>
	 * Se llama al metodo showfile
	 */
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

		} else {
			viewW.showMessage("No file selected", "Error");

		}

	}
	/**
	 * Metodo que ejecuta la busqueda utilizando el algoritmo KMP <br>
	 * <b>Pre:</b> <br>
	 * El usuario oprimio el boton de buscar <br>
	 * El usuario ingresa el texto a buscar en el JTextField <br>
	 * <b>Post:</b> <br>
	 * Se resalta el texto buscado <br>
	 * Se le informa al usuario en caso que no se encontrara el texto
	 */
	public void runSearchKMP() {
		ArrayList<Integer> matchesFound = new ArrayList<Integer>();
		String searchFor = viewS.getTf_text().getText();
		if(viewS.getCheck().isSelected()) {
			matchesFound = algorithm.KMPAlgorithm(texto,searchFor);	
		}else {
			matchesFound = algorithm.KMPAlgorithm(texto.toLowerCase(),searchFor.toLowerCase());
		}
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
	/**
	 * Metodo que ejecuta la busqueda utilizando el algoritmo BM <br>
	 * <b>Pre:</b> <br>
	 * El usuario oprimio el boton de buscar <br>
	 * El usuario ingresa el texto a buscar en el JTextField <br>
	 * <b>Post:</b> <br>
	 * Se resalta el texto buscado <br>
	 * Se le informa al usuario en caso que no se encontrara el texto
	 */
	public void runSearchBM() {
		ArrayList<Integer> matchesFound = new ArrayList<Integer>();
		String searchFor = viewS.getTf_text().getText();
		if(viewS.getCheck().isSelected()) {
			matchesFound = algorithm.bmReadLines(texto,searchFor);
		}else {
			matchesFound = algorithm.bmReadLines(texto.toLowerCase(),searchFor.toLowerCase());
		}
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
	/**
	 * Contiene la logica de los eventos que vienen de las ventanas <br>
	 * <b>Pre:</b> <br>
	 * El usuario ejecuto una evento y espera una respuesta <br>
	 * <b>Post:</b> <br>
	 * Se ejecuta la accion correspondiente al evento lanzado por el usuario
	 * 
	 * @param e Evento que ejecuta el usuario
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("KMP")) {
			chooseFile();
			viewS.getTf_text().setText("");
			viewS.getMatches().setText("");
			viewS.getBt_searchKMP().setVisible(true);
			viewS.getBt_searchBM().setVisible(false);

		} else if (e.getActionCommand().equals("BM")) {
			chooseFile();
			viewS.getTf_text().setText("");
			viewS.getMatches().setText("");
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
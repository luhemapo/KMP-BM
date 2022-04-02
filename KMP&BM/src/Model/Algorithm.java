package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
	public ArrayList<String> linesOnFile = new ArrayList<>();

	public Algorithm(String file) {

		linesOnFile = new ArrayList<String>();
		Collections.addAll(linesOnFile, file.split("\n"));
	}

	public ArrayList<Integer> readLines(String searchFor) {

		String line = "";
		int result = 0;
		int total = 0;
		ArrayList<Integer> location = new ArrayList<>();

		for (int i = 0; i < linesOnFile.size(); i++) {
			line = linesOnFile.get(i).trim();
			result = KMPAlgorithm(line, searchFor);
			if (result != -1) {
				total += result;
				location.add(total + 2);
				total -= (result);
			}
			total += linesOnFile.get(i).length() + 1;
		}
		
		return location;

	}

	public int KMPAlgorithm(String line, String searchFor) {
		int[] kmptabla = kmptabla(searchFor);

		int contSearch = 0;
		int contLine = 0;

		while (contLine < line.length()) {
			if (line.toUpperCase().charAt(contLine) == searchFor.toUpperCase().charAt(contSearch)) {
				contSearch++;
				if (contSearch == searchFor.length()) {
					int x = searchFor.length() + 1;
					return contLine - x;
				}
				contLine++;
			} else if (contSearch > 0) {
				contSearch = kmptabla[contSearch];
			} else {
				contLine++;
			}
		}
		return -1;
	}

	public int[] kmptabla(String searchFor) {
		int[] tabla = new int[searchFor.length() + 1];
		tabla[0] = -1;
		tabla[1] = 0;

		int piz = 0;
		int pder = 2;

		while (pder < tabla.length) {
			if (searchFor.charAt(pder - 1) == searchFor.charAt(piz)) {
				piz++;
				tabla[pder] = piz;
				pder++;
			} else if (piz > 0) {
				piz = tabla[piz];
			} else {
				tabla[pder] = piz;
				pder++;
			}
		}
		return tabla;
	}
}

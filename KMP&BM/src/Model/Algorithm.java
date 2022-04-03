package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
	public ArrayList<String> linesOnFile = new ArrayList<>();

	public Algorithm(String file) {

		linesOnFile = new ArrayList<String>();
		Collections.addAll(linesOnFile, file.split("\n"));
	}

	public ArrayList<Integer> kmpReadLines(String searchFor) {

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
	
	public ArrayList<Integer> bmReadLines(String searchFor) {

		String line = "";
		int result = 0;
		int total = 0;
		int count=0;
		boolean flag = true;
		ArrayList<Integer> location = new ArrayList<>();
		try {
			for (int i = 0; i < linesOnFile.size(); i++) {
				line = linesOnFile.get(i).trim();
				char[] txt= line.toCharArray();
				result = boyerMooreAlgorithm(txt, searchFor);
				if (result != -1) {
					total += result;
					location.add(total);
					total -= (result);
					flag=true;
				}else {
					flag=false;
				}
				while(flag) {
					result=0;
					char[] newTxt = new char[txt.length - 1];
					for (int k = 0; k < txt.length-1; k++) {
			            if(txt[k] == searchFor.toCharArray()[searchFor.toCharArray().length-1]){
			                for(int index = 0; index < k; index++){
			                	newTxt[index] = txt[index];
			                }
			                for(int j = k; j < txt.length - 1; j++){
			                	newTxt[j] = txt[j+1];
			                }
			                break;
			            }
			        }
					txt=newTxt;
					count++;
					result = boyerMooreAlgorithm(txt, searchFor);
					if (result != -1) {
						result+=count;
						total += result;
						location.add(total);
						total -= (result);
					}else {
						count=0;
						flag=false;
					}
					
				}
				total += linesOnFile.get(i).length() + 1;
			}
		}catch(IndexOutOfBoundsException e) {
			
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
	
	public int[] BoyerMooreTable(int[] table, char[] searchFor, int ptnLen) {
		int count = 0;

	    for(count = 0; count < table.length; count++){
	    	table[count] = ptnLen;
	    }

	    for(count = 0; count < ptnLen; count++){
	    	table[(int)searchFor[count]] = ptnLen - count - 1;
	    }

	    return table;
	}
	
	public int boyerMooreAlgorithm(char[] line, String searchFor) {
		int table[] = new int[256] ;
		int txtLen = 0;
		int ptnLen = 0;
		int i = 0; 
		int j = 0; 

		txtLen = line.length;
		ptnLen = searchFor.toCharArray().length;
		
		table= BoyerMooreTable(table, searchFor.toCharArray(),ptnLen);


	    i = j = ptnLen - 1; 
	    while((i < txtLen) && (j >= 0)){

	        if(line[i] != searchFor.toCharArray()[j]){
	        	int next = (ptnLen - j);
	        	 if(table[(int)line[i]] > (ptnLen - j)){
	 		        next=table[(int)line[i]];
	 		    }else{
	 		        next=(ptnLen - j);
	 		    }
	            i += next;
	            j = ptnLen - 1;  
	        }else{
	            j--;
	            i--;
	        }
	    }

	    if(j < 0) {
	    	return i + 1;
	    }else {
		    return -1;
	    }
	}
}

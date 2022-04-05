package Model;

import java.util.ArrayList;

public class Algorithm {
	public ArrayList<Integer> ans = new ArrayList<>();

	public Algorithm() {

	}

	public ArrayList<Integer> bmReadLines(String texto,String searchFor) {

		String[] linesOnFile = texto.split("\n");
		String line = "";
		int result = 0;
		int total = 0;
		int count=0;
		boolean flag = true;
		ArrayList<Integer> location = new ArrayList<>();
		try {
			for (int i = 0; i < linesOnFile.length; i++) {
				line = linesOnFile[i];
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
				total += linesOnFile[i].length() + 1;
			}
		}catch(IndexOutOfBoundsException e) {
			
		}
		return location;

	}
	
	public ArrayList<Integer> KMPAlgorithm(String line, String searchFor) {
		int m = searchFor.length();
		int n = line.length();
		int lps[] = new int[m];
		int j = 0;
		int i = 0;
		if(m != 0) {
			while (i < n) {
				if (searchFor.charAt(j) == line.charAt(i)) {
					j++;
					i++;
				}
				if (j == m) {
						ans.add((i - j));
						j = lps[j - 1];
				}

				else if (i < n && searchFor.charAt(j) != line.charAt(i)) {
					if (j != 0)
						j = lps[j - 1];
					else
						i = i + 1;
				}
			}	
		}
		
		return ans;
	}
		
	public int boyerMooreAlgorithm(char[] line, String searchFor) {
		int count = 0;
		int table[] = new int[256] ;
		int txtLen = 0;
		int ptnLen = 0;
		int i = 0; 
		int j = 0; 

		txtLen = line.length;
		ptnLen = searchFor.toCharArray().length;
		
		for(count = 0; count < table.length; count++){
	    	table[count] = ptnLen;
	    }

	    for(count = 0; count < ptnLen; count++){
	    	table[(int)searchFor.toCharArray()[count]] = ptnLen - count - 1;
	    }


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

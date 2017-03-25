package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary implements Comparable<String>{

	private List<String> dizionario = new ArrayList<String>();
	
	public void loadDictionary(String language){
	 
		try { 
		 FileReader fr = new FileReader("rsc/"+language+".txt");
		 BufferedReader br = new BufferedReader(fr); 
		 String word; 
		 while ((word = br.readLine()) != null){ 
			 dizionario.add(word);
		 }
		 Collections.sort(dizionario);
		 br.close(); 
	  } catch (IOException e){ 
		 
		System.out.println("Errore nella lettura del file");  
	 }
	 
	}
		
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> parole = new ArrayList<RichWord>();
		int inizio;
		int fine;
		int m;
		boolean g;
		
			for(String x : inputTextList){
				
				inizio = 0;
				fine = dizionario.size();
				g = false;
				while(inizio<fine && g ==false){
					
					m = (fine+inizio)/2;

					if(dizionario.get(m).compareTo(x)==0){
						
						g = true;
					
					}else if(dizionario.get(m).compareTo(x)>0){
					
						fine = m-1;
						
					}else{
						
						inizio = m+1;
					
					}
				}
				parole.add(new RichWord(x, g));
			}
	
		return parole;
	}
	
	@Override
	public int compareTo(String arg0) {
		return this.compareTo(arg0);
	}
}

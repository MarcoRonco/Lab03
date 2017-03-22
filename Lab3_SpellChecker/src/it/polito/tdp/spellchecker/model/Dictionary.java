package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {

	private List<String> dizionario = new ArrayList<String>();
	
	public void loadDictionary(String language){
	 
		try { 
		 FileReader fr = new FileReader("rsc/"+language+".txt");
		 BufferedReader br = new BufferedReader(fr); 
		 String word; 
		 while ((word = br.readLine()) != null){ 
			 dizionario.add(word);
		 }
		 br.close(); 
	  } catch (IOException e){ 
		 
		System.out.println("Errore nella lettura del file");  
	 }
	 
	}
		
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> parole = new ArrayList<RichWord>();
		boolean z;
		
			for(String x : inputTextList){
				z = false;
				if((dizionario.size()%2)==0){
					if(dizionario.get((dizionario.size()/2)).compareTo(x)==0){
						parole.add(new RichWord(x, true));
						z=true;
						
					} else if(dizionario.get((dizionario.size()/2)).compareTo(x)>0){
						
						for(int h = (dizionario.size()/2); h>0; h--){
							if(dizionario.get(h).compareTo(x)==0){
								parole.add(new RichWord(x, true));
								z=true;
								break;
							}
						}
					} else {
						
						for(int h = (dizionario.size()/2); h<dizionario.size(); h++){
							if(dizionario.get(h).compareTo(x)==0){
								parole.add(new RichWord(x, true));
								z=true;
								break;
							}
						}
					}
				}else{
					
					if(dizionario.get((int)(dizionario.size()/2)+1).compareTo(x)==0){
						parole.add(new RichWord(x, true));
						z=true;
						
					} else if(dizionario.get((int)(dizionario.size()/2)+1).compareTo(x)>0){
						
						for(int h = ((int)(dizionario.size()/2)+1); h>0; h--){
							if(dizionario.get(h).compareTo(x)==0){
								parole.add(new RichWord(x, true));
								z=true;
								break;
							}
						}
					} else {
						
						for(int h = ((int)(dizionario.size()/2)+1); h<dizionario.size(); h++){
							if(dizionario.get(h).compareTo(x)==0){
								parole.add(new RichWord(x, true));
								z=true;
								break;
							}
						}
					}
				}
				if(z == false)
					parole.add(new RichWord(x, false));
			}
		return parole;
	}
}

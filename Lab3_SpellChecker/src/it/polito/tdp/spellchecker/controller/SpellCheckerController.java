/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	Dictionary model;
	
	public void setDictionary(Dictionary model){
		this.model=model;
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboBoxLingua"
    private ComboBox<String> comboBoxLingua; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpell"
    private Button btnSpell; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutput"
    private TextArea txtOutput; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private Label txtTime; // Value injected by FXMLLoader

    @FXML // fx:id="txtCont"
    private Label txtCont; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML
    void doClear(ActionEvent event) {
    	comboBoxLingua.setDisable(false);
    	txtInput.setDisable(false);
    	txtInput.clear();
    	txtOutput.clear();

    }

    @FXML
    void doSpell(ActionEvent event) {
    	
    	btnClear.setDisable(false);
    	comboBoxLingua.setDisable(true);
    	txtInput.setDisable(true);
    	List<String> p = new ArrayList<String>();
    	String frase = txtInput.getText().toLowerCase().replaceAll("[\\p{Punct}]", "");
    	String h[] = frase.split(" ");
    	for(int i = 0; i<h.length; i++){
    		p.add(h[i]);
    	}
    	
    	model.loadDictionary(comboBoxLingua.getValue());
    	
    	List<RichWord> s = model.spellCheckText(p);
    	int i = 0;
    	long l1 = System.nanoTime();
    	for(RichWord k :s){
    		if(k.isCorretta()==false){
    			txtOutput.appendText(k.getParola()+'\n');
    			i++;
    		}
    	}
    	long l2 = System.nanoTime();
    	txtTime.setText(((l2-l1)/1e9)+" secondi");
    	txtCont.setText("trovati "+i+" errori");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboBoxLingua != null : "fx:id=\"comboBoxLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtCont != null : "fx:id=\"txtCont\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        
        comboBoxLingua.getItems().addAll("English", "Italian");
        
        if(comboBoxLingua.getItems().size()!=0)
        	comboBoxLingua.setValue(comboBoxLingua.getItems().get(0));
    }
}

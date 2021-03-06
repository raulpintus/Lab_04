package it.polito.tdp.lab04.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();
	List<Studente> studenti = new LinkedList<Studente>();
	
	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model=model;
		corsi.add(new Corso("","","",""));
		for(Corso c : model.addCorsi())
			corsi.add(c);
		comboCorso.getItems().addAll(corsi);
		for(Studente s : model.addStudenti())
			studenti.add(s);
	}

	@FXML
	void doReset(ActionEvent event) {

	}

	@FXML
	void doCercaNome(ActionEvent event) {
		String testo= txtMatricola.getText();
		Studente s=model.cercaNome(testo);
		txtNome.setText(s.getNome());
		txtCognome.setText(s.getCognome());
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		Corso c = comboCorso.getValue();
		if(c.equals(new Corso("","","","")))
			txtResult.setText("Seleziona il corso, coglione");
		for(Studente s : studenti){
			for(Studente st : model.getIscrittiCorso(c)){
				if(s.getMatricola().equals(st.getMatricola()))
					txtResult.appendText(s.toString());
			}
		}

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		String matricola=txtMatricola.getText();
		Studente s=model.cercaNome(matricola);
		if(!studenti.contains(s))
			txtResult.setText("Non esiste, coglione");
		for(Corso c : corsi){
			for(Corso co : model.getCorsiIscritto(matricola)){
				if(c.getCodIns().equals(co.getCodIns()))
					txtResult.appendText(c.toString());
			}
		}
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		Corso c=comboCorso.getValue();
		String matricola= txtMatricola.getText();
		if(model.getIsIscritto(matricola, c))
			txtResult.setText("Studente iscritto, coglione");
		else
			txtResult.setText("Studente non iscritto, coglione");
	}

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

}

package it.polito.tdp.porto;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.db.PortoDAO;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	Model model;
	PortoDAO dao;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	txtResult.clear();
    	Author selectedAuthor = boxPrimo.getSelectionModel().getSelectedItem();
    	List<Author> autori =  model.getNeighbors(selectedAuthor);
    	Collections.sort(autori);
    	for(Author c : autori) {
    		txtResult.appendText(c+"\n");
    	}
    	
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	txtResult.clear();
    	Author a1 = boxPrimo.getSelectionModel().getSelectedItem();
    	Author a2 = boxSecondo.getSelectionModel().getSelectedItem();
    	try{
    		for(Author a : model.getCamminoMinimo(a1, a2)) {
    			txtResult.appendText(a+"\n");
    		}
    	}
    	catch(NullPointerException e) {
    		txtResult.setText("Non esiste nessun cammino minimo tra "+a1+" e "+a2+".");
    		return;
    	}
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
        dao=new PortoDAO();
        ObservableList<Author> autori = FXCollections.observableArrayList();
        autori.addAll(dao.getAllAutori().values());
        Collections.sort(autori);
        boxPrimo.setItems(autori);
        boxPrimo.setValue(autori.get(0));
        boxSecondo.setItems(autori);
        boxSecondo.setValue(autori.get(1));
        
    }

	public void setModel(Model model) {
		this.model=model;
		
	}
}

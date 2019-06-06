package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class BarController {
	
	Simulatore simu = new Simulatore();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    void doSimulazione(ActionEvent event) {
    	
    	txtResult.clear();
    	txtResult.appendText("	***Simulazione BAR***\n");
    	simu.init();
    	simu.run();
    	txtResult.appendText("Numero totale clienti: " + simu.getNumTotClienti()+"\n");
    	txtResult.appendText("Numero clienti soddisfatti: " + simu.getSoddisfatti()+"\n");
    	txtResult.appendText("Numero clienti insoddisfatti: " + simu.getInsoddisfatti()+"\n");
    	

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";

    }
    
    public void setSimulatore(Simulatore simu) {
    	this.simu = simu;
    }
}

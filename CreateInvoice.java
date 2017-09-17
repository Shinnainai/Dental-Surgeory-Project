import java.io.Serializable;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateInvoice implements EventHandler<Event>, Serializable{

	private Patient choosenPatient;

	public void handle(Event event) {
		if (choosenPatient == null) {
			displayErrorAlertBox();
			System.out.println("Patient is null");
		}
		else {
			displayAlertBox();
			
			int ID = getIDofChoosenPatient(choosenPatient);
			if (ID == -1)
				System.out.println("ID Error");
			else
				MainApplication.createInvoice(ID);
		}
	}
	
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Create Invoice");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		Label successMessage = new Label("Success! Invoice Created!");
		grid.add(successMessage,  0,  1);

		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public void displayErrorAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Create Invoice");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		Label successMessage = new Label("Error! No patient choosen");
		grid.add(successMessage,  0,  1);

		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public int getIDofChoosenPatient(Patient choosenPatient) {
		int ID = -1;
		ID = choosenPatient.getPatientNo();
		return ID;
	}
	
	public void setPatient(Patient p) {
		this.choosenPatient = p;
	}
	
}

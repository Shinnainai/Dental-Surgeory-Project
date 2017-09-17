import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPatient implements EventHandler<Event> {

	private String name;
	private String address;
	private long phoneNo;
	private ListView<String> listViewProcedures;
	private ListView<String> listViewPayments;

	public AddPatient(ListView<String> lv, ListView<String> lv2)
	{
		this.listViewProcedures = lv;
		this.listViewPayments = lv2;
	}
	
	@Override
	public void handle(Event event) {
		displayAlertBox();
		System.out.println("Test + " + name + address + phoneNo);
		listViewProcedures.getItems().add(name);
		listViewPayments.getItems().add(name);
		MainApplication.addPatient( name,  address,  phoneNo);
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add Patient");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		Label PatientName = new Label("Patient Name:");
		grid.add(PatientName,  0,  1);
	
		TextField patientTextField = new TextField();
		grid.add(patientTextField,  1,  1);
	
		Label address = new Label("Address:");
		grid.add(address,  0, 2);
	
		TextField addressTextField = new TextField();
		grid.add(addressTextField,  1,  2);
	
		Label contactNo = new Label("Contact Number:");
		grid.add(contactNo,  0,  3);
	
		TextField contactTextField = new TextField();
		grid.add(contactTextField,  1,  3);
		
		Button addPatient = new Button("Add Patient");
		grid.add(addPatient, 1, 4);
		
		Label ErrorMessage = new Label("Error. Please enter text");
		ErrorMessage.setTextFill(Color.RED);
		ErrorMessage.setVisible(false);
		grid.add(ErrorMessage, 1, 7);
		
		addPatient.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	if( patientTextField.getText().isEmpty() || addressTextField.getText().isEmpty()) {
					ErrorMessage.setVisible(true);
				}
		    	else
		        window.close();
		    }
		});
		
		
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
		changeType(patientTextField, addressTextField, contactTextField);
		
	}
	
	public void changeType(TextField patientTextField, TextField addressTextField, TextField contactTextField) {
		try {
		name = patientTextField.getText();
		address = addressTextField.getText();
		String phoneNoStr = contactTextField.getText();
		phoneNo = Long.parseLong(phoneNoStr);
		} catch(Exception e) {
			System.out.println("Error");
		}
	}
}

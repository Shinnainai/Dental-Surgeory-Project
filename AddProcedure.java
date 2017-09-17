import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class AddProcedure implements EventHandler<Event>{

	private ComboBox<String> comboBox = new ComboBox<String>();
	private int cost;
	private Date date;
	private ListView<String> proceduresInInvoice;
	private Patient choosenPatient;
	private ArrayList<String> allProceduresList;

	
	
	public AddProcedure(ListView<String> lv, ArrayList<String> x) {
		this.proceduresInInvoice = lv;
		this.allProceduresList = x;
		
	}
	public void handle(Event event) {
		displayAlertBox();

		int ID = getIDofChoosenPatient();
		if (ID == -1) {
			System.out.println("Problem finding ID");
		}
		else{
			MainApplication.addProcedure(cost,  comboBox.getValue().toString(), date, ID);
			proceduresInInvoice.getItems().add(comboBox.getValue().toString() + date);
			System.out.println(proceduresInInvoice.getItems());
		}
		
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add Procedure");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		Label procedureName = new Label("Procedure Name:   ");
		grid.add(procedureName,  0,  1);
		
		ObservableList<String> options = FXCollections.observableArrayList();
		comboBox.getItems().clear();
				
		for(int i = 0; i < allProceduresList.size(); i ++) {
			System.out.println("allProcList at " + i + allProceduresList.get(i));
			options.add(allProceduresList.get(i));
		}
		
		comboBox.setPromptText("Select a Procedure");
		comboBox.getItems().addAll(options);
			
		grid.add(comboBox,  1,  1);
		
		Label dateLabel = new Label("Date:   ");
		grid.add(dateLabel, 0, 3);
		DatePicker datePicker = new DatePicker();

		datePicker.setOnAction(event -> {
			LocalDate localDate = datePicker.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			date = Date.from(instant);
		    System.out.println("Selected date: " + date);
		});
		grid.add(datePicker, 1, 3);
		
		
		Label cost = new Label("Cost:   ");
		grid.add(cost,  0,  4);
		
		TextField costTextField = new TextField();
		grid.add(costTextField,  1,  4);
		
		
		Button addProcedure = new Button("Add Procedure");
		grid.add(addProcedure, 1, 6);
		
		Label ErrorMessage = new Label("Error. Please enter text");
		ErrorMessage.setTextFill(Color.RED);
		ErrorMessage.setVisible(false);
		grid.add(ErrorMessage, 1, 7);
		
		addProcedure.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	if( datePicker.equals(null) || costTextField.getText().isEmpty() || comboBox.getValue().toString().isEmpty()) {
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
		changeType(costTextField);
	}
	
	public int getIDofChoosenPatient() {
		int ID = -1;
		ID = choosenPatient.getPatientNo();
		return ID;
	}
	
	public void changeType(TextField costTextField) {
		String costStr = costTextField.getText();
		cost = Integer.parseInt(costStr);
	}
	
	public void setPatient(Patient p) {
		this.choosenPatient = p;
	}
	
}

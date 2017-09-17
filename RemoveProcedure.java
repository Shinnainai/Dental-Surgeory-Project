import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveProcedure implements EventHandler<Event> {
	ComboBox<String> comboBox;
	private ArrayList<String> proceduresList;
	private Patient choosenPatient;
	private ObservableList<String> options = FXCollections.observableArrayList();
	
	public RemoveProcedure(ArrayList<String> x) {
		this.proceduresList = x;
	}
	
	public void handle(Event event) {
		displayAlertBox();
		int ID = getIDofChoosenPatient();
		if (ID == -1) {
			System.out.println("Problem finding ID");
		}
		else
		{
			System.out.println("Here");
			String value = comboBox.getValue().toString();
			String[] splited = value.split("\\s+");
			MainApplication.removeProcedure(splited[0], ID);
			int selectedIdx = comboBox.getSelectionModel().getSelectedIndex();
			proceduresList.remove(selectedIdx);
			System.out.println("Before: " + options);
			
			proceduresList.remove(selectedIdx);
			comboBox.getItems().clear();
			
			options.clear();
			System.out.println("After: " + options);
			
			getList();
		}
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Remove Procedure");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		Label procedureName = new Label("Select Procedure:   ");
		grid.add(procedureName,  0,  1);

		getList();
		System.out.println("displayAlert options : " + options);
		comboBox = new ComboBox<String>(options);
		comboBox.setPromptText("Select a Procedure");
		grid.add(comboBox,  1,  1);
		
		Button removeProcedure = new Button("Remove Procedure");
		removeProcedure.setOnAction(e -> window.close());
		grid.add(removeProcedure, 1, 4);
		
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public int getIDofChoosenPatient() {
		int ID = -1;
		ID = choosenPatient.getPatientNo();
		return ID;
	}
	
	public void getList() {
		//for all patients
		for(int i = 0; i < MainApplication.getSize(); i ++)
		{
			//get all invoices
			for(int j = 0; j < MainApplication.getInvoiceListSize(i); j++)
			{
				//get all procedures in invoice
				for(int k = 0; k < MainApplication.getProcListSize(i, j); k ++)
				{
					System.out.println("getList options.add" + MainApplication.getPatient(i).getInvoice(j).getProcedureName(k) + " " + MainApplication.getPatient(i).getInvoice(j).getProcedureDate(k));
					options.add(MainApplication.getPatient(i).getInvoice(j).getProcedureName(k) + " " + MainApplication.getPatient(i).getInvoice(j).getProcedureDate(k));
				}
			}
		}
		System.out.println("getList: " + options);	
	}
	
	public void setPatient(Patient p) {
		this.choosenPatient = p;
	}
}

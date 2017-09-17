import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addProcedureType implements EventHandler<Event>{
	private ListView<String> allProceduresLV;
	private String procedureName;
	
	public addProcedureType(ListView<String> lv) {
		this.allProceduresLV = lv;
	}
	public void handle(Event event) {
		displayAlertBox();
		allProceduresLV.getItems().add(procedureName);
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
		TextField procedureTextField = new TextField();
		grid.add(procedureTextField, 1, 1);
		
		Button addProcedure = new Button("Add Procedure");
		addProcedure.setOnAction(e -> window.close());
		grid.add(addProcedure, 1, 4);
		
		
		
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		changeType(procedureTextField);
		
		
	}
	
	public void changeType(TextField procedureTextField) {
		procedureName = procedureTextField.getText();
		System.out.println("Proc Name: " + procedureName);
	}
	
	
}
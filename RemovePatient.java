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

public class RemovePatient implements EventHandler<Event> {
	private String name;
	private ComboBox comboBox = new ComboBox();
	private ListView<String> listViewProcedures;
	private ListView<String> listViewPayments;

	public RemovePatient(ListView<String> lv, ListView<String> lv2) {
		this.listViewProcedures = lv;
		this.listViewPayments = lv2;
	}
	
	@Override
	public void handle(Event event) {
		displayAlertBox();
		System.out.println("Test + " +  comboBox.getValue().toString() + " Name: "+name);
		listViewProcedures.getItems().remove(name);
		listViewPayments.getItems().remove(name);
		MainApplication.removePatient(comboBox.getValue().toString());
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Remove Patient");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		Label PatientName = new Label("Patient Name:");
		grid.add(PatientName,  0,  1);
	
		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i = 0; i < MainApplication.getSize(); i ++) {
			options.add(MainApplication.getName(i));
		}
	
		comboBox = new ComboBox(options);
		comboBox.setPromptText("Select a patient");
		grid.add(comboBox, 0, 2);
		
		
		Button removePatient = new Button("Remove Patient");
		removePatient.setOnAction(e -> window.close());
		grid.add(removePatient, 0, 4);
		
		
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}

	
}

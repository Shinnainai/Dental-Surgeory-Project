import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DisplayPatients implements EventHandler<Event> {

	
	@Override
	public void handle(Event event) {
		displayAlertBox();
		System.out.println("Test");
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Display Patients");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		Label T1 = new Label();
		String displayPatients1 = MainApplication.displayPatients();
		T1.setText(displayPatients1);
		grid.add(T1, 0, 1);
		
		Button closeWindow = new Button("Close");
		closeWindow.setOnAction(e -> window.close());
		grid.add(closeWindow, 0, 5);
		
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
}
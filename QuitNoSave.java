import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuitNoSave implements EventHandler<Event> {
	
	@Override
	public void handle(Event event) {
		System.out.println("Test");
		displayAlertBox();
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Save and Quit");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		
		Label quitConfirm = new Label("Are you sure you want to quit?");
		grid.add(quitConfirm, 1, 2);
		
		
		//To be edited
		Button quit = new Button("Quit");
		quit.setOnAction(e -> Platform.exit());
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> window.close());
		grid.add(quit, 1, 3);
		grid.add(cancel, 2, 3);
		
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
	}
}

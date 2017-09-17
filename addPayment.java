import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addPayment implements EventHandler<Event> {

	private Patient choosenPatient;
	private Procedure choosenProcedure;
	private int amount;
	
	
	public void handle(Event event) {
		displayAlertBox();
		int ID = getIDofChoosenPatient();
		if (ID == -1) {
			System.out.println("Problem finding ID");
		}
		else
			MainApplication.addPayment(ID, amount);
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add Payment");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
	
		Label amount = new Label("Amount:");
		grid.add(amount,  0,  3);
	
		TextField amountTextField = new TextField();
		grid.add(amountTextField,  0,  4);
	
		Button addPayment = new Button("Add Payment");
		grid.add(addPayment, 0, 5);
		
		Label ErrorMessage = new Label("Error. Please enter text");
		ErrorMessage.setTextFill(Color.RED);
		ErrorMessage.setVisible(false);
		grid.add(ErrorMessage, 1, 7);
		
		addPayment.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	if(amountTextField.getText().isEmpty()) {
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
		
		changeType(amountTextField);
	}
	
	public int getIDofChoosenPatient() {
		int ID = -1;
		ID = choosenPatient.getPatientNo();
		return ID;
	}
	
	public void changeType(TextField amountTextField) {
		String amountStr = amountTextField.getText();
		amount = Integer.parseInt(amountStr);
	}
	
	public void setPatient(Patient p) {
		this.choosenPatient = p;
	}
	public Patient getPatient() {
		return this.choosenPatient;
	}
	
	public void setProcedure(Procedure p) {
		this.choosenProcedure = p;
	}
}

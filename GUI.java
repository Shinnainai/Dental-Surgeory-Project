import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GUI extends Application {

	
	private BorderPane borderPane = new BorderPane();
	private TabPane tabPane = new TabPane();
	private Tab homeTab = new Tab();
	private Tab patientTab = new Tab();
	private Tab procedureTab = new Tab();
	private Tab paymentTab = new Tab();
	private Tab reportTab = new Tab();
	private ListView<String> listViewProcedures = new ListView<String>();
	private ListView<String> listViewPayments = new ListView<String>();
	private ListView<String> proceduresInInvoice = new ListView<String>();
	private ArrayList<String> allProceduresList =new ArrayList<String>();
	String name;
	String password1;
	
	String choosenPatient1;
	String choosenPatient2;
	String choosenProcedure;
	
	//onlly solution to unsolveable problem
	private CreateInvoice ci;
	private AddProcedure aProc;
	private RemoveProcedure reProc;
	private addPayment ap;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void start(Stage primaryStage) {
		MainApplication.createDentists();
		MainApplication.readProcedures(allProceduresList);
		
		
		primaryStage.setTitle("Dental Surgery System");
	
		GridPane loginGrid = new GridPane();
		loginGrid.setPadding(new Insets(25, 25, 25, 25));
        
		Label welcome = new Label("Welcome to the Patient File System");
		welcome.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 20));
		welcome.setAlignment(Pos.CENTER);
		loginGrid.add(welcome, 1, 1, 6, 1);
		loginGrid.setVgap(3);
		
		Label dentistName = new Label("Dentist Name: ");
		loginGrid.add(dentistName, 1, 3);
		TextField dentistTextField = new TextField();
		loginGrid.add(dentistTextField, 2, 3);
		
		Label password = new Label("Password: ");
		loginGrid.add(password, 1, 4);
		PasswordField pwBox = new PasswordField();
		loginGrid.add(pwBox, 2, 4);
		
		Button login = new Button("Login");
		HBox hbBtn = new HBox();
		hbBtn.setAlignment(Pos.CENTER_LEFT);
		hbBtn.getChildren().add(login);
		loginGrid.add(hbBtn, 2, 5);
		
		FlowPane loginPane = new FlowPane();
        loginPane.setVgap(10);
        Scene loginScene = new Scene(loginPane, 300, 150, Color.WHITE);
        loginPane.getChildren().add(loginGrid);
        
        
        
		
		GridPane generalGrid = new GridPane();
		generalGrid.setPadding(new Insets(25, 25, 25, 25));
		generalGrid.add(welcome, 1, 1, 6, 1);
		generalGrid.setVgap(3);
		
		Label cont = new Label("Select a tab to continue");
		cont.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
		cont.setAlignment(Pos.CENTER);
		generalGrid.add(cont, 1, 2, 6, 1);
		
		Button loadPatients = new Button("Load Previous Patients");
		loadPatients.addEventHandler(MouseEvent.MOUSE_CLICKED, new loadFile());
		generalGrid.add(loadPatients, 0, 3, 1, 1);
		
		Separator separator = new Separator();
		separator.setMaxWidth(400);
		separator.setPadding(new Insets(40, 0, 10, 0));
		generalGrid.add(separator, 0, 4, 280, 1);
		
		ListView<String> allProceduresLV = new ListView<String>();
		allProceduresLV.getItems().addAll(allProceduresList);
		generalGrid.add(allProceduresLV, 0, 6, 4, 2);
		
		Button procedureBtn = new Button("Add Procedure Type");
		procedureBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new addProcedureType(allProceduresLV));
		procedureBtn.setAlignment(Pos.CENTER);
		generalGrid.add(procedureBtn, 0, 8);
		generalGrid.setVgap(3);
		
		homeTab.setText("Home");
		homeTab.setContent(generalGrid);
		
        
        
        FlowPane generalPane = new FlowPane();
        generalPane.setVgap(10);
        Scene generalScene = new Scene(generalPane, 550, 400, Color.WHITE);
        	
        MainApplication.printDentists();
        
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Hello");
				changeType(dentistTextField, pwBox);
				boolean exists = false;
				for(int i = 0; i < MainApplication.getDentistSize(); i ++) {
					System.out.println("i["+i+"]: "+MainApplication.getDentistName(i));
					if(MainApplication.getDentistName(i).equalsIgnoreCase(name) ){
						System.out.println("=== "+password1+" -- "+MainApplication.getDentistPassword(i));
					}
					if(MainApplication.getDentistName(i).equalsIgnoreCase(name) && MainApplication.getDentistPassword(i).equals(password1)) 
					{
						exists = true;
					}
					
				}
				if(exists == true)
					primaryStage.setScene(generalScene);
				else
					System.out.println("Error. Dentist does not exist: "+dentistTextField.getText());
				
			}
		});

        
        patientTab();
        procedureTab();
        paymentTab();
        reportsTab();
       
        
         tabPane.getTabs().add(homeTab);
         tabPane.getTabs().add(patientTab);
         tabPane.getTabs().add(procedureTab);
         tabPane.getTabs().add(paymentTab);
         tabPane.getTabs().add(reportTab);
         
         generalGrid.getChildren().addAll(tabPane);
        
    	borderPane.prefHeightProperty().bind(loginScene.heightProperty());
		borderPane.prefWidthProperty().bind(loginScene.widthProperty());
		loginPane.getChildren().add(borderPane);
		
		BorderPane borderPane2 = new BorderPane();
		borderPane2.prefHeightProperty().bind(generalScene.heightProperty());
		borderPane2.prefWidthProperty().bind(generalScene.widthProperty());
		borderPane2.setCenter(tabPane);
		borderPane2.getChildren().add(generalGrid);
		generalPane.getChildren().add(borderPane2);
       
		primaryStage.setScene(loginScene);
		primaryStage.show();
		
		
	}
	
	public void patientTab() {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(15, 25, 25, 60));
		grid.setHgap(3);
		grid.setVgap(10);
		
		Label patientsLabel = new Label("Welcome to the Patients Tab");
		patientsLabel.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 20));
		patientsLabel.setAlignment(Pos.CENTER);
		grid.add(patientsLabel, 0, 1);
		
		Button btn1 = new Button("Add Patient");
		btn1.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddPatient(listViewProcedures, listViewPayments));
			
		Button btn2 = new Button("Remove Patient");
		btn2.addEventHandler(MouseEvent.MOUSE_CLICKED, new RemovePatient(listViewProcedures, listViewPayments));
		
		Button btn3 = new Button("Display Patients");
		btn3.addEventHandler(MouseEvent.MOUSE_CLICKED, new DisplayPatients());
		
		Button btn4 = new Button("Save and Quit");
		btn4.addEventHandler(MouseEvent.MOUSE_CLICKED, new SaveAndQuit(MainApplication.getArrayPatients()));
		
		Button btn5 = new Button("Quit without saving");
		btn5.addEventHandler(MouseEvent.MOUSE_CLICKED, new QuitNoSave());
		
		HBox hbBtn = new HBox(5);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.getChildren().add(btn1);
		hbBtn.getChildren().add(btn2);
		hbBtn.getChildren().add(btn3);
		
		HBox hbBtn2 = new HBox(5);
		hbBtn2.setAlignment(Pos.CENTER);
		hbBtn2.getChildren().add(btn4);
		hbBtn2.getChildren().add(btn5);
		
		grid.add(hbBtn, 0, 2, 8, 1);
		grid.add(hbBtn2, 0, 3, 8, 1);

		patientTab.setText("Patient System");
		patientTab.setContent(grid);
	}
	
	public void procedureTab() {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setHgap(3);
		grid.setVgap(3);

		Label proceduresLabel = new Label("Welcome to the Procedures Tab");
		proceduresLabel.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 20));
		proceduresLabel.setAlignment(Pos.CENTER);
		grid.add(proceduresLabel, 0, 1);
		
		Label selectPatient = new Label("Select Patient:");
		selectPatient.setFont(Font.font("Serif", FontWeight.BOLD, 18));
		selectPatient.setAlignment(Pos.CENTER);
		grid.add(selectPatient, 0, 2, 6, 1);
		
		grid.add(this.listViewProcedures, 0, 3, 4, 2);
		

		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.CENTER);
		Button invoiceBtn = new Button("Create Invoice");
		hbBtn2.getChildren().add(invoiceBtn);
		
		Button addProcBtn = new Button("Add Procedure");
		Button removeProcBtn= new Button("Delete Procedure");
		
		hbBtn2.getChildren().add(addProcBtn);
		hbBtn2.getChildren().add(removeProcBtn);
		addProcBtn.setVisible(false);
		removeProcBtn.setVisible(false);
		
		
		listViewProcedures.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
				{
				public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue)
				{
					choosenPatient1 = newValue;
					System.out.println("New Value: " + newValue);
					for(int i = 0; i < MainApplication.getSize(); i ++) {
						if ( choosenPatient1.equalsIgnoreCase(MainApplication.getName(i)))
						{
							ci.setPatient( MainApplication.getPatient(i) );
							aProc.setPatient(MainApplication.getPatient(i));
							reProc.setPatient(MainApplication.getPatient(i));
							
							invoiceBtn.setVisible(true);
							addProcBtn.setVisible(false);
							removeProcBtn.setVisible(false);							
						}
					}					
				}
				});
		
		
		this.ci = new CreateInvoice();
		invoiceBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, ci);
		
		this.aProc = new AddProcedure(proceduresInInvoice, allProceduresList);
		addProcBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, aProc);
		
		this.reProc = new RemoveProcedure(allProceduresList);
		removeProcBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, reProc);
		
		
		Label errorMessage = new Label("Error. No Patient was choosen.");
	
			invoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if(choosenPatient1 == null) {
						invoiceBtn.setVisible(false);
						errorMessage.setTextFill(Color.RED);
						grid.add(errorMessage, 0, 10);
						invoiceBtn.setVisible(true);
					}
					else {
						invoiceBtn.setVisible(false);
						errorMessage.setVisible(false);
						addProcBtn.setVisible(true);
						removeProcBtn.setVisible(true);
					}
					
				}
			});
			
		
		
		grid.add(hbBtn2, 0, 9, 6, 1);
		
		procedureTab.setText("Procedure System");
		procedureTab.setContent(grid);
	}
		
	public void paymentTab() {
	
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setHgap(3);
		grid.setVgap(3);

		Label paymentsLabel = new Label("Welcome to the Payments Tab");
		paymentsLabel.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 20));
		paymentsLabel.setAlignment(Pos.CENTER);
		grid.add(paymentsLabel, 0, 1);
		
		Label selectPatient = new Label("Select Patient:");
		selectPatient.setFont(Font.font("Serif", FontWeight.BOLD, 18));
		selectPatient.setAlignment(Pos.CENTER);
		grid.add(selectPatient, 0, 2, 1, 1);
		
		grid.add(this.listViewPayments, 0, 3, 2, 2);
		
		
		listViewPayments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
		public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue)
			{
				choosenPatient2 = newValue;
				System.out.println("New Value: " + newValue);
				for(int i = 0; i < MainApplication.getSize(); i ++) {
					if ( choosenPatient2.equalsIgnoreCase(MainApplication.getName(i)))
					{
						ap.setPatient( MainApplication.getPatient(i) );
					}
				}	
			}	
		});
		

		Label selectProcedure = new Label("Select Procedure:");
		selectProcedure.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 18));
		selectProcedure.setAlignment(Pos.CENTER);
		grid.add(selectProcedure, 0, 6, 1, 1);
		grid.add(this.proceduresInInvoice, 0, 7, 4, 2);
		
		
		proceduresInInvoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
		public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue)
		{
			choosenProcedure = newValue;
			System.out.println("New Value: " + newValue);
			Patient choosenPat = ap.getPatient();
			int ID = choosenPat.getPatientNo();
			
			
			for(int i = 0; i < MainApplication.getSize(); i ++) {
				if(ID == MainApplication.getPatient(i).getPatientNo()) {
					for(int j = 0; j < MainApplication.getProcListSize(i); j ++) {
						if(choosenProcedure.equalsIgnoreCase(MainApplication.getPatient(i).getInvoice(0).getProcedureName(j))) {
							ap.setProcedure(MainApplication.getProcedure(i, j));
						}
					}
				}
			}	
		}
		});
		
		this.ap = new addPayment();
		Button btn8 = new Button("Add Payment");
		btn8.addEventHandler(MouseEvent.MOUSE_CLICKED, ap);
	
		HBox hbBtn3 = new HBox(10);
		hbBtn3.setAlignment(Pos.CENTER);
		hbBtn3.getChildren().add(btn8);
	
		grid.add(hbBtn3, 0, 9, 6, 1);
		
		paymentTab.setText("Payment System");
		paymentTab.setContent(grid);
	
	}
	
		
	public void reportsTab() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setHgap(3);
		grid.setVgap(3);
		
		Label reportsLabel = new Label("Welcome to the Reports Tab");
		reportsLabel.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, 20));
		reportsLabel.setAlignment(Pos.CENTER);
		grid.add(reportsLabel, 0, 1);
		
		Button alphabeticalListBtn = new Button("Generate Alphabetical Report");
		alphabeticalListBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new GenerateAlphabetical(MainApplication.getArrayPatients()));
	
		Button notPaidListBtn = new Button("Generate Report Based on Owed Amounts");
		notPaidListBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new GenerateNotPaid());
		
		
		HBox hbBtn4 = new HBox(10);
		hbBtn4.setAlignment(Pos.CENTER);
		hbBtn4.getChildren().add(alphabeticalListBtn);
		hbBtn4.getChildren().add(notPaidListBtn);
		
	
		grid.add(hbBtn4, 0, 3);
		
		reportTab.setText("Reports System");
		reportTab.setContent(grid);
		
	}
	
	
	public void changeType(TextField dentistTextField, PasswordField pwBox) {
		
		name = dentistTextField.getText();
		password1 = pwBox.getText();
	}
	
}
